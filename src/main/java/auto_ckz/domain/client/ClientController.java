package auto_ckz.domain.client;

import auto_ckz.domain.car.Car;
import auto_ckz.site.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ClientService clientService;

	//TODO: check if User accessing this page is the actual Client
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String clientOverview(@PathVariable long id, Model model) {
		Client client = repository.findOne(id);
		if(client == null) {
			throw new NotFoundException("Can't find client with given id: " + id);
		}

		List<Car> clientCars = clientService.getClientCars(id);

		//TODO: remove test car
		Car testCar = new Car();
		testCar.setId(1L);
		testCar.setMake("Polonez");
		testCar.setModel("Caro");
		testCar.setVin("1234567890123456789");
		clientCars.add(testCar);

		model.addAttribute("client", client);
		model.addAttribute("cars", clientCars);
		return "clients/overview";
	}

}