package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.DO.EHR;
import pojo.DO.User;
import service.DService;
import service.PService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ConsultController {
    @Resource
    private PService pServiceImpl;
    @Resource
    private DService dServiceImpl;

    @RequestMapping("consult")
    @ResponseBody()
    public List<EHR> consult(HttpServletRequest req) {
        User user = ((User) req.getSession().getAttribute("user"));

        //return ehrList for doctor's view
        List<EHR> ehrList = pServiceImpl.consult_P(user.getUname(), user.getPassword());
        System.out.println(ehrList);
        return ehrList;
    }

    @RequestMapping("ehr")
    public String getEHR(@RequestBody EHR ehr, HttpServletRequest req) {
        User user = ((User) req.getSession().getAttribute("user"));

        dServiceImpl.createEHR(user.getUname(), ehr);

        System.out.println(ehr);
        return "forward:code?code=1";
    }

}
