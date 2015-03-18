package guda.task.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by well on 2014/12/23.
 */
@Controller
public class IndexAction {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defalut(
            ModelMap modelMap) {
        return "redirect:/login.htm";
    }
}
