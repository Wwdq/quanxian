package dao;

import model.PowerList;

public interface PowerListMapper {
    int insert(PowerList record);

    int insertSelective(PowerList record);
}