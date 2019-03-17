package com.todolist.security;

import com.todolist.mapper.UserMapper;
import com.todolist.mapper.UserMapperJPA;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("LoginUserDetailsService")
public class LoginUserDetailsService implements UserDetailsService {

    private final UserMapperJPA userRepository;

    public LoginUserDetailsService(UserMapperJPA userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {// フォームで入力した値を使う。
        // emailでデータベースからユーザーエンティティを検索し、返ってきたUserクラスでUserDetailクラスを作成する。
    	System.out.println("UserDetailsService - loadUserByUsername : ");
        return userRepository.findOneByEmail(email)
                .map(LoginUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
