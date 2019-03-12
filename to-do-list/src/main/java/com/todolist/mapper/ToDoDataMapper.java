package com.todolist.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.todolist.domain.ToDoData;

@Mapper
@Repository
public interface ToDoDataMapper {
	
	@Select("select * from to_do_list order by start_time")
	public List<ToDoData> findAll();
	
	@Select("select * from to_do_list where id = #{id}")
	public ToDoData findOne(@Param("id") Long id);
	
	@Select("select * from to_do_list where username = #{username} order by start_time")
	public List<ToDoData> findByUserName(@Param("username") String username);
	
	@Select("select * from to_do_list where #{select_time} > start_time and #{select_time} < end_time order by start_time")
	public List<ToDoData> findBySelectTime(@Param("select_time") Timestamp select_time);
	
	@Insert("insert into to_do_list(username, start_time, end_time, to_do) values(#{username}, #{start_time}, #{end_time}, #{to_do}")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = long.class)
	public void insert(@Param("username")String username, @Param("start_time")Timestamp start_time, @Param("end_time")Timestamp end_time, @Param("to_do")String to_do);
	
	@Update("update to_do_list set username = #{username}, start_time = #{start_time}, end_time = #{end_time}, to_do = #{to_do}")
	public void update(ToDoData to_do_data);
	
	@Delete("delete from to_do_list where id = #{id}")
	public void delete(@Param("id")Long id);
	
	@Delete("delete from to_do_list")
	public void deleteAll();
	
}
