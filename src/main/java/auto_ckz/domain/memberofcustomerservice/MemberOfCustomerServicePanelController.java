package auto_ckz.domain.memberofcustomerservice;


import auto_ckz.site.error.NotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/employees/customer_service")
public class MemberOfCustomerServicePanelController {

    @Autowired
    private MemberOfCustomerServiceRepository memberOfCustomerServiceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String membersOfCustomerService(Model model) {
        model.addAttribute("membersOfCustomerService", Lists.newArrayList(memberOfCustomerServiceRepository.findAll()));
        return "admin/employees/customer_service/all";
    }

    @RequestMapping(value= "add", method = RequestMethod.GET)
    public String addMemberOfCustomerService(Model model) {
        model.addAttribute("memberOfCustomerService", new MemberOfCustomerService());
        return "admin/employees/customer_service/add";
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public String detailsMemberOfCustomerService(@PathVariable long id, Model model) {
        MemberOfCustomerService memberOfCustomerService = memberOfCustomerServiceRepository.findOne(id);
        if(memberOfCustomerService == null) {
            throw new NotFoundException("Can't find member of customer service with given id: " + id);
        }
        model.addAttribute("memberOfCustomerService", memberOfCustomerService);
        return "admin/employees/customer_service/details";
    }

    @RequestMapping(value= "/{id}/edit", method = RequestMethod.GET)
    public String editMemberOfCustomerService(@PathVariable long id, Model model) {
        MemberOfCustomerService memberOfCustomerService = memberOfCustomerServiceRepository.findOne(id);
        if(memberOfCustomerService == null) {
            throw new NotFoundException("Can't find member of customer service with given id: " + id);
        }
        model.addAttribute("memberOfCustomerService", memberOfCustomerService);
        return "admin/employees/customer_service/edit";
    }

    @RequestMapping(value= "/{id}/delete", method = RequestMethod.GET)
    public String deleteMemberOfCustomerService(@PathVariable long id, Model model) {
        MemberOfCustomerService memberOfCustomerService = memberOfCustomerServiceRepository.findOne(id);
        if(memberOfCustomerService == null) {
            throw new NotFoundException("Can't find member of customer service with given id: " + id);
        }
        model.addAttribute("memberOfCustomerService", memberOfCustomerService);
        return "admin/employees/customer_service/delete";
    }
}
