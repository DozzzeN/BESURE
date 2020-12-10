package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.DO.User;
import service.DService;
import service.PService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ConsultController {
    @Resource
    private PService pServiceImpl;
    @Resource
    private DService dServiceImpl;

    @RequestMapping("consult")
    public String consult(HttpServletRequest req) {
        User user = ((User) req.getSession().getAttribute("user"));

        pServiceImpl.consult_P(user.getPassword());
        dServiceImpl.createEHR(user.getUname());
        return "forward:code?code=1";
    }
}
