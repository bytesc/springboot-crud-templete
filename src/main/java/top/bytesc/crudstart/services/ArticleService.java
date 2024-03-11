package top.bytesc.crudstart.services;

import top.bytesc.crudstart.models.Article;
import top.bytesc.crudstart.models.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
