package rsys.domain.model.converter;

import rsys.domain.model.User;
import rsys.domain.model.form.UserInputForm;

public class ClassConverter{

	public static User convertToUser(UserInputForm userInput) {
		User user = new User();
		user.setFirstName(userInput.getFirstName());
		user.setLastName(userInput.getLastName());
		user.setEmail(userInput.getEmail());
		user.setSectionName(userInput.getSectionName());
		user.setPassword(userInput.getPassword());
		user.setSalary(userInput.getSalary());
		user.setRoleName(userInput.getRoleName());

		return user;
	}

	public static UserInputForm convertToUserInput(User user) {
		UserInputForm userInput = new UserInputForm();
		userInput.setFirstName(user.getFirstName());
		userInput.setLastName(user.getLastName());
		userInput.setEmail(user.getEmail());
		userInput.setPassword(user.getPassword());
		userInput.setSectionName(user.getSectionName());
		userInput.setSalary(user.getSalary());
		userInput.setRoleName(user.getRoleName());

		return userInput;
	}

}
