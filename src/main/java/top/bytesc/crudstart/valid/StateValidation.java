package top.bytesc.crudstart.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class StateValidation implements ConstraintValidator<State, String> {
    public boolean isValid(String value, ConstraintValidatorContext context){
        if (value==null) return true;
        if(value.equals("draft") || value.equals("published") ||value.equals("locked")){
            System.out.println(value);
            return true;
        }
        else return false;
    }

}
