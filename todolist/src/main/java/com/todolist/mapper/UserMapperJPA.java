package com.todolist.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.domain.UserDomain;

public interface UserMapperJPA extends JpaRepository<UserDomain, Long>{

}
