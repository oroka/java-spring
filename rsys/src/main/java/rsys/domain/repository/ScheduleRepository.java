package rsys.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rsys.domain.model.Schedule;
import rsys.domain.model.ScheduleId;

public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
	List<Schedule> findByScheduleId_workDateOrderByScheduleId_userIdAsc(LocalDate workDate);

	List<Schedule> findByScheduleId_userIdOrderByScheduleId_workDateAsc(Integer userId);
}
