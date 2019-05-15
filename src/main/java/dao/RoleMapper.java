package dao;

import model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    @Insert("insert into role (name,createtime) values(#{name},#{createtime})")
    int insert(Role record);

    int insertSelective(Role record);
     @Select("select * from role order by  createtime desc limit #{start},#{size}")
    List<Role> selectList(Map<String, Integer> map);
    @Select("select count(*) from role")
    int selectCount();
    @Select("select * from role where name=#{0}")
    Role selectByName(String name);
    @Select("select * from role where id=#{0}")
    Role selectById(int parseInt);
    @Update("update role set name=#{name}   where  id=#{id}")
    void update(Role role);
    @Delete("delete from role where id=#{0}")
    void delete(int parseInt);

    @Select("select * from role")
    List<Role> selectAll();
    @Select("SELECT rid FROM roleuser WHERE uid=#{0};")
    List<Integer> selectByUserID(int userId);

    void insertRolePower(Map<String, Object> map);
     @Delete("delete from rolepower where rid=#{0}")
    void deleteRolePower(Integer rid);
}