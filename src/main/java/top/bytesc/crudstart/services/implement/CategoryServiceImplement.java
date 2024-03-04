package top.bytesc.crudstart.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bytesc.crudstart.models.Category;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.services.CategoryService;
import top.bytesc.crudstart.services.mapper.CategoryMapper;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category){
        Map<String, Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Integer) map.get("id"));
        categoryMapper.add(category);
    }

    public List<Category> list(){
        Map<String, Object> map = ThreadLocalUtil.get();
        return categoryMapper.list((Integer) map.get("id"));
    }

    @Override
    public Category findCategoryById(Integer CategoryId) {
        return categoryMapper.findById(CategoryId);
    }
}
