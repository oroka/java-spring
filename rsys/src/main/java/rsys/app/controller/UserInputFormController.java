package rsys.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rsys.domain.model.RoleName;
import rsys.domain.model.SectionName;
import rsys.domain.model.form.UserInputForm;

@Controller
@RequestMapping(value = "admin/user")
public class UserInputFormController {

	private final String tplRoot = "admin/user/";

	/*
	 * ユーザーフォームのデータ保持のため
	 */
	private UserInputForm uif;

	@RequestMapping(value="input", method=RequestMethod.GET)
	public String input(Model model, UserInputForm userInputForm) {
		//System.out.println(userInputForm);
		model.addAttribute("RoleName", RoleName.values());
		model.addAttribute("SectionName", SectionName.values());
		return tplRoot + "input";
	}

	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modify(Model model) {
		model.addAttribute("userInputForm", uif);
		model.addAttribute("RoleName", RoleName.values());
		model.addAttribute("SectionName", SectionName.values());
		return tplRoot + "input";
	}

	@RequestMapping(value="confirm", method=RequestMethod.POST)
	public String confirm(Model model, UserInputForm userInputForm) {
		//System.out.println(userInputForm);
		uif = userInputForm;
		return tplRoot + "confirm";
	}
}
