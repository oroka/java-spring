package com.todolist.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="schedule")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false, length = 128)
	private String name;
	@Column(name = "email", nullable = false, unique = true, length = 255)//　認証情報emailとチェック
	private String email;
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	@Column(name = "description", nullable = false, length = 600)
	private String description;
	@Column(name = "start_time", nullable = false, length = 128)
	private LocalDateTime start_time;
	@Column(name = "end_time", nullable = false, length = 128)
	private LocalDateTime end_time;
	
	public static ScheduleDomain of(String name, String email, String title, String description, LocalDateTime start_time, LocalDateTime end_time) {
		return ScheduleDomain.builder().name(name).email(email).title(title).description(description).start_time(start_time).end_time(end_time).build();
	}
	
	public void setStart_time(String start_time) {
		this.start_time = convert_string_to_localdatetime(start_time);
	}
	
	public void setEnd_time(String end_time) {
		this.end_time = convert_string_to_localdatetime(end_time);
	}
	
	private LocalDateTime convert_string_to_localdatetime(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			return LocalDateTime.ofInstant(format.parse(time).toInstant(), ZoneId.systemDefault());
		}catch(Exception e) {
			return null;
		}
	}
	
	private Timestamp convert_string_to_timestamp(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			return new Timestamp(format.parse(time).getTime());
		}catch(Exception e) {
			return null;
		}
	}
}
