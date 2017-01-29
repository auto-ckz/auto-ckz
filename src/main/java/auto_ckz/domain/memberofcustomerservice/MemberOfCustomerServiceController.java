package auto_ckz.domain.memberofcustomerservice;


import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.repair.RepairRepository;
import auto_ckz.domain.repairorder.RepairOrder;
import auto_ckz.domain.repairorder.RepairOrderRepository;
import auto_ckz.site.error.NotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/memberOfCustomerService")
public class MemberOfCustomerServiceController {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private RepairRepository repairRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String memberOfCustomerServicePanel(Model model) {
        List<RepairOrder> repairOrders = Lists.newArrayList(repairOrderRepository.findAll());
        model.addAttribute("repairOrders", repairOrders);
        if(repairOrders.isEmpty()) {
            throw new NotFoundException("Nie znaleziono żadnego zamówienia: ");
        }
        return "/memberOfCustomerService/panel";
    }

    @RequestMapping(value= "/repair_order/{id}", method = RequestMethod.GET)
    public String chooseRepairOrder(@PathVariable long id, Model model) throws NotFoundException {
        List<Repair> repairList = repairRepository.findByRepairOrderId(id);
        RepairOrder repairOrder = repairOrderRepository.findOne(id);
        if(repairList == null) {
            throw new NotFoundException("Nie można znaleźć napraw dla zlecenia z id: " + id);
        }
        if(repairOrder == null) {
            throw new NotFoundException("Nie znaleziono zamówienia z id: " + id);
        }
        model.addAttribute("repairList", repairList);
        model.addAttribute("repairOrder", repairOrder);
        return "/memberOfCustomerService/overview";
    }
    @RequestMapping(value= "/repair_order/{id}/edit/{id2}", method = RequestMethod.GET)
    public String editRepair(@PathVariable long id, @PathVariable long id2,  Model model) throws NotFoundException {
        Repair repair = repairRepository.findOne(id2);
        RepairOrder repairOrder = repairOrderRepository.findOne(id);
        if(repair == null) {
            throw new NotFoundException("Nie można znaleźć naprawy z id: " + id2);
        }
        if(repairOrder == null) {
            throw new NotFoundException("Nie znaleziono zamówienia z id: " + id);
        }
        model.addAttribute("repair", repair);
        model.addAttribute("repairOrder", repairOrder);
        return "/memberOfCustomerService/edit";
    }
}
