package com.todolist.domain;

import javax.persistence.Entity;

import lombok.Data;

//@Entity
@Data
public class LoginForm {//クラス名をテンプレートで最初小文字で使える。たぶん。requestmappingの引数ならmode.addattributeで渡さなくていい？
	private String name;
	private String password;
	private String prePageUrl;
}
