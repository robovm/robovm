/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFilePresenterAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFilePresenter/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("presentedItemURL")
    public NSURL getPresentedItemURL() { return null; }
    @NotImplemented("presentedItemOperationQueue")
    public NSOperationQueue getPresentedItemOperationQueue() { return null; }
    @NotImplemented("primaryPresentedItemURL")
    public NSURL getPrimaryPresentedItemURL() { return null; }
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("relinquishPresentedItemToReader:")
    public void relinquishPresentedItemToReader(@Block("(@Block)") VoidBlock1<Runnable> reader) {}
    @NotImplemented("relinquishPresentedItemToWriter:")
    public void relinquishPresentedItemToWriter(@Block("(@Block)") VoidBlock1<Runnable> writer) {}
    @NotImplemented("savePresentedItemChangesWithCompletionHandler:")
    public void savePresentedItemChanges(@Block VoidBlock1<NSError> completionHandler) {}
    @NotImplemented("accommodatePresentedItemDeletionWithCompletionHandler:")
    public void accommodatePresentedItemDeletion(@Block VoidBlock1<NSError> completionHandler) {}
    @NotImplemented("presentedItemDidMoveToURL:")
    public void presentedItemDidMoveToURL(NSURL newURL) {}
    @NotImplemented("presentedItemDidChange")
    public void presentedItemDidChange() {}
    @NotImplemented("presentedItemDidGainVersion:")
    public void presentedItemDidGainVersion(NSFileVersion version) {}
    @NotImplemented("presentedItemDidLoseVersion:")
    public void presentedItemDidLoseVersion(NSFileVersion version) {}
    @NotImplemented("presentedItemDidResolveConflictVersion:")
    public void presentedItemDidResolveConflictVersion(NSFileVersion version) {}
    @NotImplemented("accommodatePresentedSubitemDeletionAtURL:completionHandler:")
    public void accommodatePresentedSubitemDeletionAtURL(NSURL url, @Block VoidBlock1<NSError> completionHandler) {}
    @NotImplemented("presentedSubitemDidAppearAtURL:")
    public void presentedSubitemDidAppearAtURL(NSURL url) {}
    @NotImplemented("presentedSubitemAtURL:didMoveToURL:")
    public void presentedSubitemAtURLDidMoveToURL(NSURL oldURL, NSURL newURL) {}
    @NotImplemented("presentedSubitemDidChangeAtURL:")
    public void presentedSubitemDidChangeAtURL(NSURL url) {}
    @NotImplemented("presentedSubitemAtURL:didGainVersion:")
    public void presentedSubitemAtURLDidGainVersion(NSURL url, NSFileVersion version) {}
    @NotImplemented("presentedSubitemAtURL:didLoseVersion:")
    public void presentedSubitemAtURLDidLoseVersion(NSURL url, NSFileVersion version) {}
    @NotImplemented("presentedSubitemAtURL:didResolveConflictVersion:")
    public void presentedSubitemAtURLDidResolveConflictVersion(NSURL url, NSFileVersion version) {}
    /*</methods>*/
}
