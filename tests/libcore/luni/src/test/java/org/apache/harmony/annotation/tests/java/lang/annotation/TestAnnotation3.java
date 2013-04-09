package org.apache.harmony.annotation.tests.java.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD})
public @interface TestAnnotation3 {
    /*
     * only primitive type, String, Class, annotation,
     * enumeration are permitted
     * or 1-dimensional arrays thereof
     */
    boolean z() default false;
    byte b() default 0;
    short s() default 0;
    int i() default 100;
    long j() default 0;

    float f() default 0.0f;
    double d() default 0.0d;

    String aString() default "";
    Class aClazz() default Void.class;
    TestEnum1 aEnum() default TestEnum1.F;
}
