/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.luni.tests.java.net;

import java.io.IOException;
import java.net.ContentHandler;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;

public class ContentHandlerTest extends TestCase {

    /**
     * java.net.ContentHandler#getContent(java.net.URLConnection,
     *        java.lang.Class[])
     */
    public void test_getContent() throws IOException {
        URLConnection conn = new URL("http://www.apache.org").openConnection();
        Class[] classes = { Foo.class, String.class, };
        ContentHandler handler = new ContentHandlerImpl();
        ((ContentHandlerImpl) handler).setContent(new Foo());
        Object content = handler.getContent(conn, classes);
        assertEquals("Foo", ((Foo) content).getFoo());

        ((ContentHandlerImpl) handler).setContent(new FooSub());
        content = handler.getContent(conn, classes);
        assertEquals("FooSub", ((Foo) content).getFoo());

        Class[] classes2 = { FooSub.class, String.class, };
        ((ContentHandlerImpl) handler).setContent(new Foo());
        content = handler.getContent(conn, classes2);
        assertNull(content);
    }
}

class ContentHandlerImpl extends ContentHandler {

    private Object content;

    @Override
    public Object getContent(URLConnection uConn) throws IOException {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

class Foo {
    public String getFoo() {
        return "Foo";
    }
}

class FooSub extends Foo {
    public String getFoo() {
        return "FooSub";
    }
}
