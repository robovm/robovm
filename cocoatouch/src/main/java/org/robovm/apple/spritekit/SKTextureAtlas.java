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
package org.robovm.apple.spritekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.avfoundation.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.scenekit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKTextureAtlas/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKTextureAtlasPtr extends Ptr<SKTextureAtlas, SKTextureAtlasPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKTextureAtlas.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKTextureAtlas() {}
    protected SKTextureAtlas(SkipInit skipInit) { super(skipInit); }
    public SKTextureAtlas(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "textureNames")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getTextureNames();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * Creates a new texture atlas object from the specified texture files.
     * @param properties The keys of this map represent the texture names. 
     * The associated values can be of type String, NSURL or UIImage.
     * @return
     * @since Available in iOS 8.0 and later.
     */
    public static SKTextureAtlas create(Map<String, Object> textures) {
        NSDictionary<NSString, NSObject> properties = new NSMutableDictionary<>();
        for (Map.Entry<String, Object> entry : textures.entrySet()) {
            NSString key = new NSString(entry.getKey());
            NSObject value = null;
            if (entry.getValue() instanceof String) {
                value = new NSString((String)entry.getValue());
            } else if (entry.getValue() instanceof NSURL || entry.getValue() instanceof UIImage || entry.getValue() instanceof NSString) {
                value = (NSObject)entry.getValue();
            } else {
                throw new IllegalArgumentException("Only String, NSURL, UIImage or NSString are allowed!");
            }
            properties.put(key, value);
        }
        return create(properties);
    }
    /*<methods>*/
    @Method(selector = "textureNamed:")
    public native SKTexture getTexture(String name);
    @Method(selector = "preloadWithCompletionHandler:")
    public native void preload(@Block Runnable completionHandler);
    @Method(selector = "atlasNamed:")
    public static native SKTextureAtlas create(String name);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "atlasWithDictionary:")
    protected static native SKTextureAtlas create(NSDictionary properties);
    @Method(selector = "preloadTextureAtlases:withCompletionHandler:")
    public static native void preloadTextureAtlases(NSArray<SKTextureAtlas> textureAtlases, @Block Runnable completionHandler);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
