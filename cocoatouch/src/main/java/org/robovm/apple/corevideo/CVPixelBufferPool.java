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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVPixelBufferPool/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Notifications {
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeFreeBuffer(CVPixelBufferPool object, final VoidBlock1<CVPixelBufferPool> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(FreeBufferNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    CVPixelBufferPool object = a.getObject() != null ? a.getObject().as(CVPixelBufferPool.class) : null;
                    block.invoke(object);
                }
            });
        }
    }

    /*<ptr>*/public static class CVPixelBufferPoolPtr extends Ptr<CVPixelBufferPool, CVPixelBufferPoolPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CVPixelBufferPool.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CVPixelBufferPool create(CVPixelBufferPoolAttributes poolAttributes, CVPixelBufferAttributes pixelBufferAttributes) {
        CVPixelBufferPoolPtr ptr = new CVPixelBufferPoolPtr();
        create(null, poolAttributes, pixelBufferAttributes, ptr);
        return ptr.get();
    }
    public static CVPixelBufferPool create(CFAllocator allocator, CVPixelBufferPoolAttributes poolAttributes, CVPixelBufferAttributes pixelBufferAttributes) {
        CVPixelBufferPoolPtr ptr = new CVPixelBufferPoolPtr();
        create(allocator, poolAttributes, pixelBufferAttributes, ptr);
        return ptr.get();
    }
    public CVPixelBuffer createPixelBuffer() {
        CVPixelBuffer.CVPixelBufferPtr ptr = new CVPixelBuffer.CVPixelBufferPtr();
        createPixelBuffer(null, this, ptr);
        return ptr.get();
    }
    public CVPixelBuffer createPixelBuffer(CFAllocator allocator) {
        CVPixelBuffer.CVPixelBufferPtr ptr = new CVPixelBuffer.CVPixelBufferPtr();
        createPixelBuffer(allocator, this, ptr);
        return ptr.get();
    }
    public CVPixelBuffer createPixelBuffer(CVPixelBufferPoolAuxiliaryAttributes auxAttributes) {
        CVPixelBuffer.CVPixelBufferPtr ptr = new CVPixelBuffer.CVPixelBufferPtr();
        createPixelBuffer(null, this, auxAttributes, ptr);
        return ptr.get();
    }
    public CVPixelBuffer createPixelBuffer(CFAllocator allocator, CVPixelBufferPoolAuxiliaryAttributes auxAttributes) {
        CVPixelBuffer.CVPixelBufferPtr ptr = new CVPixelBuffer.CVPixelBufferPtr();
        createPixelBuffer(allocator, this, auxAttributes, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolFreeBufferNotification", optional=true)
    public static native NSString FreeBufferNotification();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferPoolGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferPoolCreate", optional=true)
    private static native CVReturn create(CFAllocator allocator, CVPixelBufferPoolAttributes poolAttributes, CVPixelBufferAttributes pixelBufferAttributes, CVPixelBufferPool.CVPixelBufferPoolPtr poolOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferPoolGetAttributes", optional=true)
    public native CVPixelBufferPoolAttributes getAttributes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferPoolGetPixelBufferAttributes", optional=true)
    public native CVPixelBufferAttributes getPixelBufferAttributes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferPoolCreatePixelBuffer", optional=true)
    private static native CVReturn createPixelBuffer(CFAllocator allocator, CVPixelBufferPool pixelBufferPool, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferPoolCreatePixelBufferWithAuxAttributes", optional=true)
    private static native CVReturn createPixelBuffer(CFAllocator allocator, CVPixelBufferPool pixelBufferPool, CVPixelBufferPoolAuxiliaryAttributes auxAttributes, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
    /*</methods>*/
}
