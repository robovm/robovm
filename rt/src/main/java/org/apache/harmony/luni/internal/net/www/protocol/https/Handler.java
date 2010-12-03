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

package org.apache.harmony.luni.internal.net.www.protocol.https;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * Handler for HttpsURLConnection implementation.
 */
public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        return new HttpsURLConnectionImpl(url, getDefaultPort());
    }

    @Override
    protected URLConnection openConnection(URL url, Proxy proxy)
            throws IOException {
        if ((url == null) || (proxy == null)) {
            // luni.1B=url and proxy can not be null
            throw new IllegalArgumentException(Messages.getString("luni.1B")); //$NON-NLS-1$
        }
        return new HttpsURLConnectionImpl(url, getDefaultPort(), proxy);
    }

    @Override
    protected int getDefaultPort() {
        return 443;
    }
}
