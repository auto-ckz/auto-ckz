package auto_ckz.domain.mechanic;

import auto_ckz.domain.abstracts.AbstractPersonEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Mechanic extends AbstractPersonEntity {

    public Mechanic() {
    }

    public Mechanic(String firstName, String lastName, String phoneNumber, String pesel) {
        super(firstName, lastName, phoneNumber, pesel);
    }
}
