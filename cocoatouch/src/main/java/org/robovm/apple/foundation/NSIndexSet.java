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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSIndexSet/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSIndexSetPtr extends Ptr<NSIndexSet, NSIndexSetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSIndexSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSIndexSet() {}
    protected NSIndexSet(SkipInit skipInit) { super(skipInit); }
    public NSIndexSet(@ByVal NSRange range) { super((SkipInit) null); initObject(initWithIndexesInRange$(range)); }
    public NSIndexSet(NSIndexSet indexSet) { super((SkipInit) null); initObject(initWithIndexSet$(indexSet)); }
    public NSIndexSet(@MachineSizedUInt long value) { super((SkipInit) null); initObject(initWithIndex$(value)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithIndexesInRange:")
    protected native @Pointer long initWithIndexesInRange$(@ByVal NSRange range);
    @Method(selector = "initWithIndexSet:")
    protected native @Pointer long initWithIndexSet$(NSIndexSet indexSet);
    @Method(selector = "initWithIndex:")
    protected native @Pointer long initWithIndex$(@MachineSizedUInt long value);
    @Method(selector = "isEqualToIndexSet:")
    public native boolean isEqualToIndexSet$(NSIndexSet indexSet);
    @Method(selector = "count")
    public native @MachineSizedUInt long count();
    @Method(selector = "firstIndex")
    public native @MachineSizedUInt long firstIndex();
    @Method(selector = "lastIndex")
    public native @MachineSizedUInt long lastIndex();
    @Method(selector = "indexGreaterThanIndex:")
    public native @MachineSizedUInt long indexGreaterThanIndex$(@MachineSizedUInt long value);
    @Method(selector = "indexLessThanIndex:")
    public native @MachineSizedUInt long indexLessThanIndex$(@MachineSizedUInt long value);
    @Method(selector = "indexGreaterThanOrEqualToIndex:")
    public native @MachineSizedUInt long indexGreaterThanOrEqualToIndex$(@MachineSizedUInt long value);
    @Method(selector = "indexLessThanOrEqualToIndex:")
    public native @MachineSizedUInt long indexLessThanOrEqualToIndex$(@MachineSizedUInt long value);
    @Method(selector = "getIndexes:maxCount:inIndexRange:")
    public native @MachineSizedUInt long getIndexes$maxCount$inIndexRange$(MachineSizedUIntPtr indexBuffer, @MachineSizedUInt long bufferSize, NSRange range);
    @Method(selector = "countOfIndexesInRange:")
    public native @MachineSizedUInt long countOfIndexesInRange$(@ByVal NSRange range);
    @Method(selector = "containsIndex:")
    public native boolean containsIndex$(@MachineSizedUInt long value);
    @Method(selector = "containsIndexesInRange:")
    public native boolean containsIndexesInRange$(@ByVal NSRange range);
    @Method(selector = "containsIndexes:")
    public native boolean containsIndexes$(NSIndexSet indexSet);
    @Method(selector = "intersectsIndexesInRange:")
    public native boolean intersectsIndexesInRange$(@ByVal NSRange range);
    @Method(selector = "enumerateIndexesUsingBlock:")
    public native void enumerateIndexesUsingBlock$(ObjCBlock block);
    @Method(selector = "enumerateIndexesWithOptions:usingBlock:")
    public native void enumerateIndexesWithOptions$usingBlock$(NSEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "enumerateIndexesInRange:options:usingBlock:")
    public native void enumerateIndexesInRange$options$usingBlock$(@ByVal NSRange range, NSEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "indexPassingTest:")
    public native @MachineSizedUInt long indexPassingTest$(ObjCBlock predicate);
    @Method(selector = "indexWithOptions:passingTest:")
    public native @MachineSizedUInt long indexWithOptions$passingTest$(NSEnumerationOptions opts, ObjCBlock predicate);
    @Method(selector = "indexInRange:options:passingTest:")
    public native @MachineSizedUInt long indexInRange$options$passingTest$(@ByVal NSRange range, NSEnumerationOptions opts, ObjCBlock predicate);
    @Method(selector = "indexesPassingTest:")
    public native NSIndexSet indexesPassingTest$(ObjCBlock predicate);
    @Method(selector = "indexesWithOptions:passingTest:")
    public native NSIndexSet indexesWithOptions$passingTest$(NSEnumerationOptions opts, ObjCBlock predicate);
    @Method(selector = "indexesInRange:options:passingTest:")
    public native NSIndexSet indexesInRange$options$passingTest$(@ByVal NSRange range, NSEnumerationOptions opts, ObjCBlock predicate);
    @Method(selector = "enumerateRangesUsingBlock:")
    public native void enumerateRangesUsingBlock$(ObjCBlock block);
    @Method(selector = "enumerateRangesWithOptions:usingBlock:")
    public native void enumerateRangesWithOptions$usingBlock$(NSEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "enumerateRangesInRange:options:usingBlock:")
    public native void enumerateRangesInRange$options$usingBlock$(@ByVal NSRange range, NSEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "indexSet")
    public static native NSIndexSet indexSet();
    @Method(selector = "indexSetWithIndex:")
    public static native NSIndexSet indexSetWithIndex$(@MachineSizedUInt long value);
    @Method(selector = "indexSetWithIndexesInRange:")
    public static native NSIndexSet indexSetWithIndexesInRange$(@ByVal NSRange range);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
