/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.prefs;

/**
 * This interface is used by the {@link Preferences} class as factory class to
 * create {@code Preferences} instances. This interface can be implemented and
 * installed to replace the default preferences implementation.
 *
 * @see java.util.prefs.Preferences
 *
 * @since 1.4
 */
public interface PreferencesFactory {
    /**
     * Returns the root node of the preferences hierarchy for the calling user
     * context.
     *
     * @return the user preferences hierarchy root node.
     */
    Preferences userRoot();

    /**
     * Returns the root node of the system preferences hierarchy.
     *
     * @return the system preferences hierarchy root node.
     */
    Preferences systemRoot();
}
