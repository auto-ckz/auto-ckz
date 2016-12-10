package auto_ckz.domain.repairorder;

import auto_ckz.common.constant.Role;
import auto_ckz.domain.client.Client;
import auto_ckz.domain.client.ClientRepository;
import auto_ckz.domain.mechanic.Mechanic;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerService;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerServiceRepository;
import auto_ckz.site.account.Account;
import auto_ckz.site.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
class RepairOrderSecurityService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private MemberOfCustomerServiceRepository customerServiceRepository;

	boolean isAccountAuthorized(RepairOrder repairOrder, Principal principal){
		boolean authorized;

		Account account = accountRepository.findOneByEmail(principal.getName());
		switch (account.getRole()){
			case(Role.ROLE_DIRECTOR) : {
				authorized = true;
				break;
			}
			case(Role.ROLE_CUSTOMER_SERVICE) : {
				MemberOfCustomerService customerServiceMember = customerServiceRepository.findByAccountId(account.getId());
				authorized = customerServiceMember.getId().equals(repairOrder.getMemberOfCustomerService().getId());
				break;
			}
			case(Role.ROLE_CLIENT) : {
				Client client = clientRepository.findByAccountId(account.getId());
				authorized = client.getId().equals(repairOrder.getClient().getId());
				break;
			}
			default: authorized = false;
		}

		return authorized;
	}
}
