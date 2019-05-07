package rsys.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import rsys.domain.model.form.MailInputForm;
import rsys.domain.service.UserService;

@Controller
public class MailController {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private UserService userService;

	/*
	 * 例えばある条件で検索したユーザーを対象に一括してメールを送信数など。
	 * 複数の条件でメールを送信するコントローラを作成する。
	 *
	 */

	/*
	 * メールフォーム表示
	 */
	@GetMapping("/admin/mail")
	public String mailForm(Model model, MailInputForm mailForm) {

		model.addAttribute("list", userService.findAll());
		return "admin/mail";
	}

	/*
	 * 管理者からユーザーにメールを送信する
	 * 引数：Model model
	 * 		 MailInputForm 送信元・送信先アドレス・タイトル・内容
	 */
	@PostMapping("/admin/mail/send")
	public String sendMail(Model model,@Valid MailInputForm mailForm, BindingResult bindingResult) {

		if(bindingResult.hasErrors() ||
				(mailForm.getTo().isEmpty() && mailForm.getSelectedItems().length==0) ||
				mailForm.getText().isEmpty()) {
			return "redirect:/admin"; //エラーページに飛ばす
		}

		List<String> emails = new ArrayList<String>();
		for(String email : mailForm.getSelectedItems()) {
			emails.add(email);
		}
		if(mailForm.getTo().isEmpty()) emails.add(mailForm.getTo());

		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("admin@test.jp");
		simpleMessage.setTo(emails.toArray(new String[emails.size()]));
		simpleMessage.setSubject(mailForm.getSubject().isEmpty()?"non title":mailForm.getSubject());
		simpleMessage.setText(mailForm.getText());

		try {
			mailSender.send(simpleMessage);
		}catch(MailException e) {
			e.printStackTrace();
		}

		model.addAttribute("list", userService.findAll());

		return "admin/mail";
	}
}
