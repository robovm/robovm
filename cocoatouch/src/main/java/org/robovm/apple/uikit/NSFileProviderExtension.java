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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileProviderExtension/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFileProviderExtensionPtr extends Ptr<NSFileProviderExtension, NSFileProviderExtensionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileProviderExtension.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFileProviderExtension() {}
    protected NSFileProviderExtension(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param placeholderURL
     * @param metadata
     * @return
     * @throws NSErrorException
     */
    public static boolean writePlaceholder(NSURL placeholderURL, NSURLProperties metadata) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = writePlaceholder(placeholderURL, metadata, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "providerIdentifier")
    public native String getProviderIdentifier();
    @Method(selector = "documentStorageURL")
    public native NSURL getDocumentStorageURL();
    @Method(selector = "URLForItemWithPersistentIdentifier:")
    public native NSURL getURLForItem(String identifier);
    @Method(selector = "persistentIdentifierForItemAtURL:")
    public native String getPersistentIdentifierForItem(NSURL url);
    @Method(selector = "providePlaceholderAtURL:completionHandler:")
    public native void providePlaceholder(NSURL url, @Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "startProvidingItemAtURL:completionHandler:")
    public native void startProvidingItem(NSURL url, @Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "itemChangedAtURL:")
    public native void itemChanged(NSURL url);
    @Method(selector = "stopProvidingItemAtURL:")
    public native void stopProvidingItem(NSURL url);
    @Method(selector = "writePlaceholderAtURL:withMetadata:error:")
    protected static native boolean writePlaceholder(NSURL placeholderURL, NSURLProperties metadata, NSError.NSErrorPtr error);
    @Method(selector = "placeholderURLForURL:")
    public static native NSURL getPlaceholderURL(NSURL url);
    /*</methods>*/
}
