package top.bytesc.crudstart.controller;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.models.User;
import top.bytesc.crudstart.services.UserService;
import top.bytesc.crudstart.utils.JwtUtil;
import top.bytesc.crudstart.utils.Md5Util;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

            //token 存入redis
            stringRedisTemplate.opsForValue();
            ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);

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

    @PostMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PostMapping("/update_user_pic")
    public Result updateUserPic(@RequestParam @URL String url){
        userService.updateUserPic(url);
        return Result.success();
    }

    @PostMapping("update_pwd")
    public Result  updatePwd(@RequestBody Map<String,String> params,
                             @RequestHeader String token){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String againPwd = params.get("again_pwd");
        if(!StringUtils.hasLength(oldPwd) ||
                !StringUtils.hasLength(newPwd) ||
                !StringUtils.hasLength(againPwd) ){
            return Result.success("密码不能为空");
        }
        if(!newPwd.equals(againPwd)){
            return Result.success("两次输入密码不一致");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findUserByName(username);
        if(user==null){
            return Result.error("username does not exist");
        }else if(Md5Util.getMD5String(oldPwd).equals(user.getPassword())){
            userService.updatePwd(newPwd);
            //删除redis里的token
            ValueOperations<String,String> operations =stringRedisTemplate.opsForValue();
            operations.getAndDelete(token);
//            operations.getOperations().delete(token);
            return Result.success();
        }else{
            return Result.error("old_pwd wrong 原密码错误");
        }
    }

}
