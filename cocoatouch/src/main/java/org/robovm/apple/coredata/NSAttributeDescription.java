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
package org.robovm.apple.coredata;

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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributeDescription/*</name>*/ 
    extends /*<extends>*/NSPropertyDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSAttributeDescriptionPtr extends Ptr<NSAttributeDescription, NSAttributeDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSAttributeDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSAttributeDescription() {}
    protected NSAttributeDescription(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "attributeType")
    public native NSAttributeType attributeType();
    @Method(selector = "setAttributeType:")
    public native void setAttributeType$(NSAttributeType type);
    @Method(selector = "attributeValueClassName")
    public native String attributeValueClassName();
    @Method(selector = "defaultValue")
    public native NSObject defaultValue();
    @Method(selector = "setDefaultValue:")
    public native void setDefaultValue$(NSObject value);
    @Method(selector = "setAttributeValueClassName:")
    public native void setAttributeValueClassName$(String className);
    @Method(selector = "versionHash")
    public native NSData versionHash();
    @Method(selector = "valueTransformerName")
    public native String valueTransformerName();
    @Method(selector = "setValueTransformerName:")
    public native void setValueTransformerName$(String string);
    @Method(selector = "allowsExternalBinaryDataStorage")
    public native boolean allowsExternalBinaryDataStorage();
    @Method(selector = "setAllowsExternalBinaryDataStorage:")
    public native void setAllowsExternalBinaryDataStorage$(boolean flag);
    /*</methods>*/
}
