package top.bytesc.crudstart.exception;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.bytesc.crudstart.models.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public Result handleModelViolationException( Exception e){
//        e.printStackTrace();
        return Result.error("ViolationException 实体参数校验失败 ："+e.getMessage());
    }
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public Result handleApiViolationException( Exception e){
//        e.printStackTrace();
        return Result.error("ViolationException 接口参数校验失败 ："+e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public Result handleException( Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败，没有错误信息");
    }
}
