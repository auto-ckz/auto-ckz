package auto_ckz.domain.abstracts;

import auto_ckz.common.messages.MessagesValidate;
import auto_ckz.domain.address.Address;
import auto_ckz.site.account.Account;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractPersonEntity extends AbstractEntity {

    @NotEmpty
    @Length(min = 2, max = 30)
    @NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
    private String firstName;
    @NotEmpty
    @Length(min = 2, max = 30)
    @NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
    private String lastName;
    @NotEmpty
    @Length(max = 12)
    private String phoneNumber;
    @Length(min = 11, max = 11)
    @Column(unique = true)
    private String pesel;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

}
