package rsys.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rsys.domain.model.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {//主キー

}
