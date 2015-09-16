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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLAsset/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFastEnumeration/*</implements>*/ {

    /*<ptr>*/public static class MDLAssetPtr extends Ptr<MDLAsset, MDLAssetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLAsset.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLAsset() {}
    protected MDLAsset(SkipInit skipInit) { super(skipInit); }
    public MDLAsset(NSURL URL) { super((SkipInit) null); initObject(init(URL)); }
    public MDLAsset(NSURL URL, MDLVertexDescriptor vertexDescriptor, MDLMeshBufferAllocator bufferAllocator) { super((SkipInit) null); initObject(init(URL, vertexDescriptor, bufferAllocator)); }
    public MDLAsset(NSURL URL, MDLVertexDescriptor vertexDescriptor, MDLMeshBufferAllocator bufferAllocator, boolean preserveTopology) throws NSErrorException {
       super((SkipInit) null);
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long handle = init(URL, vertexDescriptor, bufferAllocator, preserveTopology, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       initObject(handle);
    }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "boundingBox")
    public native @ByVal MDLAxisAlignedBoundingBox getBoundingBox();
    @Property(selector = "frameInterval")
    public native double getFrameInterval();
    @Property(selector = "setFrameInterval:")
    public native void setFrameInterval(double v);
    @Property(selector = "startTime")
    public native double getStartTime();
    @Property(selector = "setStartTime:")
    public native void setStartTime(double v);
    @Property(selector = "endTime")
    public native double getEndTime();
    @Property(selector = "setEndTime:")
    public native void setEndTime(double v);
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "bufferAllocator")
    public native MDLMeshBufferAllocator getBufferAllocator();
    @Property(selector = "vertexDescriptor")
    public native MDLVertexDescriptor getVertexDescriptor();
    @Property(selector = "count")
    public native @MachineSizedUInt long getCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:")
    protected native @Pointer long init(NSURL URL);
    @Method(selector = "initWithURL:vertexDescriptor:bufferAllocator:")
    protected native @Pointer long init(NSURL URL, MDLVertexDescriptor vertexDescriptor, MDLMeshBufferAllocator bufferAllocator);
    @Method(selector = "initWithURL:vertexDescriptor:bufferAllocator:preserveTopology:error:")
    private native @Pointer long init(NSURL URL, MDLVertexDescriptor vertexDescriptor, MDLMeshBufferAllocator bufferAllocator, boolean preserveTopology, NSError.NSErrorPtr error);
    public boolean export(NSURL URL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = export(URL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "exportAssetToURL:error:")
    private native boolean export(NSURL URL, NSError.NSErrorPtr error);
    @Method(selector = "boundingBoxAtTime:")
    public native @ByVal MDLAxisAlignedBoundingBox getBoundingBox(double time);
    @Method(selector = "addObject:")
    public native void addObject(MDLObject object);
    @Method(selector = "removeObject:")
    public native void removeObject(MDLObject object);
    @Method(selector = "objectAtIndex:")
    public native MDLObject getObject(@MachineSizedUInt long index);
    @Method(selector = "canImportFileExtension:")
    public static native boolean canImportFileExtension(String extension);
    @Method(selector = "canExportFileExtension:")
    public static native boolean canExportFileExtension(String extension);
    /*</methods>*/
}
