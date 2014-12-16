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
package org.robovm.apple.foundation;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileVersion/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFileVersionPtr extends Ptr<NSFileVersion, NSFileVersionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileVersion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFileVersion() {}
    protected NSFileVersion(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "localizedName")
    public native String getLocalizedName();
    @Property(selector = "localizedNameOfSavingComputer")
    public native String getLocalizedNameOfSavingComputer();
    @Property(selector = "modificationDate")
    public native NSDate getModificationDate();
    @Property(selector = "persistentIdentifier")
    public native NSCoding getPersistentIdentifier();
    @Property(selector = "isConflict")
    public native boolean isConflict();
    @Property(selector = "isResolved")
    public native boolean isResolved();
    @Property(selector = "setResolved:")
    public native void setResolved(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "hasLocalContents")
    public native boolean isHasLocalContents();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "hasThumbnail")
    public native boolean isHasThumbnail();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param url
     * @param options
     * @return
     * @throws NSErrorException
     */
    public NSURL replaceItemAtURL(NSURL url, NSFileVersionReplacingOptions options) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSURL result = replaceItemAtURL(url, options, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @return
     * @throws NSErrorException
     */
    public boolean remove() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = remove(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param url
     * @return
     * @throws NSErrorException
     */
    public static boolean removeOtherItemVersionsAtURL(NSURL url) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = removeOtherItemVersionsAtURL(url, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "replaceItemAtURL:options:error:")
    protected native NSURL replaceItemAtURL(NSURL url, NSFileVersionReplacingOptions options, NSError.NSErrorPtr error);
    @Method(selector = "removeAndReturnError:")
    protected native boolean remove(NSError.NSErrorPtr outError);
    @Method(selector = "currentVersionOfItemAtURL:")
    public static native NSFileVersion getCurrentItemVersionAtURL(NSURL url);
    @Method(selector = "otherVersionsOfItemAtURL:")
    public static native NSArray<NSFileVersion> getOtherItemVersionsAtURL(NSURL url);
    @Method(selector = "unresolvedConflictVersionsOfItemAtURL:")
    public static native NSArray<NSFileVersion> getUnresolvedConflictItemVersionsAtURL(NSURL url);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getNonlocalVersionsOfItemAtURL:completionHandler:")
    public static native void getNonlocalItemVersionsAtURL(NSURL url, @Block VoidBlock2<NSArray<NSFileVersion>, NSError> completionHandler);
    @Method(selector = "versionOfItemAtURL:forPersistentIdentifier:")
    public static native NSFileVersion getItemVersionAtURL(NSURL url, NSObject persistentIdentifier);
    @Method(selector = "removeOtherVersionsOfItemAtURL:error:")
    protected static native boolean removeOtherItemVersionsAtURL(NSURL url, NSError.NSErrorPtr outError);
    /*</methods>*/
}
