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
package org.robovm.apple.modelio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("ModelIO") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLMaterial/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MDLNamed, NSFastEnumeration/*</implements>*/ {

    /*<ptr>*/public static class MDLMaterialPtr extends Ptr<MDLMaterial, MDLMaterialPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLMaterial.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLMaterial() {}
    protected MDLMaterial(SkipInit skipInit) { super(skipInit); }
    public MDLMaterial(String name, MDLScatteringFunction scatteringFunction) { super((SkipInit) null); initObject(init(name, scatteringFunction)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "scatteringFunction")
    public native MDLScatteringFunction getScatteringFunction();
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "baseMaterial")
    public native MDLMaterial getBaseMaterial();
    @Property(selector = "setBaseMaterial:")
    public native void setBaseMaterial(MDLMaterial v);
    @Property(selector = "count")
    public native @MachineSizedUInt long getCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithName:scatteringFunction:")
    protected native @Pointer long init(String name, MDLScatteringFunction scatteringFunction);
    @Method(selector = "setProperty:")
    public native void setProperty(MDLMaterialProperty property);
    @Method(selector = "removeProperty:")
    public native void removeProperty(MDLMaterialProperty property);
    @Method(selector = "propertyNamed:")
    public native MDLMaterialProperty getProperty(String name);
    @Method(selector = "propertyWithSemantic:")
    public native MDLMaterialProperty getProperty(MDLMaterialSemantic semantic);
    @Method(selector = "removeAllProperties")
    public native void removeAllProperties();
    @Method(selector = "objectAtIndexedSubscript:")
    public native MDLMaterialProperty getProperty(@MachineSizedUInt long idx);
    /*</methods>*/
}
