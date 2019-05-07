package rsys.domain.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MailInputForm {

	@Email
	private String from;

	@Email
	private String to;

	@NotNull
	private String subject;

	@NotNull
	@Size(max = 1000)
	private String text;

	private String[] selectedItems;
}
