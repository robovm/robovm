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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSSortDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSSortDescriptorPtr extends Ptr<NSSortDescriptor, NSSortDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSSortDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSSortDescriptor() {}
    protected NSSortDescriptor(SkipInit skipInit) { super(skipInit); }
    public NSSortDescriptor(String key, boolean ascending) { super((SkipInit) null); initObject(initWithKey$ascending$(key, ascending)); }
    public NSSortDescriptor(String key, boolean ascending, Selector selector) { super((SkipInit) null); initObject(initWithKey$ascending$selector$(key, ascending, selector)); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSSortDescriptor(String key, boolean ascending, FunctionPtr cmptr) { super((SkipInit) null); initObject(initWithKey$ascending$comparator$(key, ascending, cmptr)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithKey:ascending:")
    protected native @Pointer long initWithKey$ascending$(String key, boolean ascending);
    @Method(selector = "initWithKey:ascending:selector:")
    protected native @Pointer long initWithKey$ascending$selector$(String key, boolean ascending, Selector selector);
    @Method(selector = "key")
    public native String key();
    @Method(selector = "ascending")
    public native boolean ascending();
    @Method(selector = "selector")
    public native Selector selector();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "allowEvaluation")
    public native void allowEvaluation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithKey:ascending:comparator:")
    protected native @Pointer long initWithKey$ascending$comparator$(String key, boolean ascending, FunctionPtr cmptr);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "comparator")
    public native FunctionPtr comparator();
    @Method(selector = "compareObject:toObject:")
    public native NSComparisonResult compareObject$toObject$(NSObject object1, NSObject object2);
    @Method(selector = "reversedSortDescriptor")
    public native NSObject reversedSortDescriptor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "sortDescriptorWithKey:ascending:")
    public static native NSObject sortDescriptorWithKey$ascending$(String key, boolean ascending);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "sortDescriptorWithKey:ascending:selector:")
    public static native NSObject sortDescriptorWithKey$ascending$selector$(String key, boolean ascending, Selector selector);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "sortDescriptorWithKey:ascending:comparator:")
    public static native NSObject sortDescriptorWithKey$ascending$comparator$(String key, boolean ascending, FunctionPtr cmptr);
    /*</methods>*/
}
