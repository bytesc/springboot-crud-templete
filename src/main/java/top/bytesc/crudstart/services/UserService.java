package top.bytesc.crudstart.services;

import top.bytesc.crudstart.models.User;

public interface UserService {

    User findUserByName(String username);

    void addUser(String username, String pwd);

    void update(User user);

    void updateUserPic(String url);

    void updatePwd(String newPwd);
}
