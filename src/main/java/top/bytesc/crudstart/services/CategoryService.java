package top.bytesc.crudstart.services;

import org.springframework.stereotype.Service;
import top.bytesc.crudstart.models.Category;

import java.util.List;

@Service
public interface CategoryService {
    public void add(Category category);

    List<Category> list();

    Category findCategoryById(Integer id);

    void update(Category category);
}
