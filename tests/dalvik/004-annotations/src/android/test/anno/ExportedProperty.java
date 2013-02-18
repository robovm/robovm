/* part of test for array problem */
package android.test.anno;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)

public @interface ExportedProperty {
    boolean resolveId() default false;
    IntToString[] mapping() default { @IntToString(from = -1, to = "-1") };
}
