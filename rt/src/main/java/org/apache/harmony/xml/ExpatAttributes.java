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

package org.apache.harmony.xml;

import org.xml.sax.Attributes;

/**
 * Wraps native attribute array.
 */
abstract class ExpatAttributes implements Attributes {

    /**
     * Since we don't do validation, pretty much everything is CDATA type.
     */
    private static final String CDATA = "CDATA";

    /**
     * Gets the number of attributes.
     */
    public abstract int getLength();

    /**
     * Gets the pointer to the parser. We need this so we can get to the
     * interned string pool.
     */
    abstract int getParserPointer();

    /**
     * Gets the pointer to the underlying attribute array. Can be 0 if the
     * length is 0.
     */
    public abstract int getPointer();

    public String getURI(int index) {
        if (index < 0 || index >= getLength()) {
            return null;
        }
        return getURI(getParserPointer(), getPointer(), index);
    }

    public String getLocalName(int index) {
        return (index < 0 || index >= getLength())
                ? null
                : getLocalName(getParserPointer(), getPointer(), index);
    }

    public String getQName(int index) {
        return (index < 0 || index >= getLength())
                ? null
                : getQName(getParserPointer(), getPointer(), index);
    }

    public String getType(int index) {
        return (index < 0 || index >= getLength()) ? null : CDATA;
    }

    public String getValue(int index) {
        return (index < 0 || index >= getLength())
                ? null
                : getValueByIndex(getPointer(), index);
    }

    public int getIndex(String uri, String localName) {
        if (uri == null) {
            throw new NullPointerException("uri");
        }
        if (localName == null) {
            throw new NullPointerException("local name");
        }
        int pointer = getPointer();
        if (pointer == 0) {
            return -1;
        }
        return getIndex(pointer, uri, localName);
    }

    public int getIndex(String qName) {
        if (qName == null) {
            throw new NullPointerException("uri");
        }
        int pointer = getPointer();
        if (pointer == 0) {
            return -1;
        }
        return getIndexForQName(pointer, qName);
    }

    public String getType(String uri, String localName) {
        if (uri == null) {
            throw new NullPointerException("uri");
        }
        if (localName == null) {
            throw new NullPointerException("local name");
        }
        return getIndex(uri, localName) == -1 ? null : CDATA;
    }

    public String getType(String qName) {
        return getIndex(qName) == -1 ? null : CDATA;
    }

    public String getValue(String uri, String localName) {
        if (uri == null) {
            throw new NullPointerException("uri");
        }
        if (localName == null) {
            throw new NullPointerException("local name");
        }
        int pointer = getPointer();
        if (pointer == 0) {
            return null;
        }
        return getValue(pointer, uri, localName);
    }

    public String getValue(String qName) {
        if (qName == null) {
            throw new NullPointerException("qName");
        }
        int pointer = getPointer();
        if (pointer == 0) {
            return null;
        }
        return getValueForQName(pointer, qName);
    }

    private static native String getURI(int pointer, int attributePointer, int index);
    private static native String getLocalName(int pointer, int attributePointer, int index);
    private static native String getQName(int pointer, int attributePointer, int index);
    private static native String getValueByIndex(int attributePointer, int index);
    private static native int getIndex(int attributePointer, String uri, String localName);
    private static native int getIndexForQName(int attributePointer, String qName);
    private static native String getValue(int attributePointer, String uri, String localName);
    private static native String getValueForQName(int attributePointer, String qName);
    protected native void freeAttributes(int pointer);
}
