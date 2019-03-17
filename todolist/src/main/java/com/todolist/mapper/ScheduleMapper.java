package com.todolist.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.todolist.domain.ScheduleDomain;

@Mapper
public interface ScheduleMapper {
	@Select("select * from schedulelist order by start_time")
	public List<ScheduleDomain> selectAll();
	
	@Select("select * from schedulelist where id = #{id}")
	public ScheduleDomain selectOneById(Long id);
	
	@Select("select * from schedulelist where email = #{email} order by start_time")
	public List<ScheduleDomain> selectSomeByEmail(String email);
	
	@Select("select * from schedulelist where name = #{name} order by start_time")
	public List<ScheduleDomain> selectSomeByName(String name);
	
	//　検索ワードが含まれるものを返す name 
	@Select("select * from schedulelist where name like '%' || #{name} || '%' order by start_time")
	public List<ScheduleDomain> selectSomeHaveName(String name);
	
	@Select("select * from schedulelist where text like '%' || #{keyword} || '%' order by start_time")
	public List<ScheduleDomain> selectSomeHaveKeyword(String keyword);
	
	//　指定した時間が含まれる予定を返す database time timestamp localdate ????
	@Select("select * from schedulelist where time > #{start_time} and time < #{end_time} order by start_time")
	public List<ScheduleDomain> selectSomeByTime(Timestamp time);
	
	@Insert("insert into schedulelist(name, email, text, start_time, end_time) values(#{name}, #{email}, #{text}, #{start_time}, #{end_time}")
	public void insert(String name, String email, String text, Timestamp start_time, Timestamp end_time);
	
	//　すぐに覚えられるけどすぐに忘れる。。繰り返しが大事。
	
	@Delete("delete from schedulelist where id = #{id}")
	public void deleteOneById(Long id);
	
	//　特定ユーザーの一括削除
	@Delete("delete from schedulelist where email = #{email}")
	public void deleteSomeByEmail(String email);
	
	@Delete("delete from schedulelist")
	public void deleteAll();
	
}
