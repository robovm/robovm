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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLTextureFilter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLTextureFilterPtr extends Ptr<MDLTextureFilter, MDLTextureFilterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLTextureFilter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLTextureFilter() {}
    protected MDLTextureFilter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "sWrapMode")
    public native MDLMaterialTextureWrapMode getSWrapMode();
    @Property(selector = "setSWrapMode:")
    public native void setSWrapMode(MDLMaterialTextureWrapMode v);
    @Property(selector = "tWrapMode")
    public native MDLMaterialTextureWrapMode getTWrapMode();
    @Property(selector = "setTWrapMode:")
    public native void setTWrapMode(MDLMaterialTextureWrapMode v);
    @Property(selector = "rWrapMode")
    public native MDLMaterialTextureWrapMode getRWrapMode();
    @Property(selector = "setRWrapMode:")
    public native void setRWrapMode(MDLMaterialTextureWrapMode v);
    @Property(selector = "minFilter")
    public native MDLMaterialTextureFilterMode getMinFilter();
    @Property(selector = "setMinFilter:")
    public native void setMinFilter(MDLMaterialTextureFilterMode v);
    @Property(selector = "magFilter")
    public native MDLMaterialTextureFilterMode getMagFilter();
    @Property(selector = "setMagFilter:")
    public native void setMagFilter(MDLMaterialTextureFilterMode v);
    @Property(selector = "mipFilter")
    public native MDLMaterialMipMapFilterMode getMipFilter();
    @Property(selector = "setMipFilter:")
    public native void setMipFilter(MDLMaterialMipMapFilterMode v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
