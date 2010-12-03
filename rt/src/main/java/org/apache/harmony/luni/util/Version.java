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

package org.apache.harmony.luni.util;

import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.jar.*;
import java.io.IOException;

class Version {
    /*
     * Display VM and runtime version information
     */
    private static void displayVMVersion() {
        String version = System.getProperty("java.version");
        if (version != null) System.out.println("java version \"" + version + "\"");

        String name = System.getProperty("java.runtime.name");
        version = System.getProperty("java.runtime.version");

        if (name != null) {
            if (version != null) name = name + " (" + version + ")";
            System.out.println(name);
        }

        name = System.getProperty("java.vm.name");
        version = System.getProperty("java.vm.version");
        if (name != null) {
            if (version != null) name = name + " (" + version + ")";
            System.out.println(name);
        }

        name = System.getProperty("java.fullversion");
        if (name != null) System.out.println(name);
    }

    /*
     * Display extended class library version information
     */
    private static void displayClasslibVersion() {
        // Get the bootclasspath and tokenise for each jar file
        String bootclasspath = System.getProperty("org.apache.harmony.boot.class.path");
        if (bootclasspath == null) return;

        StringTokenizer tokenizer = new StringTokenizer(bootclasspath, System.getProperty("path.separator"));

        while (tokenizer.hasMoreTokens()) {
            String jarPath = tokenizer.nextToken();

            // If the current path is not a jar file, then continue iteration through tokens
            if (!jarPath.endsWith(".jar")) continue;

            // Get the jar manifest and find it's name and version info
            JarFile jarFile;
            Manifest manifest;
            try {
                jarFile = new JarFile(jarPath);
                manifest = jarFile.getManifest();
            } catch (IOException e) {
                // We have hit an exception - just carry onto the next jar file
                continue;
            }

            // Get the manifest attributes and output those we are interested in
            Attributes attributes = manifest.getMainAttributes();
            if (attributes == null) continue;

            String bundleName = attributes.getValue("Bundle-Name");
            if (bundleName == null) continue;
            String bundleVersion = attributes.getValue("Bundle-Version");
            if (bundleVersion == null) continue;

            System.out.println(jarPath + " " + bundleName + " " + bundleVersion);
        }

    }

    public static void version(String versionOpt) {
        if (versionOpt.equals("-version") || versionOpt.equals("-showversion")) {
            displayVMVersion();
        } else if (versionOpt.equals("-version:extended") || versionOpt.equals("-showversion:extended")) {
            displayVMVersion();
            displayClasslibVersion();
        } else {
            System.out.println("Option " + versionOpt + " unrecognised - please use -version, -showversion, -version:extended or -showversion:extended");
        }
    }
}
