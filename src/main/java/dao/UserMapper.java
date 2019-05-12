package dao;

import model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Delete("delete from user where id=#{0}")
    int deleteByPrimaryKey(Integer id);
    @Insert("insert into user (username,password,telephone,creattime) values(#{username},#{password},#{telephone},#{creattime})")
    int insert(User user);

    int insertSelective(User record);

    User selectUser(User user);

    int updateByPrimaryKeySelective(User record);
     @Update("update user set username=#{username} ,telephone=#{telephone} where  id=#{id}")
    int updateByPrimaryKey(User record);

    List<User> selectAll(Map<String, Integer> map);
    @Select("select count(*) from user")
    int selectCount();
     @Select("select * from user where username=#{0}")
    User selectByName(String username);
    @Select("select * from user where id=#{0}")
     User selectById(int parseInt);

    void insertUserRole(Map<String, Object> map);

    void deleteUserRole(Map<String, Object> map);
}