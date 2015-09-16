/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.modelio;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/MDLMeshBufferType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Vertex(1L),
    Index(2L);
    /*</values>*/

    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<MDLMeshBufferType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<MDLMeshBufferType> list = new ArrayList<>();
            for (NSNumber n : o) {
                list.add(MDLMeshBufferType.valueOf(n.longValue()));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MDLMeshBufferType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSNumber> array = new NSMutableArray<>();
            for (MDLMeshBufferType i : l) {
                array.add(NSNumber.valueOf(i.value()));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/MDLMeshBufferType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/MDLMeshBufferType/*</name>*/ valueOf(long n) {
        for (/*<name>*/MDLMeshBufferType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/MDLMeshBufferType/*</name>*/.class.getName());
    }
}
