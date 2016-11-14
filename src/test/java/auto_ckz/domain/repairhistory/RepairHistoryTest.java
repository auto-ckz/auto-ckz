package auto_ckz.domain.repairhistory;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfigForTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/testData/toDoData.xml")
public class RepairHistoryTest {

    @Autowired
    private RepairHistoryRepository repository;

    @Test
    public void NoEntries_findByCarId_ShouldReturnEmptyList() {
        List<RepairHistory> repairHistoryEntries = repository.findByCarId(null);
        assertThat(repairHistoryEntries.size(), is(0));
    }

    @Test
    public void findByDate_ShouldReturnOneRepairHistoryEntry() throws ParseException {

        String date_s = "2014-02-11";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dt.parse(date_s);
        java.sql.Date sqlDate =  new java.sql.Date(date.getTime());

        RepairHistory repairHistorytEntries = repository.findByDate(sqlDate);

        assertThat(repairHistorytEntries, allOf(
                hasProperty("id", is(1L)),
                hasProperty("description", is("Wymiana skrzyni biegow")),
                hasProperty("date", is(sqlDate)),
                hasProperty("cost", is(notNullValue())),
                hasProperty("car", allOf(
                        hasProperty("make", is("Audi")),
                        hasProperty("model", is("A8")),
                        hasProperty("year", is(2006)),
                        hasProperty("registrationNumber", is("GA5424")),
                        hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                        hasProperty("dateOfFirstRegistration", notNullValue()),
                        hasProperty("oc", is("6346gf234")),
                        hasProperty("vehicleCheckup", is(true)),
                        hasProperty("vehicleMileage", is(200000)),
                        hasProperty("engineCapacity", is("2200")),
                        hasProperty("enginePower", is("198")),
                        hasProperty("fuelType", is("diesel"))
                ))
        ));
    }

    @Test
    public void twofindByCarId_ShouldReturnAListOfTwoEntries() throws ParseException {
        List<RepairHistory> repairHistoryEntries = repository.findByCarId(1L);

        String date_s = "2014-02-11";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dt.parse(date_s);
        java.sql.Date sqlDate =  new java.sql.Date(date.getTime());

        String date_s1 = "2015-04-12";
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = dt1.parse(date_s1);
        java.sql.Date sqlDate1 =  new java.sql.Date(date1.getTime());

        assertThat(repairHistoryEntries.size(), is(2));
        assertThat(repairHistoryEntries, contains(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("description", is("Wymiana skrzyni biegow")),
                        hasProperty("date", is(sqlDate)),
                        hasProperty("cost", is(notNullValue())),
                        hasProperty("car", allOf(
                                hasProperty("make", is("Audi")),
                                hasProperty("model", is("A8")),
                                hasProperty("year", is(2006)),
                                hasProperty("registrationNumber", is("GA5424")),
                                hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                                hasProperty("dateOfFirstRegistration", notNullValue()),
                                hasProperty("oc", is("6346gf234")),
                                hasProperty("vehicleCheckup", is(true)),
                                hasProperty("vehicleMileage", is(200000)),
                                hasProperty("engineCapacity", is("2200")),
                                hasProperty("enginePower", is("198")),
                                hasProperty("fuelType", is("diesel"))
                        ))
                ),
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("description", is("Wymiana amortyzatorow z przodu i z tylu")),
                        hasProperty("date", is(sqlDate1)),
                        hasProperty("cost", is(notNullValue())),
                        hasProperty("car", allOf(
                                hasProperty("make", is("Audi")),
                                hasProperty("model", is("A8")),
                                hasProperty("year", is(2006)),
                                hasProperty("registrationNumber", is("GA5424")),
                                hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                                hasProperty("dateOfFirstRegistration", notNullValue()),
                                hasProperty("oc", is("6346gf234")),
                                hasProperty("vehicleCheckup", is(true)),
                                hasProperty("vehicleMileage", is(200000)),
                                hasProperty("engineCapacity", is("2200")),
                                hasProperty("enginePower", is("198")),
                                hasProperty("fuelType", is("diesel"))
                        ))
                )
        ));
    }
}
