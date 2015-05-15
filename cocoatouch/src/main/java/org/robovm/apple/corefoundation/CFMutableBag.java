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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableBag/*</name>*/ 
    extends /*<extends>*/CFBag/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableBagPtr extends Ptr<CFMutableBag, CFMutableBagPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableBag.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableBag() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFBagCreateMutable", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableBag create(CFAllocator allocator, @MachineSizedSInt long capacity, CFBagCallBacks callBacks);
    @Bridge(symbol="CFBagCreateMutableCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableBag createCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFBag theBag);
    @Bridge(symbol="CFBagAddValue", optional=true)
    private native void addValue(VoidPtr value);
    @Bridge(symbol="CFBagReplaceValue", optional=true)
    private native void replaceValue(VoidPtr value);
    @Bridge(symbol="CFBagSetValue", optional=true)
    private native void setValue(VoidPtr value);
    @Bridge(symbol="CFBagRemoveValue", optional=true)
    private native void removeValue(VoidPtr value);
    @Bridge(symbol="CFBagRemoveAllValues", optional=true)
    private native void removeAllValues();
    /*</methods>*/
}
