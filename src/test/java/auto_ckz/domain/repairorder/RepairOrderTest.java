package auto_ckz.domain.repairorder;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RepairOrderTest {

    @Autowired
    private RepairOrderRepository repository;

    @Test
    public void NoEntries_findByDate_ShouldReturnEmptyList() {
        List<RepairOrder> repairOrderEntries = repository.findByDate(null);
        assertThat(repairOrderEntries.size(), is(0));
    }

    @Test
    public void findByClientId_ShouldReturnOneRepairOrderEntry() throws ParseException {
        List<RepairOrder> repairOrderEntries = repository.findByClientId(1L);

        String date_s = "2016-03-19";
        String date_s1 = "2006-03-23";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        Date date1 = dt.parse(date_s1);
        java.sql.Date sqlDate =  new java.sql.Date(date.getTime());
        java.sql.Date sqlDate1 =  new java.sql.Date(date1.getTime());

        assertThat(repairOrderEntries.get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("date", is(sqlDate)),
                hasProperty("totalCost", is(notNullValue())),
                hasProperty("car", allOf(
                        hasProperty("make", is("Audi")),
                        hasProperty("model", is("A8")),
                        hasProperty("year", is(2006)),
                        hasProperty("registrationNumber", is("GA5424")),
                        hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                        hasProperty("dateOfFirstRegistration", is(sqlDate1)),
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

        ));
    }

    @Test
    public void findByMemberOfCustomerServiceId_ShouldReturnOneRepairOrderEntry() throws ParseException {
        List<RepairOrder> repairOrderEntries = repository.findByMemberOfCustomerServiceId(2L);

        String date_s = "2016-03-19";
        String date_s1 = "2014-02-11";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        Date date1 = dt.parse(date_s1);
        java.sql.Date sqlDate =  new java.sql.Date(date.getTime());
        java.sql.Date sqlDate1 =  new java.sql.Date(date1.getTime());

        assertThat(repairOrderEntries.get(0), allOf(
                hasProperty("id", is(2L)),
                hasProperty("date", is(sqlDate)),
                hasProperty("totalCost", notNullValue()),
                hasProperty("car", allOf(
                        hasProperty("make", is("Audi")),
                        hasProperty("model", is("Q4")),
                        hasProperty("year", is(2014)),
                        hasProperty("registrationNumber", is("GDA324")),
                        hasProperty("vin", is("GF834934F98HFIHF9")),
                        hasProperty("dateOfFirstRegistration", is(sqlDate1)),
                        hasProperty("oc", is("543uh2i43")),
                        hasProperty("vehicleCheckup", is(true)),
                        hasProperty("vehicleMileage", is(123431)),
                        hasProperty("engineCapacity", is("4800")),
                        hasProperty("enginePower", is("460")),
                        hasProperty("fuelType", is("petrol"))
                )),
                hasProperty("client", allOf(
                        hasProperty("firstName", is("Marek")),
                        hasProperty("lastName", is("Nowak")),
                        hasProperty("phoneNumber", is("84322345223")),
                        hasProperty("pesel", is("92013143254")),
                        hasProperty("email", is("wiK@wp.pl")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Maja")),
                                hasProperty("city", is("Sopot")),
                                hasProperty("houseNumber", is("7")),
                                hasProperty("zipCode", is("81324"))
                        ))
                )),
                hasProperty("memberOfCustomerService", allOf(
                        hasProperty("firstName", is("Wojtek")),
                        hasProperty("lastName", is("Kowalski")),
                        hasProperty("phoneNumber", is("93432423653")),
                        hasProperty("pesel", is("93634676424")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Polna")),
                                hasProperty("city", is("Kartuzy")),
                                hasProperty("houseNumber", is("2")),
                                hasProperty("zipCode", is("85432"))
                        ))
                ))

        ));
    }

    @Test
    public void findByCarId_ShouldReturnOneRepairOrderEntry() throws ParseException {
        RepairOrder repairOrderEntries = repository.findByCarId(2L);

        String date_s = "2016-03-19";
        String date_s1 = "2014-02-11";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        Date date1 = dt.parse(date_s1);
        java.sql.Date sqlDate =  new java.sql.Date(date.getTime());
        java.sql.Date sqlDate1 =  new java.sql.Date(date1.getTime());

        assertThat(repairOrderEntries, allOf(
                hasProperty("id", is(2L)),
                hasProperty("date", is(sqlDate)),
                hasProperty("totalCost", is(notNullValue())),
                hasProperty("car", allOf(
                        hasProperty("make", is("Audi")),
                        hasProperty("model", is("Q4")),
                        hasProperty("year", is(2014)),
                        hasProperty("registrationNumber", is("GDA324")),
                        hasProperty("vin", is("GF834934F98HFIHF9")),
                        hasProperty("dateOfFirstRegistration", is(sqlDate1)),
                        hasProperty("oc", is("543uh2i43")),
                        hasProperty("vehicleCheckup", is(true)),
                        hasProperty("vehicleMileage", is(123431)),
                        hasProperty("engineCapacity", is("4800")),
                        hasProperty("enginePower", is("460")),
                        hasProperty("fuelType", is("petrol"))
                )),
                hasProperty("client", allOf(
                        hasProperty("firstName", is("Marek")),
                        hasProperty("lastName", is("Nowak")),
                        hasProperty("phoneNumber", is("84322345223")),
                        hasProperty("pesel", is("92013143254")),
                        hasProperty("email", is("wiK@wp.pl")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Maja")),
                                hasProperty("city", is("Sopot")),
                                hasProperty("houseNumber", is("7")),
                                hasProperty("zipCode", is("81324"))
                        ))
                )),
                hasProperty("memberOfCustomerService", allOf(
                        hasProperty("firstName", is("Wojtek")),
                        hasProperty("lastName", is("Kowalski")),
                        hasProperty("phoneNumber", is("93432423653")),
                        hasProperty("pesel", is("93634676424")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Polna")),
                                hasProperty("city", is("Kartuzy")),
                                hasProperty("houseNumber", is("2")),
                                hasProperty("zipCode", is("85432"))
                        ))
                ))

        ));
    }

    @Test
    public void twofindByDate_ShouldReturnAListOfTwoEntries() throws ParseException {
        String date_s = "2016-03-19";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        java.sql.Date sqlDate =  new java.sql.Date(date.getTime());
        List<RepairOrder> repairOrderEntries = repository.findByDate(sqlDate);

        String date_s1 = "2006-03-23";
        String date_s2 = "2014-02-11";
        Date date1 = dt.parse(date_s1);
        Date date2 = dt.parse(date_s2);
        java.sql.Date sqlDate1 =  new java.sql.Date(date1.getTime());
        java.sql.Date sqlDate2 =  new java.sql.Date(date2.getTime());

        assertThat(repairOrderEntries.size(), is(2));
        assertThat(repairOrderEntries, contains(
                allOf(
                hasProperty("id", is(1L)),
                hasProperty("date", is(sqlDate)),
                hasProperty("totalCost", is(notNullValue())),
                hasProperty("car", allOf(
                        hasProperty("make", is("Audi")),
                        hasProperty("model", is("A8")),
                        hasProperty("year", is(2006)),
                        hasProperty("registrationNumber", is("GA5424")),
                        hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                        hasProperty("dateOfFirstRegistration", is(sqlDate1)),
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

        ),
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("date", is(sqlDate)),
                        hasProperty("totalCost", is(notNullValue())),
                        hasProperty("car", allOf(
                                hasProperty("make", is("Audi")),
                                hasProperty("model", is("Q4")),
                                hasProperty("year", is(2014)),
                                hasProperty("registrationNumber", is("GDA324")),
                                hasProperty("vin", is("GF834934F98HFIHF9")),
                                hasProperty("dateOfFirstRegistration", is(sqlDate2)),
                                hasProperty("oc", is("543uh2i43")),
                                hasProperty("vehicleCheckup", is(true)),
                                hasProperty("vehicleMileage", is(123431)),
                                hasProperty("engineCapacity", is("4800")),
                                hasProperty("enginePower", is("460")),
                                hasProperty("fuelType", is("petrol"))
                        )),
                        hasProperty("client", allOf(
                                hasProperty("firstName", is("Marek")),
                                hasProperty("lastName", is("Nowak")),
                                hasProperty("phoneNumber", is("84322345223")),
                                hasProperty("pesel", is("92013143254")),
                                hasProperty("email", is("wiK@wp.pl")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Maja")),
                                        hasProperty("city", is("Sopot")),
                                        hasProperty("houseNumber", is("7")),
                                        hasProperty("zipCode", is("81324"))
                                ))
                        )),
                        hasProperty("memberOfCustomerService", allOf(
                                hasProperty("firstName", is("Wojtek")),
                                hasProperty("lastName", is("Kowalski")),
                                hasProperty("phoneNumber", is("93432423653")),
                                hasProperty("pesel", is("93634676424")),
                                hasProperty("address", allOf(
                                        hasProperty("street", is("Polna")),
                                        hasProperty("city", is("Kartuzy")),
                                        hasProperty("houseNumber", is("2")),
                                        hasProperty("zipCode", is("85432"))
                                ))
                        ))

                )
        ));
    }
}
