package com.todolist.domain;

import javax.persistence.Entity;

@Entity
public class SearchForm {
	
	/* 名前検索 */
	private String name;
	/* 検索条件(AND,OR) */
	private String condition;
	/* 日時検索 */
	private String datetime;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getDateTime() {
		return datetime;
	}
	
	public void setDateTime(String datetime) {
		this.datetime = datetime;
	}
	
}
