/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.util.prefs;

/**
 * An exception to indicate that the input XML file is not well-formed or could
 * not be validated against the appropriate document type (specified by
 * in the {@code Preferences}).
 */
public class InvalidPreferencesFormatException extends Exception {

    private static final long serialVersionUID = -791715184232119669L;

    /**
     * Constructs a new {@code InvalidPreferencesFormatException} instance with
     * a detailed exception message.
     *
     * @param s
     *            the detailed exception message.
     */
    public InvalidPreferencesFormatException (String s) {
        super(s);
    }

    /**
     * Constructs a new {@code InvalidPreferencesFormatException} instance with
     * a detailed exception message and a nested {@code Throwable}.
     *
     * @param s
     *            the detailed exception message.
     * @param t
     *            the nested {@code Throwable}.
     */
    public InvalidPreferencesFormatException (String s, Throwable t) {
        super(s,t);
    }

    /**
     * Constructs a new {@code InvalidPreferencesFormatException} instance with
     * a nested {@code Throwable}.
     *
     * @param t
     *            the nested {@code Throwable}.
     */
    public InvalidPreferencesFormatException (Throwable t) {
        super(t);
    }
}
