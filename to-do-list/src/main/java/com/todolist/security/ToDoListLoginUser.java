package com.todolist.security;

import com.todolist.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

public class ToDoListLoginUser extends org.springframework.security.core.userdetails.User {

    // Userエンティティ
    private com.todolist.domain.User user;

    public User getUser() {
        return user;
    }

    /*
     * ToDoListAuthenticationEntryPoint と　onAuthenticationSuccess　の間
     * onAuthenticationSuccess　の前 
     * @param user
     */
    public ToDoListLoginUser(User user) {
    	super(user.getEmail(), user.getPassword(), determineRoles(user.getAdmin()));//emailじゃないの？？認証情報を他で使う場面Principalなど
    	System.out.println("ToDoListLoginUser - Constructor : ");
    	this.user = user;
    }

    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static List<GrantedAuthority> determineRoles(boolean isAdmin) {
        return isAdmin ? ADMIN_ROLES : USER_ROLES;
    }
}
