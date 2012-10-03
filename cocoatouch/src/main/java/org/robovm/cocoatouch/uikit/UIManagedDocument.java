/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIManagedDocument /*</name>*/ 
    extends /*<extends>*/ UIDocument /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIManagedDocument /*</name>*/.class);
    }

    /*<constructors>*/
    public UIManagedDocument() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("managedObjectContext") public native @Type("NSManagedObjectContext *") NSManagedObjectContext getManagedObjectContext();
    @Bind("managedObjectModel") public native @Type("NSManagedObjectModel *") NSManagedObjectModel getManagedObjectModel();
    @Bind("modelConfiguration") public native @Type("NSString *") String getModelConfiguration();
    @Bind("setModelConfiguration:") public native void setModelConfiguration(@Type("NSString *") String v);
    @Bind("persistentStoreOptions") public native @Type("NSDictionary *") NSDictionary getPersistentStoreOptions();
    @Bind("setPersistentStoreOptions:") public native void setPersistentStoreOptions(@Type("NSDictionary *") NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    @Bind("persistentStoreName") public native static @Type("NSString *") String getPersistentStoreName();
    @Bind("configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:") public native @Type("BOOL") boolean configurePersistentStoreCoordinator(@Type("NSURL *") NSURL storeURL, @Type("NSString *") String fileType, @Type("NSString *") String configuration, @Type("NSDictionary *") NSDictionary storeOptions, @Type("NSError **") Ptr<NSError> error);
    @Bind("additionalContentForURL:error:") public native @Type("id") NSObject getAdditionalContent(@Type("NSURL *") NSURL absoluteURL, @Type("NSError **") Ptr<NSError> error);
    @Bind("persistentStoreTypeForFileType:") public native @Type("NSString *") String getPersistentStoreType(@Type("NSString *") String fileType);
    @Bind("readAdditionalContentFromURL:error:") public native @Type("BOOL") boolean readAdditionalContent(@Type("NSURL *") NSURL absoluteURL, @Type("NSError **") Ptr<NSError> error);
    @Bind("writeAdditionalContent:toURL:originalContentsURL:error:") public native @Type("BOOL") boolean writeAdditionalContent(@Type("id") NSObject content, @Type("NSURL *") NSURL absoluteURL, @Type("NSURL *") NSURL absoluteOriginalContentsURL, @Type("NSError **") Ptr<NSError> error);
    /*</methods>*/

}
