package auto_ckz.domain.car;

import auto_ckz.domain.repairorder.RepairOrder;
import auto_ckz.domain.repairorder.RepairOrderRepository;
import auto_ckz.site.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private RepairOrderRepository repairOrderRepository;

	//TODO: check if User accessing this page is the owner of a car
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String carOverview(@PathVariable long id, Model model) {

		Car car = carRepository.findOne(id);
		if(car == null) {
			throw new NotFoundException("Can't find car with given id: " + id);
		}

		List<RepairOrder> repairOrders = repairOrderRepository.findByCarId(id);

		model.addAttribute("car", car);
		model.addAttribute("repairOrders", repairOrders);
		return "cars/repair_history";
	}

}