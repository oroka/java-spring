package rsys.domain.model.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import rsys.domain.model.RoleName;
import rsys.domain.model.SectionName;

/*
 * 仕様：ユーザー情報の入力フォーム用クラス
 */
public class UserInputForm {

	@NotNull
	@Size(min = 2, max = 30)
	private String firstName;

	@NotNull
	@Size(min = 2, max = 30)
	private String lastName;

	@NotNull
	@Email
	@Size(min = 5, max = 50)
	private String email;

	@NotNull
	@Size(min = 2, max = 30)
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	private SectionName sectionName;

	@NotNull
	@Min(value=150000)
	private Long salary;

	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public SectionName getSectionName() {
		return sectionName;
	}

	public void setSectionName(SectionName sectionName) {
		this.sectionName = sectionName;
	}

}
