package top.bytesc.crudstart.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import top.bytesc.crudstart.valid.State;

import java.time.LocalDateTime;

@Data //lombok
public class Article {
    private Integer id;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,100}$")
    private String title;
    @NotEmpty
    private String content;
    private String coverImg;
    @State
    private String state;
    @NotNull
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
