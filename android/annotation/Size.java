package android.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@Target({PARAMETER,LOCAL_VARIABLE,METHOD,FIELD})
public @interface Size {
    long value() default -1;
    long min() default Long.MIN_VALUE;
    long max() default Long.MAX_VALUE;
    long multiple() default 1;
}