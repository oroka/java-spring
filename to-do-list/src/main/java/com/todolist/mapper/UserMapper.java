package com.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.todolist.domain.User;

@Mapper
@Repository
public interface UserMapper {
	
	@Select("select * from users")
    List<User> findAll();

    @Select("select * from users where id = #{id}")
    User findOne(Long id);

    @Insert("insert into users (name, password, role) values (#{name}, #{password}, #{role})")
    @Options(useGeneratedKeys = true)
    void save(User user);

    @Update("update users set name = #{name}, password = #{password}, role = #{role} where id = #{id}")
    void update(User user);

    @Delete("delete from users where id = #{id}")
    void delete(Long id);
    
}
