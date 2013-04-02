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

/**
 * Defines an enumeration of possible states a test case can be in.
 *
 * @hide
 */
public enum TestLevel {

    /**
     * Indicates that a test method completely tests its target API method.
     */
    COMPLETE,

    /**
     * Indicates that a test method partially tests its target API method and
     * that together with all other {@code PARTIAL_COMPLETE} tests for the same
     * method it is sufficient.
     */
    PARTIAL_COMPLETE,

    /**
     * Just for compatibility purposes, will be removed later.
     */
    PARTIAL_OK,

    /**
     * Indicates that a test method partially tests its target API method. It
     * needs a second review phase to find out if the sum of all partial tests
     * is sufficient for completely testing the target API method. If yes, the
     * level has to be changed to {@code PARTIAL_COMPLETE}.
     */
    PARTIAL,

    /**
     * Indicates that a test method is known to not completely test an API
     * method but the missing test steps are too complex and costly to
     * implement. This level is positioned somewhere between {@code PARTIAL}
     * and {@code COMPLETE}.
     */
    SUFFICIENT,

    /**
     * Indicates that a test method provides additional testing for an API
     * method for which there already exists one {@code COMPLETE} or a set of
     * {@code PARTIAL_COMPLETE} tests. This level may also be used for test
     * methods that test a concept which can not be directly attributed to
     * the specification of an API method or class.
     */
    ADDITIONAL,

    /**
     * Indicates that there is nothing to test in an API method, for example if
     * the specification states that a method does nothing.
     */
    NOT_NECESSARY,

    /**
     * Indicates that it is very hard or impossible to test an API method.
     */
    NOT_FEASIBLE,

    /**
     * Indicates that the tests is either insufficient or wrong. Something needs
     * to be done about it.
     */
    TODO,

}
