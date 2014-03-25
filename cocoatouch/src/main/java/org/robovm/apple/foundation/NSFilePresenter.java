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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSFilePresenter/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "presentedItemURL")
    NSURL getPresentedItemURL();
    @Property(selector = "presentedItemOperationQueue")
    NSOperationQueue getPresentedItemOperationQueue();
    @Property(selector = "primaryPresentedItemURL")
    NSURL getPrimaryPresentedItemURL();
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "relinquishPresentedItemToReader:")
    void relinquishPresentedItemToReader$(@Block("(@Block)") VoidBlock1<Runnable> reader);
    @Method(selector = "relinquishPresentedItemToWriter:")
    void relinquishPresentedItemToWriter$(@Block("(@Block)") VoidBlock1<Runnable> writer);
    @Method(selector = "savePresentedItemChangesWithCompletionHandler:")
    void savePresentedItemChangesWithCompletionHandler$(@Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "accommodatePresentedItemDeletionWithCompletionHandler:")
    void accommodatePresentedItemDeletionWithCompletionHandler$(@Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "presentedItemDidMoveToURL:")
    void presentedItemDidMoveToURL$(NSURL newURL);
    @Method(selector = "presentedItemDidChange")
    void presentedItemDidChange();
    @Method(selector = "presentedItemDidGainVersion:")
    void presentedItemDidGainVersion$(NSFileVersion version);
    @Method(selector = "presentedItemDidLoseVersion:")
    void presentedItemDidLoseVersion$(NSFileVersion version);
    @Method(selector = "presentedItemDidResolveConflictVersion:")
    void presentedItemDidResolveConflictVersion$(NSFileVersion version);
    @Method(selector = "accommodatePresentedSubitemDeletionAtURL:completionHandler:")
    void accommodatePresentedSubitemDeletionAtURL$completionHandler$(NSURL url, @Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "presentedSubitemDidAppearAtURL:")
    void presentedSubitemDidAppearAtURL$(NSURL url);
    @Method(selector = "presentedSubitemAtURL:didMoveToURL:")
    void presentedSubitemAtURL$didMoveToURL$(NSURL oldURL, NSURL newURL);
    @Method(selector = "presentedSubitemDidChangeAtURL:")
    void presentedSubitemDidChangeAtURL$(NSURL url);
    @Method(selector = "presentedSubitemAtURL:didGainVersion:")
    void presentedSubitemAtURL$didGainVersion$(NSURL url, NSFileVersion version);
    @Method(selector = "presentedSubitemAtURL:didLoseVersion:")
    void presentedSubitemAtURL$didLoseVersion$(NSURL url, NSFileVersion version);
    @Method(selector = "presentedSubitemAtURL:didResolveConflictVersion:")
    void presentedSubitemAtURL$didResolveConflictVersion$(NSURL url, NSFileVersion version);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
