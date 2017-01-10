package auto_ckz.domain.director;

import auto_ckz.common.constant.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin")
@Secured(value = Role.ROLE_DIRECTOR)
public class DirectorController {

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String directorIndex() {
        return "admin/panel";
    }

}