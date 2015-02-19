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
package org.robovm.apple.photos;

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
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.avfoundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Photos") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHPhotoLibrary/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PHPhotoLibraryPtr extends Ptr<PHPhotoLibrary, PHPhotoLibraryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHPhotoLibrary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHPhotoLibrary() {}
    protected PHPhotoLibrary(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param changeBlock
     * @return
     * @throws NSErrorException
     */
    public boolean performChangesAndWait(@Block Runnable changeBlock) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = performChangesAndWait(changeBlock, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "performChanges:completionHandler:")
    public native void performChanges(@Block Runnable changeBlock, @Block VoidBlock2<Boolean, NSError> completionHandler);
    @Method(selector = "performChangesAndWait:error:")
    protected native boolean performChangesAndWait(@Block Runnable changeBlock, NSError.NSErrorPtr error);
    @Method(selector = "registerChangeObserver:")
    public native void registerChangeObserver(PHPhotoLibraryChangeObserver observer);
    @Method(selector = "unregisterChangeObserver:")
    public native void unregisterChangeObserver(PHPhotoLibraryChangeObserver observer);
    @Method(selector = "sharedPhotoLibrary")
    public static native PHPhotoLibrary getSharedPhotoLibrary();
    @Method(selector = "authorizationStatus")
    public static native PHAuthorizationStatus getAuthorizationStatus();
    @Method(selector = "requestAuthorization:")
    public static native void requestAuthorization(@Block VoidBlock1<PHAuthorizationStatus> handler);
    /*</methods>*/
}
