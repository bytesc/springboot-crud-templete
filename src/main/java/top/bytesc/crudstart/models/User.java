package top.bytesc.crudstart.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data //lombok getter，setter
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
