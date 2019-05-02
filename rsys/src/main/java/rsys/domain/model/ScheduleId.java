package rsys.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleId implements Serializable {

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "work_date", columnDefinition = "DATE")
	private LocalDate workDate;
}
