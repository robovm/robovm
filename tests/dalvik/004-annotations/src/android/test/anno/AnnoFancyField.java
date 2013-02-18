package android.test.anno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited      // should have no effect
@Documented

public @interface AnnoFancyField {
    public String nombre() default "no se";
}
