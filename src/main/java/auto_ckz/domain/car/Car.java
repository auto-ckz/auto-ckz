package auto_ckz.domain.car;

import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Data
public class Car extends AbstractEntity {

    @Length(max = 50)
    private String make;

    @Length(max = 50)
    private String model;

    @Length(max = 4)
    private int year;

    @Length(max = 50)
    private String registrationNumber;

    @Length(max = 17)
    private String vin;

    private Date dateOfFirstRegistration;

    @Length(max = 50)
    private String oc;

    private boolean vehicleCheckup;

    @Length(max = 7)
    private int vehicleMileage;

    @Length(max = 10)
    private String engineCapacity;

    @Length(max = 10)
    private String enginePower;

    @Length(max = 20)
    private String fuelType;

}
