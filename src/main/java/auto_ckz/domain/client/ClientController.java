package auto_ckz.domain.client;

import auto_ckz.domain.car.Car;
import auto_ckz.domain.car.CarRepository;
import auto_ckz.site.error.NotFoundException;
import auto_ckz.site.error.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ClientSecurityService securityService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String clientOverview(@PathVariable long id, Model model, Principal principal) {
		Client client = clientRepository.findOne(id);
		if(client == null) {
			throw new NotFoundException("Nie można znaleść klienta z id: " + id);
		}

		if(!securityService.isAccountAuthorized(client, principal)){
			throw new UnauthorizedException("Nie posiadasz uprawnień do tego zasobu.");
		}

		List<Car> clientCars = carRepository.findByClient(id);

		model.addAttribute("client", client);
		model.addAttribute("cars", clientCars);
		return "clients/overview";
	}

	//TODO: move exception handler to superclass
	@ExceptionHandler(NotFoundException.class)
	public String handleNotFoundException(final NotFoundException ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		return "error/general";
	}

}