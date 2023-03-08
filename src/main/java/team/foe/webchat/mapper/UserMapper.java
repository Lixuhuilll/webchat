package team.foe.webchat.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;
import team.foe.webchat.entity.User;

@Mapper
@Transactional
public interface UserMapper {
    @Insert("INSERT INTO users(name, password) VALUES (#{user.name}, #{user.password})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    Integer addUser(@Param("user") User user);

    @Select("SELECT id, name, password FROM users WHERE id = #{id};")
    User findById(@Param("id") Long id);

    @Select("SELECT id, name, password FROM users WHERE name = #{name};")
    User findByName(@Param("name") String name);

    @Delete("DELETE FROM users WHERE id = #{id};")
    Integer delById(@Param("id") Long id);

    /**
     * 建表语句，表不存在才有效
     */
    @Update("CREATE TABLE IF NOT EXISTS `users` (\n" +
            "  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id，主键',\n" +
            "  `name` varchar(100) NOT NULL COMMENT '用户名',\n" +
            "  `password` varchar(100) NOT NULL COMMENT '用户密码，BCrypt混淆',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `users_name_IDX` (`name`) USING BTREE\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='用户基础表';")
    void creatUsersTable();
}
