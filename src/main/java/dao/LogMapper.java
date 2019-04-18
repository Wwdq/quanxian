package dao;

import model.Log;

public interface LogMapper {
    int insert(Log record);

    int insertSelective(Log record);
}