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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSValueTransformer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSValueTransformerPtr extends Ptr<NSValueTransformer, NSValueTransformerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSValueTransformer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSValueTransformer() {}
    protected NSValueTransformer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSNegateBooleanTransformerName")
    public static native String NameNegateBooleanTransformer();
    @GlobalValue(symbol="NSIsNilTransformerName")
    public static native String NameIsNilTransformer();
    @GlobalValue(symbol="NSIsNotNilTransformerName")
    public static native String NameIsNotNilTransformer();
    @GlobalValue(symbol="NSUnarchiveFromDataTransformerName")
    public static native String NameUnarchiveFromDataTransformer();
    @GlobalValue(symbol="NSKeyedUnarchiveFromDataTransformerName")
    public static native String NameKeyedUnarchiveFromDataTransformer();
    
    @Method(selector = "transformedValue:")
    public native NSObject transformedValue$(NSObject value);
    @Method(selector = "reverseTransformedValue:")
    public native NSObject reverseTransformedValue$(NSObject value);
    @Method(selector = "setValueTransformer:forName:")
    public static native void setValueTransformer$forName$(NSValueTransformer transformer, String name);
    @Method(selector = "valueTransformerForName:")
    public static native NSValueTransformer valueTransformerForName$(String name);
    @Method(selector = "valueTransformerNames")
    public static native NSArray<?> valueTransformerNames();
    @Method(selector = "transformedValueClass")
    public static native ObjCClass transformedValueClass();
    @Method(selector = "allowsReverseTransformation")
    public static native boolean allowsReverseTransformation();
    /*</methods>*/
}
