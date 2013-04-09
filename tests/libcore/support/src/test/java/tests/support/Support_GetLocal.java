/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package tests.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import tests.support.resource.Support_Resources;

public class Support_GetLocal {

    static Hashtable<String, File> cache = new Hashtable<String, File>(20);

    public static File getLocalFile(String url) throws IOException,
            MalformedURLException {
        url = Support_Resources.RESOURCE_PACKAGE + url;
        File temp = cache.get(url);
        if (temp == null) {
            InputStream in = Support_GetLocal.class.getResourceAsStream(url);
            temp = File.createTempFile("hyts_local", ".tmp", null);
            temp.deleteOnExit();
            FileOutputStream out = new FileOutputStream(temp);
            int result;
            byte[] buf = new byte[4096];
            while ((result = in.read(buf)) != -1) {
                out.write(buf, 0, result);
            }
            in.close();
            out.close();
            cache.put(url, temp);
        }
        return temp;
    }

    public static File getExternalLocalFile(String url) throws IOException,
            MalformedURLException {
        File temp = cache.get(url);
        if (temp == null) {
            InputStream in = new URL(url).openStream();
            temp = File.createTempFile("hyts_local", ".tmp", null);
            temp.deleteOnExit();
            FileOutputStream out = new FileOutputStream(temp);
            int result;
            byte[] buf = new byte[4096];
            while ((result = in.read(buf)) != -1) {
                out.write(buf, 0, result);
            }
            in.close();
            out.close();
            cache.put(url, temp);
        }
        return temp;
    }

    static ByteArrayInputStream getStream(String url) throws IOException,
            MalformedURLException {
        InputStream in = new URL(url).openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream(256);
        int result;
        byte[] buf = new byte[256];
        while ((result = in.read(buf)) != -1) {
            out.write(buf, 0, result);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static File createTempFile(String suffix) throws IOException {
        return File.createTempFile("hyts_", suffix, null);
    }

    public static JarURLConnection getJarURLConnection() throws IOException {
        JarURLConnection con1 = null;
        File file = getLocalFile("hyts_att.jar");
        URL fUrl1 = new URL("jar:file:" + file.getPath() + "!/");
        con1 = (JarURLConnection) fUrl1.openConnection();
        return con1;
    }
}