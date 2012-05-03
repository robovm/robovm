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
 * The default implementation of <code>PreferencesFactory</code> for the Linux
 * platform, using the file system as its back end.
 *
 * @since 1.4
 */
class FilePreferencesFactoryImpl implements PreferencesFactory {
    //  user root preferences
    private static final Preferences USER_ROOT = new FilePreferencesImpl(true);

    //  system root preferences
    private static final Preferences SYSTEM_ROOT = new FilePreferencesImpl(false);

    public FilePreferencesFactoryImpl() {
    }

    public Preferences userRoot() {
        return USER_ROOT;
    }

    public Preferences systemRoot() {
        return SYSTEM_ROOT;
    }

}
