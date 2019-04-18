package service;

import dao.UserMapper;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    Logger logger = Logger.getLogger(UserService.class);
    public User doLogin(User user){
        User user1 = userMapper.selectUser(user);
    logger.debug("数据查到了" +  user1);
         return user1;
    }

    public List<User> selectUser(Map<String, Integer> map) {
        List<User> list = userMapper.selectAll(map);
        return list;
    }

    public int selectCount() {
        return userMapper.selectCount();
    }
}
