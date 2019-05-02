package rsys.app.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rsys.domain.model.calendar.Calendar;

@Controller
@RequestMapping("admin/schedule")
public class ScheduleListController {

	LocalDate date;
	Calendar calendar;

	@GetMapping(value = {"", "list"})
	public String index(Model model) {
		date = LocalDate.now();
		calendar = new Calendar(date);
		model.addAttribute("date", date);
		model.addAttribute("calendar", calendar.getCalendar());
		return "admin/schedule/list";
	}

	@GetMapping(value = "{date}")
	public String selectedMonthCalendar(Model model,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date) {
		System.out.println(date);
		calendar = new Calendar(date);
		model.addAttribute("calendar", calendar.getCalendar());
		return "admin/schedule/list";
	}
}
