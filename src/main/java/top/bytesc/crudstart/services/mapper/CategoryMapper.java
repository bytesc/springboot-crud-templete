package top.bytesc.crudstart.services.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.bytesc.crudstart.models.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name, create_user, create_time, update_time)"+" "+
    "values(#{categoryName}, #{createUser}, now(), now())")
    void add(Category category);

    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);

    @Select("select * from category where id=#{CategoryId}")
    Category findById(Integer CategoryId);
}
