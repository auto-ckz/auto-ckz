package auto_ckz.domain.car;

import auto_ckz.config.JpaConfigForTest;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfigForTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/testData/testData.xml")
@Transactional
public class CarTest {

    @Autowired
    private CarRepository repository;

    @Test
    public void NoEntries_findByMake_ShouldReturnEmptyList() {
        List<Car> carsEntries = repository.findByMake("NOT FOUND");
        assertThat(carsEntries.size(), is(0));
    }

    @Test
    public void findByRegistrationNumber_ShouldReturnOneCarEntry() {
        Car carEntries = repository.findByRegistrationNumber("GA5424");

        assertThat(carEntries, allOf(
                hasProperty("id", is(1L)),
                hasProperty("make", is("Audi")),
                hasProperty("model", is("A8")),
                hasProperty("year", is(2006)),
                hasProperty("registrationNumber", is("GA5424")),
                hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                hasProperty("dateOfFirstRegistration", is(Date.valueOf("2006-03-23"))),
                hasProperty("oc", is("6346gf234")),
                hasProperty("vehicleCheckup", is(true)),
                hasProperty("vehicleMileage", is(200000)),
                hasProperty("engineCapacity", is("2200")),
                hasProperty("enginePower", is("198")),
                hasProperty("fuelType", is("diesel"))
        ));
    }

    @Test
    public void findByVin_ShouldReturnOneCarEntry() {
        Car carEntries = repository.findByVin("HF3JF4JVJ36JVJEKD");

        assertThat(carEntries, allOf(
                hasProperty("id", is(1L)),
                hasProperty("make", is("Audi")),
                hasProperty("model", is("A8")),
                hasProperty("year", is(2006)),
                hasProperty("registrationNumber", is("GA5424")),
                hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                hasProperty("dateOfFirstRegistration", is(Date.valueOf("2006-03-23"))),
                hasProperty("oc", is("6346gf234")),
                hasProperty("vehicleCheckup", is(true)),
                hasProperty("vehicleMileage", is(200000)),
                hasProperty("engineCapacity", is("2200")),
                hasProperty("enginePower", is("198")),
                hasProperty("fuelType", is("diesel"))
        ));
    }

    @Test
    public void findByOc_ShouldReturnOneCarEntry() {
        Car carEntries = repository.findByOc("6346gf234");

        assertThat(carEntries, allOf(
                hasProperty("id", is(1L)),
                hasProperty("make", is("Audi")),
                hasProperty("model", is("A8")),
                hasProperty("year", is(2006)),
                hasProperty("registrationNumber", is("GA5424")),
                hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                hasProperty("dateOfFirstRegistration", is(Date.valueOf("2006-03-23"))),
                hasProperty("oc", is("6346gf234")),
                hasProperty("vehicleCheckup", is(true)),
                hasProperty("vehicleMileage", is(200000)),
                hasProperty("engineCapacity", is("2200")),
                hasProperty("enginePower", is("198")),
                hasProperty("fuelType", is("diesel"))
        ));
    }

    @Test
    public void twoFindByMake_ShouldReturnAListOfTwoEntries() {
        List<Car> carEntries = repository.findByMake("Audi");

        assertThat(carEntries.size(), is(2));
        assertThat(carEntries, contains(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("make", is("Audi")),
                        hasProperty("model", is("A8")),
                        hasProperty("year", is(2006)),
                        hasProperty("registrationNumber", is("GA5424")),
                        hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                        hasProperty("dateOfFirstRegistration", is(Date.valueOf("2006-03-23"))),
                        hasProperty("oc", is("6346gf234")),
                        hasProperty("vehicleCheckup", is(true)),
                        hasProperty("vehicleMileage", is(200000)),
                        hasProperty("engineCapacity", is("2200")),
                        hasProperty("enginePower", is("198")),
                        hasProperty("fuelType", is("diesel"))
                ),
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("make", is("Audi")),
                        hasProperty("model", is("Q4")),
                        hasProperty("year", is(2014)),
                        hasProperty("registrationNumber", is("GDA324")),
                        hasProperty("vin", is("GF834934F98HFIHF9")),
                        hasProperty("dateOfFirstRegistration", is(Date.valueOf("2014-02-11"))),
                        hasProperty("oc", is("543uh2i43")),
                        hasProperty("vehicleCheckup", is(true)),
                        hasProperty("vehicleMileage", is(123431)),
                        hasProperty("engineCapacity", is("4800")),
                        hasProperty("enginePower", is("460")),
                        hasProperty("fuelType", is("petrol"))
                )
        ));
    }
}

