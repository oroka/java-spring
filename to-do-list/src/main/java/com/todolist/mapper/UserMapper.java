package com.todolist.mapper;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.domain.User;

@Transactional
@Repository
public interface UserMapper extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
    Optional<User> findFirstByName(String name);
    
    @Modifying
    @Query(value = "update User u set u.name = :name where u.id = :id")
    int updateNameById(Long id, String name);
    //@Query("update users u set u.id = :id, u.name = :name, u.password = :password, u.email = :email, u.admin_flag = :admin_flag where u.id= :id")
    //Optional<User> update(@Param("id")Long id, @Param("name")String name, @Param("password")String password, @Param("email")String email, @Param("admin_flag")Boolean admin_flag);
}
