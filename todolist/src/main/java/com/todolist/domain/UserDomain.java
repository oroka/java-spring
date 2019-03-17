package com.todolist.domain;

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
@Table(name="users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name", nullable = false, length = 128)
	private String name;
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	// no table column
	private String confpassword;
	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;
	@Column(name = "admin_flag", nullable = false)
	private Boolean admin;
	
	public static UserDomain of(String name, String password, String email) {
		return UserDomain.builder().name(name).password(password).email(email).build();
	}
}
