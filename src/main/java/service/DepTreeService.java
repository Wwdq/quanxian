package service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import common.LevelUtil;
import dao.DepartmentMapper;
import lombok.Getter;
import lombok.Setter;
import model.DepTree;
import model.Department;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Getter
@Setter
@Service
public class DepTreeService {
    Logger logger = Logger.getLogger(DepTreeService.class);
    @Resource
    private DepartmentMapper depMapper;
    public List<DepTree> DepToTree(){
        logger.debug("进入dep树");
        logger.debug(depMapper);
        List<Department> list = depMapper.selectAll();
        List<DepTree> tree=new ArrayList<>();
        logger.debug(tree);
        for(Department dp:list){
            DepTree dt=DepTree.adapt(dp);
            tree.add(dt);
        }
        return toTree(tree);
    }

    private List<DepTree> toTree(List<DepTree> tree) {
        if(tree.isEmpty())
            return new ArrayList<>();
         Multimap<String ,DepTree> map= ArrayListMultimap.create();
        List<DepTree> root= new ArrayList<>();
        for(DepTree dt:tree){
            map.put(dt.getLevel(), dt);
            if(dt.getLevel().equals("0"))
                root.add(dt);
        }
        Collections.sort(root,(a,b)->a.getSeq()-b.getSeq());
        DFS(root, "0", map);
        return root;

    }

    private void DFS(List<DepTree> root, String s, Multimap<String, DepTree> map) {
        for(int i=0;i<root.size();i++){
            DepTree depTree=root.get(i);
            String next = LevelUtil.getLevel(s, depTree.getId());
            List<DepTree> list = (List<DepTree>) map.get(next);
            if(!list.isEmpty()){
                Collections.sort(list, (a, b) -> a.getSeq() - b.getSeq());
                depTree.setList(list);
                DFS(list, next, map);
            }
        }
    }
}
