package auto_ckz.domain.mechanic;

import auto_ckz.domain.repair.Repair;
import auto_ckz.domain.repair.RepairRepository;
import auto_ckz.domain.repairorder.RepairOrder;
import auto_ckz.domain.repairorder.RepairOrderRepository;
import auto_ckz.site.error.NotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/mechanics")
public class MechanicController {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private RepairRepository repairRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String mechanicPanel(Model model) {
        List<RepairOrder> repairOrders = Lists.newArrayList(repairOrderRepository.findAll());
        model.addAttribute("repairOrders", repairOrders);
        if(repairOrders.isEmpty()) {
            throw new NotFoundException("Nie znaleziono żadnego zamówienia: ");
        }
        return "/mechanics/panel";
    }

    @RequestMapping(value= "/repair_order/{id}", method = RequestMethod.GET)
    public String chooseRepair(@PathVariable long id, Model model) throws NotFoundException {
        List<Repair> repairList = repairRepository.findByRepairOrderId(id);
        if(repairList == null) {
            throw new NotFoundException("Nie można znaleźć naprawy z id: " + id);
        }
        model.addAttribute("repairs", repairList);
        return "/mechanics/repair";
    }

    @RequestMapping(value= "/repair_order/{id}", method = RequestMethod.GET,  params={"repairid"})
    public String chooseRepair(@PathVariable long id, @RequestParam("repairid") long repairid, RedirectAttributes redirectAttrs) throws NotFoundException {
        Repair repair = repairRepository.findOne(repairid);
        List<Repair> repairList = repairRepository.findByRepairOrderId(id);
        if(repair == null) {
            throw new NotFoundException("Nie można znaleźć zamówienia z id: " + repairid);
        }
        if(repairList == null){
            throw new NotFoundException("Nie można znaleźć naprawy z id: " + id);
        }
        redirectAttrs.addFlashAttribute("repair", repair);
        redirectAttrs.addFlashAttribute("repairs", repairList);
        return "redirect:/mechanics/repair_order/" + id;
    }

    //TODO: move exception handler to superclass
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(final NotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/general";
    }
}
