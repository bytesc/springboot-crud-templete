package top.bytesc.crudstart.services.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data //lombok getter，setter
public class User {
    private Integer id;
    private String username;
    @JsonIgnore  //转换为json字符串时候忽略pwd
    private String password;
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
