package com.todolist.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todolist.domain.Memo;

public interface MemoMapper extends JpaRepository<Memo, Long> {
}
