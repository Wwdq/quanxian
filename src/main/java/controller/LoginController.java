package controller;

import common.JsonData;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpSession;

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
        return "redirect:/login";

    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
