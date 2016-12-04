package auto_ckz.domain.client;

import auto_ckz.site.error.NotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/clients")
public class ClientAdministrationController {

	@Autowired
	private ClientRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public String clients(Model model) {
		// TODO: pagination
		model.addAttribute("clients", Lists.newArrayList(repository.findAll()));
		return "admin/clients/all";
	}

	@RequestMapping(value= "add", method = RequestMethod.GET)
	public String addClient(Model model) {
		model.addAttribute("client", new Client());
		return "admin/clients/add";
	}

	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public String detailsClient(@PathVariable long id, Model model) throws NotFoundException {
		Client client = repository.findOne(id);
		if(client == null) {
			throw new NotFoundException("Can't find client with given id: " + id);
		}

		model.addAttribute("client", client);
		return "admin/clients/details";
	}

	@RequestMapping(value= "/{id}/edit", method = RequestMethod.GET)
	public String editClient(@PathVariable long id, Model model) throws NotFoundException {
		Client client = repository.findOne(id);
		if(client == null) {
			throw new NotFoundException("Can't find client with given id: " + id);
		}

		model.addAttribute("client", client);
		return "admin/clients/edit";
	}

	@RequestMapping(value= "/{id}/delete", method = RequestMethod.GET)
	public String deleteClient(@PathVariable long id, Model model) throws NotFoundException {
		Client client = repository.findOne(id);
		if(client == null) {
			throw new NotFoundException("Can't find client with given id: " + id);
		}

		model.addAttribute("client", client);
		return "admin/clients/delete";
	}

}
