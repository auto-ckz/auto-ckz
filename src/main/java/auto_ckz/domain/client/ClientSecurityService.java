package auto_ckz.domain.client;

import auto_ckz.common.constant.Role;
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

	//TODO: unit tests
	boolean isAccountAuthorized(Client client, Principal principal){
		boolean authorized;

		Account account = accountRepository.findOneByEmail(principal.getName());
		switch (account.getRole()){
			case(Role.ROLE_DIRECTOR) : {
				authorized = true;
				break;
			}
			case(Role.ROLE_CLIENT) : {
				Client matchedClient = clientRepository.findByAccountId(account.getId());
				authorized = matchedClient != null && client.getId().equals(matchedClient.getId());
				break;
			}
			default: authorized = false;
		}

		return authorized;
	}
}
