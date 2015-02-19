/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.compiler.target.ios;

import java.io.File;
import java.util.Properties;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;

public class InfoPList {
    private File file;
    private NSDictionary dictionary;

    public InfoPList(File file) {
        this.file = file;
    }

    public void parse(Properties props) {
        try {
            this.dictionary = (NSDictionary) IOSTarget.parsePropertyList(file, props);
        } catch (Throwable t) {
            throw new IllegalArgumentException("Failed to parse Info.plist XML file: " + file, t);
        }
    }

    public String getBundleIdentifier() {
        if (dictionary != null) {
            NSObject object = dictionary.objectForKey("CFBundleIdentifier");
            if (object != null) {
                return object.toString();
            }
        }
        return null;
    }

    public String getBundleExecutable() {
        if (dictionary != null) {
            NSObject object = dictionary.objectForKey("CFBundleExecutable");
            if (object != null) {
                return object.toString();
            }
        }
        return null;
    }

    public String getMinimumOSVersion() {
        if (dictionary != null) {
            NSObject object = dictionary.objectForKey("MinimumOSVersion");
            if (object != null) {
                return object.toString();
            }
        }
        return null;
    }

    public File getFile() {
        return file;
    }

    public NSDictionary getDictionary() {
        return dictionary;
    }
}
