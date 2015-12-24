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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.corelocation.*;
import org.robovm.apple.contacts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKLocationSortDescriptor/*</name>*/ 
    extends /*<extends>*/NSSortDescriptor/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKLocationSortDescriptorPtr extends Ptr<CKLocationSortDescriptor, CKLocationSortDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKLocationSortDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKLocationSortDescriptor() {}
    protected CKLocationSortDescriptor(SkipInit skipInit) { super(skipInit); }
    @WeaklyLinked
    public CKLocationSortDescriptor(String key, CLLocation relativeLocation) { super((SkipInit) null); initObject(init(key, relativeLocation)); }
    public CKLocationSortDescriptor(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    public CKLocationSortDescriptor(NSSortIdentifier key, boolean ascending) {
        this(key.value().toString(), ascending);
    }
    public CKLocationSortDescriptor(NSSortIdentifier key, boolean ascending, Selector selector) {
        this(key.value().toString(), ascending, selector);
    }
    public CKLocationSortDescriptor(NSSortIdentifier key, boolean ascending, @Block Block2<NSObject, NSObject, NSComparisonResult> cmptr) {
        this(key.value().toString(), ascending, cmptr);
    }
    /*<constructors>*/
    public CKLocationSortDescriptor(String key, boolean ascending) { 
        super(key, ascending);
    }
    public CKLocationSortDescriptor(String key, boolean ascending, Selector selector) {
        super(key, ascending, selector);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CKLocationSortDescriptor(String key, boolean ascending, @Block Block2<NSObject, NSObject, NSComparisonResult> cmptr) { 
        super(key, ascending, cmptr);
    }

    /*<properties>*/
    @WeaklyLinked
    @Property(selector = "relativeLocation")
    public native CLLocation getRelativeLocation();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @WeaklyLinked
    @Method(selector = "initWithKey:relativeLocation:")
    protected native @Pointer long init(String key, CLLocation relativeLocation);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
