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
package org.robovm.apple.uikit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UIStateRestorationKey.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIStateRestorationKey/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static UIStateRestorationKey toObject(Class<UIStateRestorationKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return UIStateRestorationKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(UIStateRestorationKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIStateRestorationKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final UIStateRestorationKey ViewControllerStoryboard = new UIStateRestorationKey("ViewControllerStoryboardKey");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final UIStateRestorationKey ApplicationBundleVersion = new UIStateRestorationKey("ApplicationBundleVersionKey");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final UIStateRestorationKey ApplicationUserInterfaceIdiom = new UIStateRestorationKey("ApplicationUserInterfaceIdiomKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIStateRestorationKey ApplicationTimestamp = new UIStateRestorationKey("ApplicationTimestampKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final UIStateRestorationKey ApplicationSystemVersion = new UIStateRestorationKey("ApplicationSystemVersionKey");
    
    private static UIStateRestorationKey[] values = new UIStateRestorationKey[] {ViewControllerStoryboard, ApplicationBundleVersion, ApplicationUserInterfaceIdiom, 
        ApplicationTimestamp, ApplicationSystemVersion};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private UIStateRestorationKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static UIStateRestorationKey valueOf(NSString value) {
        for (UIStateRestorationKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIStateRestorationKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIStateRestorationViewControllerStoryboardKey", optional=true)
    protected static native String ViewControllerStoryboardKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationBundleVersionKey", optional=true)
    protected static native String ApplicationBundleVersionKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationUserInterfaceIdiomKey", optional=true)
    protected static native String ApplicationUserInterfaceIdiomKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationTimestampKey", optional=true)
    protected static native String ApplicationTimestampKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationSystemVersionKey", optional=true)
    protected static native String ApplicationSystemVersionKey();
    /*</methods>*/
}
