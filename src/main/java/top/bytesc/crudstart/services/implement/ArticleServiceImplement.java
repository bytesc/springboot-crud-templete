package top.bytesc.crudstart.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bytesc.crudstart.models.Article;
import top.bytesc.crudstart.services.ArticleService;
import top.bytesc.crudstart.services.mapper.ArticleMapper;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

import java.util.Map;

@Service
public class ArticleServiceImplement implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article){
        Map<String, Object> map=ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("id"));
        article.setState("draft");
        System.out.println(article);
        articleMapper.add(article);
    }
}
