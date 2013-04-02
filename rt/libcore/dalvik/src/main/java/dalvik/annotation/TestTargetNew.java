/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dalvik.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines an annotation used be used within the TestInfo annotation. It
 * specifies a single method target for the test (but can be used multiple
 * times).
 * @hide
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface TestTargetNew {

    /**
     * Specifies the name of the API method that is being tested. This field
     * may be left empty if the test target is a concept implemented in a
     * class rather than a concrete API method.
     */
    String method() default "";

    /**
     * Specifies the signature of the API method that is being tested, in terms
     * of Java classes.
     */
    Class<?>[] args() default {};

    /**
     * Specifies the class to which the tested method belongs. If this value is
     * not provided, the class identified in @TestTargetClass is used by the
     * test progress doclet.
     */
    Class<?> clazz() default void.class;

    /**
     * Specifies the level of coverage the tested API method has.
     */
    TestLevel level();

    /**
     * Specifies noteworthy plain-text information about the test, for example
     * if something is NOT covered by the test method.
     */
    String notes() default "";
}
