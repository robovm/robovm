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

/**
* @author Vladimir N. Molotkov, Stepan M. Mishura
*/

package org.apache.harmony.security.asn1;

import java.io.IOException;

/**
 * Thrown by decoder/encoder stream to indicate violation of encoding rules.
 */

public class ASN1Exception extends IOException {

    /**
     * @serial
     */
    private static final long serialVersionUID = -3561981263989123987L;

    /**
     * Constructs an ASN1Exception without a message. 
     */
    public ASN1Exception(){
    }

    /**
     * Constructs an ASN1Exception with a message. 
     * 
     * @param message - a string that describes encoding violation 
     */
    public ASN1Exception(String message){
        super(message);
    }
}
