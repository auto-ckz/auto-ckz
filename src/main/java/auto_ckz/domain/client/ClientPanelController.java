package auto_ckz.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/clients")
public class ClientPanelController {

	@Autowired
	private ClientRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public String clients(Model model) {
		// TODO: pagination
		model.addAttribute("clients", repository.findAll());
		return "admin/clients/all";
	}

	@RequestMapping(value= "add", method = RequestMethod.GET)
	public String addClient(Model model) {
		model.addAttribute("client", new Client());
		return "admin/clients/add";
	}

	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public String detailsClient(@PathVariable long id, Model model) {
		model.addAttribute("client", repository.findOne(id));
		return "admin/clients/details";
	}

	@RequestMapping(value= "/{id}/edit", method = RequestMethod.GET)
	public String editClient(@PathVariable long id, Model model) {
		model.addAttribute("client", repository.findOne(id));
		return "admin/clients/edit";
	}

}