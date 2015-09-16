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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLVertexDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLVertexDescriptorPtr extends Ptr<MDLVertexDescriptor, MDLVertexDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLVertexDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLVertexDescriptor() {}
    protected MDLVertexDescriptor(SkipInit skipInit) { super(skipInit); }
    public MDLVertexDescriptor(MDLVertexDescriptor vertexDescriptor) { super((SkipInit) null); initObject(init(vertexDescriptor)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "attributes")
    public native NSMutableArray<MDLVertexAttribute> getAttributes();
    @Property(selector = "setAttributes:")
    public native void setAttributes(NSMutableArray<MDLVertexAttribute> v);
    @Property(selector = "layouts")
    public native NSMutableArray<MDLVertexBufferLayout> getLayouts();
    @Property(selector = "setLayouts:")
    public native void setLayouts(NSMutableArray<MDLVertexBufferLayout> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public MDLVertexAttribute getAttribute(MDLVertexAttributeName name) {
        return getAttribute(name.value());
    }
    @Method(selector = "attributeNamed:")
    private native MDLVertexAttribute getAttribute(NSString name);
    /*<methods>*/
    @Method(selector = "initWithVertexDescriptor:")
    protected native @Pointer long init(MDLVertexDescriptor vertexDescriptor);
    @Method(selector = "attributeNamed:")
    public native MDLVertexAttribute getAttribute(String name);
    @Method(selector = "addOrReplaceAttribute:")
    public native void addOrReplaceAttribute(MDLVertexAttribute attribute);
    @Method(selector = "reset")
    public native void reset();
    @Method(selector = "setPackedStrides")
    public native void setPackedStrides();
    @Method(selector = "setPackedOffsets")
    public native void setPackedOffsets();
    /*</methods>*/
}
