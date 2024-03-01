package top.bytesc.crudstart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data //lombok getter，setter
public class User {
    @NotNull
    private Integer id;
    @NotEmpty
    @Pattern(regexp="^\\S{1,10}$")
    private String username;
    @JsonIgnore  //转换为json字符串时候忽略pwd
    private String password;
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
