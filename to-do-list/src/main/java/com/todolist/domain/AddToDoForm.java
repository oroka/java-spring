package com.todolist.domain;

import javax.persistence.Entity;

@Entity
public class AddToDoForm {
	
	/* 開始時刻 */
	private String start_time;
	/* 終了時刻 */
	private String end_time;
	/* スケジュール */
	private String to_do;
	
	public String getStartTime() {
		return start_time;
	}
	
	public void setStartTime(String start_time) {
		this.start_time = start_time;
	}
	
	public String getEndTime() {
		return end_time;
	}
	
	public void setEndTime(String end_time) {
		this.end_time = end_time;
	}
	
	public String getToDo() {
		return to_do;
	}
	
	public void setToDo(String to_do) {
		this.to_do = to_do;
	}
}
