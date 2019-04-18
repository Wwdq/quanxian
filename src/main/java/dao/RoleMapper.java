package dao;

import model.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}