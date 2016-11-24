package auto_ckz.domain.client;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.MessageFormat;

@Controller
@RequestMapping("/admin/clients")
public class ClientPanelController {

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
	public String detailsClient(@PathVariable long id, Model model) {
		Client client = repository.findOne(id);
		if(client == null) {
			String message = MessageFormat.format("Can't find client with given id: {0}", id );
			model.addAttribute("errorMessage", message);
			return "error/general";
		}

		model.addAttribute("client", client);
		return "admin/clients/details";
	}

	@RequestMapping(value= "/{id}/edit", method = RequestMethod.GET)
	public String editClient(@PathVariable long id, Model model) {
		Client client = repository.findOne(id);
		if(client == null) {
			String message = MessageFormat.format("Can't find client with given id: {0}", id );
			model.addAttribute("errorMessage", message);
			return "error/general";
		}

		model.addAttribute("client", client);
		return "admin/clients/edit";
	}

	@RequestMapping(value= "/{id}/delete", method = RequestMethod.GET)
	public String deleteClient(@PathVariable long id, Model model) {
		Client client = repository.findOne(id);
		if(client == null) {
			String message = MessageFormat.format("Can't find client with given id: {0}", id );
			model.addAttribute("errorMessage", message);
			return "error/general";
		}

		model.addAttribute("client", client);
		return "admin/clients/delete";
	}

}
