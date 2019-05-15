package controller;

import common.JsonData;
import model.Role;
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
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
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

    @RequestMapping("/add")

    public String add(){
        return "user/add";
    }
    @ResponseBody
    @RequestMapping("/select")

    public JsonData selectUser(String username){
        if(userService.selectUserByName(username)==null)
            return JsonData.success();
        else
            return JsonData.fail("账户存在了");
    }
   @RequestMapping("/insert")
   @ResponseBody
    public JsonData insert(User user){
        user.setPassword("123456");
        user.setCreattime(new Date(System.currentTimeMillis()).toString());
       userService.insertUser(user);
        return JsonData.success();
   }
   @RequestMapping("/update")
   public String update(String id,HttpServletRequest req){
       User user = userService.selectUserById(Integer.parseInt(id));
       req.setAttribute("user", user);
       return "user/update";
   }
    @RequestMapping("/doUpdate")
    @ResponseBody
    public JsonData doUpdate(User user){
        userService.update(user);
        return JsonData.success();
    }
    @RequestMapping("/delete")
    public void delete(String id, HttpServletRequest req, HttpServletResponse re) throws ServletException, IOException {
        userService.delete(Integer.parseInt(id));
        req.getRequestDispatcher("/user/main").forward(req, re);
        return ;
    }
    @RequestMapping("/assign")
    public String assign(String id,HttpServletRequest req){
        List<Role> lists =roleService.selectAll();
        List<Integer> n = roleService.selectByUserId(Integer.parseInt(id));
        User user = userService.selectUserById(Integer.parseInt(id));
        req.setAttribute("user", user);

        List<Role> assignRole=new ArrayList<>();
        List<Role> unAssignRole=new ArrayList<>();
        for(Role i: lists){
              if(n.contains(i.getId()))
                  assignRole.add(i);
              else
                  unAssignRole.add(i);
        }
        req.setAttribute("assignRole", assignRole);
        req.setAttribute("unAssignRole",unAssignRole );
        return "user/assign";
    }

    @RequestMapping("/doAssign")
    @ResponseBody
    public JsonData doAssign(String userId,Integer[] unAssign){
        Map<String ,Object> map=new HashMap<>();
        map.put("userId", userId);
        map.put("unAssign", unAssign);
        userService.doUserRole(map);
        return JsonData.success();
    }
    @RequestMapping("/doUnAssign")
    @ResponseBody
    public JsonData doUnAssign(String userId,Integer[] assign){
        Map<String ,Object> map=new HashMap<>();
        map.put("userId", userId);
        map.put("assign", assign);
        userService.deleteUserRole(map);
        return JsonData.success();
    }
    @RequestMapping("/password")
    public  String toPassword(){
        return "user/password";
    }
    @RequestMapping("/passwordUpdate")
    @ResponseBody
    public  JsonData updatePassword(User user){
        try {
            userService.updatePassword(user);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.fail("失败");

        }
        return JsonData.success();
    }
}
