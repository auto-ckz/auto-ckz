package auto_ckz.domain.client;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class Client {
	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2)
	private String firstName;

	@Size(min = 2)
	private String lastName;

	@Size(min = 6)
	private String phoneNumber;

	private String address;

	// Used pattern from Oracle docs: https://docs.oracle.com/cd/E19798-01/821-1841/gkahq/index.html
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
			+"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
			message="{invalid.email}")
	private String email;
}
