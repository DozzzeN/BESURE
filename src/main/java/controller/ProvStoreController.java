package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.DO.User;
import service.DService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProvStoreController {
    @Resource
    private DService dServiceImpl;

    @RequestMapping("provStore")
    public String provStore(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String idP = ((User) req.getSession().getAttribute("user")).getUname();
        String txContent = dServiceImpl.outsource(idP);

        session.setAttribute("txContent", txContent);
        if (txContent != null) {
            return "forward:code?code=1";
        } else {
            return "forward:code?code=0";
        }
    }

    @RequestMapping("afterProvStore")
    public String afterProvStore(HttpServletRequest req) {
        String idP = ((User) req.getSession().getAttribute("user")).getUname();
        String blockHash = req.getParameter("blockHash");
        dServiceImpl.sendBlockHash(idP, blockHash);
        return "forward:code?code=1";
    }
}
