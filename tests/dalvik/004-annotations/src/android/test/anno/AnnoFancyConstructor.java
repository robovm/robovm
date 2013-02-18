package android.test.anno;

import java.lang.annotation.*;

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)

public @interface AnnoFancyConstructor {
    public int numArgs() default 0;
}
