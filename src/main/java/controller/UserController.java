package controller;

import common.JsonData;
import model.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/main")
    public String main(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize, HttpServletRequest request){
        Map<String ,Integer>map=new HashMap<>();
        int pageStart=(pageNo-1)*pageSize;
        map.put("start", pageStart);
        map.put("size", pageSize);
        List<User> users = userService.selectUser(map);
        request.setAttribute("users", users);
        request.setAttribute("pageNo", pageNo);
        int count =userService.selectCount();
        int pageMax=count%pageSize==0?count/pageSize:count/pageSize+1;
        request.setAttribute("pageMax", pageMax);
        return "user/user";
    }


}
