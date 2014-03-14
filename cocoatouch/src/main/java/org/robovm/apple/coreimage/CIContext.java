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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.opengles.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CIContextPtr extends Ptr<CIContext, CIContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CIContext() {}
    protected CIContext(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public CIContext(NSDictionary<?, ?> options) {
        super(contextWithOptions$(options));
    }
    public CIContext(EAGLContext eaglContext) {
        super(contextWithEAGLContext$(eaglContext));
    }
    public CIContext(EAGLContext eaglContext, NSDictionary<?, ?> options) {
        super(contextWithEAGLContext$options$(eaglContext, options));
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCIContextOutputColorSpace")
    public static native NSString KeyOutputColorSpace();
    @GlobalValue(symbol="kCIContextWorkingColorSpace")
    public static native NSString KeyWorkingColorSpace();
    @GlobalValue(symbol="kCIContextUseSoftwareRenderer")
    public static native NSString KeyUseSoftwareRenderer();
    
    @Method(selector = "drawImage:atPoint:fromRect:")
    public native void drawImage(CIImage im, @ByVal CGPoint p, @ByVal CGRect src);
    @Method(selector = "drawImage:inRect:fromRect:")
    public native void drawImage(CIImage im, @ByVal CGRect dest, @ByVal CGRect src);
    @Method(selector = "createCGImage:fromRect:")
    public native CGImage createCGImage(CIImage im, @ByVal CGRect r);
    @Method(selector = "createCGImage:fromRect:format:colorSpace:")
    public native CGImage createCGImage(CIImage im, @ByVal CGRect r, int f, CGColorSpace cs);
    @Method(selector = "render:toBitmap:rowBytes:bounds:format:colorSpace:")
    public native void render(CIImage im, VoidPtr data, int rb, @ByVal CGRect r, int f, CGColorSpace cs);
    @Method(selector = "inputImageMaximumSize")
    public native @ByVal CGSize getInputImageMaximumSize();
    @Method(selector = "outputImageMaximumSize")
    public native @ByVal CGSize getOutputImageMaximumSize();
    @Method(selector = "contextWithOptions:")
    protected static native @Pointer long contextWithOptions$(NSDictionary<?, ?> dict);
    @Method(selector = "contextWithEAGLContext:")
    protected static native @Pointer long contextWithEAGLContext$(EAGLContext eaglContext);
    @Method(selector = "contextWithEAGLContext:options:")
    protected static native @Pointer long contextWithEAGLContext$options$(EAGLContext eaglContext, NSDictionary<?, ?> dict);
    /*</methods>*/
}
