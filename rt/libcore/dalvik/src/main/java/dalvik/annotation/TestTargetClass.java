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
 * Defines an annotation for test classes that allows to link them to the class
 * that is being tested. The current assumption is that the test are somewhat
 * organized according to the API classes they test. Might be too strict for
 * some cases.
 *
 * @deprecated Obsolete
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Deprecated
public @interface TestTargetClass {

    /**
     * Specifies the class being tested.
     */
    Class<?> value();

    /**
     * Option to specify untested methods for the class.
     * @hide
     */
    TestTargetNew[] untestedMethods() default {};
}
