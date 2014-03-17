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

/**
 *
 * <div class="javadoc"></div>
 */
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
    public GLKTextureLoader(EAGLSharegroup sharegroup) { super((SkipInit) null); initObject(initWithSharegroup$(sharegroup)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSharegroup:")
    protected native @Pointer long initWithSharegroup$(EAGLSharegroup sharegroup);
    @Method(selector = "textureWithContentsOfFile:options:queue:completionHandler:")
    public native void textureWithContentsOfFile$options$queue$completionHandler$(String path, NSDictionary<?, ?> options, DispatchQueue queue, FunctionPtr block);
    @Method(selector = "textureWithContentsOfURL:options:queue:completionHandler:")
    public native void textureWithContentsOfURL$options$queue$completionHandler$(NSURL url, NSDictionary<?, ?> options, DispatchQueue queue, FunctionPtr block);
    @Method(selector = "textureWithContentsOfData:options:queue:completionHandler:")
    public native void textureWithContentsOfData$options$queue$completionHandler$(NSData data, NSDictionary<?, ?> options, DispatchQueue queue, FunctionPtr block);
    @Method(selector = "textureWithCGImage:options:queue:completionHandler:")
    public native void textureWithCGImage$options$queue$completionHandler$(CGImage cgImage, NSDictionary<?, ?> options, DispatchQueue queue, FunctionPtr block);
    @Method(selector = "cubeMapWithContentsOfFiles:options:queue:completionHandler:")
    public native void cubeMapWithContentsOfFiles$options$queue$completionHandler$(NSArray<?> paths, NSDictionary<?, ?> options, DispatchQueue queue, FunctionPtr block);
    @Method(selector = "cubeMapWithContentsOfFile:options:queue:completionHandler:")
    public native void cubeMapWithContentsOfFile$options$queue$completionHandler$(String path, NSDictionary<?, ?> options, DispatchQueue queue, FunctionPtr block);
    @Method(selector = "cubeMapWithContentsOfURL:options:queue:completionHandler:")
    public native void cubeMapWithContentsOfURL$options$queue$completionHandler$(NSURL url, NSDictionary<?, ?> options, DispatchQueue queue, FunctionPtr block);
    @Method(selector = "textureWithContentsOfFile:options:error:")
    public static native GLKTextureInfo textureWithContentsOfFile$options$error$(String path, NSDictionary<?, ?> options, NSError.NSErrorPtr outError);
    @Method(selector = "textureWithContentsOfURL:options:error:")
    public static native GLKTextureInfo textureWithContentsOfURL$options$error$(NSURL url, NSDictionary<?, ?> options, NSError.NSErrorPtr outError);
    @Method(selector = "textureWithContentsOfData:options:error:")
    public static native GLKTextureInfo textureWithContentsOfData$options$error$(NSData data, NSDictionary<?, ?> options, NSError.NSErrorPtr outError);
    @Method(selector = "textureWithCGImage:options:error:")
    public static native GLKTextureInfo textureWithCGImage$options$error$(CGImage cgImage, NSDictionary<?, ?> options, NSError.NSErrorPtr outError);
    @Method(selector = "cubeMapWithContentsOfFiles:options:error:")
    public static native GLKTextureInfo cubeMapWithContentsOfFiles$options$error$(NSArray<?> paths, NSDictionary<?, ?> options, NSError.NSErrorPtr outError);
    @Method(selector = "cubeMapWithContentsOfFile:options:error:")
    public static native GLKTextureInfo cubeMapWithContentsOfFile$options$error$(String path, NSDictionary<?, ?> options, NSError.NSErrorPtr outError);
    @Method(selector = "cubeMapWithContentsOfURL:options:error:")
    public static native GLKTextureInfo cubeMapWithContentsOfURL$options$error$(NSURL url, NSDictionary<?, ?> options, NSError.NSErrorPtr outError);
    /*</methods>*/
}
