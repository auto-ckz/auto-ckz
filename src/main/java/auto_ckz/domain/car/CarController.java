package auto_ckz.domain.car;

import auto_ckz.domain.repairorder.RepairOrder;
import auto_ckz.domain.repairorder.RepairOrderRepository;
import auto_ckz.site.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		/*
		Car car = carRepository.findOne(id);
		if(car == null) {
			throw new NotFoundException("Can't find car with given id: " + id);
		}
		*/
		Car testCar = new Car();
		testCar.setId(1L);
		testCar.setMake("Polonez");
		testCar.setModel("Caro");
		testCar.setVin("1234567890123456789");

		RepairOrder testRepairOrder = new RepairOrder();
		testRepairOrder.setId(1L);
		testRepairOrder.setCar(testCar);
		testRepairOrder.setTotalCost(new BigDecimal(1500));
<<<<<<< HEAD
		testRepairOrder.setDescription("test");
=======
>>>>>>> fa049aa... Refactoring, add CarController

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date parsedDate = format.parse("3-12-2016");
			testRepairOrder.setDate(new java.sql.Date(parsedDate.getTime()));
		}
		catch (ParseException e){
			e.printStackTrace();
		}

		//List<RepairOrder> repairOrders = repairOrderRepository.findByCarId(id);
		List<RepairOrder> repairOrders = new ArrayList<>();
		repairOrders.add(testRepairOrder);

		model.addAttribute("car", testCar);
		model.addAttribute("repairOrders", repairOrders);
		return "cars/repair_history";
	}

}