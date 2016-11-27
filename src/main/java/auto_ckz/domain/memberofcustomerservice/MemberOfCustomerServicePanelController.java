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
@RequestMapping("/admin/membersOfCustomerService")
public class MemberOfCustomerServicePanelController {

    @Autowired
    private MemberOfCustomerServiceRepository memberOfCustomerServiceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String membersOfCustomerService(Model model) {
        model.addAttribute("membersOfCustomerService", Lists.newArrayList(memberOfCustomerServiceRepository.findAll()));
        return "admin/membersOfCustomerService/all";
    }

    @RequestMapping(value= "add", method = RequestMethod.GET)
    public String addMemberOfCustomerService(Model model) {
        model.addAttribute("memberOfCustomerService", new MemberOfCustomerService());
        return "admin/membersOfCustomerService/add";
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public String detailsMemberOfCustomerService(@PathVariable long id, Model model) {
        MemberOfCustomerService memberOfCustomerService = memberOfCustomerServiceRepository.findOne(id);
        if(memberOfCustomerService == null) {
            throw new NotFoundException("Can't find member of customer service with given id: {0}" + id);
        }
        model.addAttribute("memberOfCustomerService", memberOfCustomerService);
        return "admin/membersOfCustomerService/details";
    }

    @RequestMapping(value= "/{id}/edit", method = RequestMethod.GET)
    public String editMemberOfCustomerService(@PathVariable long id, Model model) {
        model.addAttribute("memberOfCustomerService", memberOfCustomerServiceRepository.findOne(id));
        MemberOfCustomerService memberOfCustomerService = memberOfCustomerServiceRepository.findOne(id);
        if(memberOfCustomerService == null) {
            throw new NotFoundException("Can't find member of customer service with given id: {0}" + id);
        }
        model.addAttribute("memberOfCustomerService", memberOfCustomerService);
        return "admin/membersOfCustomerService/edit";
    }

    @RequestMapping(value= "/{id}/delete", method = RequestMethod.GET)
    public String deleteMemberOfCustomerService(@PathVariable long id, Model model) {
        MemberOfCustomerService memberOfCustomerService = memberOfCustomerServiceRepository.findOne(id);
        if(memberOfCustomerService == null) {
            throw new NotFoundException("Can't find member of customer service with given id: {0}" + id);
        }
        model.addAttribute("memberOfCustomerService", memberOfCustomerService);
        return "admin/membersOfCustomerService/delete";
    }
}
