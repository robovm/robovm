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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSet/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFSetPtr extends Ptr<CFSet, CFSetPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFSet() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFTypeSetCallBacks")
    public static native @ByVal CFSetCallBacks SetCallBacks();
    @GlobalValue(symbol="kCFCopyStringSetCallBacks")
    public static native @ByVal CFSetCallBacks CopyStringSetCallBacks();
    
    @Bridge(symbol="CFSetGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFSetCreate")
    public static native CFSet create(CFAllocator allocator, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFSetCallBacks callBacks);
    @Bridge(symbol="CFSetCreateCopy")
    public static native CFSet createCopy(CFAllocator allocator, CFSet theSet);
    @Bridge(symbol="CFSetGetCount")
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFSetGetCountOfValue")
    public native @MachineSizedSInt long getCountOfValue(VoidPtr value);
    @Bridge(symbol="CFSetContainsValue")
    public native boolean containsValue(VoidPtr value);
    @Bridge(symbol="CFSetGetValue")
    public native VoidPtr getValue(VoidPtr value);
    @Bridge(symbol="CFSetGetValueIfPresent")
    public native boolean getValueIfPresent(VoidPtr candidate, VoidPtr.VoidPtrPtr value);
    @Bridge(symbol="CFSetGetValues")
    public native void getValues(VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFSetApplyFunction")
    public native void applyFunction(FunctionPtr applier, VoidPtr context);
    /*</methods>*/
}
