package rsys.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;

@Data
@Entity
public class Schedule {

	@EmbeddedId
	private ScheduleId scheduleId;

	@ManyToOne
	@JoinColumn(name = "shift_name", insertable = false, updatable = false)
	@MapsId("name")
	private Shift shift;

	public Schedule(ScheduleId scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Schedule() {

	}
}
