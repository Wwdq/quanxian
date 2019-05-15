package service;

import dao.RoleMapper;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> selectRole(Map<String, Integer> map) {
        return roleMapper.selectList(map);
    }

    public int selectCount() {
        return roleMapper.selectCount();
    }

    public Role selectByName(String name) {
        return roleMapper.selectByName(name);

    }

    public void insertRole(Role role) {
        roleMapper.insert(role);
    }

    public Role selectById(int parseInt) {
        return roleMapper.selectById(parseInt);
    }

    public void update(Role role) {
        roleMapper.update(role);
    }

    public void delete(int parseInt) {
        roleMapper.delete(parseInt);
    }

    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    public List<Integer> selectByUserId(int userId) {
        return roleMapper.selectByUserID(userId);
    }

    public void updateRolePower(Map<String, Object> map) {
        roleMapper.insertRolePower(map);
    }

    public void deleteRolePower(Integer rid) {
        roleMapper.deleteRolePower(rid);
    }
}
