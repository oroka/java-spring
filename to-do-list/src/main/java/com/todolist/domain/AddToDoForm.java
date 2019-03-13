package com.todolist.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
//@Entity
public class AddToDoForm {
	
	/* 開始時刻 */
	private String start_time;
	/* 終了時刻 */
	private String end_time;
	/* スケジュール */
	private String to_do;
}
