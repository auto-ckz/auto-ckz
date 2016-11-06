package auto_ckz.domain;

import auto_ckz.domain.abstracts.AbstractPersonEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Client extends AbstractPersonEntity {

    @Length(max = 100)
    @Column(unique = true)
    private String email;

}
