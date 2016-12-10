package auto_ckz.domain.client;

import auto_ckz.site.account.Account;
import auto_ckz.site.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
class ClientSecurityService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ClientRepository clientRepository;

	boolean isAccountAuthorized(Client client, Principal principal){
		boolean authorized;

		Account account = accountRepository.findOneByEmail(principal.getName());
		switch (account.getRole()){
			case("DIRECTOR") : {
				authorized = true;
				break;
			}
			case("CLIENT") : {
				authorized = client.getId().equals(clientRepository.findByAccountId(account.getId()).getId());
				break;
			}
			default: authorized = false;
		}

		return authorized;
	}
}
