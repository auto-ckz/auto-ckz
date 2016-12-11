package auto_ckz.domain.memberofcustomerservice;

import auto_ckz.domain.abstracts.AbstractPersonEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class MemberOfCustomerService extends AbstractPersonEntity {
    public MemberOfCustomerService() {
    }

    public MemberOfCustomerService(String firstName, String lastName, String phoneNumber, String pesel) {
        super(firstName, lastName, phoneNumber, pesel);
    }
}
