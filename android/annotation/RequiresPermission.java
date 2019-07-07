package android.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(SOURCE)
@Target({ANNOTATION_TYPE,METHOD,CONSTRUCTOR,FIELD,PARAMETER})
public @interface RequiresPermission {
    String value() default "";

    String[] allOf() default {};

    String[] anyOf() default {};

    boolean conditional() default false;

    @Target({FIELD, METHOD, PARAMETER})
    @interface Read {
        RequiresPermission value() default @RequiresPermission;
    }

    @Target({FIELD, METHOD, PARAMETER})
    @interface Write {
        RequiresPermission value() default @RequiresPermission;
    }
}
