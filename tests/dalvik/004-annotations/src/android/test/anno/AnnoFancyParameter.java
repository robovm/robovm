package android.test.anno;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

public @interface AnnoFancyParameter {
    double factor();
}
