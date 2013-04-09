package org.apache.harmony.annotation.tests.java.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD})
public @interface TestAnnotation4 {
    int i() default 0;
}
