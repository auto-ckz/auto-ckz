package auto_ckz.site.signup;

import auto_ckz.common.constant.Role;
import auto_ckz.common.messages.MessagesValidate;
import auto_ckz.common.validators.UniqueEmail;
import auto_ckz.site.account.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class SignupForm {


	@NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
	@Email(message = MessagesValidate.EMAIL_MESSAGE)
	@UniqueEmail(message = MessagesValidate.EMAIL_EXIST)
	private String email;

	@NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
	@Length(min = 5, message = MessagesValidate.MIN_PASSWORD_LENGTH_MESSAGE)
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
