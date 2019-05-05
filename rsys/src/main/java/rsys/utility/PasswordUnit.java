package rsys.utility;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { PasswordUnitValidator.class })
@Retention(CLASS)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE })
public @interface PasswordUnit {

	String message() default "{rsys.utility.PasswordUnit.message}";//メッセージキー

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Documented
	@Retention(CLASS)
	@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE })
	public @interface List {
		PasswordUnit[] value();
	}

}
