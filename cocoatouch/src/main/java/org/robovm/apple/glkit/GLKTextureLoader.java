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
package org.robovm.apple.glkit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GLKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKTextureLoader/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKTextureLoaderPtr extends Ptr<GLKTextureLoader, GLKTextureLoaderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GLKTextureLoader.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKTextureLoader() {}
    protected GLKTextureLoader(SkipInit skipInit) { super(skipInit); }
    public GLKTextureLoader(EAGLSharegroup sharegroup) { super((SkipInit) null); initObject(init(sharegroup)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSharegroup:")
    protected native @Pointer long init(EAGLSharegroup sharegroup);
    @Method(selector = "textureWithContentsOfFile:options:queue:completionHandler:")
    public native void loadTexture(String path, GLKTextureLoaderOptions options, DispatchQueue queue, @Block VoidBlock2<GLKTextureInfo, NSError> block);
    @Method(selector = "textureWithContentsOfURL:options:queue:completionHandler:")
    public native void loadTexture(NSURL url, GLKTextureLoaderOptions options, DispatchQueue queue, @Block VoidBlock2<GLKTextureInfo, NSError> block);
    @Method(selector = "textureWithContentsOfData:options:queue:completionHandler:")
    public native void createTexture(NSData data, GLKTextureLoaderOptions options, DispatchQueue queue, @Block VoidBlock2<GLKTextureInfo, NSError> block);
    @Method(selector = "textureWithCGImage:options:queue:completionHandler:")
    public native void createTexture(CGImage cgImage, GLKTextureLoaderOptions options, DispatchQueue queue, @Block VoidBlock2<GLKTextureInfo, NSError> block);
    @Method(selector = "cubeMapWithContentsOfFiles:options:queue:completionHandler:")
    public native void loadCubeMap(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> paths, GLKTextureLoaderOptions options, DispatchQueue queue, @Block VoidBlock2<GLKTextureInfo, NSError> block);
    @Method(selector = "cubeMapWithContentsOfFile:options:queue:completionHandler:")
    public native void loadCubeMap(String path, GLKTextureLoaderOptions options, DispatchQueue queue, @Block VoidBlock2<GLKTextureInfo, NSError> block);
    @Method(selector = "cubeMapWithContentsOfURL:options:queue:completionHandler:")
    public native void loadCubeMap(NSURL url, GLKTextureLoaderOptions options, DispatchQueue queue, @Block VoidBlock2<GLKTextureInfo, NSError> block);
    public static GLKTextureInfo loadTexture(String path, GLKTextureLoaderOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       GLKTextureInfo result = loadTexture(path, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "textureWithContentsOfFile:options:error:")
    private static native GLKTextureInfo loadTexture(String path, GLKTextureLoaderOptions options, NSError.NSErrorPtr outError);
    public static GLKTextureInfo loadTexture(NSURL url, GLKTextureLoaderOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       GLKTextureInfo result = loadTexture(url, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "textureWithContentsOfURL:options:error:")
    private static native GLKTextureInfo loadTexture(NSURL url, GLKTextureLoaderOptions options, NSError.NSErrorPtr outError);
    public static GLKTextureInfo createTexture(NSData data, GLKTextureLoaderOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       GLKTextureInfo result = createTexture(data, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "textureWithContentsOfData:options:error:")
    private static native GLKTextureInfo createTexture(NSData data, GLKTextureLoaderOptions options, NSError.NSErrorPtr outError);
    public static GLKTextureInfo createTexture(CGImage cgImage, GLKTextureLoaderOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       GLKTextureInfo result = createTexture(cgImage, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "textureWithCGImage:options:error:")
    private static native GLKTextureInfo createTexture(CGImage cgImage, GLKTextureLoaderOptions options, NSError.NSErrorPtr outError);
    public static GLKTextureInfo loadCubeMap(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> paths, GLKTextureLoaderOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       GLKTextureInfo result = loadCubeMap(paths, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "cubeMapWithContentsOfFiles:options:error:")
    private static native GLKTextureInfo loadCubeMap(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> paths, GLKTextureLoaderOptions options, NSError.NSErrorPtr outError);
    public static GLKTextureInfo loadCubeMap(String path, GLKTextureLoaderOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       GLKTextureInfo result = loadCubeMap(path, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "cubeMapWithContentsOfFile:options:error:")
    private static native GLKTextureInfo loadCubeMap(String path, GLKTextureLoaderOptions options, NSError.NSErrorPtr outError);
    public static GLKTextureInfo loadCubeMap(NSURL url, GLKTextureLoaderOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       GLKTextureInfo result = loadCubeMap(url, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "cubeMapWithContentsOfURL:options:error:")
    private static native GLKTextureInfo loadCubeMap(NSURL url, GLKTextureLoaderOptions options, NSError.NSErrorPtr outError);
    /*</methods>*/
}
