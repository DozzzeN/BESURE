package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.DO.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppointController {
    @Resource
    private service.PService pService;

    @RequestMapping("appoint")
    public String appoint(HttpServletRequest req) {
        String idP = ((User) req.getSession().getAttribute("user")).getUname();

        int ret = pService.authenticate(idP);
        if (ret == 1) {
            return "forward:code?code=1";
        } else {
            return "forward:code?code=0";
        }
    }
}
