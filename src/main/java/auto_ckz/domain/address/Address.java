package auto_ckz.domain.address;

import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;

@Entity
@Data
public class Address extends AbstractEntity {

    @Length(max = 100)
    private String street;

    @Length(max = 100)
    private String city;

    @Length(max = 10)
    private String houseNumber;

    @Length(max = 6, min = 6)
    private String zipCode;

}