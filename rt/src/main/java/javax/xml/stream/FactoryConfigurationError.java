/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: FactoryConfigurationError.java 670245 2008-06-21 18:03:15Z mrglavas $

package javax.xml.stream;

public class FactoryConfigurationError extends Error {
    
    private static final long serialVersionUID = -2994412584589975744L;
    
    private Exception nested;

    public FactoryConfigurationError() {
        super();
    }

    public FactoryConfigurationError(Exception e) {
        nested = e;
    }

    public FactoryConfigurationError(Exception e, String msg) {
        super(msg);
        nested = e;
    }

    public FactoryConfigurationError(String msg) {
        super(msg);
    }

    public FactoryConfigurationError(String msg, Exception e) {
        super(msg);
        nested = e;
    }

    public Exception getException() {
        return nested;
    }

    public String getMessage() {
        String msg = super.getMessage();
        if (msg != null) {
            return msg;
        }
        if (nested != null) {
            msg = nested.getMessage();
            if (msg == null)
                msg = nested.getClass().toString();
        }
        return msg;
    }
}