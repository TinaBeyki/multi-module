package ir.ansarit.common.util.validation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckValidationLanguage {

    String message() default "";
    String lang() default "fa";

    boolean isDigitAllow() default false;
    boolean isSpecialCharacter() default false;

    //ToDo
}
