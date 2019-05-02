package rsys.domain.model.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

	private List<Week> calendar = new ArrayList<>();

	public Calendar() {}

	public Calendar(int year, int month) {
		setCalendar(year, month);
	}

	public Calendar(LocalDate localdate) {
		setCalendar(localdate.getYear(), localdate.getMonthValue());
	}

	public void setCalendar(LocalDate localdate) {
		setCalendar(localdate.getYear(), localdate.getMonthValue());
	}

	public void setCalendar(int year, int month) {

		/*
		 * その月の初日の曜日
		 */
		LocalDate date = LocalDate.of(year, month, 1);
		int startDayOfWeek = date.getDayOfWeek().getValue();

		/*
		 * その月の末日の日付
		 */
		date = date.plusMonths(1).minusDays(1);
		int lastDay = date.getDayOfMonth();

		/*
		 * その前の月の末日の日付
		 */
		int preMonthLastDay = date.minusMonths(1).getDayOfMonth();

		int count = 0;
		boolean dispPreMonthDay = false;//その前の月の日付を表示するか
		Week week = new Week();
		/*
		 * 最初の曜日が日曜日以外ならその前の月の日付をweek配列に格納する boolean dispPreMonthDay == true;
		 * または、空白にする dispPreMonthDay == false
		 */
		if(startDayOfWeek!=7) {
			for(int i=startDayOfWeek-1; i>=0; i--) {
				week.setWeekDay(i, dispPreMonthDay ? String.valueOf(preMonthLastDay--) : " ");
				count++;
			}
		}

		/*
		 * その月の日付を格納する。week配列がいっぱいになればList(calendar)に格納する。
		 */
		for(int i=1; i<=lastDay; i++) {
			if(count==7) {
				count=0;
				calendar.add(week);
				week = new Week();
				week.setWeekDay(count++, String.valueOf(i));
			}else {
				week.setWeekDay(count++, String.valueOf(i));
			}
		}

		/*
		 * 今月のカレンダーに来月の日付を追加する
		 */
		boolean dispNextMonthDay = false;
		if(count!=0) {
			for(int i=1; count<7; i++) {
				week.setWeekDay(count++, dispNextMonthDay ? String.valueOf(i) : " ");
			}
		}

		/*
		 * List(calendar)に最終週を追加する
		 */
		calendar.add(week);
	}

	public List<Week> getCalendar(){
		return calendar;
	}
}
