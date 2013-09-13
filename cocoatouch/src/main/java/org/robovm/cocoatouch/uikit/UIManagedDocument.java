/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html">UIManagedDocument Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIManagedDocument /*</name>*/ 
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
    
    private static final Selector managedObjectContext = Selector.register("managedObjectContext");
    @Bridge private native static NSManagedObjectContext objc_getManagedObjectContext(UIManagedDocument __self__, Selector __cmd__);
    @Bridge private native static NSManagedObjectContext objc_getManagedObjectContextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/managedObjectContext">@property(nonatomic, retain, readonly) NSManagedObjectContext *managedObjectContext</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSManagedObjectContext getManagedObjectContext() {
        if (customClass) { return objc_getManagedObjectContextSuper(getSuper(), managedObjectContext); } else { return objc_getManagedObjectContext(this, managedObjectContext); }
    }
    
    private static final Selector managedObjectModel = Selector.register("managedObjectModel");
    @Bridge private native static NSManagedObjectModel objc_getManagedObjectModel(UIManagedDocument __self__, Selector __cmd__);
    @Bridge private native static NSManagedObjectModel objc_getManagedObjectModelSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/managedObjectModel">@property(nonatomic, retain, readonly) NSManagedObjectModel *managedObjectModel</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSManagedObjectModel getManagedObjectModel() {
        if (customClass) { return objc_getManagedObjectModelSuper(getSuper(), managedObjectModel); } else { return objc_getManagedObjectModel(this, managedObjectModel); }
    }
    
    private static final Selector modelConfiguration = Selector.register("modelConfiguration");
    @Bridge private native static String objc_getModelConfiguration(UIManagedDocument __self__, Selector __cmd__);
    @Bridge private native static String objc_getModelConfigurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/modelConfiguration">@property(nonatomic, copy) NSString *modelConfiguration</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getModelConfiguration() {
        if (customClass) { return objc_getModelConfigurationSuper(getSuper(), modelConfiguration); } else { return objc_getModelConfiguration(this, modelConfiguration); }
    }
    
    private static final Selector setModelConfiguration$ = Selector.register("setModelConfiguration:");
    @Bridge private native static void objc_setModelConfiguration(UIManagedDocument __self__, Selector __cmd__, String modelConfiguration);
    @Bridge private native static void objc_setModelConfigurationSuper(ObjCSuper __super__, Selector __cmd__, String modelConfiguration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/modelConfiguration">@property(nonatomic, copy) NSString *modelConfiguration</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setModelConfiguration(String modelConfiguration) {
        if (customClass) { objc_setModelConfigurationSuper(getSuper(), setModelConfiguration$, modelConfiguration); } else { objc_setModelConfiguration(this, setModelConfiguration$, modelConfiguration); }
    }
    
    private static final Selector persistentStoreOptions = Selector.register("persistentStoreOptions");
    @Bridge private native static NSDictionary objc_getPersistentStoreOptions(UIManagedDocument __self__, Selector __cmd__);
    @Bridge private native static NSDictionary objc_getPersistentStoreOptionsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/persistentStoreOptions">@property(nonatomic, copy) NSDictionary *persistentStoreOptions</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getPersistentStoreOptions() {
        if (customClass) { return objc_getPersistentStoreOptionsSuper(getSuper(), persistentStoreOptions); } else { return objc_getPersistentStoreOptions(this, persistentStoreOptions); }
    }
    
    private static final Selector setPersistentStoreOptions$ = Selector.register("setPersistentStoreOptions:");
    @Bridge private native static void objc_setPersistentStoreOptions(UIManagedDocument __self__, Selector __cmd__, NSDictionary persistentStoreOptions);
    @Bridge private native static void objc_setPersistentStoreOptionsSuper(ObjCSuper __super__, Selector __cmd__, NSDictionary persistentStoreOptions);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instp/UIManagedDocument/persistentStoreOptions">@property(nonatomic, copy) NSDictionary *persistentStoreOptions</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPersistentStoreOptions(NSDictionary persistentStoreOptions) {
        if (customClass) { objc_setPersistentStoreOptionsSuper(getSuper(), setPersistentStoreOptions$, persistentStoreOptions); } else { objc_setPersistentStoreOptions(this, setPersistentStoreOptions$, persistentStoreOptions); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector persistentStoreName = Selector.register("persistentStoreName");
    @Bridge private native static String objc_getPersistentStoreName(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/clm/UIManagedDocument/persistentStoreName">+ (NSString *)persistentStoreName</a>
     * @since Available in iOS 5.0 and later.
     */
    public static String getPersistentStoreName() {
        return objc_getPersistentStoreName(objCClass, persistentStoreName);
    }
    
    private static final Selector configurePersistentStoreCoordinatorForURL$ofType$modelConfiguration$storeOptions$error$ = Selector.register("configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:");
    @Bridge private native static boolean objc_configurePersistentStoreCoordinator(UIManagedDocument __self__, Selector __cmd__, NSURL storeURL, String fileType, String configuration, NSDictionary storeOptions, Ptr<NSError> error);
    @Bridge private native static boolean objc_configurePersistentStoreCoordinatorSuper(ObjCSuper __super__, Selector __cmd__, NSURL storeURL, String fileType, String configuration, NSDictionary storeOptions, Ptr<NSError> error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:">- (BOOL)configurePersistentStoreCoordinatorForURL:(NSURL *)storeURL ofType:(NSString *)fileType modelConfiguration:(NSString *)configuration storeOptions:(NSDictionary *)storeOptions error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean configurePersistentStoreCoordinator(NSURL storeURL, String fileType, String configuration, NSDictionary storeOptions, Ptr<NSError> error) {
        if (customClass) { return objc_configurePersistentStoreCoordinatorSuper(getSuper(), configurePersistentStoreCoordinatorForURL$ofType$modelConfiguration$storeOptions$error$, storeURL, fileType, configuration, storeOptions, error); } else { return objc_configurePersistentStoreCoordinator(this, configurePersistentStoreCoordinatorForURL$ofType$modelConfiguration$storeOptions$error$, storeURL, fileType, configuration, storeOptions, error); }
    }
    
    private static final Selector additionalContentForURL$error$ = Selector.register("additionalContentForURL:error:");
    @Bridge private native static NSObject objc_getAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    @Bridge private native static NSObject objc_getAdditionalContentSuper(ObjCSuper __super__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/additionalContentForURL:error:">- (id)additionalContentForURL:(NSURL *)absoluteURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject getAdditionalContent(NSURL absoluteURL, Ptr<NSError> error) {
        if (customClass) { return objc_getAdditionalContentSuper(getSuper(), additionalContentForURL$error$, absoluteURL, error); } else { return objc_getAdditionalContent(this, additionalContentForURL$error$, absoluteURL, error); }
    }
    
    private static final Selector persistentStoreTypeForFileType$ = Selector.register("persistentStoreTypeForFileType:");
    @Bridge private native static String objc_getPersistentStoreType(UIManagedDocument __self__, Selector __cmd__, String fileType);
    @Bridge private native static String objc_getPersistentStoreTypeSuper(ObjCSuper __super__, Selector __cmd__, String fileType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/persistentStoreTypeForFileType:">- (NSString *)persistentStoreTypeForFileType:(NSString *)fileType</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getPersistentStoreType(String fileType) {
        if (customClass) { return objc_getPersistentStoreTypeSuper(getSuper(), persistentStoreTypeForFileType$, fileType); } else { return objc_getPersistentStoreType(this, persistentStoreTypeForFileType$, fileType); }
    }
    
    private static final Selector readAdditionalContentFromURL$error$ = Selector.register("readAdditionalContentFromURL:error:");
    @Bridge private native static boolean objc_readAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    @Bridge private native static boolean objc_readAdditionalContentSuper(ObjCSuper __super__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/readAdditionalContentFromURL:error:">- (BOOL)readAdditionalContentFromURL:(NSURL *)absoluteURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean readAdditionalContent(NSURL absoluteURL, Ptr<NSError> error) {
        if (customClass) { return objc_readAdditionalContentSuper(getSuper(), readAdditionalContentFromURL$error$, absoluteURL, error); } else { return objc_readAdditionalContent(this, readAdditionalContentFromURL$error$, absoluteURL, error); }
    }
    
    private static final Selector writeAdditionalContent$toURL$originalContentsURL$error$ = Selector.register("writeAdditionalContent:toURL:originalContentsURL:error:");
    @Bridge private native static boolean objc_writeAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, Ptr<NSError> error);
    @Bridge private native static boolean objc_writeAdditionalContentSuper(ObjCSuper __super__, Selector __cmd__, NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, Ptr<NSError> error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIManagedDocument_Class/Reference/Reference.html#//apple_ref/occ/instm/UIManagedDocument/writeAdditionalContent:toURL:originalContentsURL:error:">- (BOOL)writeAdditionalContent:(id)content toURL:(NSURL *)absoluteURL originalContentsURL:(NSURL *)absoluteOriginalContentsURL error:(NSError **)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean writeAdditionalContent(NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, Ptr<NSError> error) {
        if (customClass) { return objc_writeAdditionalContentSuper(getSuper(), writeAdditionalContent$toURL$originalContentsURL$error$, content, absoluteURL, absoluteOriginalContentsURL, error); } else { return objc_writeAdditionalContent(this, writeAdditionalContent$toURL$originalContentsURL$error$, content, absoluteURL, absoluteOriginalContentsURL, error); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("managedObjectContext") public static NSManagedObjectContext getManagedObjectContext(UIManagedDocument __self__, Selector __cmd__) { return __self__.getManagedObjectContext(); }
        @Callback @BindSelector("managedObjectModel") public static NSManagedObjectModel getManagedObjectModel(UIManagedDocument __self__, Selector __cmd__) { return __self__.getManagedObjectModel(); }
        @Callback @BindSelector("modelConfiguration") public static String getModelConfiguration(UIManagedDocument __self__, Selector __cmd__) { return __self__.getModelConfiguration(); }
        @Callback @BindSelector("setModelConfiguration:") public static void setModelConfiguration(UIManagedDocument __self__, Selector __cmd__, String modelConfiguration) { __self__.setModelConfiguration(modelConfiguration); }
        @Callback @BindSelector("persistentStoreOptions") public static NSDictionary getPersistentStoreOptions(UIManagedDocument __self__, Selector __cmd__) { return __self__.getPersistentStoreOptions(); }
        @Callback @BindSelector("setPersistentStoreOptions:") public static void setPersistentStoreOptions(UIManagedDocument __self__, Selector __cmd__, NSDictionary persistentStoreOptions) { __self__.setPersistentStoreOptions(persistentStoreOptions); }
        @Callback @BindSelector("configurePersistentStoreCoordinatorForURL:ofType:modelConfiguration:storeOptions:error:") public static boolean configurePersistentStoreCoordinator(UIManagedDocument __self__, Selector __cmd__, NSURL storeURL, String fileType, String configuration, NSDictionary storeOptions, Ptr<NSError> error) { return __self__.configurePersistentStoreCoordinator(storeURL, fileType, configuration, storeOptions, error); }
        @Callback @BindSelector("additionalContentForURL:error:") public static NSObject getAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error) { return __self__.getAdditionalContent(absoluteURL, error); }
        @Callback @BindSelector("persistentStoreTypeForFileType:") public static String getPersistentStoreType(UIManagedDocument __self__, Selector __cmd__, String fileType) { return __self__.getPersistentStoreType(fileType); }
        @Callback @BindSelector("readAdditionalContentFromURL:error:") public static boolean readAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSURL absoluteURL, Ptr<NSError> error) { return __self__.readAdditionalContent(absoluteURL, error); }
        @Callback @BindSelector("writeAdditionalContent:toURL:originalContentsURL:error:") public static boolean writeAdditionalContent(UIManagedDocument __self__, Selector __cmd__, NSObject content, NSURL absoluteURL, NSURL absoluteOriginalContentsURL, Ptr<NSError> error) { return __self__.writeAdditionalContent(content, absoluteURL, absoluteOriginalContentsURL, error); }
    }
    /*</callbacks>*/

}
