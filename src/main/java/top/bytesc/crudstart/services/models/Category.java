package top.bytesc.crudstart.services.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data //lombok
public class Category {
    private Integer id;
    private String categoryName;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
