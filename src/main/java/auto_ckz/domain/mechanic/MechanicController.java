package auto_ckz.domain.mechanic;

import auto_ckz.common.enums.RepairStatusWrapper;
import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.repair.RepairRepository;
import auto_ckz.domain.repairorder.RepairOrderRepository;
import auto_ckz.site.error.NotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/mechanic")
public class MechanicController {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private RepairRepository repairRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String mechanicPanel(Model model) {
        model.addAttribute("repairOrders", Lists.newArrayList(repairOrderRepository.findAll()));
        return "/mechanic/panel";
    }

    @RequestMapping(value= "/repair_order/{id}", method = RequestMethod.GET)
    public String chooseRepair(@PathVariable long id, Model model) throws NotFoundException {
        List<Repair> repairList = repairRepository.findByRepairOrderId(id);
        if(repairList == null) {
            throw new NotFoundException("Can't find repair with given id: " + id);
        }
        model.addAttribute("repairStatus", RepairStatusWrapper.instance);
        model.addAttribute("repairs", repairList);
        return "/mechanic/repair";
    }

    @RequestMapping(value= "/repair_order/{id}", method = RequestMethod.GET,  params={"repairid"})
    public String chooseRepair(@PathVariable long id, @RequestParam("repairid") long repairid, RedirectAttributes redirectAttrs) throws NotFoundException {
        Repair repair = repairRepository.findOne(repairid);
        List<Repair> repairList = repairRepository.findByRepairOrderId(id);
        if(repair == null || repairList == null) {
            throw new NotFoundException("Can't find repair or repairOrder");
        }
        redirectAttrs.addFlashAttribute("repair", repair);
        redirectAttrs.addFlashAttribute("repairs", repairList);
        redirectAttrs.addFlashAttribute("repairStatus", RepairStatusWrapper.instance);
        return "redirect:/mechanic/repair_order/" + id;
    }
}
