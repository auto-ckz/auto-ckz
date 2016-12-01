package auto_ckz.domain.mechanic;


import auto_ckz.site.error.NotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/employees/mechanics")
public class MechanicPanelController {

    @Autowired
	private MechanicRepository mechanicRepository;

    @RequestMapping(method = RequestMethod.GET)
	public String mechanics(Model model) {
        model.addAttribute("mechanics", Lists.newArrayList(mechanicRepository.findAll()));
        return "admin/employees/mechanics/all";
    }

    @RequestMapping(value= "add", method = RequestMethod.GET)
	public String addMechanic(Model model) {
        model.addAttribute("mechanic", new Mechanic());
        return "admin/employees/mechanics/add";
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public String detailsMechanic(@PathVariable long id, Model model) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        if(mechanic == null) {
            throw new NotFoundException("Can't find mechanic with given id: " + id);
        }
        model.addAttribute("mechanic", mechanic);
        return "admin/employees/mechanics/details";
    }

    @RequestMapping(value= "/{id}/edit", method = RequestMethod.GET)
	public String editMechanic(@PathVariable long id, Model model) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        if(mechanic == null) {
            throw new NotFoundException("Can't find mechanic with given id: " + id);
        }
        model.addAttribute("mechanic", mechanic);
        return "admin/employees/mechanics/edit";
    }

    @RequestMapping(value= "/{id}/delete", method = RequestMethod.GET)
	public String deleteMechanic(@PathVariable long id, Model model) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        if(mechanic == null) {
            throw new NotFoundException("Can't find mechanic with given id: " + id);
        }
        model.addAttribute("mechanic", mechanic);
        return "admin/employees/mechanics/delete";
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(final NotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/general";
    }

}
