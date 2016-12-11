package auto_ckz.domain.car;

import auto_ckz.domain.abstracts.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Data
public class Car extends AbstractEntity {

    @Length(max = 50)
    private String make;

    @Length(max = 50)
    private String model;

    @Range(min = 1900)
    private int year;

    @Length(max = 50)
    private String registrationNumber;

    @Length(min = 17, max = 17)
    private String vin;

    private Date dateOfFirstRegistration;

    @Length(max = 50)
    private String oc;

    private boolean vehicleCheckup;

    @Range(min = 0, max = 9999999)
    private int vehicleMileage;

    @Length(max = 10)
    private String engineCapacity;

    @Length(max = 10)
    private String enginePower;

    @Length(max = 20)
    private String fuelType;

    public Car() {
    }

    public Car(String make, String model, int year, String registrationNumber, String vin, Date dateOfFirstRegistration, String oc, boolean vehicleCheckup, int vehicleMileage, String engineCapacity, String enginePower, String fuelType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.dateOfFirstRegistration = dateOfFirstRegistration;
        this.oc = oc;
        this.vehicleCheckup = vehicleCheckup;
        this.vehicleMileage = vehicleMileage;
        this.engineCapacity = engineCapacity;
        this.enginePower = enginePower;
        this.fuelType = fuelType;
    }
}
