package auto_ckz.domain.client;

import auto_ckz.domain.address.Address;
import auto_ckz.domain.address.AddressRepository;
import auto_ckz.domain.car.Car;
import auto_ckz.domain.car.CarRepository;
import auto_ckz.site.account.Account;
import auto_ckz.site.account.AccountRepository;
import auto_ckz.site.error.NotFoundException;
import auto_ckz.site.error.UnauthorizedException;
import auto_ckz.site.signup.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ClientSecurityService securityService;

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String clientIndex(Model model, Principal principal) {
		Account account = accountRepository.findOneByEmail(principal.getName());
		Client client = clientRepository.findByAccountId(account.getId());
		if(client == null) {
			model.addAttribute("client", new Client());
			model.addAttribute("address", new Address());
			return "clients/edit";
		}
		return "redirect:/clients/" + client.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String clientOverview(@PathVariable long id, Model model, Principal principal) {
		Client client = clientRepository.findOne(id);
		if(client == null) {
			throw new NotFoundException("Nie można znaleść klienta z id: " + id);
		}

		if(!securityService.isAccountAuthorized(client, principal)){
			throw new UnauthorizedException("Nie posiadasz uprawnień do tego zasobu.");
		}

		List<Car> clientCars = carRepository.findByClientId(id);

		model.addAttribute("client", client);
		model.addAttribute("cars", clientCars);
		return "clients/overview";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPersonalData(@Valid @ModelAttribute Client client, BindingResult bindingResultClient,
								  @Valid @ModelAttribute Address address, BindingResult bindingResultAddress, Principal principal) {
		if(bindingResultAddress.hasErrors() || bindingResultClient.hasErrors()){
			return "clients/edit";
		}
		Account account = accountRepository.findOneByEmail(principal.getName());
		addressRepository.save(address);
		client.setAddress(address);
		client.setAccount(account);
		clientRepository.save(client);

		return "redirect:/clients/" + client.getId();
	}

}