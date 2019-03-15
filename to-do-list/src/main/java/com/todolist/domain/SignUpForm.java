package com.todolist.domain;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm implements Serializable {
	
	@NotNull
    @Size(min = 2, max = 30)
    private String firstname;

	@NotNull
    @Size(min = 2, max = 30)
    private String lastname;

	@NotNull
    @Size(min = 2, max = 30)
    private String nickname;

    @NotNull
    @Pattern(regexp = "([0-9]{3})-([0-9]{4})-([0-9]{4})")
    private String phonenum;

    // Optional
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 99)
    private String password;
    
    @NotNull
    @Size(min = 6, max = 99)
    private String confpassword;
    
}
