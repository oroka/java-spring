package com.todolist.security;

import com.todolist.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Userエンティティ
 */
@Service("toDoListUserDetailsService")
public class ToDoListUserDetailsService implements UserDetailsService {

    private final UserMapper userRepository;

    public ToDoListUserDetailsService(UserMapper userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        // emailでデータベースからユーザーエンティティを検索する
        return userRepository.findByEmail(email)
                .map(ToDoListLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
