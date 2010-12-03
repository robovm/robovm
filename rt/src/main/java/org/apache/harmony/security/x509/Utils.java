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
* @author Alexander V. Esin
*/

package org.apache.harmony.security.x509;

/**
 * Text utils for processing DN string representations
 */

public class Utils {
    
    /**
     * Checks if the string is PrintableString (see X.680)
     * @param str input string
     * @return true if the string is PrintableString, false otherwise
     */
    public static boolean isPrintableString(String str) {
        for (int i= 0; i< str.length(); ++i) {
            char ch= str.charAt(i); 
            if (!(ch== 0x20 
                || ch>= 0x27 && ch<= 0x29 // '()
                || ch>= 0x2B && ch<= 0x3A // +,-./0-9:
                || ch== '=' 
                || ch== '?'
                || ch>= 'A' && ch<= 'Z'
                || ch>= 'a' && ch<= 'z')) {
                return false;
            }
        }
        return true;
    }
}    