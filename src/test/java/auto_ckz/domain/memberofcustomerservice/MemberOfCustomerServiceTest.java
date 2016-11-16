package auto_ckz.domain.memberofcustomerservice;

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
@DatabaseSetup("/testData/toDoData.xml")
@Transactional
public class MemberOfCustomerServiceTest {

    @Autowired
    private MemberOfCustomerServiceRepository repository;

    @Test
    public void NoEntries_findByFirstNameAndLastName_ShouldReturnEmptyList() {
        List<MemberOfCustomerService> memberOfCustomerServicerEntries = repository.findByFirstNameAndLastName("NOT FOUND", "NOT FOUND");
        assertThat(memberOfCustomerServicerEntries.size(), is(0));
    }

    @Test
    public void findByPhoneNumber_ShouldReturnOneMemberOfCustomerServiceEntry() throws ParseException {
        MemberOfCustomerService memberOfCustomerServicerEntries = repository.findByPhoneNumber("96345253242");

        assertThat(memberOfCustomerServicerEntries, allOf(
                hasProperty("id", is(1L)),
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
        ));
    }

    @Test
    public void findByPesel_ShouldReturnOneMemberOfCustomerServiceEntry() throws ParseException {
        MemberOfCustomerService memberOfCustomerServicerEntries = repository.findByPesel("93634676424");

        assertThat(memberOfCustomerServicerEntries, allOf(
                hasProperty("id", is(2L)),
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
        ));
    }

    @Test
    public void twofindByFirstNameAndLastName_ShouldReturnAListOfTwoEntries() {
        List<MemberOfCustomerService> memberOfCustomerServicerEntries = repository.findByFirstNameAndLastName("Wojtek", "Kowalski");
        assertThat(memberOfCustomerServicerEntries.size(), is(2));
        assertThat(memberOfCustomerServicerEntries, contains(
                allOf(
                        hasProperty("id", is(1L)),
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
                ),
                allOf(
                        hasProperty("id", is(2L)),
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
                )
        ));
    }
}
