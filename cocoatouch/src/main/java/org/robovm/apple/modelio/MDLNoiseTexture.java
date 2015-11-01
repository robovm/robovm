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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLNoiseTexture/*</name>*/ 
    extends /*<extends>*/MDLTexture/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLNoiseTexturePtr extends Ptr<MDLNoiseTexture, MDLNoiseTexturePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLNoiseTexture.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLNoiseTexture() {}
    protected MDLNoiseTexture(SkipInit skipInit) { super(skipInit); }
    public MDLNoiseTexture(float smoothness, String name, VectorInt2 textureDimensions, MDLTextureChannelEncoding channelEncoding) { super((SkipInit) null); initObject(init(smoothness, name, textureDimensions, channelEncoding)); }
    public MDLNoiseTexture(float smoothness, String name, VectorInt2 textureDimensions, int channelCount, MDLTextureChannelEncoding channelEncoding, boolean grayscale) { super((SkipInit) null); initObject(init(smoothness, name, textureDimensions, channelCount, channelEncoding, grayscale)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initVectorNoiseWithSmoothness:name:textureDimensions:channelEncoding:")
    protected native @Pointer long init(float smoothness, String name, VectorInt2 textureDimensions, MDLTextureChannelEncoding channelEncoding);
    @Method(selector = "initScalarNoiseWithSmoothness:name:textureDimensions:channelCount:channelEncoding:grayscale:")
    protected native @Pointer long init(float smoothness, String name, VectorInt2 textureDimensions, int channelCount, MDLTextureChannelEncoding channelEncoding, boolean grayscale);
    /*</methods>*/
}
