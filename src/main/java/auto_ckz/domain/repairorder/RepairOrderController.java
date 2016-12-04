package auto_ckz.domain.repairorder;

import auto_ckz.domain.car.Car;
import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.repair.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/repair_orders")
public class RepairOrderController {
	@Autowired
	private RepairOrderRepository repairOrderRepository;

	@Autowired
	private RepairRepository repairRepository;

	//TODO: check if User accessing this page is the owner of a car
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String repairOrderOverview(@PathVariable long id, Model model) {
		RepairOrder repairOrder = repairOrderRepository.findOne(id);
		Car car = repairOrder.getCar();
		List<Repair> repairs = repairRepository.findByRepairOrderId(id);

		model.addAttribute("repairOrder", repairOrder);
		model.addAttribute("car", car);
		model.addAttribute("repairs", repairs);
		return "repair_orders/summary";
	}

}