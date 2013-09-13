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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html">UIDocument Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIDocument /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIDocument /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIDocument /*</name>*/.class);

    /*<constructors>*/
    protected UIDocument(SkipInit skipInit) { super(skipInit); }
    public UIDocument() {}
    
    private static final Selector initWithFileURL$ = Selector.register("initWithFileURL:");
    @Bridge private native static @Pointer long objc_initWithFileURL(UIDocument __self__, Selector __cmd__, NSURL url);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/initWithFileURL:">- (id)initWithFileURL:(NSURL *)url</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIDocument(NSURL url) {
        super((SkipInit) null);
        initObject(objc_initWithFileURL(this, initWithFileURL$, url));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector documentState = Selector.register("documentState");
    @Bridge private native static UIDocumentState objc_getDocumentState(UIDocument __self__, Selector __cmd__);
    @Bridge private native static UIDocumentState objc_getDocumentStateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/documentState">@property(readonly) UIDocumentState documentState</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIDocumentState getDocumentState() {
        if (customClass) { return objc_getDocumentStateSuper(getSuper(), documentState); } else { return objc_getDocumentState(this, documentState); }
    }
    
    private static final Selector fileModificationDate = Selector.register("fileModificationDate");
    @Bridge private native static NSDate objc_getFileModificationDate(UIDocument __self__, Selector __cmd__);
    @Bridge private native static NSDate objc_getFileModificationDateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileModificationDate">@property(copy) NSDate *fileModificationDate</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDate getFileModificationDate() {
        if (customClass) { return objc_getFileModificationDateSuper(getSuper(), fileModificationDate); } else { return objc_getFileModificationDate(this, fileModificationDate); }
    }
    
    private static final Selector setFileModificationDate$ = Selector.register("setFileModificationDate:");
    @Bridge private native static void objc_setFileModificationDate(UIDocument __self__, Selector __cmd__, NSDate fileModificationDate);
    @Bridge private native static void objc_setFileModificationDateSuper(ObjCSuper __super__, Selector __cmd__, NSDate fileModificationDate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileModificationDate">@property(copy) NSDate *fileModificationDate</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setFileModificationDate(NSDate fileModificationDate) {
        if (customClass) { objc_setFileModificationDateSuper(getSuper(), setFileModificationDate$, fileModificationDate); } else { objc_setFileModificationDate(this, setFileModificationDate$, fileModificationDate); }
    }
    
    private static final Selector fileType = Selector.register("fileType");
    @Bridge private native static String objc_getFileType(UIDocument __self__, Selector __cmd__);
    @Bridge private native static String objc_getFileTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileType">@property(readonly, copy) NSString *fileType</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getFileType() {
        if (customClass) { return objc_getFileTypeSuper(getSuper(), fileType); } else { return objc_getFileType(this, fileType); }
    }
    
    private static final Selector fileURL = Selector.register("fileURL");
    @Bridge private native static NSURL objc_getFileURL(UIDocument __self__, Selector __cmd__);
    @Bridge private native static NSURL objc_getFileURLSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileURL">@property(readonly) NSURL *fileURL</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSURL getFileURL() {
        if (customClass) { return objc_getFileURLSuper(getSuper(), fileURL); } else { return objc_getFileURL(this, fileURL); }
    }
    
    private static final Selector localizedName = Selector.register("localizedName");
    @Bridge private native static String objc_getLocalizedName(UIDocument __self__, Selector __cmd__);
    @Bridge private native static String objc_getLocalizedNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/localizedName">@property(readonly, copy) NSString *localizedName</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getLocalizedName() {
        if (customClass) { return objc_getLocalizedNameSuper(getSuper(), localizedName); } else { return objc_getLocalizedName(this, localizedName); }
    }
    
    private static final Selector undoManager = Selector.register("undoManager");
    @Bridge private native static NSUndoManager objc_getUndoManager(UIDocument __self__, Selector __cmd__);
    @Bridge private native static NSUndoManager objc_getUndoManagerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/undoManager">@property(retain) NSUndoManager *undoManager</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSUndoManager getUndoManager() {
        if (customClass) { return objc_getUndoManagerSuper(getSuper(), undoManager); } else { return objc_getUndoManager(this, undoManager); }
    }
    
    private static final Selector setUndoManager$ = Selector.register("setUndoManager:");
    @Bridge private native static void objc_setUndoManager(UIDocument __self__, Selector __cmd__, NSUndoManager undoManager);
    @Bridge private native static void objc_setUndoManagerSuper(ObjCSuper __super__, Selector __cmd__, NSUndoManager undoManager);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/undoManager">@property(retain) NSUndoManager *undoManager</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setUndoManager(NSUndoManager undoManager) {
        if (customClass) { objc_setUndoManagerSuper(getSuper(), setUndoManager$, undoManager); } else { objc_setUndoManager(this, setUndoManager$, undoManager); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector autosaveWithCompletionHandler$ = Selector.register("autosaveWithCompletionHandler:");
    @Bridge private native static void objc_autoSave(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    @Bridge private native static void objc_autoSaveSuper(ObjCSuper __super__, Selector __cmd__, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/autosaveWithCompletionHandler:">- (void)autosaveWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void autoSave(VoidBooleanBlock completionHandler) {
        if (customClass) { objc_autoSaveSuper(getSuper(), autosaveWithCompletionHandler$, completionHandler); } else { objc_autoSave(this, autosaveWithCompletionHandler$, completionHandler); }
    }
    
    private static final Selector changeCountTokenForSaveOperation$ = Selector.register("changeCountTokenForSaveOperation:");
    @Bridge private native static NSObject objc_changeCountTokenForSaveOperation(UIDocument __self__, Selector __cmd__, UIDocumentSaveOperation saveOperation);
    @Bridge private native static NSObject objc_changeCountTokenForSaveOperationSuper(ObjCSuper __super__, Selector __cmd__, UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/changeCountTokenForSaveOperation:">- (id)changeCountTokenForSaveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject changeCountTokenForSaveOperation(UIDocumentSaveOperation saveOperation) {
        if (customClass) { return objc_changeCountTokenForSaveOperationSuper(getSuper(), changeCountTokenForSaveOperation$, saveOperation); } else { return objc_changeCountTokenForSaveOperation(this, changeCountTokenForSaveOperation$, saveOperation); }
    }
    
    private static final Selector closeWithCompletionHandler$ = Selector.register("closeWithCompletionHandler:");
    @Bridge private native static void objc_close(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    @Bridge private native static void objc_closeSuper(ObjCSuper __super__, Selector __cmd__, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/closeWithCompletionHandler:">- (void)closeWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void close(VoidBooleanBlock completionHandler) {
        if (customClass) { objc_closeSuper(getSuper(), closeWithCompletionHandler$, completionHandler); } else { objc_close(this, closeWithCompletionHandler$, completionHandler); }
    }
    
    private static final Selector contentsForType$error$ = Selector.register("contentsForType:error:");
    @Bridge private native static NSObject objc_contentsForType(UIDocument __self__, Selector __cmd__, String typeName, Ptr<NSError> outError);
    @Bridge private native static NSObject objc_contentsForTypeSuper(ObjCSuper __super__, Selector __cmd__, String typeName, Ptr<NSError> outError);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/contentsForType:error:">- (id)contentsForType:(NSString *)typeName error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject contentsForType(String typeName, Ptr<NSError> outError) {
        if (customClass) { return objc_contentsForTypeSuper(getSuper(), contentsForType$error$, typeName, outError); } else { return objc_contentsForType(this, contentsForType$error$, typeName, outError); }
    }
    
    private static final Selector disableEditing = Selector.register("disableEditing");
    @Bridge private native static void objc_disableEditing(UIDocument __self__, Selector __cmd__);
    @Bridge private native static void objc_disableEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/disableEditing">- (void)disableEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public void disableEditing() {
        if (customClass) { objc_disableEditingSuper(getSuper(), disableEditing); } else { objc_disableEditing(this, disableEditing); }
    }
    
    private static final Selector enableEditing = Selector.register("enableEditing");
    @Bridge private native static void objc_enableEditing(UIDocument __self__, Selector __cmd__);
    @Bridge private native static void objc_enableEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/enableEditing">- (void)enableEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public void enableEditing() {
        if (customClass) { objc_enableEditingSuper(getSuper(), enableEditing); } else { objc_enableEditing(this, enableEditing); }
    }
    
    private static final Selector finishedHandlingError$recovered$ = Selector.register("finishedHandlingError:recovered:");
    @Bridge private native static void objc_finishedHandlingError(UIDocument __self__, Selector __cmd__, NSError error, boolean recovered);
    @Bridge private native static void objc_finishedHandlingErrorSuper(ObjCSuper __super__, Selector __cmd__, NSError error, boolean recovered);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/finishedHandlingError:recovered:">- (void)finishedHandlingError:(NSError *)error recovered:(BOOL)recovered</a>
     * @since Available in iOS 5.0 and later.
     */
    public void finishedHandlingError(NSError error, boolean recovered) {
        if (customClass) { objc_finishedHandlingErrorSuper(getSuper(), finishedHandlingError$recovered$, error, recovered); } else { objc_finishedHandlingError(this, finishedHandlingError$recovered$, error, recovered); }
    }
    
    private static final Selector fileAttributesToWriteToURL$forSaveOperation$error$ = Selector.register("fileAttributesToWriteToURL:forSaveOperation:error:");
    @Bridge private native static NSDictionary objc_getFileAttributesToWrite(UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    @Bridge private native static NSDictionary objc_getFileAttributesToWriteSuper(ObjCSuper __super__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/fileAttributesToWriteToURL:forSaveOperation:error:">- (NSDictionary *)fileAttributesToWriteToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getFileAttributesToWrite(NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError) {
        if (customClass) { return objc_getFileAttributesToWriteSuper(getSuper(), fileAttributesToWriteToURL$forSaveOperation$error$, url, saveOperation, outError); } else { return objc_getFileAttributesToWrite(this, fileAttributesToWriteToURL$forSaveOperation$error$, url, saveOperation, outError); }
    }
    
    private static final Selector fileNameExtensionForType$saveOperation$ = Selector.register("fileNameExtensionForType:saveOperation:");
    @Bridge private native static String objc_getFileNameExtension(UIDocument __self__, Selector __cmd__, String typeName, UIDocumentSaveOperation saveOperation);
    @Bridge private native static String objc_getFileNameExtensionSuper(ObjCSuper __super__, Selector __cmd__, String typeName, UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/fileNameExtensionForType:saveOperation:">- (NSString *)fileNameExtensionForType:(NSString *)typeName saveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getFileNameExtension(String typeName, UIDocumentSaveOperation saveOperation) {
        if (customClass) { return objc_getFileNameExtensionSuper(getSuper(), fileNameExtensionForType$saveOperation$, typeName, saveOperation); } else { return objc_getFileNameExtension(this, fileNameExtensionForType$saveOperation$, typeName, saveOperation); }
    }
    
    private static final Selector savingFileType = Selector.register("savingFileType");
    @Bridge private native static String objc_getSavingFileType(UIDocument __self__, Selector __cmd__);
    @Bridge private native static String objc_getSavingFileTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/savingFileType">- (NSString *)savingFileType</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getSavingFileType() {
        if (customClass) { return objc_getSavingFileTypeSuper(getSuper(), savingFileType); } else { return objc_getSavingFileType(this, savingFileType); }
    }
    
    private static final Selector handleError$userInteractionPermitted$ = Selector.register("handleError:userInteractionPermitted:");
    @Bridge private native static void objc_handleError(UIDocument __self__, Selector __cmd__, NSError error, boolean userInteractionPermitted);
    @Bridge private native static void objc_handleErrorSuper(ObjCSuper __super__, Selector __cmd__, NSError error, boolean userInteractionPermitted);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/handleError:userInteractionPermitted:">- (void)handleError:(NSError *)error userInteractionPermitted:(BOOL)userInteractionPermitted</a>
     * @since Available in iOS 5.0 and later.
     */
    public void handleError(NSError error, boolean userInteractionPermitted) {
        if (customClass) { objc_handleErrorSuper(getSuper(), handleError$userInteractionPermitted$, error, userInteractionPermitted); } else { objc_handleError(this, handleError$userInteractionPermitted$, error, userInteractionPermitted); }
    }
    
    private static final Selector hasUnsavedChanges = Selector.register("hasUnsavedChanges");
    @Bridge private native static boolean objc_hasUnsavedChanges(UIDocument __self__, Selector __cmd__);
    @Bridge private native static boolean objc_hasUnsavedChangesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/hasUnsavedChanges">- (BOOL)hasUnsavedChanges</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean hasUnsavedChanges() {
        if (customClass) { return objc_hasUnsavedChangesSuper(getSuper(), hasUnsavedChanges); } else { return objc_hasUnsavedChanges(this, hasUnsavedChanges); }
    }
    
    private static final Selector loadFromContents$ofType$error$ = Selector.register("loadFromContents:ofType:error:");
    @Bridge private native static boolean objc_loadFromContents(UIDocument __self__, Selector __cmd__, NSObject contents, String typeName, Ptr<NSError> outError);
    @Bridge private native static boolean objc_loadFromContentsSuper(ObjCSuper __super__, Selector __cmd__, NSObject contents, String typeName, Ptr<NSError> outError);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/loadFromContents:ofType:error:">- (BOOL)loadFromContents:(id)contents ofType:(NSString *)typeName error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean loadFromContents(NSObject contents, String typeName, Ptr<NSError> outError) {
        if (customClass) { return objc_loadFromContentsSuper(getSuper(), loadFromContents$ofType$error$, contents, typeName, outError); } else { return objc_loadFromContents(this, loadFromContents$ofType$error$, contents, typeName, outError); }
    }
    
    private static final Selector openWithCompletionHandler$ = Selector.register("openWithCompletionHandler:");
    @Bridge private native static void objc_open(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    @Bridge private native static void objc_openSuper(ObjCSuper __super__, Selector __cmd__, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/openWithCompletionHandler:">- (void)openWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void open(VoidBooleanBlock completionHandler) {
        if (customClass) { objc_openSuper(getSuper(), openWithCompletionHandler$, completionHandler); } else { objc_open(this, openWithCompletionHandler$, completionHandler); }
    }
    
    private static final Selector performAsynchronousFileAccessUsingBlock$ = Selector.register("performAsynchronousFileAccessUsingBlock:");
    @Bridge private native static void objc_performAsynchronousFileAccess(UIDocument __self__, Selector __cmd__, VoidBlock block);
    @Bridge private native static void objc_performAsynchronousFileAccessSuper(ObjCSuper __super__, Selector __cmd__, VoidBlock block);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/performAsynchronousFileAccessUsingBlock:">- (void)performAsynchronousFileAccessUsingBlock:(void (^)(void))block</a>
     * @since Available in iOS 5.0 and later.
     */
    public void performAsynchronousFileAccess(VoidBlock block) {
        if (customClass) { objc_performAsynchronousFileAccessSuper(getSuper(), performAsynchronousFileAccessUsingBlock$, block); } else { objc_performAsynchronousFileAccess(this, performAsynchronousFileAccessUsingBlock$, block); }
    }
    
    private static final Selector readFromURL$error$ = Selector.register("readFromURL:error:");
    @Bridge private native static boolean objc_read(UIDocument __self__, Selector __cmd__, NSURL url, Ptr<NSError> outError);
    @Bridge private native static boolean objc_readSuper(ObjCSuper __super__, Selector __cmd__, NSURL url, Ptr<NSError> outError);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/readFromURL:error:">- (BOOL)readFromURL:(NSURL *)url error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean read(NSURL url, Ptr<NSError> outError) {
        if (customClass) { return objc_readSuper(getSuper(), readFromURL$error$, url, outError); } else { return objc_read(this, readFromURL$error$, url, outError); }
    }
    
    private static final Selector revertToContentsOfURL$completionHandler$ = Selector.register("revertToContentsOfURL:completionHandler:");
    @Bridge private native static void objc_revert(UIDocument __self__, Selector __cmd__, NSURL url, VoidBooleanBlock completionHandler);
    @Bridge private native static void objc_revertSuper(ObjCSuper __super__, Selector __cmd__, NSURL url, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/revertToContentsOfURL:completionHandler:">- (void)revertToContentsOfURL:(NSURL *)url completionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void revert(NSURL url, VoidBooleanBlock completionHandler) {
        if (customClass) { objc_revertSuper(getSuper(), revertToContentsOfURL$completionHandler$, url, completionHandler); } else { objc_revert(this, revertToContentsOfURL$completionHandler$, url, completionHandler); }
    }
    
    private static final Selector saveToURL$forSaveOperation$completionHandler$ = Selector.register("saveToURL:forSaveOperation:completionHandler:");
    @Bridge private native static void objc_save(UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, VoidBooleanBlock completionHandler);
    @Bridge private native static void objc_saveSuper(ObjCSuper __super__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/saveToURL:forSaveOperation:completionHandler:">- (void)saveToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation completionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void save(NSURL url, UIDocumentSaveOperation saveOperation, VoidBooleanBlock completionHandler) {
        if (customClass) { objc_saveSuper(getSuper(), saveToURL$forSaveOperation$completionHandler$, url, saveOperation, completionHandler); } else { objc_save(this, saveToURL$forSaveOperation$completionHandler$, url, saveOperation, completionHandler); }
    }
    
    private static final Selector updateChangeCountWithToken$forSaveOperation$ = Selector.register("updateChangeCountWithToken:forSaveOperation:");
    @Bridge private native static void objc_updateChangeCount(UIDocument __self__, Selector __cmd__, NSObject changeCountToken, UIDocumentSaveOperation saveOperation);
    @Bridge private native static void objc_updateChangeCountSuper(ObjCSuper __super__, Selector __cmd__, NSObject changeCountToken, UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/updateChangeCountWithToken:forSaveOperation:">- (void)updateChangeCountWithToken:(id)changeCountToken forSaveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    public void updateChangeCount(NSObject changeCountToken, UIDocumentSaveOperation saveOperation) {
        if (customClass) { objc_updateChangeCountSuper(getSuper(), updateChangeCountWithToken$forSaveOperation$, changeCountToken, saveOperation); } else { objc_updateChangeCount(this, updateChangeCountWithToken$forSaveOperation$, changeCountToken, saveOperation); }
    }
    
    private static final Selector updateChangeCount$ = Selector.register("updateChangeCount:");
    @Bridge private native static void objc_updateChangeCount(UIDocument __self__, Selector __cmd__, UIDocumentChangeKind change);
    @Bridge private native static void objc_updateChangeCountSuper(ObjCSuper __super__, Selector __cmd__, UIDocumentChangeKind change);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/updateChangeCount:">- (void)updateChangeCount:(UIDocumentChangeKind)change</a>
     * @since Available in iOS 5.0 and later.
     */
    public void updateChangeCount(UIDocumentChangeKind change) {
        if (customClass) { objc_updateChangeCountSuper(getSuper(), updateChangeCount$, change); } else { objc_updateChangeCount(this, updateChangeCount$, change); }
    }
    
    private static final Selector userInteractionNoLongerPermittedForError$ = Selector.register("userInteractionNoLongerPermittedForError:");
    @Bridge private native static void objc_userInteractionNoLongerPermittedForError(UIDocument __self__, Selector __cmd__, NSError error);
    @Bridge private native static void objc_userInteractionNoLongerPermittedForErrorSuper(ObjCSuper __super__, Selector __cmd__, NSError error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/userInteractionNoLongerPermittedForError:">- (void)userInteractionNoLongerPermittedForError:(NSError *)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public void userInteractionNoLongerPermittedForError(NSError error) {
        if (customClass) { objc_userInteractionNoLongerPermittedForErrorSuper(getSuper(), userInteractionNoLongerPermittedForError$, error); } else { objc_userInteractionNoLongerPermittedForError(this, userInteractionNoLongerPermittedForError$, error); }
    }
    
    private static final Selector writeContents$andAttributes$safelyToURL$forSaveOperation$error$ = Selector.register("writeContents:andAttributes:safelyToURL:forSaveOperation:error:");
    @Bridge private native static boolean objc_writeContents(UIDocument __self__, Selector __cmd__, NSObject contents, NSDictionary additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    @Bridge private native static boolean objc_writeContentsSuper(ObjCSuper __super__, Selector __cmd__, NSObject contents, NSDictionary additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/writeContents:andAttributes:safelyToURL:forSaveOperation:error:">- (BOOL)writeContents:(id)contents andAttributes:(NSDictionary *)additionalFileAttributes safelyToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean writeContents(NSObject contents, NSDictionary additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError) {
        if (customClass) { return objc_writeContentsSuper(getSuper(), writeContents$andAttributes$safelyToURL$forSaveOperation$error$, contents, additionalFileAttributes, url, saveOperation, outError); } else { return objc_writeContents(this, writeContents$andAttributes$safelyToURL$forSaveOperation$error$, contents, additionalFileAttributes, url, saveOperation, outError); }
    }
    
    private static final Selector writeContents$toURL$forSaveOperation$originalContentsURL$error$ = Selector.register("writeContents:toURL:forSaveOperation:originalContentsURL:error:");
    @Bridge private native static boolean objc_writeContents(UIDocument __self__, Selector __cmd__, NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, Ptr<NSError> outError);
    @Bridge private native static boolean objc_writeContentsSuper(ObjCSuper __super__, Selector __cmd__, NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, Ptr<NSError> outError);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/writeContents:toURL:forSaveOperation:originalContentsURL:error:">- (BOOL)writeContents:(id)contents toURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation originalContentsURL:(NSURL *)originalContentsURL error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean writeContents(NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, Ptr<NSError> outError) {
        if (customClass) { return objc_writeContentsSuper(getSuper(), writeContents$toURL$forSaveOperation$originalContentsURL$error$, contents, url, saveOperation, originalContentsURL, outError); } else { return objc_writeContents(this, writeContents$toURL$forSaveOperation$originalContentsURL$error$, contents, url, saveOperation, originalContentsURL, outError); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("documentState") public static UIDocumentState getDocumentState(UIDocument __self__, Selector __cmd__) { return __self__.getDocumentState(); }
        @Callback @BindSelector("fileModificationDate") public static NSDate getFileModificationDate(UIDocument __self__, Selector __cmd__) { return __self__.getFileModificationDate(); }
        @Callback @BindSelector("setFileModificationDate:") public static void setFileModificationDate(UIDocument __self__, Selector __cmd__, NSDate fileModificationDate) { __self__.setFileModificationDate(fileModificationDate); }
        @Callback @BindSelector("fileType") public static String getFileType(UIDocument __self__, Selector __cmd__) { return __self__.getFileType(); }
        @Callback @BindSelector("fileURL") public static NSURL getFileURL(UIDocument __self__, Selector __cmd__) { return __self__.getFileURL(); }
        @Callback @BindSelector("localizedName") public static String getLocalizedName(UIDocument __self__, Selector __cmd__) { return __self__.getLocalizedName(); }
        @Callback @BindSelector("undoManager") public static NSUndoManager getUndoManager(UIDocument __self__, Selector __cmd__) { return __self__.getUndoManager(); }
        @Callback @BindSelector("setUndoManager:") public static void setUndoManager(UIDocument __self__, Selector __cmd__, NSUndoManager undoManager) { __self__.setUndoManager(undoManager); }
        @Callback @BindSelector("autosaveWithCompletionHandler:") public static void autoSave(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler) { __self__.autoSave(completionHandler); }
        @Callback @BindSelector("changeCountTokenForSaveOperation:") public static NSObject changeCountTokenForSaveOperation(UIDocument __self__, Selector __cmd__, UIDocumentSaveOperation saveOperation) { return __self__.changeCountTokenForSaveOperation(saveOperation); }
        @Callback @BindSelector("closeWithCompletionHandler:") public static void close(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler) { __self__.close(completionHandler); }
        @Callback @BindSelector("contentsForType:error:") public static NSObject contentsForType(UIDocument __self__, Selector __cmd__, String typeName, Ptr<NSError> outError) { return __self__.contentsForType(typeName, outError); }
        @Callback @BindSelector("disableEditing") public static void disableEditing(UIDocument __self__, Selector __cmd__) { __self__.disableEditing(); }
        @Callback @BindSelector("enableEditing") public static void enableEditing(UIDocument __self__, Selector __cmd__) { __self__.enableEditing(); }
        @Callback @BindSelector("finishedHandlingError:recovered:") public static void finishedHandlingError(UIDocument __self__, Selector __cmd__, NSError error, boolean recovered) { __self__.finishedHandlingError(error, recovered); }
        @Callback @BindSelector("fileAttributesToWriteToURL:forSaveOperation:error:") public static NSDictionary getFileAttributesToWrite(UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError) { return __self__.getFileAttributesToWrite(url, saveOperation, outError); }
        @Callback @BindSelector("fileNameExtensionForType:saveOperation:") public static String getFileNameExtension(UIDocument __self__, Selector __cmd__, String typeName, UIDocumentSaveOperation saveOperation) { return __self__.getFileNameExtension(typeName, saveOperation); }
        @Callback @BindSelector("savingFileType") public static String getSavingFileType(UIDocument __self__, Selector __cmd__) { return __self__.getSavingFileType(); }
        @Callback @BindSelector("handleError:userInteractionPermitted:") public static void handleError(UIDocument __self__, Selector __cmd__, NSError error, boolean userInteractionPermitted) { __self__.handleError(error, userInteractionPermitted); }
        @Callback @BindSelector("hasUnsavedChanges") public static boolean hasUnsavedChanges(UIDocument __self__, Selector __cmd__) { return __self__.hasUnsavedChanges(); }
        @Callback @BindSelector("loadFromContents:ofType:error:") public static boolean loadFromContents(UIDocument __self__, Selector __cmd__, NSObject contents, String typeName, Ptr<NSError> outError) { return __self__.loadFromContents(contents, typeName, outError); }
        @Callback @BindSelector("openWithCompletionHandler:") public static void open(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler) { __self__.open(completionHandler); }
        @Callback @BindSelector("performAsynchronousFileAccessUsingBlock:") public static void performAsynchronousFileAccess(UIDocument __self__, Selector __cmd__, VoidBlock block) { __self__.performAsynchronousFileAccess(block); }
        @Callback @BindSelector("readFromURL:error:") public static boolean read(UIDocument __self__, Selector __cmd__, NSURL url, Ptr<NSError> outError) { return __self__.read(url, outError); }
        @Callback @BindSelector("revertToContentsOfURL:completionHandler:") public static void revert(UIDocument __self__, Selector __cmd__, NSURL url, VoidBooleanBlock completionHandler) { __self__.revert(url, completionHandler); }
        @Callback @BindSelector("saveToURL:forSaveOperation:completionHandler:") public static void save(UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, VoidBooleanBlock completionHandler) { __self__.save(url, saveOperation, completionHandler); }
        @Callback @BindSelector("updateChangeCountWithToken:forSaveOperation:") public static void updateChangeCount(UIDocument __self__, Selector __cmd__, NSObject changeCountToken, UIDocumentSaveOperation saveOperation) { __self__.updateChangeCount(changeCountToken, saveOperation); }
        @Callback @BindSelector("updateChangeCount:") public static void updateChangeCount(UIDocument __self__, Selector __cmd__, UIDocumentChangeKind change) { __self__.updateChangeCount(change); }
        @Callback @BindSelector("userInteractionNoLongerPermittedForError:") public static void userInteractionNoLongerPermittedForError(UIDocument __self__, Selector __cmd__, NSError error) { __self__.userInteractionNoLongerPermittedForError(error); }
        @Callback @BindSelector("writeContents:andAttributes:safelyToURL:forSaveOperation:error:") public static boolean writeContents(UIDocument __self__, Selector __cmd__, NSObject contents, NSDictionary additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError) { return __self__.writeContents(contents, additionalFileAttributes, url, saveOperation, outError); }
        @Callback @BindSelector("writeContents:toURL:forSaveOperation:originalContentsURL:error:") public static boolean writeContents(UIDocument __self__, Selector __cmd__, NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, Ptr<NSError> outError) { return __self__.writeContents(contents, url, saveOperation, originalContentsURL, outError); }
    }
    /*</callbacks>*/

}
