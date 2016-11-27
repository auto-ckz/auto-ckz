package auto_ckz.domain.director;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class DirectorController {

    @RequestMapping(method = RequestMethod.GET)
    public String clientPanel() {
        return "/admin/panel";
    }
}