package top.bytesc.crudstart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bytesc.crudstart.models.Result;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("all art");
    }
//    public Result<String> list(@RequestHeader String token, HttpServletResponse res){
//        try{
//            Map<String,Object> claims = JwtUtil.parseToken(token);
//        }catch (Exception e){
//            res.setStatus(401);
//            return Result.error("未登录");
//        }
//
//        return Result.success("all art");
//    }
}
