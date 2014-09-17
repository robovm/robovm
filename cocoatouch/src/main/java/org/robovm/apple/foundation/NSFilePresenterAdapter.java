/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
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
    public NSURL getPresentedItemURL() { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedItemOperationQueue")
    public NSOperationQueue getPresentedItemOperationQueue() { throw new UnsupportedOperationException(); }
    @NotImplemented("primaryPresentedItemURL")
    public NSURL getPrimaryPresentedItemURL() { throw new UnsupportedOperationException(); }
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("relinquishPresentedItemToReader:")
    public void relinquishPresentedItemToReader$(@Block("(@Block)") VoidBlock1<Runnable> reader) { throw new UnsupportedOperationException(); }
    @NotImplemented("relinquishPresentedItemToWriter:")
    public void relinquishPresentedItemToWriter$(@Block("(@Block)") VoidBlock1<Runnable> writer) { throw new UnsupportedOperationException(); }
    @NotImplemented("savePresentedItemChangesWithCompletionHandler:")
    public void savePresentedItemChangesWithCompletionHandler$(@Block VoidBlock1<NSError> completionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("accommodatePresentedItemDeletionWithCompletionHandler:")
    public void accommodatePresentedItemDeletionWithCompletionHandler$(@Block VoidBlock1<NSError> completionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedItemDidMoveToURL:")
    public void presentedItemDidMoveToURL$(NSURL newURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedItemDidChange")
    public void presentedItemDidChange() { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedItemDidGainVersion:")
    public void presentedItemDidGainVersion$(NSFileVersion version) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedItemDidLoseVersion:")
    public void presentedItemDidLoseVersion$(NSFileVersion version) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedItemDidResolveConflictVersion:")
    public void presentedItemDidResolveConflictVersion$(NSFileVersion version) { throw new UnsupportedOperationException(); }
    @NotImplemented("accommodatePresentedSubitemDeletionAtURL:completionHandler:")
    public void accommodatePresentedSubitemDeletionAtURL$completionHandler$(NSURL url, @Block VoidBlock1<NSError> completionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedSubitemDidAppearAtURL:")
    public void presentedSubitemDidAppearAtURL$(NSURL url) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedSubitemAtURL:didMoveToURL:")
    public void presentedSubitemAtURL$didMoveToURL$(NSURL oldURL, NSURL newURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedSubitemDidChangeAtURL:")
    public void presentedSubitemDidChangeAtURL$(NSURL url) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedSubitemAtURL:didGainVersion:")
    public void presentedSubitemAtURL$didGainVersion$(NSURL url, NSFileVersion version) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedSubitemAtURL:didLoseVersion:")
    public void presentedSubitemAtURL$didLoseVersion$(NSURL url, NSFileVersion version) { throw new UnsupportedOperationException(); }
    @NotImplemented("presentedSubitemAtURL:didResolveConflictVersion:")
    public void presentedSubitemAtURL$didResolveConflictVersion$(NSURL url, NSFileVersion version) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
