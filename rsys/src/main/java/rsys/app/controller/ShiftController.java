package rsys.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rsys.domain.model.Shift;
import rsys.domain.service.ShiftService;

@Controller
@RequestMapping("admin/shift")
public class ShiftController {

	@Autowired
	private ShiftService shiftService;

	private final String tplRoot = "admin/shift/";

	@GetMapping(value = {"input", ""})
	public String input(Model model, Shift shift) {
		model.addAttribute("entities", shiftService.findAll());
		return tplRoot + "manage";
	}

	@PostMapping("input")
	public String insert(Model model, Shift shift) {
		if(shift!=null) {
			if(shift.getId()!=null) {
				shiftService.update(shift);
			}else if(shiftService.findOneByName(shift.getName()) == null) {
				shiftService.insert(shift);
			}
		}
		model.addAttribute("shift", new Shift());
		model.addAttribute("entities", shiftService.findAll());
		return tplRoot + "manage";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(Model model, Integer id) {
		model.addAttribute("shift", shiftService.findOneById(id));
		model.addAttribute("entities", shiftService.findAll());
		return tplRoot + "manage";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(Model model, Integer id) {
		shiftService.delete(id);
		model.addAttribute("shift", new Shift());
		model.addAttribute("entities", shiftService.findAll());
		return tplRoot + "manage";
	}
}
