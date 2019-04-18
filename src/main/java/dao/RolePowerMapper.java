package dao;

import model.RolePower;

public interface RolePowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePower record);

    int insertSelective(RolePower record);

    RolePower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePower record);

    int updateByPrimaryKey(RolePower record);
}