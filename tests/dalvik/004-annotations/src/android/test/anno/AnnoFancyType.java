package android.test.anno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface AnnoFancyType {
    public int num();
    public String name() default "unknown";
}
