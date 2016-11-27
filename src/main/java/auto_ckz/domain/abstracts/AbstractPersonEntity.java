package auto_ckz.domain.abstracts;

import auto_ckz.domain.address.Address;
import auto_ckz.site.account.Account;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractPersonEntity extends AbstractEntity {

    @NotEmpty
    @Length(max = 30)
    private String firstName;
    @NotEmpty
    @Length(max = 30)
    private String lastName;
    @NotEmpty
    @Length(max = 12)
    private String phoneNumber;
    @NotEmpty
    @Length(min = 11, max = 11)
    @Column(unique = true)
    private String pesel;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

}
