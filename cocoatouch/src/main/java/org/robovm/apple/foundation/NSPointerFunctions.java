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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPointerFunctions/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSPointerFunctionsPtr extends Ptr<NSPointerFunctions, NSPointerFunctionsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPointerFunctions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPointerFunctions() {}
    protected NSPointerFunctions(SkipInit skipInit) { super(skipInit); }
    public NSPointerFunctions(NSPointerFunctionsOptions options) { super((SkipInit) null); initObject(initWithOptions$(options)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "hashFunction")
    public native FunctionPtr getHashFunction();
    @Property(selector = "setHashFunction:")
    public native void setHashFunction(FunctionPtr v);
    @Property(selector = "isEqualFunction")
    public native FunctionPtr getIsEqualFunction();
    @Property(selector = "setIsEqualFunction:")
    public native void setIsEqualFunction(FunctionPtr v);
    @Property(selector = "sizeFunction")
    public native FunctionPtr getSizeFunction();
    @Property(selector = "setSizeFunction:")
    public native void setSizeFunction(FunctionPtr v);
    @Property(selector = "descriptionFunction")
    public native FunctionPtr getDescriptionFunction();
    @Property(selector = "setDescriptionFunction:")
    public native void setDescriptionFunction(FunctionPtr v);
    @Property(selector = "relinquishFunction")
    public native FunctionPtr getRelinquishFunction();
    @Property(selector = "setRelinquishFunction:")
    public native void setRelinquishFunction(FunctionPtr v);
    @Property(selector = "acquireFunction")
    public native FunctionPtr getAcquireFunction();
    @Property(selector = "setAcquireFunction:")
    public native void setAcquireFunction(FunctionPtr v);
    @Property(selector = "usesStrongWriteBarrier")
    public native boolean isUsesStrongWriteBarrier();
    @Property(selector = "setUsesStrongWriteBarrier:")
    public native void setUsesStrongWriteBarrier(boolean v);
    @Property(selector = "usesWeakReadAndWriteBarriers")
    public native boolean isUsesWeakReadAndWriteBarriers();
    @Property(selector = "setUsesWeakReadAndWriteBarriers:")
    public native void setUsesWeakReadAndWriteBarriers(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithOptions:")
    protected native @Pointer long initWithOptions$(NSPointerFunctionsOptions options);
    @Method(selector = "pointerFunctionsWithOptions:")
    public static native NSObject pointerFunctionsWithOptions$(NSPointerFunctionsOptions options);
    /*</methods>*/
}
