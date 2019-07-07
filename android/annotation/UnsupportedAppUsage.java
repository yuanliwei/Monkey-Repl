package android.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD, FIELD, TYPE })
@Repeatable(UnsupportedAppUsage.Container.class)
public @interface UnsupportedAppUsage {
    long trackingBug() default 0;

    int maxTargetSdk() default Integer.MAX_VALUE;

    String expectedSignature() default "";

    String implicitMember() default "";

    String publicAlternatives() default "";

    @Retention(CLASS)
    @Target(TYPE)
    @interface Container {
        UnsupportedAppUsage[] value();
    }
}