package auto_ckz.domain.client;

import auto_ckz.domain.car.Car;
import auto_ckz.domain.car.CarRepository;
import auto_ckz.domain.repairorder.RepairOrder;
import auto_ckz.domain.repairorder.RepairOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class ClientService {

	@Autowired
	private RepairOrderRepository repairOrderRepository;

	public List<Car> getClientCars(long clientId){
		List<RepairOrder> repairOrders = repairOrderRepository.findByClientId(clientId);
		List<Car> clientCars = new ArrayList<>();
		for(RepairOrder repairOrder : repairOrders){
			clientCars.add(repairOrder.getCar());
		}
		return clientCars;
	}

}
