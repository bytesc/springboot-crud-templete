package top.bytesc.crudstart.services.implement;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bytesc.crudstart.models.Article;
import top.bytesc.crudstart.models.PageBean;
import top.bytesc.crudstart.services.ArticleService;
import top.bytesc.crudstart.services.mapper.ArticleMapper;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

import java.util.List;
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

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state){
        PageBean<Article> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);

        Map<String, Object> map=ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        List<Article> as = articleMapper.list(categoryId,state,userId);
        Page<Article> p = (Page<Article>)as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

}
