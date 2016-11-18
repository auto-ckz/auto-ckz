package auto_ckz.domain.repair;

import auto_ckz.domain.repairorder.RepairOrder;
import auto_ckz.domain.abstracts.AbstractEntity;
import auto_ckz.common.enums.RepairStatus;
import auto_ckz.domain.mechanic.Mechanic;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
public class Repair extends AbstractEntity {

    @Length(max = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repairOrderId", nullable = false)
    private RepairOrder repairOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanicId", nullable = false)
    private Mechanic mechanic;
}
