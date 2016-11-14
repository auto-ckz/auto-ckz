package auto_ckz.domain.mechanic;


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
public class MechanicTest {

    @Autowired
    private MechanicRepository repository;

    @Test
    public void NoEntries_findByFirstNameAndLastName_ShouldReturnEmptyList() {
        List<Mechanic> mechanicEntries = repository.findByFirstNameAndLastName("NOT FOUND", "NOT FOUND");
        assertThat(mechanicEntries.size(), is(0));
    }

    @Test
    public void findByPhoneNumber_ShouldReturnOneMechanicEntry() throws ParseException {
        Mechanic mechanicEntries = repository.findByPhoneNumber("94235435454");

        assertThat(mechanicEntries, allOf(
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
        ));
    }

    @Test
    public void findByPesel_ShouldReturnOneMechanicEntry() throws ParseException {
        Mechanic mechanicEntries = repository.findByPesel("98432465664");

        assertThat(mechanicEntries, allOf(
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
        ));
    }

    @Test
    public void twofindByFirstNameAndLastName_ShouldReturnAListOfTwoEntries() {
        List<Mechanic> mechanicEntries = repository.findByFirstNameAndLastName("David", "Kos");
        assertThat(mechanicEntries.size(), is(2));
        assertThat(mechanicEntries, contains(
                allOf(
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
                ),
                allOf(
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
                )
        ));
    }
}
