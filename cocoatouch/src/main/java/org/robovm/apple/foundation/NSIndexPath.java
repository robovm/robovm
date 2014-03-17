/*
 * Copyright (C) 2014 Trillian AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSIndexPath/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSIndexPathPtr extends Ptr<NSIndexPath, NSIndexPathPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSIndexPath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSIndexPath() {}
    protected NSIndexPath(SkipInit skipInit) { super(skipInit); }
    public NSIndexPath(MachineSizedUIntPtr indexes, @MachineSizedUInt long length) { super((SkipInit) null); initObject(initWithIndexes$length$(indexes, length)); }
    public NSIndexPath(@MachineSizedUInt long index) { super((SkipInit) null); initObject(initWithIndex$(index)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithIndexes:length:")
    protected native @Pointer long initWithIndexes$length$(MachineSizedUIntPtr indexes, @MachineSizedUInt long length);
    @Method(selector = "initWithIndex:")
    protected native @Pointer long initWithIndex$(@MachineSizedUInt long index);
    @Method(selector = "indexPathByAddingIndex:")
    public native NSIndexPath addIndex(@MachineSizedUInt long index);
    @Method(selector = "indexPathByRemovingLastIndex")
    public native NSIndexPath removeLastIndex();
    @Method(selector = "indexAtPosition:")
    public native @MachineSizedUInt long getIndexAt(@MachineSizedUInt long position);
    @Method(selector = "length")
    public native @MachineSizedUInt long getLength();
    @Method(selector = "getIndexes:")
    public native void getIndexes$(MachineSizedUIntPtr indexes);
    @Method(selector = "compare:")
    public native NSComparisonResult compare$(NSIndexPath otherObject);
    @Method(selector = "indexPathWithIndex:")
    public static native NSIndexPath indexPathWithIndex$(@MachineSizedUInt long index);
    @Method(selector = "indexPathWithIndexes:length:")
    public static native NSIndexPath indexPathWithIndexes$length$(MachineSizedUIntPtr indexes, @MachineSizedUInt long length);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
