package rsys.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordUnitValidator implements ConstraintValidator<PasswordUnit, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO パスワードの文字列がこの形式に合っているか判定
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(value);
		if(matcher.find()) {
			pattern = Pattern.compile("[a-zA-Z]");
			matcher = pattern.matcher(value);
			return matcher.find();
		}
		return false;
	}

}
