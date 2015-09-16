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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLVertexAttribute/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLVertexAttributePtr extends Ptr<MDLVertexAttribute, MDLVertexAttributePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLVertexAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLVertexAttribute() {}
    protected MDLVertexAttribute(SkipInit skipInit) { super(skipInit); }
    public MDLVertexAttribute(String name, MDLVertexFormat format, @MachineSizedUInt long offset, @MachineSizedUInt long bufferIndex) { super((SkipInit) null); initObject(init(name, format, offset, bufferIndex)); }
    /*</constructors>*/
    public MDLVertexAttribute(MDLVertexAttributeName name, MDLVertexFormat format, @MachineSizedUInt long offset, @MachineSizedUInt long bufferIndex) {
        super((SkipInit) null);
        initObject(init(name.value(), format, offset, bufferIndex));
    }
    
    public void setName(MDLVertexAttributeName name) {
        setName(name.value());
    }
    @Property(selector = "setName:")
    private native void setName(NSString v);
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "format")
    public native MDLVertexFormat getFormat();
    @Property(selector = "setFormat:")
    public native void setFormat(MDLVertexFormat v);
    @Property(selector = "offset")
    public native @MachineSizedUInt long getOffset();
    @Property(selector = "setOffset:")
    public native void setOffset(@MachineSizedUInt long v);
    @Property(selector = "bufferIndex")
    public native @MachineSizedUInt long getBufferIndex();
    @Property(selector = "setBufferIndex:")
    public native void setBufferIndex(@MachineSizedUInt long v);
    @Property(selector = "initializationValue")
    public native VectorFloat4 getInitializationValue();
    @Property(selector = "setInitializationValue:")
    public native void setInitializationValue(VectorFloat4 v);
    /*</properties>*/
    /*<members>*//*</members>*/
    @Method(selector = "initWithName:format:offset:bufferIndex:")
    private native @Pointer long init(NSString name, MDLVertexFormat format, @MachineSizedUInt long offset, @MachineSizedUInt long bufferIndex);
    /*<methods>*/
    @Method(selector = "initWithName:format:offset:bufferIndex:")
    protected native @Pointer long init(String name, MDLVertexFormat format, @MachineSizedUInt long offset, @MachineSizedUInt long bufferIndex);
    /*</methods>*/
}
