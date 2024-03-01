package top.bytesc.crudstart.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data //lombok
public class Category {
    private Integer id;
    @NotNull
    @NotEmpty
    private String categoryName;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
