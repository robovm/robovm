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
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSValueTransformerName/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSValueTransformerName.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSValueTransformerName NegateBoolean = new NSValueTransformerName("NegateBooleanValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSValueTransformerName IsNull = new NSValueTransformerName("IsNilValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSValueTransformerName IsNotNull = new NSValueTransformerName("IsNotNilValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSValueTransformerName UnarchiveFromData = new NSValueTransformerName("UnarchiveFromDataValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSValueTransformerName KeyedUnarchiveFromData = new NSValueTransformerName("KeyedUnarchiveFromDataValue");
    
    private static NSValueTransformerName[] values = new NSValueTransformerName[] {NegateBoolean, IsNull, IsNotNull, UnarchiveFromData, KeyedUnarchiveFromData};
    private final LazyGlobalValue<String> lazyGlobalValue;
    
    private NSValueTransformerName(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public String value() {
        return lazyGlobalValue.value();
    }
    
    public static NSValueTransformerName valueOf(NSString value) {
        for (NSValueTransformerName v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSValueTransformerName/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSNegateBooleanTransformerName", optional=true)
    protected static native String NegateBooleanValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIsNilTransformerName", optional=true)
    protected static native String IsNilValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIsNotNilTransformerName", optional=true)
    protected static native String IsNotNilValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUnarchiveFromDataTransformerName", optional=true)
    protected static native String UnarchiveFromDataValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSKeyedUnarchiveFromDataTransformerName", optional=true)
    protected static native String KeyedUnarchiveFromDataValue();
    /*</methods>*/
}
