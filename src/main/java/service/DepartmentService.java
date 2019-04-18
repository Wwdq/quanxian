package service;

import common.LevelUtil;
import dao.DepartmentMapper;
import exception.ParamException;
import lombok.Getter;
import lombok.Setter;
import model.Department;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

@Getter
@Setter
@Service
public class DepartmentService {
    @Resource
    private  DepartmentMapper depMapper;
    Logger logger = Logger.getLogger(DepartmentService.class);
//    定义处理新增部门的函数
    public void insert(Department dp) {
        logger.debug("执行dep service方法" +dp);
        logger.debug(depMapper);
        if(checkExist(dp.getId(),dp.getpId(),dp.getName()))
            throw new ParamException("部门已经存在");
      dp.setLevel(LevelUtil.getLevel(getLevel(dp.getpId()),dp.getpId()));
//        TODO
        dp.setOperator("insert");
        dp.setOperatorIp("127.0.0.1");
        dp.setOperatorTime(new Date(System.currentTimeMillis()));
        logger.debug("参数设置完毕");
        depMapper.insertSelective(dp);
    }
    public void updateDep(Department dp) {
        logger.debug("执行dep更新 service方法" +dp);
        if(checkExist(dp.getId(),dp.getpId(),dp.getName()))
            throw new ParamException("部门已经存在");
        dp.setLevel(LevelUtil.getLevel(getLevel(dp.getpId()),dp.getpId()));
//        TODO
        dp.setOperator("insert");
        dp.setOperatorIp("127.0.0.1");
        dp.setOperatorTime(new Date(System.currentTimeMillis()));
        logger.debug("参数设置完毕");
        depMapper.insertSelective(dp);
    }
    private void updateChild(Department be,Department af){
        depMapper.update(af);
        if(!be.getLevel().equals(af.getLevel())){
            List<Department> list = depMapper.selectByLevel(be);
        }
    }

  //定义检验函数
    private boolean checkExist(Integer id, Integer getpId, String name) {

        return false;
    }
//    通过id值获得level
    private  String getLevel(Integer id){
        if(id==null)
            return null;
        logger.debug("执行 获得level方法 id值为：" + id);
        Department dp=depMapper.selectByPrimaryKey(id);
        logger.debug(dp+"  获得dep等级完毕");
        return dp.getLevel();
    }
}
