package auto_ckz.domain.director;

import auto_ckz.domain.abstracts.AbstractPersonEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;

@Entity
@Data
public class Director extends AbstractPersonEntity {

    @Length(max = 11)
    private int pesel;

}
