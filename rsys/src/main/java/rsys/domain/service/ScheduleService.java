package rsys.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rsys.domain.model.Schedule;
import rsys.domain.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleService {
	@Autowired
	ScheduleRepository scheduleRepository;

	public List<Schedule> findSchedulesByDate(LocalDate date){
		return scheduleRepository.findByScheduleId_workDateOrderByScheduleId_userIdAsc(date);
	}

	public List<Schedule> findAll(){
		return scheduleRepository.findAll();
	}

	public Page<Schedule> findAll(Pageable pageable){
		return scheduleRepository.findAll(pageable);
	}

	public List<Schedule> findSchedulesByUserId(Integer userId){
		return scheduleRepository.findByScheduleId_userIdOrderByScheduleId_workDateAsc(userId);
	}

	public Page<Schedule> findAll(Example<Schedule> example, Pageable pageable){
		return scheduleRepository.findAll(example, pageable);
	}
}
