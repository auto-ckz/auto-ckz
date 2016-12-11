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
    @Length(min = 2, max = 30)
    private String firstName;
    @NotEmpty
    @Length(min = 2, max = 30)
    private String lastName;
    @NotEmpty
    @Length(max = 12)
    private String phoneNumber;
    @Length(min = 11, max = 11)
    @Column(unique = true)
    private String pesel;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    public AbstractPersonEntity() {
    }

    public AbstractPersonEntity(String firstName, String lastName, String phoneNumber, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
    }
}
