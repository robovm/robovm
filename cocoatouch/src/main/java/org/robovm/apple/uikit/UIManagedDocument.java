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
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIManagedDocument/*</name>*/ 
    extends /*<extends>*/UIDocument/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIManagedDocumentPtr extends Ptr<UIManagedDocument, UIManagedDocumentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIManagedDocument.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIManagedDocument() {}
    protected UIManagedDocument(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "managedObjectContext")
    public native NSManagedObjectContext getManagedObjectContext();
    @Property(selector = "managedObjectModel")
    public native NSManagedObjectModel getManagedObjectModel();
    @Property(selector = "persistentStoreOptions")
    public native NSPersistentStoreOptions getPersistentStoreOptions();
    @Property(selector = "setPersistentStoreOptions:")
    public native void setPersistentStoreOptions(NSPersistentStoreOptions v);
    @Property(selector = "modelConfiguration")
    public native String getModelConfiguration();
    @Property(selector = "setModelConfiguration:")
    public native void setModelConfiguration(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param storeURL
     * @param fileType
     * @param configuration
     * @param storeOptions
     * @return
     * @throws NSErrorException
     */
    public boolean configurePersistentStoreCoordinator(NSURL storeURL, String fileType, String configuration, NSPersistentStoreOptions storeOptions) {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = configurePersistentStoreCoordinator(storeURL, fileType, configuration, storeOptions, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param absoluteURL
     * @return
     * @throws NSErrorException
     */
    public boolean readAdditionalContent(NSURL absoluteURL) {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = readAdditionalContent(absoluteURL, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param absoluteURL
     * @return
     * @throws NSErrorException
     */
    public NSObject getAdditionalContent(NSURL absoluteURL) {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSObject result = getAdditionalContent(absoluteURL, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param content
     * @param absoluteURL
     * @param absoluteOriginalContentsURL
     * @return
     * @throws NSErrorException
     */
    public boolean writeAdditionalContent(NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL) {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = writeAdditionalContent(content, absoluteURL, absoluteOriginalContentsURL, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:")
    protected native boolean configurePersistentStoreCoordinator(NSURL storeURL, String fileType, String configuration, NSPersistentStoreOptions storeOptions, NSError.NSErrorPtr error);
    @Method(selector = "persistentStoreTypeForFileType:")
    public native String getPersistentStoreType(String fileType);
    @Method(selector = "readAdditionalContentFromURL:error:")
    protected native boolean readAdditionalContent(NSURL absoluteURL, NSError.NSErrorPtr error);
    @Method(selector = "additionalContentForURL:error:")
    protected native NSObject getAdditionalContent(NSURL absoluteURL, NSError.NSErrorPtr error);
    @Method(selector = "writeAdditionalContent:toURL:originalContentsURL:error:")
    protected native boolean writeAdditionalContent(NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, NSError.NSErrorPtr error);
    @Method(selector = "persistentStoreName")
    public static native String getPersistentStoreName();
    /*</methods>*/
}
