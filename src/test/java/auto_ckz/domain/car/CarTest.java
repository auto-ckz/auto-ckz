package auto_ckz.domain.car;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import auto_ckz.config.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfigForTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/testData/toDoDataCar.xml")
public class CarTest {

    @Autowired
    private CarRepository repository;

    @Test
    public void search_OneTodoEntryFound_ShouldReturnAListOfOneEntry() throws ParseException {
        List<Car> carsEntries = repository.findByModel("A8");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2006);
        calendar.set(Calendar.MONTH, 2); // Assuming you wanted May 1st
        calendar.set(Calendar.DAY_OF_MONTH, 23);

        java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
        System.out.println(date);

        assertThat(carsEntries.size(), is(1));
        assertThat(carsEntries.get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("make", is("Audi")),
                hasProperty("model", is("A8")),
                hasProperty("year", is(2006)),
                hasProperty("registrationNumber", is("GA5424")),
                hasProperty("vin", is("HF3JF4JVJ36JVJEKD")),
                hasProperty("dateOfFirstRegistration", notNullValue()),
                hasProperty("oc", is("wykupione")),
                hasProperty("vehicleCheckup", is(true)),
                hasProperty("vehicleMileage", is(200000)),
                hasProperty("engineCapacity", is("2200")),
                hasProperty("enginePower", is("198")),
                hasProperty("fuelType", is("diesel"))
        ));
    }
}

