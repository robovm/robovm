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

import org.apache.harmony.security.internal.nls.Messages;


/**
 * This abstract class represents ASN.1 type that is a collection of ASN.1 types.
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public abstract class ASN1TypeCollection extends ASN1Constructured {

    public final ASN1Type[] type; //TODO comment me

    public final boolean[] OPTIONAL; //TODO comment me

    public final Object[] DEFAULT; //TODO comment me

    /**
     * Constructs ASN.1 collection type.
     * 
     * @param tagNumber - ASN.1 tag number
     * @param type - a collection of one or more ASN.1 types. 
     * @throws IllegalArgumentException - if tagNumber is invalid
     */
    public ASN1TypeCollection(int tagNumber, ASN1Type[] type) {
        super(tagNumber);
        // FIXME what about empty sequence?
        //        if (type.length == 0) {
        //            throw new ASN1Exception("ASN1 collection type: "
        //                    + getClass().getName()
        //                    + " MUST have at least one component");
        //        }

        this.type = type;
        this.OPTIONAL = new boolean[type.length];
        this.DEFAULT = new Object[type.length];
    }

    /**
     * Sets a collection component as optional
     *
     * @param index - an index of a component
     */
    protected final void setOptional(int index) {
        OPTIONAL[index] = true;
    }

    /**
     * Sets a default value for a collection component.
     * The component also became an optional component.
     *
     * @param object - a component's default value
     * @param index - an index of a component
     */
    protected final void setDefault(Object object, int index) {
        OPTIONAL[index] = true;
        DEFAULT[index] = object;
    }

    /**
     * Provides an object's values to be encoded
     * 
     * Derived classes should override this method to provide
     * encoding for a selected class of objects. 
     * 
     * The default implementation throws RuntimeException.
     *
     * @param object - an object to be encoded
     * @param values - an array to store an object's values to be encoded
     */
    protected void getValues(Object object, Object[] values) {
        throw new RuntimeException(Messages.getString("security.101", getClass().getName())); //$NON-NLS-1$
    }
}