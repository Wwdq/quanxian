package dao;

import model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    int insert(Department record);
    int insertSelective(Department record);
    Department selectByPrimaryKey(Integer id);

    int update(Department dp);
    List<Department> selectAll();

    List<Department> selectByLevel(Department dp);
}