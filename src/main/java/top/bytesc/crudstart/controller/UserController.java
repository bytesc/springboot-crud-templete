package top.bytesc.crudstart.controller;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.models.User;
import top.bytesc.crudstart.services.UserService;
import top.bytesc.crudstart.utils.JwtUtil;
import top.bytesc.crudstart.utils.Md5Util;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result add(@Pattern(regexp="^\\S{5,16}$") String username,
                      @Pattern(regexp="^\\S{5,16}$") String pwd){
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

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp="^\\S{5,16}$") String username,
                                @Pattern(regexp="^\\S{5,16}$") String pwd){
        // 查询用户
        User user = userService.findUserByName(username);
        if(user==null){
            return Result.error("username does not exist");
        }else if(Md5Util.getMD5String(pwd).equals(user.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }else{
            return Result.error("pwd wrong");
        }
    }
}
