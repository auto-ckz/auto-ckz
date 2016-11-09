package auto_ckz.domain.abstracts;

import auto_ckz.domain.address.Address;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractPersonEntity extends AbstractEntity {

    @Length(max = 50)
    private String firstName;
    @Length(max = 50)
    private String lastName;
    @Length(max = 12)
    private String phoneNumber;
    @Length(min = 11, max = 11)
    @Column(unique = true)
    private Long pesel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;
}
