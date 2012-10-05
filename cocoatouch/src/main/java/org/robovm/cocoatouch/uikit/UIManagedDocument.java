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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIManagedDocument /*</name>*/.class);

    /*<constructors>*/
    protected UIManagedDocument(SkipInit skipInit) { super(skipInit); }
    public UIManagedDocument() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/managedObjectContext">@property(nonatomic, retain, readonly) NSManagedObjectContext *managedObjectContext</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("managedObjectContext") public native NSManagedObjectContext getManagedObjectContext();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/managedObjectModel">@property(nonatomic, retain, readonly) NSManagedObjectModel *managedObjectModel</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("managedObjectModel") public native NSManagedObjectModel getManagedObjectModel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/modelConfiguration">@property(nonatomic, copy) NSString *modelConfiguration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("modelConfiguration") public native String getModelConfiguration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/modelConfiguration">@property(nonatomic, copy) NSString *modelConfiguration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setModelConfiguration:") public native void setModelConfiguration(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/persistentStoreOptions">@property(nonatomic, copy) NSDictionary *persistentStoreOptions</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("persistentStoreOptions") public native NSDictionary getPersistentStoreOptions();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/persistentStoreOptions">@property(nonatomic, copy) NSDictionary *persistentStoreOptions</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setPersistentStoreOptions:") public native void setPersistentStoreOptions(NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector persistentStoreName = Selector.register("persistentStoreName");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getPersistentStoreName(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/clm/UIManagedDocument/persistentStoreName">+ (NSString *)persistentStoreName</a>
     * @since Available in iOS 5.0 and later.
     */
    public static String getPersistentStoreName() {
        return objc_getPersistentStoreName(objCClass, persistentStoreName);
    }
    
    private static final Selector configurePersistentStoreCoordinatorForURL$ofType$modelConfiguration$storeOptions$error$ = Selector.register("configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_configurePersistentStoreCoordinator(UIManagedDocument __self__, Selector __cmd__, NSURL storeURL, String fileType, String configuration, NSDictionary storeOptions, Ptr<NSError> error);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_configurePersistentStoreCoordinatorSuper(ObjCSuper __super__, UIManagedDocument __self__, Selector __cmd__, NSURL storeURL, String fileType, String configuration, NSDictionary storeOptions, Ptr<NSError> error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:">- (BOOL)configurePersistentStoreCoordinatorForURL:(NSURL *)storeURL ofType:(NSString *)fileType modelConfiguration:(NSString *)configuration storeOptions:(NSDictionary *)storeOptions error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean configurePersistentStoreCoordinator(NSURL storeURL, String fileType, String configuration, NSDictionary storeOptions, Ptr<NSError> error) {
        if (customClass) { return objc_configurePersistentStoreCoordinatorSuper(getSuper(), this, configurePersistentStoreCoordinatorForURL$ofType$modelConfiguration$storeOptions$error$, storeURL, fileType, configuration, storeOptions, error); } else { return objc_configurePersistentStoreCoordinator(this, configurePersistentStoreCoordinatorForURL$ofType$modelConfiguration$storeOptions$error$, storeURL, fileType, configuration, storeOptions, error); }
    }
    
    private static final Selector additionalContentForURL$error$ = Selector.register("additionalContentForURL:error:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_getAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_getAdditionalContentSuper(ObjCSuper __super__, UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/additionalContentForURL:error:">- (id)additionalContentForURL:(NSURL *)absoluteURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject getAdditionalContent(NSURL absoluteURL, Ptr<NSError> error) {
        if (customClass) { return objc_getAdditionalContentSuper(getSuper(), this, additionalContentForURL$error$, absoluteURL, error); } else { return objc_getAdditionalContent(this, additionalContentForURL$error$, absoluteURL, error); }
    }
    
    private static final Selector persistentStoreTypeForFileType$ = Selector.register("persistentStoreTypeForFileType:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getPersistentStoreType(UIManagedDocument __self__, Selector __cmd__, String fileType);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getPersistentStoreTypeSuper(ObjCSuper __super__, UIManagedDocument __self__, Selector __cmd__, String fileType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/persistentStoreTypeForFileType:">- (NSString *)persistentStoreTypeForFileType:(NSString *)fileType</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getPersistentStoreType(String fileType) {
        if (customClass) { return objc_getPersistentStoreTypeSuper(getSuper(), this, persistentStoreTypeForFileType$, fileType); } else { return objc_getPersistentStoreType(this, persistentStoreTypeForFileType$, fileType); }
    }
    
    private static final Selector readAdditionalContentFromURL$error$ = Selector.register("readAdditionalContentFromURL:error:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_readAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_readAdditionalContentSuper(ObjCSuper __super__, UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/readAdditionalContentFromURL:error:">- (BOOL)readAdditionalContentFromURL:(NSURL *)absoluteURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean readAdditionalContent(NSURL absoluteURL, Ptr<NSError> error) {
        if (customClass) { return objc_readAdditionalContentSuper(getSuper(), this, readAdditionalContentFromURL$error$, absoluteURL, error); } else { return objc_readAdditionalContent(this, readAdditionalContentFromURL$error$, absoluteURL, error); }
    }
    
    private static final Selector writeAdditionalContent$toURL$originalContentsURL$error$ = Selector.register("writeAdditionalContent:toURL:originalContentsURL:error:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_writeAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, Ptr<NSError> error);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_writeAdditionalContentSuper(ObjCSuper __super__, UIManagedDocument __self__, Selector __cmd__, NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, Ptr<NSError> error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/writeAdditionalContent:toURL:originalContentsURL:error:">- (BOOL)writeAdditionalContent:(id)content toURL:(NSURL *)absoluteURL originalContentsURL:(NSURL *)absoluteOriginalContentsURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean writeAdditionalContent(NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, Ptr<NSError> error) {
        if (customClass) { return objc_writeAdditionalContentSuper(getSuper(), this, writeAdditionalContent$toURL$originalContentsURL$error$, content, absoluteURL, absoluteOriginalContentsURL, error); } else { return objc_writeAdditionalContent(this, writeAdditionalContent$toURL$originalContentsURL$error$, content, absoluteURL, absoluteOriginalContentsURL, error); }
    }
    /*</methods>*/

}
