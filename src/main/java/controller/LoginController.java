package controller;

import common.JsonData;
import model.Power;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/doLogin")
    @ResponseBody
    public JsonData doLogin(User user, HttpSession session){
        User  us =userService.doLogin(user);
        if(us!=null) {
            session.setAttribute("user", us);
            List<Power> list=userService.selectPowerByUserId(us.getId());
            Map<Integer,Power> map=new HashMap<>();
            Power userPower=new Power();
            Set<String>userUrl=new HashSet<>();
            for(Power power:list){
                map.put(power.getId(), power);
                if(power.getUrl()!=null&& !power.getUrl().equals("")) {
                    userUrl.add(power.getUrl());
                }
            }
            for(Power power:list){
                if(power.getPid()==0)
                    userPower=power;
                else{
                    map.get(power.getPid()).getChildren().add(power);
                }
                session.setAttribute("userPower", userPower);
                session.setAttribute("userUrl", userUrl);
            }
            return JsonData.success();
        }
        else {
            return JsonData.fail("用户名与密码不匹配");
        }
    }
    @RequestMapping("/main")
    public String toMain(){
        return "main";
    }
    @RequestMapping("/doOut")
     public String doOut(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("userPower");
        session.removeAttribute("userUrl");
        return "redirect:/login";

    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
