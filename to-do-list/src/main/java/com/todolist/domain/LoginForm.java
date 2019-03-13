package com.todolist.domain;

import javax.persistence.Entity;

//@Entity
public class LoginForm {//クラス名をテンプレートで最初小文字で使える。たぶん。requestmappingの引数ならmode.addattributeで渡さなくていい？

	/* 名前 */
	private String name;
	/* パスワード */
	private String password;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
