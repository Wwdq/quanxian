package controller;

import common.JsonData;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/main")
    public String main(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize, HttpServletRequest request){
        Map<String ,Integer> map=new HashMap<>();
        int pageStart=(pageNo-1)*pageSize;
        map.put("start", pageStart);
        map.put("size", pageSize);
        List<Role> roles = roleService.selectRole(map);
        request.setAttribute("roles", roles);
        request.setAttribute("pageNo", pageNo);
        int count =roleService.selectCount();
        int pageMax=count%pageSize==0?count/pageSize:count/pageSize+1;
        request.setAttribute("pageMax", pageMax);
        return "role/main";
    }

   @RequestMapping("/add")
   public String add(){
         return "role/add";
    }
    @ResponseBody
    @RequestMapping("/select")

    public JsonData selectRole(String name){
        if(roleService.selectByName(name)==null)
            return JsonData.success();
        else
            return JsonData.fail("账户存在了");
    }
    @RequestMapping("/insert")
    @ResponseBody
    public JsonData insert(Role role){

        role.setCreatetime(new Date(System.currentTimeMillis()).toString());
        roleService.insertRole(role);
        return JsonData.success();
    }
    @RequestMapping("/update")
    public String update(String id,HttpServletRequest req){
        Role role = roleService.selectById(Integer.parseInt(id));
        req.setAttribute("role", role);
        return "role/update";
    }
    @RequestMapping("/doUpdate")
    @ResponseBody
    public JsonData doUpdate(Role role){
        roleService.update(role);
        return JsonData.success();
    }
    @RequestMapping("/delete")
    public void delete(String id, HttpServletRequest req, HttpServletResponse re) throws ServletException, IOException {
        roleService.delete(Integer.parseInt(id));
        req.getRequestDispatcher("/role/main").forward(req, re);
        return ;
    }
}
