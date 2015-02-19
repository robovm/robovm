/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATiledLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CATiledLayerPtr extends Ptr<CATiledLayer, CATiledLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CATiledLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CATiledLayer() {}
    protected CATiledLayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "levelsOfDetail")
    public native @MachineSizedUInt long getLevelsOfDetail();
    @Property(selector = "setLevelsOfDetail:")
    public native void setLevelsOfDetail(@MachineSizedUInt long v);
    @Property(selector = "levelsOfDetailBias")
    public native @MachineSizedUInt long getLevelsOfDetailBias();
    @Property(selector = "setLevelsOfDetailBias:")
    public native void setLevelsOfDetailBias(@MachineSizedUInt long v);
    @Property(selector = "tileSize")
    public native @ByVal CGSize getTileSize();
    @Property(selector = "setTileSize:")
    public native void setTileSize(@ByVal CGSize v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "fadeDuration")
    public static native double getFadeDuration();
    /*</methods>*/
}
