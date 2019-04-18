package dao;

import model.Power;

public interface PowerMapper {
    int insert(Power record);

    int insertSelective(Power record);
}