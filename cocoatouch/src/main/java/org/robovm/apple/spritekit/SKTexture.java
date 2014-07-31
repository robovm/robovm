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
package org.robovm.apple.spritekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.avfoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKTexture/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKTexturePtr extends Ptr<SKTexture, SKTexturePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKTexture.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKTexture() {}
    protected SKTexture(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "filteringMode")
    public native SKTextureFilteringMode getFilteringMode();
    @Property(selector = "setFilteringMode:")
    public native void setFilteringMode(SKTextureFilteringMode v);
    @Property(selector = "usesMipmaps")
    public native boolean isUsesMipmaps();
    @Property(selector = "setUsesMipmaps:")
    public native void setUsesMipmaps(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "textureByApplyingCIFilter:")
    public native SKTexture newTextureByApplyingCIFilter(CIFilter filter);
    @Method(selector = "textureRect")
    public native @ByVal CGRect getTextureRect();
    @Method(selector = "size")
    public native @ByVal CGSize size();
    @Method(selector = "preloadWithCompletionHandler:")
    public native void preload(@Block Runnable completionHandler);
    @Method(selector = "textureWithImageNamed:")
    public static native SKTexture create(String name);
    @Method(selector = "textureWithRect:inTexture:")
    public static native SKTexture create(@ByVal CGRect rect, SKTexture texture);
    @Method(selector = "textureWithCGImage:")
    public static native SKTexture create(CGImage image);
    @Method(selector = "textureWithImage:")
    public static native SKTexture create(UIImage image);
    @Method(selector = "textureWithData:size:")
    public static native SKTexture create(NSData pixelData, @ByVal CGSize size);
    @Method(selector = "textureWithData:size:rowLength:alignment:")
    public static native SKTexture create(NSData pixelData, @ByVal CGSize size, int rowLength, int alignment);
    @Method(selector = "preloadTextures:withCompletionHandler:")
    public static native void preloadTextures(NSArray<SKTexture> textures, @Block Runnable completionHandler);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
