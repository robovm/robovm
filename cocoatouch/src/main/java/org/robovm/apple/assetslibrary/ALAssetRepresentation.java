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
package org.robovm.apple.assetslibrary;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AssetsLibrary") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetRepresentation/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ALAssetRepresentationPtr extends Ptr<ALAssetRepresentation, ALAssetRepresentationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(ALAssetRepresentation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public ALAssetRepresentation() {}
    protected ALAssetRepresentation(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param offset
     * @param length
     * @return
     * @throws NSErrorException
     */
    public byte[] getBytes(int offset, int length) {
        byte[] bytes = new byte[length];
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        getBytes(VM.getArrayValuesAddress(bytes), offset, length, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return bytes;
    }
    /*<methods>*/
    @Method(selector = "UTI")
    public native String getUTI();
    @Method(selector = "dimensions")
    public native @ByVal CGSize getDimensions();
    @Method(selector = "size")
    public native long getSize();
    @Method(selector = "getBytes:fromOffset:length:error:")
    protected native @MachineSizedUInt long getBytes(@Pointer long buffer, long offset, @MachineSizedUInt long length, NSError.NSErrorPtr error);
    @Method(selector = "fullResolutionImage")
    public native CGImage getFullResolutionImage();
    @Method(selector = "CGImageWithOptions:")
    public native CGImage getCGImage(CGImageSourceOptions options);
    @Method(selector = "fullScreenImage")
    public native CGImage getFullScreenImage();
    @Method(selector = "url")
    public native NSURL getUrl();
    @Method(selector = "metadata")
    public native CGImageProperties getMetadata();
    @Method(selector = "orientation")
    public native ALAssetOrientation getOrientation();
    @Method(selector = "scale")
    public native float getScale();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "filename")
    public native String getFilename();
    /*</methods>*/
}
