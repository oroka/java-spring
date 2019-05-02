package rsys.app.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rsys.domain.model.RoleName;
import rsys.domain.model.SectionName;
import rsys.domain.model.User;
import rsys.domain.model.converter.ClassConverter;
import rsys.domain.model.form.UserInputForm;
import rsys.domain.service.UserService;

/*
 * 仕様：ユーザー情報の管理
 *
 */

@Controller
@RequestMapping("admin/user")
public class UserController {
	@Autowired
	UserService userService;

	private final String tplRoot = "admin/user/";

	@GetMapping("list")
	public String viewAll(Model model) {
		List<User> list = userService.findAll();
		Collections.sort(list, (User s, User t)->s.getId() - t.getId());
		model.addAttribute("users", list);
		return tplRoot + "list";
	}

	/*
	 * 仕様：ユーザー情報をデータベースに登録する
	 * 引数：UserInputForm
	 */
	@PostMapping("add")
	public String addNew(Model model, @Valid @ModelAttribute UserInputForm userInputForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "admin/confirm";
		}

		User user = ClassConverter.convertToUser(userInputForm);
		userService.insertOne(user);

		model.addAttribute("users", userService.findAll());

		return tplRoot + "list";
	}

	/*
	 * 仕様：データベースのユーザー情報を更新する
	 */
	@PostMapping("update")
	public String update(Model model, User user) {
		//System.out.println("userId : " + user.getId());
		userService.update(user);
		model.addAttribute("users", userService.findAll());
		return tplRoot + "list";
	}

	/*
	 * 仕様：データベースのＩＤの選択されたユーザー情報を編集する
	 * 引数：Integer id : 選択されたID
	 */
	@PostMapping("edit")
	public String edit(Model model, Integer id) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		model.addAttribute("RoleName", RoleName.values());
		model.addAttribute("SectionName", SectionName.values());
		return tplRoot + "edit";
	}

	@PostMapping("deleteConfirm")
	public String deleteConfirm(Model model, Integer id) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);

		return tplRoot + "delete";
	}

	@PostMapping("delete")
	public String delete(Model model, Integer id) {
		userService.deleteById(id);

		model.addAttribute("users", userService.findAll());
		return tplRoot + "list";
	}
}
