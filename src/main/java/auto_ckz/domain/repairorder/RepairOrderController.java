package auto_ckz.domain.repairorder;

import auto_ckz.common.enums.RepairStatus;
import auto_ckz.domain.car.Car;
import auto_ckz.domain.car.CarRepository;
import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.repair.RepairRepository;
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
@RequestMapping("/repair_orders")
public class RepairOrderController {
	@Autowired
	private RepairOrderRepository repairOrderRepository;

	@Autowired
	private RepairRepository repairRepository;

	//TODO: check if User accessing this page is the owner of a car
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String repairOrderOverview(@PathVariable long id, Model model) {
		Car testCar = new Car();
		testCar.setId(1L);
		testCar.setMake("Polonez");
		testCar.setModel("Caro");
		testCar.setVin("1234567890123456789");

		RepairOrder testRepairOrder = new RepairOrder();
		testRepairOrder.setId(1L);
		testRepairOrder.setCar(testCar);
		testRepairOrder.setTotalCost(new BigDecimal(1500));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date parsedDate = format.parse("2016-12-03");
			testRepairOrder.setDate(new java.sql.Date(parsedDate.getTime()));
		}
		catch (ParseException e){
			e.printStackTrace();
		}

		Repair testRepair = new Repair();
		testRepair.setRepairOrder(testRepairOrder);
		testRepair.setDescription("blabla");
		testRepair.setStatus(RepairStatus.DONE);


		//List<RepairOrder> repairOrders = repairOrderRepository.findByCarId(id);
		List<Repair> repairs = new ArrayList<>();
		repairs.add(testRepair);

		model.addAttribute("car", testCar);
		model.addAttribute("repairOrder", testRepairOrder);
		model.addAttribute("repairs", repairs);
		return "repair_orders/summary";
	}

}