package top.bytesc.crudstart.services;

import top.bytesc.crudstart.services.models.User;

public interface UserService {

    User findUserByName(String username);

    void addUser(String username, String pwd);
}
