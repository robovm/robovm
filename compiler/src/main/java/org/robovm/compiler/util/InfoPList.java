/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.compiler.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.XMLPropertyListParser;

public class InfoPList {
    private File file;
    private NSDictionary dictionary;

    public InfoPList(File file) {
        this.file = file;
    }

    public void parse(Properties props) {
        parse(props, true);
    }

    public void parse(Properties props, boolean includeSystemProperties) {
        try {
            this.dictionary = (NSDictionary) parsePropertyList(file, props, includeSystemProperties);
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
    
    private final static Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{([^}]+)\\}");

    static void replacePropertyRefs(Node node, Properties props) {
        if (node instanceof Text) {
            Text el = (Text) node;
            String value = el.getNodeValue();
            if (value != null && value.trim().length() > 0) {
                Matcher matcher = VARIABLE_PATTERN.matcher(value);
                StringBuilder sb = new StringBuilder();
                int pos = 0;
                while (matcher.find()) {
                    if (pos < matcher.start()) {
                        sb.append(value.substring(pos, matcher.start()));
                    }
                    String key = matcher.group(1);
                    sb.append(props.getProperty(key, matcher.group()));
                    pos = matcher.end();
                }
                if (pos < value.length()) {
                    sb.append(value.substring(pos));
                }
                el.setNodeValue(sb.toString());
            }
        }
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            replacePropertyRefs(children.item(i), props);
        }
    }

    static NSObject parsePropertyList(File file, Properties props, boolean includeSystemProperties) throws Exception {
        Properties allProps = new Properties(includeSystemProperties ? System.getProperties() : new Properties());
        allProps.putAll(props);

        Method getDocBuilder = XMLPropertyListParser.class.getDeclaredMethod("getDocBuilder");
        getDocBuilder.setAccessible(true);
        Method parseDocument = XMLPropertyListParser.class.getDeclaredMethod("parseDocument", Document.class);
        parseDocument.setAccessible(true);
        DocumentBuilder docBuilder = (DocumentBuilder) getDocBuilder.invoke(null);
        Document doc = docBuilder.parse(file);
        replacePropertyRefs(doc, allProps);
        return (NSObject) parseDocument.invoke(null, doc);
    }
}
