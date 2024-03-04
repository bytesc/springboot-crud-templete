package top.bytesc.crudstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.bytesc.crudstart.models.Category;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add")  // 分组校验
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category c = categoryService.findCategoryById(id);
        return Result.success(c);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }
}
