package auto_ckz.domain.repairorder;

import auto_ckz.domain.car.Car;
import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.repair.RepairRepository;
import auto_ckz.site.account.Account;
import auto_ckz.site.error.NotFoundException;
import auto_ckz.site.error.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/repair_orders")
public class RepairOrderController {
	@Autowired
	private RepairOrderRepository repairOrderRepository;

	@Autowired
	private RepairRepository repairRepository;

	@Autowired
	private RepairOrderSecurityService securityService;

	//TODO: check if User accessing this page is the owner of a car
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String repairOrderOverview(@PathVariable long id, Model model, Principal principal) {
		RepairOrder repairOrder = repairOrderRepository.findOne(id);
		if(repairOrder == null) {
			throw new NotFoundException("Nie można znaleść zlecenia naprawy z id: " + id);
		}

		if(!securityService.isAccountAuthorized(repairOrder, principal)){
			throw new UnauthorizedException("Nie posiadasz uprawnień do tego zasobu.");
		}

		model.addAttribute("repairOrder", repairOrder);
		model.addAttribute("car", repairOrder.getCar());
		model.addAttribute("repairs", repairRepository.findByRepairOrderId(id));
		return "repair_orders/summary";
	}

}