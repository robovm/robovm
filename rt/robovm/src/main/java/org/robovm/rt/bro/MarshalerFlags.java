/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.rt.bro;

import org.omg.CORBA.StructMember;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Callback;

/**
 * Flags passed to a marshaler.
 */
public class MarshalerFlags {

    // Bit 1-2: The type of call marshaled for
    /**
     * Tells the marshaler that a {@link Bridge} call is being marshaled.
     */
    public static final long CALL_TYPE_BRIDGE               = 0 << 0;
    /**
     * Tells the marshaler that a {@link Callback} call is being marshaled.
     */
    public static final long CALL_TYPE_CALLBACK             = 1 << 0;
    /**
     * Tells the marshaler that a {@link StructMember} call is being marshaled.
     */
    public static final long CALL_TYPE_STRUCT_MEMBER        = 2 << 0;
    /**
     * Tells the marshaler that a {@link Ptr} to something is being marshaled.
     */
    public static final long CALL_TYPE_PTR                  = 3 << 0;
    /**
     * ANDing flags with this value gets the call type.
     */
    public static final long CALL_TYPE_MASK                 = 3 << 0;
    
}
