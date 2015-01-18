/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.foundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSKeyValueOperator.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSKeyValueOperator/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSKeyValueOperator toObject(Class<NSKeyValueOperator> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSKeyValueOperator.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSKeyValueOperator o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSKeyValueOperator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSKeyValueOperator Average = new NSKeyValueOperator("AverageValue");
    public static final NSKeyValueOperator Count = new NSKeyValueOperator("CountValue");
    public static final NSKeyValueOperator DistinctUnionOfArrays = new NSKeyValueOperator("DistinctUnionOfArraysValue");
    public static final NSKeyValueOperator DistinctUnionOfObjects = new NSKeyValueOperator("DistinctUnionOfObjectsValue");
    public static final NSKeyValueOperator DistinctUnionOfSets = new NSKeyValueOperator("DistinctUnionOfSetsValue");
    public static final NSKeyValueOperator Maximum = new NSKeyValueOperator("MaximumValue");
    public static final NSKeyValueOperator Minimum = new NSKeyValueOperator("MinimumValue");
    public static final NSKeyValueOperator Sum = new NSKeyValueOperator("SumValue");
    public static final NSKeyValueOperator UnionOfArrays = new NSKeyValueOperator("UnionOfArraysValue");
    public static final NSKeyValueOperator UnionOfObjects = new NSKeyValueOperator("UnionOfObjectsValue");
    public static final NSKeyValueOperator UnionOfSets = new NSKeyValueOperator("UnionOfSetsValue");
    
    private static NSKeyValueOperator[] values = new NSKeyValueOperator[] {Average, Count, DistinctUnionOfArrays, DistinctUnionOfObjects, 
        DistinctUnionOfSets, Maximum, Minimum, Sum, UnionOfArrays, UnionOfObjects, UnionOfSets};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSKeyValueOperator(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSKeyValueOperator valueOf(NSString value) {
        for (NSKeyValueOperator v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSKeyValueOperator/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSAverageKeyValueOperator", optional=true)
    protected static native NSString AverageValue();
    @GlobalValue(symbol="NSCountKeyValueOperator", optional=true)
    protected static native NSString CountValue();
    @GlobalValue(symbol="NSDistinctUnionOfArraysKeyValueOperator", optional=true)
    protected static native NSString DistinctUnionOfArraysValue();
    @GlobalValue(symbol="NSDistinctUnionOfObjectsKeyValueOperator", optional=true)
    protected static native NSString DistinctUnionOfObjectsValue();
    @GlobalValue(symbol="NSDistinctUnionOfSetsKeyValueOperator", optional=true)
    protected static native NSString DistinctUnionOfSetsValue();
    @GlobalValue(symbol="NSMaximumKeyValueOperator", optional=true)
    protected static native NSString MaximumValue();
    @GlobalValue(symbol="NSMinimumKeyValueOperator", optional=true)
    protected static native NSString MinimumValue();
    @GlobalValue(symbol="NSSumKeyValueOperator", optional=true)
    protected static native NSString SumValue();
    @GlobalValue(symbol="NSUnionOfArraysKeyValueOperator", optional=true)
    protected static native NSString UnionOfArraysValue();
    @GlobalValue(symbol="NSUnionOfObjectsKeyValueOperator", optional=true)
    protected static native NSString UnionOfObjectsValue();
    @GlobalValue(symbol="NSUnionOfSetsKeyValueOperator", optional=true)
    protected static native NSString UnionOfSetsValue();
    /*</methods>*/
}
