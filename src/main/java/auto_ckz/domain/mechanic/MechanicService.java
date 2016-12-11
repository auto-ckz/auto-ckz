package auto_ckz.domain.mechanic;

import auto_ckz.common.enums.RepairStatus;
import auto_ckz.domain.car.Car;
import auto_ckz.domain.car.CarRepository;
import auto_ckz.domain.client.Client;
import auto_ckz.domain.client.ClientRepository;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerService;
import auto_ckz.domain.memberofcustomerservice.MemberOfCustomerServiceRepository;
import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.repair.RepairRepository;
import auto_ckz.domain.repairorder.RepairOrder;
import auto_ckz.domain.repairorder.RepairOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Date;

@Service
@Transactional
public class MechanicService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MemberOfCustomerServiceRepository memberOfCustomerServiceRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private RepairRepository repairRepository;

    @PostConstruct
    protected void initialize() {
        Client client = new Client("Marek", "Kowal", "525343544", "93435443645");
        MemberOfCustomerService memberOfCustomerService = new MemberOfCustomerService("Karol", "Nowak", "663545654", "92051603293");
        Car car = new Car("Audi", "A3", 2000, "GA544N3", "65454643657849587", new Date(16), "wykupione", true, 200000, "1600", "100", "Pertrol");
        Mechanic mechanic = new Mechanic("Marek", "Kowal", "525343544", "96546534654");
        RepairOrder repairOrder = new RepairOrder(new Date(15), new BigDecimal(460), client, memberOfCustomerService, car);
        Repair repair = new Repair("wymiana tarcz", RepairStatus.IN_PROGRESS,"należy wymienić również klocki hamulcowe", repairOrder, mechanic);
        Repair repair2 = new Repair("wymiana opon", RepairStatus.NOT_STARTED,"tylna prawa opona popękana", repairOrder, mechanic);
        Repair repair3 = new Repair("wymiana oleju", RepairStatus.SUSPENDED,"niewielki wyciek oleju", repairOrder, mechanic);
        clientRepository.save(client);
        memberOfCustomerServiceRepository.save(memberOfCustomerService);
        carRepository.save(car);
        mechanicRepository.save(mechanic);
        repairOrderRepository.save(repairOrder);
        repairRepository.save(repair);
        repairRepository.save(repair2);
        repairRepository.save(repair3);
    }
}
