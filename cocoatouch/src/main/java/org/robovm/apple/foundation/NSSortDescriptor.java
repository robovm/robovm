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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
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
    public NSSortDescriptor(NSSortIdentifier key, boolean ascending) {
        this(key.value().toString(), ascending);
    }
    public NSSortDescriptor(NSSortIdentifier key, boolean ascending, Selector selector) {
        this(key.value().toString(), ascending, selector);
    }
    public NSSortDescriptor(NSSortIdentifier key, boolean ascending, @Block Block2<NSObject, NSObject, NSComparisonResult> cmptr) {
        this(key.value().toString(), ascending, cmptr);
    }
    /*<constructors>*/
    public NSSortDescriptor() {}
    protected NSSortDescriptor(SkipInit skipInit) { super(skipInit); }
    public NSSortDescriptor(String key, boolean ascending) { super((SkipInit) null); initObject(init(key, ascending)); }
    public NSSortDescriptor(String key, boolean ascending, Selector selector) { super((SkipInit) null); initObject(init(key, ascending, selector)); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSSortDescriptor(String key, boolean ascending, @Block Block2<NSObject, NSObject, NSComparisonResult> cmptr) { super((SkipInit) null); initObject(init(key, ascending, cmptr)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "key")
    public native String getKey();
    @Property(selector = "ascending")
    public native boolean isAscending();
    @Property(selector = "selector")
    public native Selector getSelector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "comparator")
    public native @Block Block2<NSObject, NSObject, NSComparisonResult> getComparator();
    @Property(selector = "reversedSortDescriptor")
    public native NSSortDescriptor getReversedSortDescriptor();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithKey:ascending:")
    protected native @Pointer long init(String key, boolean ascending);
    @Method(selector = "initWithKey:ascending:selector:")
    protected native @Pointer long init(String key, boolean ascending, Selector selector);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "allowEvaluation")
    public native void allowEvaluation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithKey:ascending:comparator:")
    protected native @Pointer long init(String key, boolean ascending, @Block Block2<NSObject, NSObject, NSComparisonResult> cmptr);
    @Method(selector = "compareObject:toObject:")
    public native NSComparisonResult compare(NSObject object1, NSObject object2);
    /*</methods>*/
}
