package auto_ckz.domain.repairorder;

import auto_ckz.domain.abstracts.AbstractEntity;
import auto_ckz.domain.car.Car;
import auto_ckz.domain.client.Client;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerService;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
public class RepairOrder extends AbstractEntity {


    private Date date;

    @Range(min = 0, max = 999999)
    private BigDecimal totalCost;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "memberOfCustomerServiceId", nullable = false)
    private MemberOfCustomerService memberOfCustomerService;

    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    public RepairOrder() {
    }

    public RepairOrder(Date date, BigDecimal totalCost, Client client, MemberOfCustomerService memberOfCustomerService, Car car) {
        this.date = date;
        this.totalCost = totalCost;
        this.client = client;
        this.memberOfCustomerService = memberOfCustomerService;
        this.car = car;
    }
}
