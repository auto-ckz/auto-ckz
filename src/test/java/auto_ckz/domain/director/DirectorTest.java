package auto_ckz.domain.director;

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

public class DirectorTest {

    @Autowired
    private DirectorRepository repository;

    @Test
    public void NoEntries_findByFirstNameAndLastName_ShouldReturnEmptyList() {
        List<Director> directorEntries = repository.findByFirstNameAndLastName("NOT FOUND", "NOT FOUND");
        assertThat(directorEntries.size(), is(0));
    }

    @Test
    public void findByPhoneNumber_ShouldReturnOneDirectorEntry() throws ParseException {
        Director directorEntries = repository.findByPhoneNumber("64354354353");

        assertThat(directorEntries, allOf(
                hasProperty("id", is(1L)),
                hasProperty("firstName", is("Karol")),
                hasProperty("lastName", is("Kowal")),
                hasProperty("phoneNumber", is("64354354353")),
                hasProperty("pesel", is("86354534545")),
                hasProperty("address", allOf(
                        hasProperty("street", is("Nowa")),
                        hasProperty("city", is("Bialystok")),
                        hasProperty("houseNumber", is("1")),
                        hasProperty("zipCode", is("81253"))
                ))
        ));
    }

    @Test
    public void findByPesel_ShouldReturnOneDirectorEntry() throws ParseException {
        Director directorEntries = repository.findByPesel("74532636455");

        assertThat(directorEntries, allOf(
                hasProperty("id", is(2L)),
                hasProperty("firstName", is("Karol")),
                hasProperty("lastName", is("Kowal")),
                hasProperty("phoneNumber", is("32534634656")),
                hasProperty("pesel", is("74532636455")),
                hasProperty("address", allOf(
                        hasProperty("street", is("Zaplecza")),
                        hasProperty("city", is("Gdansk")),
                        hasProperty("houseNumber", is("4")),
                        hasProperty("zipCode", is("83224"))
                ))
        ));
    }

    @Test
    public void twofindByFirstNameAndLastName_ShouldReturnAListOfTwoEntries() {
        List<Director> directorEntries = repository.findByFirstNameAndLastName("Karol", "Kowal");
        assertThat(directorEntries.size(), is(2));
        assertThat(directorEntries, contains(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("firstName", is("Karol")),
                        hasProperty("lastName", is("Kowal")),
                        hasProperty("phoneNumber", is("64354354353")),
                        hasProperty("pesel", is("86354534545")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Nowa")),
                                hasProperty("city", is("Bialystok")),
                                hasProperty("houseNumber", is("1")),
                                hasProperty("zipCode", is("81253"))
                        ))
                ),
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("firstName", is("Karol")),
                        hasProperty("lastName", is("Kowal")),
                        hasProperty("phoneNumber", is("32534634656")),
                        hasProperty("pesel", is("74532636455")),
                        hasProperty("address", allOf(
                                hasProperty("street", is("Zaplecza")),
                                hasProperty("city", is("Gdansk")),
                                hasProperty("houseNumber", is("4")),
                                hasProperty("zipCode", is("83224"))
                        ))
                )
        ));
    }

}
