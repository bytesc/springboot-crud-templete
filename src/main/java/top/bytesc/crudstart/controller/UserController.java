package top.bytesc.crudstart.controller;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.bytesc.crudstart.services.models.Result;
import top.bytesc.crudstart.services.models.User;
import top.bytesc.crudstart.services.UserService;
import top.bytesc.crudstart.utils.JwtUtil;
import top.bytesc.crudstart.utils.Md5Util;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

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

    @GetMapping("/info")
    public Result<User> userInfo(@RequestHeader(name="token") String token ){
//        Map<String,Object> map = JwtUtil.parseToken(token);
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findUserByName(username);
        return Result.success(user);
    }

}
