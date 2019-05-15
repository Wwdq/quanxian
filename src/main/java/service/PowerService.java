package service;

import dao.PowerMapper;
import model.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerService {
    @Autowired
    private PowerMapper powerMapper;
    public Power  findRoot(){
        return powerMapper.selectRoot();
    }

    public List<Power> selectAll() {
        return powerMapper.selectAll();

    }

    public Power selectName(int parseInt, String name) {
        return powerMapper.selectName(parseInt, name);
    }

    public Power selectUrl(String url) {
        return powerMapper.selectUrl(url);
    }

    public void insertPower(Power power) {
        powerMapper.insert(power);
    }

    public Power selectById(int parseInt) {
        return powerMapper.selectById(parseInt);
    }

    public void updatePower(Power power) {
        powerMapper.updatePower(power);
    }

    public void delete(int parseInt) {
        powerMapper.delete(parseInt);
    }

    public List<Integer> selectPidByRid(Integer rid) {
        return  powerMapper.selectPidByRid(rid);
    }

    public List<String> selectAllUri() {
        return powerMapper.selectAllUri();
    }
}
