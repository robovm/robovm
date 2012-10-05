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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html">UIManagedDocument Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/managedObjectContext">@property(nonatomic, retain, readonly) NSManagedObjectContext *managedObjectContext</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("managedObjectContext") public native @Type("NSManagedObjectContext *") NSManagedObjectContext getManagedObjectContext();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/managedObjectModel">@property(nonatomic, retain, readonly) NSManagedObjectModel *managedObjectModel</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("managedObjectModel") public native @Type("NSManagedObjectModel *") NSManagedObjectModel getManagedObjectModel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/modelConfiguration">@property(nonatomic, copy) NSString *modelConfiguration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("modelConfiguration") public native @Type("NSString *") String getModelConfiguration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/modelConfiguration">@property(nonatomic, copy) NSString *modelConfiguration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setModelConfiguration:") public native void setModelConfiguration(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/persistentStoreOptions">@property(nonatomic, copy) NSDictionary *persistentStoreOptions</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("persistentStoreOptions") public native @Type("NSDictionary *") NSDictionary getPersistentStoreOptions();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/persistentStoreOptions">@property(nonatomic, copy) NSDictionary *persistentStoreOptions</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setPersistentStoreOptions:") public native void setPersistentStoreOptions(@Type("NSDictionary *") NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/clm/UIManagedDocument/persistentStoreName">+ (NSString *)persistentStoreName</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("persistentStoreName") public native static @Type("NSString *") String getPersistentStoreName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:">- (BOOL)configurePersistentStoreCoordinatorForURL:(NSURL *)storeURL ofType:(NSString *)fileType modelConfiguration:(NSString *)configuration storeOptions:(NSDictionary *)storeOptions error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:") public native @Type("BOOL") boolean configurePersistentStoreCoordinator(@Type("NSURL *") NSURL storeURL, @Type("NSString *") String fileType, @Type("NSString *") String configuration, @Type("NSDictionary *") NSDictionary storeOptions, @Type("NSError **") Ptr<NSError> error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/additionalContentForURL:error:">- (id)additionalContentForURL:(NSURL *)absoluteURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("additionalContentForURL:error:") public native @Type("id") NSObject getAdditionalContent(@Type("NSURL *") NSURL absoluteURL, @Type("NSError **") Ptr<NSError> error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/persistentStoreTypeForFileType:">- (NSString *)persistentStoreTypeForFileType:(NSString *)fileType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("persistentStoreTypeForFileType:") public native @Type("NSString *") String getPersistentStoreType(@Type("NSString *") String fileType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/readAdditionalContentFromURL:error:">- (BOOL)readAdditionalContentFromURL:(NSURL *)absoluteURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("readAdditionalContentFromURL:error:") public native @Type("BOOL") boolean readAdditionalContent(@Type("NSURL *") NSURL absoluteURL, @Type("NSError **") Ptr<NSError> error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/writeAdditionalContent:toURL:originalContentsURL:error:">- (BOOL)writeAdditionalContent:(id)content toURL:(NSURL *)absoluteURL originalContentsURL:(NSURL *)absoluteOriginalContentsURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("writeAdditionalContent:toURL:originalContentsURL:error:") public native @Type("BOOL") boolean writeAdditionalContent(@Type("id") NSObject content, @Type("NSURL *") NSURL absoluteURL, @Type("NSURL *") NSURL absoluteOriginalContentsURL, @Type("NSError **") Ptr<NSError> error);
    /*</methods>*/

}
