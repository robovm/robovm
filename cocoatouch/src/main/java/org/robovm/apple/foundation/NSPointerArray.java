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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPointerArray/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSPointerArrayPtr extends Ptr<NSPointerArray, NSPointerArrayPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPointerArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPointerArray() {}
    protected NSPointerArray(SkipInit skipInit) { super(skipInit); }
    public NSPointerArray(NSPointerFunctionsOptions options) { super((SkipInit) null); initObject(initWithOptions$(options)); }
    public NSPointerArray(NSPointerFunctions functions) { super((SkipInit) null); initObject(initWithPointerFunctions$(functions)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "pointerFunctions")
    public native NSPointerFunctions getPointerFunctions();
    @Property(selector = "count")
    public native @MachineSizedUInt long getCount();
    @Property(selector = "setCount:")
    public native void setCount(@MachineSizedUInt long v);
    @Property(selector = "allObjects")
    public native NSArray<?> getAllObjects();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithOptions:")
    protected native @Pointer long initWithOptions$(NSPointerFunctionsOptions options);
    @Method(selector = "initWithPointerFunctions:")
    protected native @Pointer long initWithPointerFunctions$(NSPointerFunctions functions);
    @Method(selector = "pointerAtIndex:")
    public native VoidPtr pointerAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "addPointer:")
    public native void addPointer$(VoidPtr pointer);
    @Method(selector = "removePointerAtIndex:")
    public native void removePointerAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "insertPointer:atIndex:")
    public native void insertPointer$atIndex$(VoidPtr item, @MachineSizedUInt long index);
    @Method(selector = "replacePointerAtIndex:withPointer:")
    public native void replacePointerAtIndex$withPointer$(@MachineSizedUInt long index, VoidPtr item);
    @Method(selector = "compact")
    public native void compact();
    @Method(selector = "pointerArrayWithOptions:")
    public static native NSPointerArray pointerArrayWithOptions$(NSPointerFunctionsOptions options);
    @Method(selector = "pointerArrayWithPointerFunctions:")
    public static native NSPointerArray pointerArrayWithPointerFunctions$(NSPointerFunctions functions);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "strongObjectsPointerArray")
    public static native NSPointerArray strongObjectsPointerArray();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "weakObjectsPointerArray")
    public static native NSPointerArray weakObjectsPointerArray();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
