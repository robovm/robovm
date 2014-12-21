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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CATransactionProperty.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATransactionProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CATransactionProperty toObject(Class<CATransactionProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CATransactionProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CATransactionProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CATransactionProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CATransactionProperty AnimationDuration = new CATransactionProperty("AnimationDurationValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CATransactionProperty DisableActions = new CATransactionProperty("DisableActionsValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CATransactionProperty AnimationTimingFunction = new CATransactionProperty("AnimationTimingFunctionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CATransactionProperty CompletionBlock = new CATransactionProperty("CompletionBlockValue");
    
    private static CATransactionProperty[] values = new CATransactionProperty[] {AnimationDuration, DisableActions, 
        AnimationTimingFunction, CompletionBlock};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CATransactionProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CATransactionProperty valueOf(NSString value) {
        for (CATransactionProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CATransactionProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransactionAnimationDuration", optional=true)
    protected static native NSString AnimationDurationValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransactionDisableActions", optional=true)
    protected static native NSString DisableActionsValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCATransactionAnimationTimingFunction", optional=true)
    protected static native NSString AnimationTimingFunctionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCATransactionCompletionBlock", optional=true)
    protected static native NSString CompletionBlockValue();
    /*</methods>*/
}
