package top.bytesc.crudstart.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //lombok 构造方法
@AllArgsConstructor //lombok
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <E> Result<E> success( E data){
        return new Result<>(0,"success",data);
    }
    public static  Result success(){
        return new Result(0,"success",null);
    }

    public static  Result error(String errorMsg){
        return new Result(1,errorMsg,null);
    }
}
