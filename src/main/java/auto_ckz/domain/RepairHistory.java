package auto_ckz.domain;

import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
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
