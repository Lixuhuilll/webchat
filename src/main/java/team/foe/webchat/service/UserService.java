package team.foe.webchat.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import team.foe.webchat.entity.User;
import team.foe.webchat.mapper.UserMapper;

@Component
public class UserService {
    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void init() {
        // 如果数据库不存在表则创建
        userMapper.creatUsersTable();
    }

    public User login(String name, String password) {
        User user = userMapper.findByName(name);
        if (user == null) return null;
            // 校验BCrypt，因为该算法会产生随机盐，无法直接对比字符串是否相等，必须通过算法解出是否一致
        else if (!BCrypt.checkpw(password, user.getPassword())) return null;
        return user;
    }

    public User signup(String name, String password) {
        // 检查密码长度和复杂度（至少6位数且必须同时存在字母和数字）
        if (password.length() < 6
                || password.length() >= 100
                || !password.matches(".*[a-zA-Z]+.*")
                || !password.matches(".*[0-9]+.*")) {
            return null;
        }
        // 检查用户名是否已存在
        if (userMapper.findByName(name) != null) {
            return null;
        }
        // 加密密码
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User();
        user.setName(name);
        user.setPassword(hashed);
        // 插入成功后会自动获取数据库自增id并通过setter方法赋值给user对象
        userMapper.addUser(user);
        return user;
    }

    public boolean delete(String name, String password) {
        User user = login(name, password);
        if (user == null) return false;
        // 如果成功删除，即受影响的行数不为0，返回true
        return userMapper.delById(user.getId()) != 0;
    }
}
