package auto_ckz.site.signup;

import auto_ckz.common.constant.Role;
import auto_ckz.common.validators.UniqueEmail;
import org.hibernate.validator.constraints.*;

import auto_ckz.site.account.Account;

import javax.validation.constraints.Min;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";
	private static final String EMAIL_EXIST = "{emailExist.message}";
	private static final String MIN_PASSWORD_LENGTH_MESSAGE = "{password.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	@UniqueEmail(message = EMAIL_EXIST)
	private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Min(value = 5, message = SignupForm.MIN_PASSWORD_LENGTH_MESSAGE)
	private String password;

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

	public Account createAccount() {
        return new Account(getEmail(), getPassword(), Role.ROLE_CLIENT);
	}
}
