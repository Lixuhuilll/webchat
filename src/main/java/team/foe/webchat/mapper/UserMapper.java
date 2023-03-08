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
}
