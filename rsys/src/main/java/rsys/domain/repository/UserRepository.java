package rsys.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rsys.domain.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
}
