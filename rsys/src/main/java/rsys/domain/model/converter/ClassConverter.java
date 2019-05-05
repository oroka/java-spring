package rsys.domain.model.converter;

import rsys.domain.model.RoleName;
import rsys.domain.model.SectionName;
import rsys.domain.model.User;
import rsys.domain.model.csv.UserCSV;
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

	public static User convertUserCsvToUser(UserCSV userCsv) {
		User user = new User();
		user.setFirstName(userCsv.getName().split(" ")[0]);
		user.setLastName(userCsv.getName().split(" ")[1]);
		user.setEmail(userCsv.getAddress());
		user.setSectionName(SectionName.SEC_A_1);
		user.setPassword(userCsv.getPortablePhoneNumber().toString());
		user.setSalary((long)300000);
		user.setRoleName(RoleName.USER);

		return user;
	}

}
