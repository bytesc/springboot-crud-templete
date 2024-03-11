package top.bytesc.crudstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.bytesc.crudstart.models.Article;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.services.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
//    @GetMapping("/list")
//    public Result<String> list(){
//        return Result.success("all art");
//    }
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

    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
}
