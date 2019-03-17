package com.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.todolist.domain.UserDomain;

@Mapper
public interface UserMapper {
	@Select("select * from users order by id")
	public List<UserDomain> selectAll();
	
	@Select("select * from users where id = #{id}")
	public UserDomain selectOneById(Long id);
	
	@Select("select * from users where email = #{email}")
	public UserDomain selectOneByEmail(String email);
	
	@Select("select * from users where name = #{name}")
	public UserDomain selectOneByName(String name);
	
	//　名前に要素を含む複数人を返すやつを追加
	
	@Insert("insert into users(name, password, email, admin_flag) values (#{name}, #{password}, #{email}, #{admin_flag}")
	public void insert(String name, String password, String email, Boolean admin_flag);
	
	@Delete("delete from users where id = #{id}")
	public void deleteOneById(Long id);
	
	@Delete("delete from users")
	public void deleteAll();
}
