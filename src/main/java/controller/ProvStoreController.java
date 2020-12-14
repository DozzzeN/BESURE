package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.DO.User;
import service.DService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Controller
public class ProvStoreController {
    @Resource
    private DService dServiceImpl;

    @RequestMapping("provStore")
    @ResponseBody
    public String provStore(HttpServletRequest req) {
        String idP = ((User) req.getSession().getAttribute("user")).getUname();
        byte[] txContent = dServiceImpl.outsource(idP);
        String txContentStr = "";
        try {
            //需要编码，否则前端报错
            txContentStr = new String(Base64.getEncoder().encode(txContent), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return txContentStr;
    }

    @RequestMapping("afterProvStore")
    public String afterProvStore(HttpServletRequest req) {
        String idP = ((User) req.getSession().getAttribute("user")).getUname();
        String blockHash = req.getParameter("blockHash");
        dServiceImpl.sendBlockHash(idP, blockHash);
        return "forward:code?code=1";
    }
}
