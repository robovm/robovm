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

package javax.net.ssl;

public class SSLParameters {

    protected String[] enabledCipherSuites;

    private String[] enabledProtocols;

    private boolean need_client_auth;

    private boolean want_client_auth;

    public SSLParameters() {
        enabledCipherSuites = null;
        enabledProtocols = null;
        need_client_auth = false;
        want_client_auth = false;
    };

    public SSLParameters(String[] cipherSuites) {
        this.enabledCipherSuites = cipherSuites;
    }

    public SSLParameters(String[] cipherSuites, String[] protocols) {
        // input maybe is null
        this.enabledCipherSuites = cipherSuites;
        this.enabledCipherSuites = protocols;
    }

    public String[] getCipherSuites() {
        if (enabledCipherSuites == null)
            return null;
        return this.enabledCipherSuites.clone();
    }

    public boolean getNeedClientAuth() {
        return this.need_client_auth;
    }

    public String[] getProtocols() {
        if (enabledProtocols == null)
            return null;
        else
            return enabledProtocols.clone();
    }

    public boolean getWantClientAuth() {
        return this.want_client_auth;
    }

    public void setCipherSuites(String[] cipherSuites) {
        this.enabledCipherSuites = cipherSuites.clone();
    }

    public void setNeedClientAuth(boolean needClientAuth) {
        this.need_client_auth = needClientAuth;
    }

    public void setProtocols(String[] protocols) {

        this.enabledProtocols = protocols.clone();
    }

    public void setWantClientAuth(boolean wantClientAuth) {
        this.want_client_auth = wantClientAuth;
    }
}
