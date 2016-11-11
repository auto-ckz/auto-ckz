package auto_ckz.domain.carpart;

import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
public class CarPart extends AbstractEntity {

    @Length(max = 50)
    private String name;

    @Range(min =0, max = 999999)
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repairId", nullable = false)
    private Repair repair;
}
