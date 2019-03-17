package com.todolist.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.domain.ScheduleDomain;

public interface ScheduleMapperJPA extends JpaRepository<ScheduleDomain, Long>{

}
