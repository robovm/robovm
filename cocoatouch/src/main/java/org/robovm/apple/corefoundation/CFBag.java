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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="kCFTypeBagCallBacks")
    public static native @ByVal CFBagCallBacks BagCallBacks();
    @GlobalValue(symbol="kCFCopyStringBagCallBacks")
    public static native @ByVal CFBagCallBacks CopyStringBagCallBacks();
    
    @Bridge(symbol="CFBagGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBagCreate")
    public static native CFBag create(CFAllocator allocator, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFBagCallBacks callBacks);
    @Bridge(symbol="CFBagCreateCopy")
    public static native CFBag createCopy(CFAllocator allocator, CFBag theBag);
    @Bridge(symbol="CFBagGetCount")
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFBagGetCountOfValue")
    public native @MachineSizedSInt long getCountOfValue(VoidPtr value);
    @Bridge(symbol="CFBagContainsValue")
    public native boolean containsValue(VoidPtr value);
    @Bridge(symbol="CFBagGetValue")
    public native VoidPtr getValue(VoidPtr value);
    @Bridge(symbol="CFBagGetValueIfPresent")
    public native boolean getValueIfPresent(VoidPtr candidate, VoidPtr.VoidPtrPtr value);
    @Bridge(symbol="CFBagGetValues")
    public native void getValues(VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFBagApplyFunction")
    public native void applyFunction(FunctionPtr applier, VoidPtr context);
    /*</methods>*/
}
