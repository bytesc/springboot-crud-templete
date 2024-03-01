package top.bytesc.crudstart.services;

import org.springframework.stereotype.Service;
import top.bytesc.crudstart.models.Category;

@Service
public interface CategoryService {
    public void add(Category category);
}
