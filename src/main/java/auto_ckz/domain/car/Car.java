package auto_ckz.domain.car;

import auto_ckz.domain.abstracts.AbstractEntity;
import auto_ckz.domain.client.Client;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Data
public class Car extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

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

}
