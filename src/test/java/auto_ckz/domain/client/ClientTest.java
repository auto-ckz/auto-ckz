package auto_ckz.domain.client;


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
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfigForTest.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/testData/toDoData.xml")
public class ClientTest {

    @Autowired
    private ClientRepository repository;

    @Test
    public void NoEntries_findByFirstNameAndLastName_ShouldReturnEmptyList() {
        List<Client> clientEntries = repository.findByFirstNameAndLastName("NOT FOUND", "NOT FOUND");
        assertThat(clientEntries.size(), is(0));
    }

    @Test
    public void findByPhoneNumber_ShouldReturnOneClientEntry() throws ParseException {
        Client clientEntries = repository.findByPhoneNumber("94332454343");

        assertThat(clientEntries, allOf(
                hasProperty("id", is(1L)),
                hasProperty("firstName", is("Marek")),
                hasProperty("lastName", is("Nowak")),
                hasProperty("phoneNumber", is("94332454343")),
                hasProperty("pesel", is(95021232434L)),
                hasProperty("email", is("maN@wp.pl")),
                hasProperty("address", allOf(
                        hasProperty("street", is("Ikara")),
                        hasProperty("city", is("Gdynia")),
                        hasProperty("houseNumber", is("2")),
                        hasProperty("zipCode", is("82153"))
                ))
        ));
    }
    @Test
    public void findByPesel_ShouldReturnOneClientEntry() throws ParseException {
        Client clientEntries = repository.findByPesel(92013143254L);

        assertThat(clientEntries, allOf(
                hasProperty("id", is(2L)),
                hasProperty("firstName", is("Marek")),
                hasProperty("lastName", is("Nowak")),
                hasProperty("phoneNumber", is("84322345223")),
                hasProperty("pesel", is(92013143254L)),
                hasProperty("email", is("wiK@wp.pl")),
                hasProperty("address", allOf(
                        hasProperty("street", is("Maja")),
                        hasProperty("city", is("Sopot")),
                        hasProperty("houseNumber", is("7")),
                        hasProperty("zipCode", is("81324"))
                ))
        ));
    }
        @Test
        public void twofindByFirstNameAndLastName_ShouldReturnAListOfTwoEntries() {
            List<Client> clientEntries = repository.findByFirstNameAndLastName("Marek", "Nowak");
            assertThat(clientEntries.size(), is(2));
            assertThat(clientEntries, contains(
                    allOf(
                            hasProperty("id", is(1L)),
                            hasProperty("firstName", is("Marek")),
                            hasProperty("lastName", is("Nowak")),
                            hasProperty("phoneNumber", is("94332454343")),
                            hasProperty("pesel", is(95021232434L)),
                            hasProperty("email", is("maN@wp.pl")),
                            hasProperty("address", allOf(
                                    hasProperty("street", is("Ikara")),
                                    hasProperty("city", is("Gdynia")),
                                    hasProperty("houseNumber", is("2")),
                                    hasProperty("zipCode", is("82153"))
                            ))
                    ),
                    allOf(
                            hasProperty("id", is(2L)),
                            hasProperty("firstName", is("Marek")),
                            hasProperty("lastName", is("Nowak")),
                            hasProperty("phoneNumber", is("84322345223")),
                            hasProperty("pesel", is(92013143254L)),
                            hasProperty("email", is("wiK@wp.pl")),
                            hasProperty("address", allOf(
                                    hasProperty("street", is("Maja")),
                                    hasProperty("city", is("Sopot")),
                                    hasProperty("houseNumber", is("7")),
                                    hasProperty("zipCode", is("81324"))
                            ))
                    )
            ));
    }
}
