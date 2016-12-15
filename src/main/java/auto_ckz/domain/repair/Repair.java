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

    @Length(max = 200)
    private String observation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repairOrderId", nullable = true)
    private RepairOrder repairOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mechanicId", nullable = true)
    private Mechanic mechanic;

}
