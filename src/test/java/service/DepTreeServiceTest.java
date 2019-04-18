package service;
import model.DepTree;
import model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc.xml","classpath:ApplicationContext.xml"})
@WebAppConfiguration("src/main/resources")
public class DepTreeServiceTest {
    @Resource
    private  DepTreeService service;
    @Test
    public void test(){
        Department dp=new Department();
        dp.setName("技术部");
        dp.setId(10);
        DepTree dt = DepTree.adapt(dp);
        System.out.println(dp);
        System.out.println(dt.getName());
    }
    @Test
    public void test1(){
        List<DepTree> list=service.DepToTree();
        System.out.println(list);


    }
}