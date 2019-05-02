package rsys.domain.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/*
 * 仕様：シフト（稼働時間）クラス
 * Integer id : データベース登録番号
 * String name：名称
 * LocalTime startTime : 開始時間
 * LocalTime endTime：終了時間
 */
@Data
@Entity
public class Shift {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min=2, max=10)
	@Column(name="name")
	private String name;

	@NotNull
	@Column(name="start_time")
	private LocalTime startTime;

	@NotNull
	@Column(name="end_time")
	private LocalTime endTime;
}
