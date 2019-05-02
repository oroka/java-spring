package rsys.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import rsys.domain.model.Shift;
import rsys.domain.repository.ShiftRepository;

@Service
public class ShiftService {

	@Autowired
	ShiftRepository shiftRepository;

	public List<Shift> findAll(){
		return shiftRepository.findAll();
	}

	public Shift findOne(Shift shift) {
		Example<Shift> example = Example.of(shift);
		Optional<Shift> tmp = shiftRepository.findOne(example);
		if(tmp.isPresent()) return tmp.get();
		return null;
	}

	public Shift findOneById(Integer id) {
		Optional<Shift> shift = shiftRepository.findById(id);
		if(shift.isPresent()) return shift.get();
		return null;
	}

	public Shift findOneByName(String name) {
		Shift shift = new Shift();
		shift.setName(name);
		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withMatcher("name", match->match.ignoreCase().contains());
		Example<Shift> example = Example.of(shift, matcher);
		Optional<Shift> oShift = shiftRepository.findOne(example);
		if(oShift.isPresent()) return oShift.get();
		return null;
	}

	public void insert(Shift shift) {
		shiftRepository.save(shift);
	}

	public void insert(List<Shift> list) {
		shiftRepository.saveAll(list);
	}

	public void update(Shift shift) {
		shiftRepository.save(shift);
	}


	public void delete(Shift shift) {
		shiftRepository.delete(shift);
	}

	public void delete(Integer id) {
		shiftRepository.deleteById(id);
	}
}
