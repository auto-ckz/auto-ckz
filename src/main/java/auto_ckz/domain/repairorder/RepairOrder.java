package auto_ckz.domain.repairorder;

import auto_ckz.domain.abstracts.AbstractEntity;
import auto_ckz.domain.car.Car;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerService;
import auto_ckz.domain.client.Client;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Data
public class RepairOrder extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "memberOfCustomerServiceId", nullable = false)
    private MemberOfCustomerService memberOfCustomerService;

    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    private Date date;

    @Length(max = 6)
    private int totalCost;

}
