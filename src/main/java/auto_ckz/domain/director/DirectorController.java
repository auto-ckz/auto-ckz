package auto_ckz.domain.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adminPanel")
public class DirectorController {

    private DirectorRepository directorRepository;

    @Autowired
    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @RequestMapping(value = "clientPanel", method = RequestMethod.GET)
    public String clientPanel() {
        return "/adminPanel/clientPanel";
    }
    @RequestMapping(value = "employeePanel", method = RequestMethod.GET)
    public String employeePanel() {
        return "/adminPanel/employeePanel";
    }
    @RequestMapping(value = "summaryPanel", method = RequestMethod.GET)
    public String summaryPanel() {
        return "/adminPanel/summaryPanel";
    }

}
