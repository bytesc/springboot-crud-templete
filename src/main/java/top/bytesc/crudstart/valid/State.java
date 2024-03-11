package top.bytesc.crudstart.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    // 校验失败信息
    String message() default "state can only be draft / published / locked";
    // 指定分组
    Class<?>[] groups() default {};
    //负载
    Class<? extends Payload>[] payload() default {};

}
