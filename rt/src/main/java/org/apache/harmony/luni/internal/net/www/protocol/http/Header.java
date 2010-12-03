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

package org.apache.harmony.luni.internal.net.www.protocol.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The general structure for request / response header. It is essentially
 * constructed by hashtable with key indexed in a vector for position lookup.
 */
public class Header implements Cloneable {
    /*
     * we use the non-synchronized ArrayList and TreehMap instead of the
     * synchronized Vector and Hashtable
     */
    private ArrayList<String> props;

    private SortedMap<String, LinkedList<String>> keyTable;

    private String statusLine;

    /**
     * A generic header structure. Used mostly for request / response header.
     * The key/value pair of the header may be inserted for later use. The key
     * is stored in an array for indexed slot access.
     */
    public Header() {
        super();
        this.props = new ArrayList<String>(20);
        this.keyTable = new TreeMap<String, LinkedList<String>>(
                                String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * The alternative constructor which sets the input map as its initial
     * keyTable.
     * 
     * @param map
     *            the initial keyTable as a map
     */
    public Header(Map<String, List<String>> map) {
        this(); // initialize fields
        for (Entry<String, List<String>> next : map.entrySet()) {
            String key = next.getKey();
            List<String> value = next.getValue();
            LinkedList<String> linkedList = new LinkedList<String>();
            for (String element : value) {
                linkedList.add(element);
                props.add(key);
                props.add(element);
            }
            keyTable.put(key, linkedList);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        try {
            Header clone = (Header) super.clone();
            clone.props = (ArrayList<String>) props.clone();
            clone.keyTable = new TreeMap<String, LinkedList<String>>(
                                     String.CASE_INSENSITIVE_ORDER);
            for (Map.Entry<String, LinkedList<String>> next : this.keyTable
                    .entrySet()) {
                LinkedList<String> v = (LinkedList<String>) next.getValue()
                        .clone();
                clone.keyTable.put(next.getKey(), v);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Add a field with the specified value.
     * 
     * @param key
     * @param value
     */
    public void add(String key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList<String> list = keyTable.get(key);
        if (list == null) {
            list = new LinkedList<String>();
            keyTable.put(key, list);
        }
        list.add(value);
        props.add(key);
        props.add(value);
    }

    /**
     * Set a field with the specified value. If the field is not found, it is
     * added. If the field is found, the existing value(s) are overwritten.
     * 
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList<String> list = keyTable.get(key);
        if (list == null) {
            add(key, value);
        } else {
            list.clear();
            list.add(value);
            for (int i = 0; i < props.size(); i += 2) {
                String propKey = props.get(i);
                if (propKey != null && key.equals(propKey)) {
                    props.set(i + 1, value);
                }
            }
        }
    }

    /**
     * Provides an unmodifiable map with all String header names mapped to their
     * String values. The map keys are Strings and the values are unmodifiable
     * Lists of Strings.
     * 
     * @return an unmodifiable map of the headers
     * 
     * @since 1.4
     */
    public Map<String, List<String>> getFieldMap() {
        Map<String, List<String>> result = new HashMap<String, List<String>>(
                keyTable.size());
        for (Map.Entry<String, LinkedList<String>> next : keyTable.entrySet()) {
            List<String> v = next.getValue();
            result.put(next.getKey(), Collections.unmodifiableList(v));
        }
        return Collections.unmodifiableMap(result);
    }

    /**
     * Answers the element at <code>pos</code>, null if no such element
     * exist.
     * 
     * @return java.lang.String the value of the key
     * @param pos
     *            int the position to look for
     */
    public String get(int pos) {
        if (pos >= 0 && pos < props.size() / 2) {
            return props.get(pos * 2 + 1);
        }
        return null;
    }

    /**
     * Answers the key of this header at <code>pos</code>, null if there are
     * fewer keys in the header
     * 
     * 
     * @return the key the desired position
     * @param pos
     *            the position to look for
     */
    public String getKey(int pos) {
        if (pos >= 0 && pos < props.size() / 2) {
            return props.get(pos * 2);
        }
        return null;
    }

    /**
     * Answers the value corresponding to the specified key.
     * 
     * @param key
     *            the key to look up.
     * @return Answers the value for the given key, or <code>null</code> if no
     *         such key exists.
     */
    public String get(String key) {
        LinkedList<String> result = keyTable.get(key);
        if (result == null) {
            return null;
        }
        return result.getLast();
    }

    /**
     * Answers the number of keys stored in this header
     * 
     * @return the number of keys.
     */
    public int length() {
        return props.size() / 2;
    }

    /**
     * Sets the status line in the header request example: GET / HTTP/1.1
     * response example: HTTP/1.1 200 OK
     * 
     * @param statusLine
     */
    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
        /*
         * we add the status line to the list of headers so that it is
         * accessible from java.net.HttpURLConnection.getResponseCode() which
         * calls
         * org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnection.getHeaderField(0)
         * to get it
         */
        props.add(0, null);
        props.add(1, statusLine);
    }

    /**
     * Gets the status line in the header request example: GET / HTTP/1.1
     * response example: HTTP/1.1 200 OK
     * 
     * @return the status line
     */
    public String getStatusLine() {
        return statusLine;
    }
}
