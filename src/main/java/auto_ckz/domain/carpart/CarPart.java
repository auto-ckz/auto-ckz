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

@Entity
@Data
public class CarPart extends AbstractEntity {

    @Length(max = 50)
    private String name;

    @Range(max = 999999)
    private int cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repairId", nullable = false)
    private Repair repair;
}
