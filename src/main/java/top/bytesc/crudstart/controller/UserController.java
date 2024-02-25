package top.bytesc.crudstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.models.User;
import top.bytesc.crudstart.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String pwd){
        // 查询用户
        User user = userService.findUserByName(username);
        //添加
        if(user==null){
            userService.addUser(username,pwd);
            return Result.success();
        }else{
            return Result.error("username had been used");
        }
    }
}
