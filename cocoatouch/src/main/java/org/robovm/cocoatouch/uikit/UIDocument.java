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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html">UIDocument Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIDocument /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithFileURL(UIDocument __self__, Selector __cmd__, NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/initWithFileURL:">- (id)initWithFileURL:(NSURL *)url</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIDocument(NSURL url) {
        super((SkipInit) null);
        objc_initWithFileURL(this, initWithFileURL$, url);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/documentState">@property(readonly) UIDocumentState documentState</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("documentState") public native UIDocumentState getDocumentState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileModificationDate">@property(copy) NSDate *fileModificationDate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileModificationDate") public native NSDate getFileModificationDate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileModificationDate">@property(copy) NSDate *fileModificationDate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setFileModificationDate:") public native void setFileModificationDate(NSDate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileType">@property(readonly, copy) NSString *fileType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileType") public native String getFileType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileURL">@property(readonly) NSURL *fileURL</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileURL") public native NSURL getFileURL();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/localizedName">@property(readonly, copy) NSString *localizedName</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("localizedName") public native String getLocalizedName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/undoManager">@property(retain) NSUndoManager *undoManager</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("undoManager") public native NSUndoManager getUndoManager();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/undoManager">@property(retain) NSUndoManager *undoManager</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setUndoManager:") public native void setUndoManager(NSUndoManager v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector autosaveWithCompletionHandler$ = Selector.register("autosaveWithCompletionHandler:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_autoSave(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_autoSaveSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/autosaveWithCompletionHandler:">- (void)autosaveWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void autoSave(VoidBooleanBlock completionHandler) {
        if (customClass) { objc_autoSaveSuper(getSuper(), this, autosaveWithCompletionHandler$, completionHandler); } else { objc_autoSave(this, autosaveWithCompletionHandler$, completionHandler); }
    }
    
    private static final Selector changeCountTokenForSaveOperation$ = Selector.register("changeCountTokenForSaveOperation:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_changeCountTokenForSaveOperation(UIDocument __self__, Selector __cmd__, UIDocumentSaveOperation saveOperation);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_changeCountTokenForSaveOperationSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/changeCountTokenForSaveOperation:">- (id)changeCountTokenForSaveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject changeCountTokenForSaveOperation(UIDocumentSaveOperation saveOperation) {
        if (customClass) { return objc_changeCountTokenForSaveOperationSuper(getSuper(), this, changeCountTokenForSaveOperation$, saveOperation); } else { return objc_changeCountTokenForSaveOperation(this, changeCountTokenForSaveOperation$, saveOperation); }
    }
    
    private static final Selector closeWithCompletionHandler$ = Selector.register("closeWithCompletionHandler:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_close(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_closeSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/closeWithCompletionHandler:">- (void)closeWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void close(VoidBooleanBlock completionHandler) {
        if (customClass) { objc_closeSuper(getSuper(), this, closeWithCompletionHandler$, completionHandler); } else { objc_close(this, closeWithCompletionHandler$, completionHandler); }
    }
    
    private static final Selector contentsForType$error$ = Selector.register("contentsForType:error:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_contentsForType(UIDocument __self__, Selector __cmd__, String typeName, Ptr<NSError> outError);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_contentsForTypeSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, String typeName, Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/contentsForType:error:">- (id)contentsForType:(NSString *)typeName error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject contentsForType(String typeName, Ptr<NSError> outError) {
        if (customClass) { return objc_contentsForTypeSuper(getSuper(), this, contentsForType$error$, typeName, outError); } else { return objc_contentsForType(this, contentsForType$error$, typeName, outError); }
    }
    
    private static final Selector disableEditing = Selector.register("disableEditing");
    @Bridge(symbol = "objc_msgSend") private native static void objc_disableEditing(UIDocument __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_disableEditingSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/disableEditing">- (void)disableEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public void disableEditing() {
        if (customClass) { objc_disableEditingSuper(getSuper(), this, disableEditing); } else { objc_disableEditing(this, disableEditing); }
    }
    
    private static final Selector enableEditing = Selector.register("enableEditing");
    @Bridge(symbol = "objc_msgSend") private native static void objc_enableEditing(UIDocument __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_enableEditingSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/enableEditing">- (void)enableEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public void enableEditing() {
        if (customClass) { objc_enableEditingSuper(getSuper(), this, enableEditing); } else { objc_enableEditing(this, enableEditing); }
    }
    
    private static final Selector finishedHandlingError$recovered$ = Selector.register("finishedHandlingError:recovered:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_finishedHandlingError(UIDocument __self__, Selector __cmd__, NSError error, boolean recovered);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_finishedHandlingErrorSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSError error, boolean recovered);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/finishedHandlingError:recovered:">- (void)finishedHandlingError:(NSError *)error recovered:(BOOL)recovered</a>
     * @since Available in iOS 5.0 and later.
     */
    public void finishedHandlingError(NSError error, boolean recovered) {
        if (customClass) { objc_finishedHandlingErrorSuper(getSuper(), this, finishedHandlingError$recovered$, error, recovered); } else { objc_finishedHandlingError(this, finishedHandlingError$recovered$, error, recovered); }
    }
    
    private static final Selector fileAttributesToWriteToURL$forSaveOperation$error$ = Selector.register("fileAttributesToWriteToURL:forSaveOperation:error:");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getFileAttributesToWrite(UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getFileAttributesToWriteSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/fileAttributesToWriteToURL:forSaveOperation:error:">- (NSDictionary *)fileAttributesToWriteToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getFileAttributesToWrite(NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError) {
        if (customClass) { return objc_getFileAttributesToWriteSuper(getSuper(), this, fileAttributesToWriteToURL$forSaveOperation$error$, url, saveOperation, outError); } else { return objc_getFileAttributesToWrite(this, fileAttributesToWriteToURL$forSaveOperation$error$, url, saveOperation, outError); }
    }
    
    private static final Selector fileNameExtensionForType$saveOperation$ = Selector.register("fileNameExtensionForType:saveOperation:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getFileNameExtension(UIDocument __self__, Selector __cmd__, String typeName, UIDocumentSaveOperation saveOperation);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getFileNameExtensionSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, String typeName, UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/fileNameExtensionForType:saveOperation:">- (NSString *)fileNameExtensionForType:(NSString *)typeName saveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getFileNameExtension(String typeName, UIDocumentSaveOperation saveOperation) {
        if (customClass) { return objc_getFileNameExtensionSuper(getSuper(), this, fileNameExtensionForType$saveOperation$, typeName, saveOperation); } else { return objc_getFileNameExtension(this, fileNameExtensionForType$saveOperation$, typeName, saveOperation); }
    }
    
    private static final Selector savingFileType = Selector.register("savingFileType");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getSavingFileType(UIDocument __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getSavingFileTypeSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/savingFileType">- (NSString *)savingFileType</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getSavingFileType() {
        if (customClass) { return objc_getSavingFileTypeSuper(getSuper(), this, savingFileType); } else { return objc_getSavingFileType(this, savingFileType); }
    }
    
    private static final Selector handleError$userInteractionPermitted$ = Selector.register("handleError:userInteractionPermitted:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_handleError(UIDocument __self__, Selector __cmd__, NSError error, boolean userInteractionPermitted);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_handleErrorSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSError error, boolean userInteractionPermitted);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/handleError:userInteractionPermitted:">- (void)handleError:(NSError *)error userInteractionPermitted:(BOOL)userInteractionPermitted</a>
     * @since Available in iOS 5.0 and later.
     */
    public void handleError(NSError error, boolean userInteractionPermitted) {
        if (customClass) { objc_handleErrorSuper(getSuper(), this, handleError$userInteractionPermitted$, error, userInteractionPermitted); } else { objc_handleError(this, handleError$userInteractionPermitted$, error, userInteractionPermitted); }
    }
    
    private static final Selector hasUnsavedChanges = Selector.register("hasUnsavedChanges");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_hasUnsavedChanges(UIDocument __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_hasUnsavedChangesSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/hasUnsavedChanges">- (BOOL)hasUnsavedChanges</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean hasUnsavedChanges() {
        if (customClass) { return objc_hasUnsavedChangesSuper(getSuper(), this, hasUnsavedChanges); } else { return objc_hasUnsavedChanges(this, hasUnsavedChanges); }
    }
    
    private static final Selector loadFromContents$ofType$error$ = Selector.register("loadFromContents:ofType:error:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_loadFromContents(UIDocument __self__, Selector __cmd__, NSObject contents, String typeName, Ptr<NSError> outError);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_loadFromContentsSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSObject contents, String typeName, Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/loadFromContents:ofType:error:">- (BOOL)loadFromContents:(id)contents ofType:(NSString *)typeName error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean loadFromContents(NSObject contents, String typeName, Ptr<NSError> outError) {
        if (customClass) { return objc_loadFromContentsSuper(getSuper(), this, loadFromContents$ofType$error$, contents, typeName, outError); } else { return objc_loadFromContents(this, loadFromContents$ofType$error$, contents, typeName, outError); }
    }
    
    private static final Selector openWithCompletionHandler$ = Selector.register("openWithCompletionHandler:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_open(UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_openSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/openWithCompletionHandler:">- (void)openWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void open(VoidBooleanBlock completionHandler) {
        if (customClass) { objc_openSuper(getSuper(), this, openWithCompletionHandler$, completionHandler); } else { objc_open(this, openWithCompletionHandler$, completionHandler); }
    }
    
    private static final Selector performAsynchronousFileAccessUsingBlock$ = Selector.register("performAsynchronousFileAccessUsingBlock:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_performAsynchronousFileAccess(UIDocument __self__, Selector __cmd__, VoidBlock block);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_performAsynchronousFileAccessSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, VoidBlock block);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/performAsynchronousFileAccessUsingBlock:">- (void)performAsynchronousFileAccessUsingBlock:(void (^)(void))block</a>
     * @since Available in iOS 5.0 and later.
     */
    public void performAsynchronousFileAccess(VoidBlock block) {
        if (customClass) { objc_performAsynchronousFileAccessSuper(getSuper(), this, performAsynchronousFileAccessUsingBlock$, block); } else { objc_performAsynchronousFileAccess(this, performAsynchronousFileAccessUsingBlock$, block); }
    }
    
    private static final Selector readFromURL$error$ = Selector.register("readFromURL:error:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_read(UIDocument __self__, Selector __cmd__, NSURL url, Ptr<NSError> outError);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_readSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSURL url, Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/readFromURL:error:">- (BOOL)readFromURL:(NSURL *)url error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean read(NSURL url, Ptr<NSError> outError) {
        if (customClass) { return objc_readSuper(getSuper(), this, readFromURL$error$, url, outError); } else { return objc_read(this, readFromURL$error$, url, outError); }
    }
    
    private static final Selector revertToContentsOfURL$completionHandler$ = Selector.register("revertToContentsOfURL:completionHandler:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_revert(UIDocument __self__, Selector __cmd__, NSURL url, VoidBooleanBlock completionHandler);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_revertSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSURL url, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/revertToContentsOfURL:completionHandler:">- (void)revertToContentsOfURL:(NSURL *)url completionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void revert(NSURL url, VoidBooleanBlock completionHandler) {
        if (customClass) { objc_revertSuper(getSuper(), this, revertToContentsOfURL$completionHandler$, url, completionHandler); } else { objc_revert(this, revertToContentsOfURL$completionHandler$, url, completionHandler); }
    }
    
    private static final Selector saveToURL$forSaveOperation$completionHandler$ = Selector.register("saveToURL:forSaveOperation:completionHandler:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_save(UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, VoidBooleanBlock completionHandler);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_saveSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSURL url, UIDocumentSaveOperation saveOperation, VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/saveToURL:forSaveOperation:completionHandler:">- (void)saveToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation completionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    public void save(NSURL url, UIDocumentSaveOperation saveOperation, VoidBooleanBlock completionHandler) {
        if (customClass) { objc_saveSuper(getSuper(), this, saveToURL$forSaveOperation$completionHandler$, url, saveOperation, completionHandler); } else { objc_save(this, saveToURL$forSaveOperation$completionHandler$, url, saveOperation, completionHandler); }
    }
    
    private static final Selector updateChangeCountWithToken$forSaveOperation$ = Selector.register("updateChangeCountWithToken:forSaveOperation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_updateChangeCount(UIDocument __self__, Selector __cmd__, NSObject changeCountToken, UIDocumentSaveOperation saveOperation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_updateChangeCountSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSObject changeCountToken, UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/updateChangeCountWithToken:forSaveOperation:">- (void)updateChangeCountWithToken:(id)changeCountToken forSaveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    public void updateChangeCount(NSObject changeCountToken, UIDocumentSaveOperation saveOperation) {
        if (customClass) { objc_updateChangeCountSuper(getSuper(), this, updateChangeCountWithToken$forSaveOperation$, changeCountToken, saveOperation); } else { objc_updateChangeCount(this, updateChangeCountWithToken$forSaveOperation$, changeCountToken, saveOperation); }
    }
    
    private static final Selector updateChangeCount$ = Selector.register("updateChangeCount:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_updateChangeCount(UIDocument __self__, Selector __cmd__, UIDocumentChangeKind change);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_updateChangeCountSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, UIDocumentChangeKind change);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/updateChangeCount:">- (void)updateChangeCount:(UIDocumentChangeKind)change</a>
     * @since Available in iOS 5.0 and later.
     */
    public void updateChangeCount(UIDocumentChangeKind change) {
        if (customClass) { objc_updateChangeCountSuper(getSuper(), this, updateChangeCount$, change); } else { objc_updateChangeCount(this, updateChangeCount$, change); }
    }
    
    private static final Selector userInteractionNoLongerPermittedForError$ = Selector.register("userInteractionNoLongerPermittedForError:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_userInteractionNoLongerPermittedForError(UIDocument __self__, Selector __cmd__, NSError error);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_userInteractionNoLongerPermittedForErrorSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSError error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/userInteractionNoLongerPermittedForError:">- (void)userInteractionNoLongerPermittedForError:(NSError *)error</a>
     * @since Available in iOS 5.0 and later.
     */
    public void userInteractionNoLongerPermittedForError(NSError error) {
        if (customClass) { objc_userInteractionNoLongerPermittedForErrorSuper(getSuper(), this, userInteractionNoLongerPermittedForError$, error); } else { objc_userInteractionNoLongerPermittedForError(this, userInteractionNoLongerPermittedForError$, error); }
    }
    
    private static final Selector writeContents$andAttributes$safelyToURL$forSaveOperation$error$ = Selector.register("writeContents:andAttributes:safelyToURL:forSaveOperation:error:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_writeContents(UIDocument __self__, Selector __cmd__, NSObject contents, NSDictionary additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_writeContentsSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSObject contents, NSDictionary additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/writeContents:andAttributes:safelyToURL:forSaveOperation:error:">- (BOOL)writeContents:(id)contents andAttributes:(NSDictionary *)additionalFileAttributes safelyToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean writeContents(NSObject contents, NSDictionary additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, Ptr<NSError> outError) {
        if (customClass) { return objc_writeContentsSuper(getSuper(), this, writeContents$andAttributes$safelyToURL$forSaveOperation$error$, contents, additionalFileAttributes, url, saveOperation, outError); } else { return objc_writeContents(this, writeContents$andAttributes$safelyToURL$forSaveOperation$error$, contents, additionalFileAttributes, url, saveOperation, outError); }
    }
    
    private static final Selector writeContents$toURL$forSaveOperation$originalContentsURL$error$ = Selector.register("writeContents:toURL:forSaveOperation:originalContentsURL:error:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_writeContents(UIDocument __self__, Selector __cmd__, NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, Ptr<NSError> outError);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_writeContentsSuper(ObjCSuper __super__, UIDocument __self__, Selector __cmd__, NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/writeContents:toURL:forSaveOperation:originalContentsURL:error:">- (BOOL)writeContents:(id)contents toURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation originalContentsURL:(NSURL *)originalContentsURL error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean writeContents(NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, Ptr<NSError> outError) {
        if (customClass) { return objc_writeContentsSuper(getSuper(), this, writeContents$toURL$forSaveOperation$originalContentsURL$error$, contents, url, saveOperation, originalContentsURL, outError); } else { return objc_writeContents(this, writeContents$toURL$forSaveOperation$originalContentsURL$error$, contents, url, saveOperation, originalContentsURL, outError); }
    }
    /*</methods>*/

}
