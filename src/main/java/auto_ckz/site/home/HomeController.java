package auto_ckz.site.home;

import auto_ckz.common.constant.Role;
import auto_ckz.domain.address.Address;
import auto_ckz.domain.client.Client;
import auto_ckz.domain.client.ClientRepository;
import auto_ckz.site.account.Account;
import auto_ckz.site.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {

	@Autowired
	private AccountRepository repository;

	@Autowired
	private ClientRepository clientRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal,  Model model) {
		if(principal == null)
			return "home/homeNotSignedIn";

		Account account = repository.findOneByEmail(principal.getName());
		switch (account.getRole()){
			case Role.ROLE_DIRECTOR:
				return "redirect:/admin/";
			case Role.ROLE_MECHANIC:
				return "redirect:/mechanics/";
			case Role.ROLE_CUSTOMER_SERVICE:
				return "redirect:/customerService/";
			default:
				Client client = clientRepository.findByAccountId(account.getId());
				if(client != null){
					return "redirect:/clients/" + client.getId();
				}
				client = new Client();
				client.setAddress(new Address());
				model.addAttribute(new Client());
				return "clients/edit";

		}
	}
}
