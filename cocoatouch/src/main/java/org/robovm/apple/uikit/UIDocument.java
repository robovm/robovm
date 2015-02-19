/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIDocument/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFilePresenter/*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeStateChanged(UIDocument object, final VoidBlock1<UIDocument> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(StateChangedNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((UIDocument) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class UIDocumentPtr extends Ptr<UIDocument, UIDocumentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIDocument.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIDocument() {}
    protected UIDocument(SkipInit skipInit) { super(skipInit); }
    public UIDocument(NSURL url) { super((SkipInit) null); initObject(init(url)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "fileURL")
    public native NSURL getFileURL();
    @Property(selector = "localizedName")
    public native String getLocalizedName();
    @Property(selector = "fileType")
    public native String getFileType();
    @Property(selector = "fileModificationDate")
    public native NSDate getFileModificationDate();
    @Property(selector = "setFileModificationDate:")
    public native void setFileModificationDate(NSDate v);
    @Property(selector = "documentState")
    public native UIDocumentState getDocumentState();
    @Property(selector = "undoManager")
    public native NSUndoManager getUndoManager();
    @Property(selector = "setUndoManager:")
    public native void setUndoManager(NSUndoManager v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "userActivity")
    public native NSUserActivity getUserActivity();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setUserActivity:")
    public native void setUserActivity(NSUserActivity v);
    @Property(selector = "presentedItemURL")
    public native NSURL getPresentedItemURL();
    @Property(selector = "presentedItemOperationQueue")
    public native NSOperationQueue getPresentedItemOperationQueue();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param contents
     * @param typeName
     * @return
     * @throws NSErrorException
     */
    public boolean loadFromContents(NSObject contents, String typeName) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = loadFromContents(contents, typeName, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param typeName
     * @return
     * @throws NSErrorException
     */
    public NSObject getContentsForType(String typeName) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSObject result = getContentsForType(typeName, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param contents
     * @param additionalFileAttributes
     * @param url
     * @param saveOperation
     * @return
     * @throws NSErrorException
     */
    public boolean writeContents(NSObject contents, NSFileAttributes additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = writeContents(contents, additionalFileAttributes, url, saveOperation, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param contents
     * @param url
     * @param saveOperation
     * @param originalContentsURL
     * @return
     * @throws NSErrorException
     */
    public boolean writeContents(NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = writeContents(contents, url, saveOperation, originalContentsURL, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param url
     * @param saveOperation
     * @return
     * @throws NSErrorException
     */
    public NSFileAttributes getFileAttributesToWrite(NSURL url, UIDocumentSaveOperation saveOperation) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSFileAttributes result = getFileAttributesToWrite(url, saveOperation, err);
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
    public boolean read(NSURL url) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = read(url, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIDocumentStateChangedNotification", optional=true)
    public static native NSString StateChangedNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSUserActivityDocumentURLKey", optional=true)
    public static native String UserActivityDocumentURLKey();
    
    @Method(selector = "initWithFileURL:")
    protected native @Pointer long init(NSURL url);
    @Method(selector = "openWithCompletionHandler:")
    public native void open(@Block VoidBooleanBlock completionHandler);
    @Method(selector = "closeWithCompletionHandler:")
    public native void close(@Block VoidBooleanBlock completionHandler);
    @Method(selector = "loadFromContents:ofType:error:")
    protected native boolean loadFromContents(NSObject contents, String typeName, NSError.NSErrorPtr outError);
    @Method(selector = "contentsForType:error:")
    protected native NSObject getContentsForType(String typeName, NSError.NSErrorPtr outError);
    @Method(selector = "disableEditing")
    public native void disableEditing();
    @Method(selector = "enableEditing")
    public native void enableEditing();
    @Method(selector = "hasUnsavedChanges")
    public native boolean hasUnsavedChanges();
    @Method(selector = "updateChangeCount:")
    public native void updateChangeCount(UIDocumentChangeKind change);
    @Method(selector = "changeCountTokenForSaveOperation:")
    public native NSObject getChangeCountToken(UIDocumentSaveOperation saveOperation);
    @Method(selector = "updateChangeCountWithToken:forSaveOperation:")
    public native void updateChangeCount(NSObject changeCountToken, UIDocumentSaveOperation saveOperation);
    @Method(selector = "saveToURL:forSaveOperation:completionHandler:")
    public native void save(NSURL url, UIDocumentSaveOperation saveOperation, @Block VoidBooleanBlock completionHandler);
    @Method(selector = "autosaveWithCompletionHandler:")
    public native void autoSave(@Block VoidBooleanBlock completionHandler);
    @Method(selector = "savingFileType")
    public native String getSavingFileType();
    @Method(selector = "fileNameExtensionForType:saveOperation:")
    public native String getFileNameExtension(String typeName, UIDocumentSaveOperation saveOperation);
    @Method(selector = "writeContents:andAttributes:safelyToURL:forSaveOperation:error:")
    protected native boolean writeContents(NSObject contents, NSFileAttributes additionalFileAttributes, NSURL url, UIDocumentSaveOperation saveOperation, NSError.NSErrorPtr outError);
    @Method(selector = "writeContents:toURL:forSaveOperation:originalContentsURL:error:")
    protected native boolean writeContents(NSObject contents, NSURL url, UIDocumentSaveOperation saveOperation, NSURL originalContentsURL, NSError.NSErrorPtr outError);
    @Method(selector = "fileAttributesToWriteToURL:forSaveOperation:error:")
    protected native NSFileAttributes getFileAttributesToWrite(NSURL url, UIDocumentSaveOperation saveOperation, NSError.NSErrorPtr outError);
    @Method(selector = "readFromURL:error:")
    protected native boolean read(NSURL url, NSError.NSErrorPtr outError);
    @Method(selector = "performAsynchronousFileAccessUsingBlock:")
    public native void performAsynchronousFileAccess(@Block Runnable block);
    @Method(selector = "handleError:userInteractionPermitted:")
    public native void handleError(NSError error, boolean userInteractionPermitted);
    @Method(selector = "finishedHandlingError:recovered:")
    public native void finishedHandlingError(NSError error, boolean recovered);
    @Method(selector = "userInteractionNoLongerPermittedForError:")
    public native void userInteractionNoLongerPermitted(NSError error);
    @Method(selector = "revertToContentsOfURL:completionHandler:")
    public native void revert(NSURL url, @Block VoidBooleanBlock completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "updateUserActivityState:")
    public native void updateUserActivityState(NSUserActivity userActivity);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "restoreUserActivityState:")
    public native void restoreUserActivityState(NSUserActivity userActivity);
    @Method(selector = "relinquishPresentedItemToReader:")
    public native void relinquishPresentedItemToReader(@Block("(@Block)") VoidBlock1<Runnable> reader);
    @Method(selector = "relinquishPresentedItemToWriter:")
    public native void relinquishPresentedItemToWriter(@Block("(@Block)") VoidBlock1<Runnable> writer);
    @Method(selector = "savePresentedItemChangesWithCompletionHandler:")
    public native void savePresentedItemChanges(@Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "accommodatePresentedItemDeletionWithCompletionHandler:")
    public native void accommodatePresentedItemDeletion(@Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "presentedItemDidMoveToURL:")
    public native void presentedItemDidMoveToURL(NSURL newURL);
    @Method(selector = "presentedItemDidChange")
    public native void presentedItemDidChange();
    @Method(selector = "presentedItemDidGainVersion:")
    public native void presentedItemDidGainVersion(NSFileVersion version);
    @Method(selector = "presentedItemDidLoseVersion:")
    public native void presentedItemDidLoseVersion(NSFileVersion version);
    @Method(selector = "presentedItemDidResolveConflictVersion:")
    public native void presentedItemDidResolveConflictVersion(NSFileVersion version);
    @Method(selector = "accommodatePresentedSubitemDeletionAtURL:completionHandler:")
    public native void accommodatePresentedSubitemDeletionAtURL(NSURL url, @Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "presentedSubitemDidAppearAtURL:")
    public native void presentedSubitemDidAppearAtURL(NSURL url);
    @Method(selector = "presentedSubitemAtURL:didMoveToURL:")
    public native void presentedSubitemAtURLDidMoveToURL(NSURL oldURL, NSURL newURL);
    @Method(selector = "presentedSubitemDidChangeAtURL:")
    public native void presentedSubitemDidChangeAtURL(NSURL url);
    @Method(selector = "presentedSubitemAtURL:didGainVersion:")
    public native void presentedSubitemAtURLDidGainVersion(NSURL url, NSFileVersion version);
    @Method(selector = "presentedSubitemAtURL:didLoseVersion:")
    public native void presentedSubitemAtURLDidLoseVersion(NSURL url, NSFileVersion version);
    @Method(selector = "presentedSubitemAtURL:didResolveConflictVersion:")
    public native void presentedSubitemAtURLDidResolveConflictVersion(NSURL url, NSFileVersion version);
    /*</methods>*/
}
