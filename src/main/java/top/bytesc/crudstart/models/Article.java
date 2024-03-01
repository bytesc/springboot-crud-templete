package top.bytesc.crudstart.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data //lombok
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String coverImg;
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
