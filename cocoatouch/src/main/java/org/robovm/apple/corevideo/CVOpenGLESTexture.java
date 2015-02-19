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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVOpenGLESTexture/*</name>*/ 
    extends /*<extends>*/CVImageBuffer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVOpenGLESTexturePtr extends Ptr<CVOpenGLESTexture, CVOpenGLESTexturePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CVOpenGLESTexture.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public float[] getCleanTexCoords() {
        FloatPtr lowerLeft = new FloatPtr();
        FloatPtr lowerRight = new FloatPtr();
        FloatPtr upperRight = new FloatPtr();
        FloatPtr upperLeft = new FloatPtr();
        getCleanTexCoords(lowerLeft, lowerRight, upperRight, upperLeft);
        float[] coords = {lowerLeft.get(), lowerRight.get(), upperRight.get(), upperLeft.get()};
        return coords;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CVOpenGLESTextureGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CVOpenGLESTextureGetTarget", optional=true)
    public native int getTarget();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CVOpenGLESTextureGetName", optional=true)
    public native int getName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CVOpenGLESTextureIsFlipped", optional=true)
    public native boolean isFlipped();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CVOpenGLESTextureGetCleanTexCoords", optional=true)
    protected native void getCleanTexCoords(FloatPtr lowerLeft, FloatPtr lowerRight, FloatPtr upperRight, FloatPtr upperLeft);
    /*</methods>*/
}
