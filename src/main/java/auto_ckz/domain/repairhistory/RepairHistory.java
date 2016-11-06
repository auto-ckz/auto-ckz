package auto_ckz.domain.repairhistory;

import auto_ckz.domain.abstracts.AbstractEntity;
import auto_ckz.domain.car.Car;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Data
public class RepairHistory extends AbstractEntity{

    @Length(max = 200)
    private String description;

    private Date date;

    @Length(max = 10)
    private int cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

}
