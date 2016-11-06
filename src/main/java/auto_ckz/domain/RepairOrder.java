package auto_ckz.domain;

import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    private Date date;

    @Length(max = 6)
    private int totalCost;

}
