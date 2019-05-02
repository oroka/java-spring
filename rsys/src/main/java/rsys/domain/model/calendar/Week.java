package rsys.domain.model.calendar;

/*
 * ある週の日付を保持するクラス
 */
public class Week {

	/*
	 * 一週間の日付を格納する配列
	 */
	private String[] weekDay = new String[7];

	/*
	 * weekDay配列から日付を取り出す
	 */
	public String getWeekDay(int index) {
		return weekDay[index];
	}

	public String[] getWeekDay() {
		return weekDay;
	}

	/*
	 * weekDay配列に日付を格納する
	 */
	public void setWeekDay(int index, String day) {
		weekDay[index] = day;
	}
}
