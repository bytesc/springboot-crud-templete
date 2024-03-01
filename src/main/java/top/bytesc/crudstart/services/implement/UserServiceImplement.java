package top.bytesc.crudstart.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bytesc.crudstart.services.mapper.UserMapper;
import top.bytesc.crudstart.models.User;
import top.bytesc.crudstart.services.UserService;
import top.bytesc.crudstart.utils.Md5Util;

import java.time.LocalDateTime;

@Service //注册对象到spring容器
public class UserServiceImplement implements UserService {
    @Autowired
    private UserMapper userMap;
    @Override
    public User findUserByName(String username) {
        return userMap.findUserByName(username);
    }

    @Override
    public void addUser(String username, String pwd) {
        String md5Pwd = Md5Util.getMD5String(pwd);
        userMap.add(username,md5Pwd);
    }

    public void update(User user){
        user.setUpdateTime(LocalDateTime.now());
        userMap.update(user);
    }
}
