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
public class /*<name>*/ UIDocument /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIDocument /*</name>*/.class);
    }

    /*<constructors>*/
    public UIDocument() {}
    @Bind("initWithFileURL:") public UIDocument(@Type("NSURL *") NSURL url) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("documentState") public native @Type("UIDocumentState") UIDocumentState getDocumentState();
    @Bind("fileModificationDate") public native @Type("NSDate *") NSDate getFileModificationDate();
    @Bind("setFileModificationDate:") public native void setFileModificationDate(@Type("NSDate *") NSDate v);
    @Bind("fileType") public native @Type("NSString *") String getFileType();
    @Bind("fileURL") public native @Type("NSURL *") NSURL getFileURL();
    @Bind("localizedName") public native @Type("NSString *") String getLocalizedName();
    @Bind("undoManager") public native @Type("NSUndoManager *") NSUndoManager getUndoManager();
    @Bind("setUndoManager:") public native void setUndoManager(@Type("NSUndoManager *") NSUndoManager v);
    /*</properties>*/
    /*<methods>*/
    @Bind("autosaveWithCompletionHandler:") public native @Type("void") void autoSave(@Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    @Bind("changeCountTokenForSaveOperation:") public native @Type("id") NSObject changeCountTokenForSaveOperation(@Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation);
    @Bind("closeWithCompletionHandler:") public native @Type("void") void close(@Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    @Bind("contentsForType:error:") public native @Type("id") NSObject contentsForType(@Type("NSString *") String typeName, @Type("NSError **") Ptr<NSError> outError);
    @Bind("disableEditing") public native @Type("void") void disableEditing();
    @Bind("enableEditing") public native @Type("void") void enableEditing();
    @Bind("finishedHandlingError:recovered:") public native @Type("void") void finishedHandlingError(@Type("NSError *") NSError error, @Type("BOOL") boolean recovered);
    @Bind("fileAttributesToWriteToURL:forSaveOperation:error:") public native @Type("NSDictionary *") NSDictionary getFileAttributesToWrite(@Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("NSError **") Ptr<NSError> outError);
    @Bind("fileNameExtensionForType:saveOperation:") public native @Type("NSString *") String getFileNameExtension(@Type("NSString *") String typeName, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation);
    @Bind("savingFileType") public native @Type("NSString *") String getSavingFileType();
    @Bind("handleError:userInteractionPermitted:") public native @Type("void") void handleError(@Type("NSError *") NSError error, @Type("BOOL") boolean userInteractionPermitted);
    @Bind("hasUnsavedChanges") public native @Type("BOOL") boolean hasUnsavedChanges();
    @Bind("loadFromContents:ofType:error:") public native @Type("BOOL") boolean loadFromContents(@Type("id") NSObject contents, @Type("NSString *") String typeName, @Type("NSError **") Ptr<NSError> outError);
    @Bind("openWithCompletionHandler:") public native @Type("void") void open(@Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    @Bind("performAsynchronousFileAccessUsingBlock:") public native @Type("void") void performAsynchronousFileAccess(@Type("void (^)(void)") VoidBlock block);
    @Bind("readFromURL:error:") public native @Type("BOOL") boolean read(@Type("NSURL *") NSURL url, @Type("NSError **") Ptr<NSError> outError);
    @Bind("revertToContentsOfURL:completionHandler:") public native @Type("void") void revert(@Type("NSURL *") NSURL url, @Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    @Bind("saveToURL:forSaveOperation:completionHandler:") public native @Type("void") void save(@Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("void (^)(BOOL success)") VoidBooleanBlock completionHandler);
    @Bind("updateChangeCountWithToken:forSaveOperation:") public native @Type("void") void updateChangeCount(@Type("id") NSObject changeCountToken, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation);
    @Bind("updateChangeCount:") public native @Type("void") void updateChangeCount(@Type("UIDocumentChangeKind") UIDocumentChangeKind change);
    @Bind("userInteractionNoLongerPermittedForError:") public native @Type("void") void userInteractionNoLongerPermittedForError(@Type("NSError *") NSError error);
    @Bind("writeContents:andAttributes:safelyToURL:forSaveOperation:error:") public native @Type("BOOL") boolean writeContents(@Type("id") NSObject contents, @Type("NSDictionary *") NSDictionary additionalFileAttributes, @Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("NSError **") Ptr<NSError> outError);
    @Bind("writeContents:toURL:forSaveOperation:originalContentsURL:error:") public native @Type("BOOL") boolean writeContents(@Type("id") NSObject contents, @Type("NSURL *") NSURL url, @Type("UIDocumentSaveOperation") UIDocumentSaveOperation saveOperation, @Type("NSURL *") NSURL originalContentsURL, @Type("NSError **") Ptr<NSError> outError);
    /*</methods>*/

}
