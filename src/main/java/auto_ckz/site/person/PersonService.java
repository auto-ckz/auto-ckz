package auto_ckz.site.person;

import auto_ckz.common.constant.Role;
import auto_ckz.domain.abstracts.AbstractPersonEntity;
import auto_ckz.domain.client.Client;
import auto_ckz.domain.client.ClientRepository;
import auto_ckz.domain.director.Director;
import auto_ckz.domain.director.DirectorRepository;
import auto_ckz.domain.mechanic.Mechanic;
import auto_ckz.domain.mechanic.MechanicRepository;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerService;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerServiceRepository;
import auto_ckz.site.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private MemberOfCustomerServiceRepository memberOfCustomerServiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    public AbstractPersonEntity save(Account account){

        AbstractPersonEntity person;
        switch (account.getRole()){
            case Role.ROLE_DIRECTOR :
                person = new Director();
                person.setAccount(account);
                person = directorRepository.save((Director)person);
                break;
            case Role.ROLE_MECHANIC :
                person = new Mechanic();
                person.setAccount(account);
                person = mechanicRepository.save((Mechanic) person);
                break;
            case Role.ROLE_MEMBER :
                person = new MemberOfCustomerService();
                person.setAccount(account);
                person = memberOfCustomerServiceRepository.save((MemberOfCustomerService)person);
                break;
            default :
                person = new Client();
                person.setAccount(account);
                person = clientRepository.save((Client)person);
                break;
        }

        return person;
    }

    public AbstractPersonEntity update(Account account, String firstName, String lastName, String phoneNumber, String pesel) {

        AbstractPersonEntity person;
        switch (account.getRole()){
            case Role.ROLE_DIRECTOR :
                person = directorRepository.findByAccountId(account.getId());
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setPhoneNumber(phoneNumber);
                person.setPesel(pesel);
                person = directorRepository.save((Director)person);
                break;
            case Role.ROLE_MECHANIC :
                person = mechanicRepository.findByAccountId(account.getId());
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setPhoneNumber(phoneNumber);
                person.setPesel(pesel);
                person = mechanicRepository.save((Mechanic) person);

                break;
            case Role.ROLE_MEMBER :
                person = memberOfCustomerServiceRepository.findByAccountId(account.getId());
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setPhoneNumber(phoneNumber);
                person.setPesel(pesel);
                person = memberOfCustomerServiceRepository.save((MemberOfCustomerService)person);
                break;
            default :
                person = clientRepository.findByAccountId(account.getId());
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setPhoneNumber(phoneNumber);
                person.setPesel(pesel);
                person = clientRepository.save((Client)person);
                break;
        }
        return person;

    }
}
