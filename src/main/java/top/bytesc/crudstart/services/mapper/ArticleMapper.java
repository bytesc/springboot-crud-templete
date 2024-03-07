package top.bytesc.crudstart.services.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.bytesc.crudstart.models.Article;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(title, content, cover_img, state, category_id, create_user, create_time, update_time)"+" "+
    "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now());")
    void add(Article article);
}
