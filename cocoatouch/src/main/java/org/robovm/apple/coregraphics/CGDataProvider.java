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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGDataProvider/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGDataProviderPtr extends Ptr<CGDataProvider, CGDataProviderPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGDataProvider.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGDataProvider() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CGDataProvider create(File file) {
        if (file == null) {
            throw new NullPointerException("file");
        }
        return create(VM.getStringUTFChars(file.getAbsolutePath()));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataProviderGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataProviderCreateWithCFData", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGDataProvider create(NSData data);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataProviderCreateWithURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGDataProvider create(NSURL url);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataProviderCreateWithFilename", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGDataProvider create(@Pointer long filename);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataProviderCopyData", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSData getData();
    /*</methods>*/
}
