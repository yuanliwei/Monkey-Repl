package android.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@Target({ANNOTATION_TYPE})
public @interface StringDef {
    String[] prefix() default {};
    String[] suffix() default {};

    String[] value() default {};
}
