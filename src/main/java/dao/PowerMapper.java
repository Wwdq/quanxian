package dao;

import model.Power;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface PowerMapper {
    @Insert("insert into power (name,url,pid) values (#{name},#{url},#{pid})")
    int insert(Power record);

    int insertSelective(Power record);
    @Select("select * from power where pid=0")
    Power selectRoot();
   @Select("select *from power")
    List<Power> selectAll();
   @Select("select * from power where pid=#{param1} and name =#{param2}")
    Power selectName(int parseInt, String name);
   @Select("select * from power  where  url=#{0}")
    Power selectUrl(String url);
    @Select("select * from power where id = #{0}")
    Power selectById(int parseInt);

    @Update("update  power set name=#{name},url=#{url}  where  id=#{id}")

    void updatePower(Power power);
    @Delete("delete from power where  id=#{0}")
    void delete(int parseInt);
    @Select("select pid from rolepower where rid=#{0}")
    List<Integer> selectPidByRid(Integer rid);
    @Select("select url from power ")
    List<String> selectAllUri();
}