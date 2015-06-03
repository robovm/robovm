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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFBag/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFBagPtr extends Ptr<CFBag, CFBagPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFBag.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFBag() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFTypeBagCallBacks", optional=true)
    public static native @ByVal CFBagCallBacks getTypeCallBacks();
    @GlobalValue(symbol="kCFCopyStringBagCallBacks", optional=true)
    public static native @ByVal CFBagCallBacks getCopyStringCallBacks();
    
    @Bridge(symbol="CFBagGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBagCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFBag create(CFAllocator allocator, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFBagCallBacks callBacks);
    @Bridge(symbol="CFBagCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFBag createCopy(CFAllocator allocator, CFBag theBag);
    @Bridge(symbol="CFBagGetCount", optional=true)
    protected native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFBagGetCountOfValue", optional=true)
    protected native @MachineSizedSInt long getCountOfValue(VoidPtr value);
    @Bridge(symbol="CFBagContainsValue", optional=true)
    protected native boolean containsValue(VoidPtr value);
    @Bridge(symbol="CFBagGetValue", optional=true)
    protected native VoidPtr getValue(VoidPtr value);
    @Bridge(symbol="CFBagGetValueIfPresent", optional=true)
    protected native boolean getValueIfPresent(VoidPtr candidate, VoidPtr.VoidPtrPtr value);
    @Bridge(symbol="CFBagGetValues", optional=true)
    protected native void getValues(VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFBagApplyFunction", optional=true)
    protected native void applyFunction(FunctionPtr applier, VoidPtr context);
    /*</methods>*/
}
