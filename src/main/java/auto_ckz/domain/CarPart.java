package auto_ckz.domain;

import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class CarPart extends AbstractEntity {

    @Length(max = 50)
    private String name;

    @Length(max = 6)
    private int cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repairId", nullable = false)
    private Repair repair;
}
