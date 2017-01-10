package auto_ckz.domain.address;

import auto_ckz.common.messages.MessagesValidate;
import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;

@Entity
@Data
public class Address extends AbstractEntity {

    @Length(max = 100)
    @NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
    private String street;

    @Length(max = 100)
    @NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
    private String city;

    @Length(max = 10)
    @NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
    private String houseNumber;

    @Length(max = 6, min = 6)
    @NotBlank(message = MessagesValidate.NOT_BLANK_MESSAGE)
    private String zipCode;

}