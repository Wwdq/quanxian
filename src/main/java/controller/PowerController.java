package controller;

import common.JsonData;
import dao.PowerMapper;
import model.Power;
import org.apache.ibatis.annotations.Insert;
import org.omg.CORBA.PolicyError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.PowerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/power")
public class PowerController {

    @Autowired
    private PowerService powerService;
    @RequestMapping("/main")
    public String main(){
        return "power/main";
    }
    @RequestMapping("/loadData")
    @ResponseBody
    public Object loadData(){
        List<Power> list=new ArrayList<>();

        List<Power> all =powerService.selectAll();
        Map<Integer,Power> map=new HashMap<>();
        for(Power power:all){
            map.put(power.getId(), power);
        }
        for(Power power:all){
            if(power.getPid()==0)
                list.add(power);
            else {
                map.get((power.getPid())).getChildren().add(power);
            }

        }
        return list;
    }
    @RequestMapping("/loadAssignData")
    @ResponseBody
    public Object loadAssignData(Integer rid){
        List<Power> list=new ArrayList<>();
        List<Integer> powers = powerService.selectPidByRid(rid);
        List<Power> all =powerService.selectAll();
        Map<Integer,Power> map=new HashMap<>();
        for(Power power:all){

            if(powers.contains(power.getId())){
                power.setChecked(true);
            }
            map.put(power.getId(), power);
        }
        for(Power power:all){
            if(power.getPid()==0)
                list.add(power);
            else {
                map.get((power.getPid())).getChildren().add(power);
            }

        }
        return list;
    }
    @ResponseBody
    @RequestMapping("/select")
    public JsonData selectName(String id,String name){
        Power power=powerService.selectName(Integer.parseInt(id), name);
        System.out.println(id);
        if(power!=null)
            return JsonData.fail("已经存在");
        else
            return JsonData.success();
    }
    @ResponseBody
    @RequestMapping("/selectUrl")
    public JsonData selectName(String url){
        Power power=powerService.selectUrl(url);
        if(power!=null)
            return JsonData.fail("已经存在");
        else
            return JsonData.success();
    }
    @RequestMapping("/insert")
    @ResponseBody
    public JsonData insert(Power power){
        powerService.insertPower(power);
        return JsonData.success();
    }

    @RequestMapping("/add")

    public String add(String id){
        System.out.println(id);
        return "power/add";
    }
    @RequestMapping("/update")

    public String update(String id, HttpServletRequest req){
        Power power=powerService.selectById(Integer.parseInt(id));
        req.setAttribute("power", power);
        return "power/update";
    }
    @RequestMapping("/updatePower")
    @ResponseBody
    public JsonData  updatePower(Power power){
        try{
            powerService.updatePower(power);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.fail("失败");
        }
        return JsonData.success();
    }
    @RequestMapping("/delete")
    public void delete(String id, HttpServletRequest req, HttpServletResponse re) throws ServletException, IOException {
        powerService.delete(Integer.parseInt(id));
        req.getRequestDispatcher("/power/main").forward(req, re);
        return ;
    }
}
