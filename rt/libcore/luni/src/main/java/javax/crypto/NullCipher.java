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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package javax.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import org.apache.harmony.crypto.internal.NullCipherSpi;


/**
 * This class provides an identity cipher that does not transform the input data
 * in any way. The <i>encrypted</i> data is identical to the <i>plain text</i>.
 */
public class NullCipher extends Cipher {

    /**
     * Creates a new {@code NullCipher} instance.
     */
    public NullCipher() {
        super(new NullCipherSpi(), null, null);
        try {
            this.init(Cipher.ENCRYPT_MODE, (Key)null, (SecureRandom)null);
        } catch (InvalidKeyException e) {
        }
    }

}
