package top.bytesc.crudstart.services.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.bytesc.crudstart.models.Category;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name, create_user, create_time, update_time)"+" "+
    "values(#{categoryName}, #{createUser}, now(), now())")
    void add(Category category);
}
