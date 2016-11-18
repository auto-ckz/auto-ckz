package auto_ckz.domain.repair;

import auto_ckz.config.JpaConfigForTest;
import auto_ckz.common.enums.RepairStatus;
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
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfigForTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/testData/testData.xml")
@Transactional
public class RepairTest {

    @Autowired
    private RepairRepository repository;

    @Test
    public void NoEntries_findByRepairOrderId_ShouldReturnEmptyList() {
        List<Repair> repairEntries = repository.findByRepairOrderId(null);
        assertThat(repairEntries.size(), is(0));
    }

    @Test
    public void findByMechanicId_ShouldReturnOneRepairEntry(){
        List<Repair> repairEntries = repository.findByMechanicId(1L);

        assertThat(repairEntries.get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("description", is("Wymiana klockow hamulcowych")),
                hasProperty("status", is(RepairStatus.IN_PROGRESS)),
                hasProperty("repairOrder", allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("date", is(Date.valueOf("2016-03-19"))),
                        hasProperty("totalCost", is(notNullValue())),
                        hasProperty("car", allOf(
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
                        )),
                        hasProperty("client", allOf(
                                hasProperty("firstName", is("Marek")),
                                hasProperty("lastName", is("Nowak")),
                                hasProperty("phoneNumber", is("94332454343")),
                                hasProperty("pesel", is("95021232434")),
                                hasProperty("email", is("maN@wp.pl")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Ikara")),
                                        hasProperty("city", is("Gdynia")),
                                        hasProperty("houseNumber", is("2")),
                                        hasProperty("zipCode", is("82153"))
                                ))
                        )),
                        hasProperty("memberOfCustomerService", allOf(
                                hasProperty("firstName", is("Wojtek")),
                                hasProperty("lastName", is("Kowalski")),
                                hasProperty("phoneNumber", is("96345253242")),
                                hasProperty("pesel", is("91235436565")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Karola")),
                                        hasProperty("city", is("Bydgoszcz")),
                                        hasProperty("houseNumber", is("12")),
                                        hasProperty("zipCode", is("82542"))
                                ))
                        ))

                )),
                hasProperty("mechanic", allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("firstName", is("David")),
                        hasProperty("lastName", is("Kos")),
                        hasProperty("phoneNumber", is("94235435454")),
                        hasProperty("pesel", is("93245435435")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Kijana")),
                                hasProperty("city", is("Bytom")),
                                hasProperty("houseNumber", is("7")),
                                hasProperty("zipCode", is("92543"))
                        ))
                ))
        ));
    }

    @Test
    public void findByRepairOrderIdAndMechanicId_ShouldReturnOneRepairEntry(){
        List<Repair> repairEntries = repository.findByRepairOrderIdAndMechanicId(1L, 2L);

        assertThat(repairEntries.get(0), allOf(
                hasProperty("id", is(2L)),
                hasProperty("description", is("Wymiana oleju silnika")),
                hasProperty("status", is(RepairStatus.DONE)),
                hasProperty("repairOrder", allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("date", is(Date.valueOf("2016-03-19"))),
                        hasProperty("totalCost", is(notNullValue())),
                        hasProperty("car", allOf(
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
                        )),
                        hasProperty("client", allOf(
                                hasProperty("firstName", is("Marek")),
                                hasProperty("lastName", is("Nowak")),
                                hasProperty("phoneNumber", is("94332454343")),
                                hasProperty("pesel", is("95021232434")),
                                hasProperty("email", is("maN@wp.pl")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Ikara")),
                                        hasProperty("city", is("Gdynia")),
                                        hasProperty("houseNumber", is("2")),
                                        hasProperty("zipCode", is("82153"))
                                ))
                        )),
                        hasProperty("memberOfCustomerService", allOf(
                                hasProperty("firstName", is("Wojtek")),
                                hasProperty("lastName", is("Kowalski")),
                                hasProperty("phoneNumber", is("96345253242")),
                                hasProperty("pesel", is("91235436565")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Karola")),
                                        hasProperty("city", is("Bydgoszcz")),
                                        hasProperty("houseNumber", is("12")),
                                        hasProperty("zipCode", is("82542"))
                                ))
                        ))
                )),
                hasProperty("mechanic", allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("firstName", is("David")),
                        hasProperty("lastName", is("Kos")),
                        hasProperty("phoneNumber", is("24652524555")),
                        hasProperty("pesel", is("98432465664")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Rycha")),
                                hasProperty("city", is("Katowice")),
                                hasProperty("houseNumber", is("8")),
                                hasProperty("zipCode", is("91245"))
                        ))
                ))
        ));
    }

    @Test
    public void twofindByRepairOrderIdShouldReturnAListOfTwoEntries() {
        List<Repair> repairEntries = repository.findByRepairOrderId(1L);

        assertThat(repairEntries.size(), is(2));
        assertThat(repairEntries, contains(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("description", is("Wymiana klockow hamulcowych")),
                        hasProperty("status", is(RepairStatus.IN_PROGRESS)),
                        hasProperty("repairOrder", allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("date", is(Date.valueOf("2016-03-19"))),
                                hasProperty("totalCost", is(notNullValue())),
                                hasProperty("car", allOf(
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
                                )),
                                hasProperty("client", allOf(
                                        hasProperty("firstName", is("Marek")),
                                        hasProperty("lastName", is("Nowak")),
                                        hasProperty("phoneNumber", is("94332454343")),
                                        hasProperty("pesel", is("95021232434")),
                                        hasProperty("email", is("maN@wp.pl")),
                                        hasProperty("address", allOf(
                                                hasProperty("street", is("Ikara")),
                                                hasProperty("city", is("Gdynia")),
                                                hasProperty("houseNumber", is("2")),
                                                hasProperty("zipCode", is("82153"))
                                        ))
                                )),
                                hasProperty("memberOfCustomerService", allOf(
                                        hasProperty("firstName", is("Wojtek")),
                                        hasProperty("lastName", is("Kowalski")),
                                        hasProperty("phoneNumber", is("96345253242")),
                                        hasProperty("pesel", is("91235436565")),
                                        hasProperty("address", allOf(
                                                hasProperty("street", is("Karola")),
                                                hasProperty("city", is("Bydgoszcz")),
                                                hasProperty("houseNumber", is("12")),
                                                hasProperty("zipCode", is("82542"))
                                        ))
                                ))
                                )),
                        hasProperty("mechanic", allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("David")),
                                hasProperty("lastName", is("Kos")),
                                hasProperty("phoneNumber", is("94235435454")),
                                hasProperty("pesel", is("93245435435")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Kijana")),
                                        hasProperty("city", is("Bytom")),
                                        hasProperty("houseNumber", is("7")),
                                        hasProperty("zipCode", is("92543"))
                                ))
                ))
                ),
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("description", is("Wymiana oleju silnika")),
                        hasProperty("status", is(RepairStatus.DONE)),
                        hasProperty("repairOrder", allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("date", is(Date.valueOf("2016-03-19"))),
                                hasProperty("totalCost", is(notNullValue())),
                                hasProperty("car", allOf(
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
                                )),
                                hasProperty("client", allOf(
                                        hasProperty("firstName", is("Marek")),
                                        hasProperty("lastName", is("Nowak")),
                                        hasProperty("phoneNumber", is("94332454343")),
                                        hasProperty("pesel", is("95021232434")),
                                        hasProperty("email", is("maN@wp.pl")),
                                        hasProperty("address", allOf(
                                                hasProperty("street", is("Ikara")),
                                                hasProperty("city", is("Gdynia")),
                                                hasProperty("houseNumber", is("2")),
                                                hasProperty("zipCode", is("82153"))
                                        ))
                                )),
                                hasProperty("memberOfCustomerService", allOf(
                                        hasProperty("firstName", is("Wojtek")),
                                        hasProperty("lastName", is("Kowalski")),
                                        hasProperty("phoneNumber", is("96345253242")),
                                        hasProperty("pesel", is("91235436565")),
                                        hasProperty("address", allOf(
                                                hasProperty("street", is("Karola")),
                                                hasProperty("city", is("Bydgoszcz")),
                                                hasProperty("houseNumber", is("12")),
                                                hasProperty("zipCode", is("82542"))
                                        ))
                                ))
                        )),
                        hasProperty("mechanic", allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("firstName", is("David")),
                                hasProperty("lastName", is("Kos")),
                                hasProperty("phoneNumber", is("24652524555")),
                                hasProperty("pesel", is("98432465664")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Rycha")),
                                        hasProperty("city", is("Katowice")),
                                        hasProperty("houseNumber", is("8")),
                                        hasProperty("zipCode", is("91245"))
                                ))
                        ))
                )
        ));
    }
}
