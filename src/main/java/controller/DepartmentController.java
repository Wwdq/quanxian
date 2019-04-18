package controller;

import common.JsonData;
import lombok.Getter;
import lombok.Setter;
import model.DepTree;
import model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.DepTreeService;
import service.DepartmentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/dep")
@Getter
@Setter
public class DepartmentController {
    Logger logger = Logger.getLogger(DepartmentController.class);
    @Resource
    private DepartmentService depService;
    @Resource
    private DepTreeService treeService;
    @RequestMapping("/insert")
    @ResponseBody
//    插入一个新的部门
    public JsonData insertDep(Department dp){
        logger.debug(dp +"  insert控制器接受参数");
        depService.insert(dp);
            return JsonData.success();
    }
    @RequestMapping("/page")
    public ModelAndView page(){
        return new ModelAndView("dep");
    }
    @RequestMapping("/tree")
    @ResponseBody
//    查看部门树
    private JsonData findTree(){
        List<DepTree> depTrees = treeService.DepToTree();
        System.out.println(depTrees);
        return JsonData.success(depTrees);
    }
}
