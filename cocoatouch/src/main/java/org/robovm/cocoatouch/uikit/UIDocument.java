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

    /*<constructors>*/
    public UIDocument() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/initWithFileURL:">- (id)initWithFileURL:(NSURL *)url</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("initWithFileURL:") public UIDocument(@Type("NSURL *") NSURL url) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/documentState">@property(readonly) UIDocumentState documentState</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("documentState") public native @Type("UIDocumentState") UIDocumentState getDocumentState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileModificationDate">@property(copy) NSDate *fileModificationDate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileModificationDate") public native @Type("NSDate *") NSDate getFileModificationDate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileModificationDate">@property(copy) NSDate *fileModificationDate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setFileModificationDate:") public native void setFileModificationDate(@Type("NSDate *") NSDate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileType">@property(readonly, copy) NSString *fileType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileType") public native @Type("NSString *") String getFileType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/fileURL">@property(readonly) NSURL *fileURL</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileURL") public native @Type("NSURL *") NSURL getFileURL();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/localizedName">@property(readonly, copy) NSString *localizedName</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("localizedName") public native @Type("NSString *") String getLocalizedName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/undoManager">@property(retain) NSUndoManager *undoManager</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("undoManager") public native @Type("NSUndoManager *") NSUndoManager getUndoManager();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instp/UIDocument/undoManager">@property(retain) NSUndoManager *undoManager</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setUndoManager:") public native void setUndoManager(@Type("NSUndoManager *") NSUndoManager v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/autosaveWithCompletionHandler:">- (void)autosaveWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("autosaveWithCompletionHandler:") public native @Type("void") void autoSave(@Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/changeCountTokenForSaveOperation:">- (id)changeCountTokenForSaveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("changeCountTokenForSaveOperation:") public native @Type("id") NSObject changeCountTokenForSaveOperation(@Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/closeWithCompletionHandler:">- (void)closeWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("closeWithCompletionHandler:") public native @Type("void") void close(@Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/contentsForType:error:">- (id)contentsForType:(NSString *)typeName error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("contentsForType:error:") public native @Type("id") NSObject contentsForType(@Type("NSString *") String typeName, @Type("NSError **") Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/disableEditing">- (void)disableEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("disableEditing") public native @Type("void") void disableEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/enableEditing">- (void)enableEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("enableEditing") public native @Type("void") void enableEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/finishedHandlingError:recovered:">- (void)finishedHandlingError:(NSError *)error recovered:(BOOL)recovered</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("finishedHandlingError:recovered:") public native @Type("void") void finishedHandlingError(@Type("NSError *") NSError error, @Type("BOOL") boolean recovered);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/fileAttributesToWriteToURL:forSaveOperation:error:">- (NSDictionary *)fileAttributesToWriteToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileAttributesToWriteToURL:forSaveOperation:error:") public native @Type("NSDictionary *") NSDictionary getFileAttributesToWrite(@Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("NSError **") Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/fileNameExtensionForType:saveOperation:">- (NSString *)fileNameExtensionForType:(NSString *)typeName saveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("fileNameExtensionForType:saveOperation:") public native @Type("NSString *") String getFileNameExtension(@Type("NSString *") String typeName, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/savingFileType">- (NSString *)savingFileType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("savingFileType") public native @Type("NSString *") String getSavingFileType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/handleError:userInteractionPermitted:">- (void)handleError:(NSError *)error userInteractionPermitted:(BOOL)userInteractionPermitted</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("handleError:userInteractionPermitted:") public native @Type("void") void handleError(@Type("NSError *") NSError error, @Type("BOOL") boolean userInteractionPermitted);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/hasUnsavedChanges">- (BOOL)hasUnsavedChanges</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("hasUnsavedChanges") public native @Type("BOOL") boolean hasUnsavedChanges();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/loadFromContents:ofType:error:">- (BOOL)loadFromContents:(id)contents ofType:(NSString *)typeName error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("loadFromContents:ofType:error:") public native @Type("BOOL") boolean loadFromContents(@Type("id") NSObject contents, @Type("NSString *") String typeName, @Type("NSError **") Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/openWithCompletionHandler:">- (void)openWithCompletionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("openWithCompletionHandler:") public native @Type("void") void open(@Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/performAsynchronousFileAccessUsingBlock:">- (void)performAsynchronousFileAccessUsingBlock:(void (^)(void))block</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("performAsynchronousFileAccessUsingBlock:") public native @Type("void") void performAsynchronousFileAccess(@Type("void (^)(void)") VoidBlock block);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/readFromURL:error:">- (BOOL)readFromURL:(NSURL *)url error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("readFromURL:error:") public native @Type("BOOL") boolean read(@Type("NSURL *") NSURL url, @Type("NSError **") Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/revertToContentsOfURL:completionHandler:">- (void)revertToContentsOfURL:(NSURL *)url completionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("revertToContentsOfURL:completionHandler:") public native @Type("void") void revert(@Type("NSURL *") NSURL url, @Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/saveToURL:forSaveOperation:completionHandler:">- (void)saveToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation completionHandler:(void (^)(BOOL success))completionHandler</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("saveToURL:forSaveOperation:completionHandler:") public native @Type("void") void save(@Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/updateChangeCountWithToken:forSaveOperation:">- (void)updateChangeCountWithToken:(id)changeCountToken forSaveOperation:(UIDocumentSaveOperation)saveOperation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("updateChangeCountWithToken:forSaveOperation:") public native @Type("void") void updateChangeCount(@Type("id") NSObject changeCountToken, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/updateChangeCount:">- (void)updateChangeCount:(UIDocumentChangeKind)change</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("updateChangeCount:") public native @Type("void") void updateChangeCount(@Type("UIDocumentChangeKind") UIDocumentChangeKind change);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/userInteractionNoLongerPermittedForError:">- (void)userInteractionNoLongerPermittedForError:(NSError *)error</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("userInteractionNoLongerPermittedForError:") public native @Type("void") void userInteractionNoLongerPermittedForError(@Type("NSError *") NSError error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/writeContents:andAttributes:safelyToURL:forSaveOperation:error:">- (BOOL)writeContents:(id)contents andAttributes:(NSDictionary *)additionalFileAttributes safelyToURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("writeContents:andAttributes:safelyToURL:forSaveOperation:error:") public native @Type("BOOL") boolean writeContents(@Type("id") NSObject contents, @Type("NSDictionary *") NSDictionary additionalFileAttributes, @Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("NSError **") Ptr<NSError> outError);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocument_Class/UIDocument/UIDocument.html#//apple_ref/occ/instm/UIDocument/writeContents:toURL:forSaveOperation:originalContentsURL:error:">- (BOOL)writeContents:(id)contents toURL:(NSURL *)url forSaveOperation:(UIDocumentSaveOperation)saveOperation originalContentsURL:(NSURL *)originalContentsURL error:(NSError **)outError</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("writeContents:toURL:forSaveOperation:originalContentsURL:error:") public native @Type("BOOL") boolean writeContents(@Type("id") NSObject contents, @Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("NSURL *") NSURL originalContentsURL, @Type("NSError **") Ptr<NSError> outError);
    /*</methods>*/

}
