/* part of test for array problem */
package android.test.anno;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface IntToString {
    int from();
    String to();
}
