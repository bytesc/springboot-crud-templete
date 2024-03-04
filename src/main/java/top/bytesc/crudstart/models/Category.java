package top.bytesc.crudstart.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

@Data //lombok
public class Category {
    @NotNull(groups = Update.class) //分组校验
    private Integer id;
    @NotEmpty(groups = {Update.class,Add.class})
    @Pattern(regexp="^\\S{1,10}$",
            groups = {Update.class,Add.class})
    private String categoryName;
    private Integer createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 默认Default分组
    // 分组之间可以继承

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
