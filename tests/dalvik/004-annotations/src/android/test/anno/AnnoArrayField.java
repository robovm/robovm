package android.test.anno;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoArrayField {
    boolean[] zz() default {};
    byte[] bb() default {};
    char[] cc() default {'a', 'b'};
    short[] ss() default {};
    int[] ii() default {};
    float[] ff() default {3.141592654f};
    long[] jj() default {};
    double[] dd() default {0.987654321};
    String[] str() default {};
}
