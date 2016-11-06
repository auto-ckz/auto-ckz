package auto_ckz.domain.abstracts;

import auto_ckz.domain.Address;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
@Data
public abstract class AbstractPersonEntity extends AbstractEntity {

    @Length(max = 50)
    private String firstName;
    @Length(max = 50)
    private String lastName;
    @Length(max = 12)
    private int phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;
}
