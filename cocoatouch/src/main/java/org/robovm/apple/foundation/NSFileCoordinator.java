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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileCoordinator/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFileCoordinatorPtr extends Ptr<NSFileCoordinator, NSFileCoordinatorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileCoordinator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFileCoordinator() {}
    protected NSFileCoordinator(SkipInit skipInit) { super(skipInit); }
    public NSFileCoordinator(NSFilePresenter filePresenterOrNil) { super((SkipInit) null); initObject(initWithFilePresenter$(filePresenterOrNil)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFilePresenter:")
    protected native @Pointer long initWithFilePresenter$(NSFilePresenter filePresenterOrNil);
    @Method(selector = "coordinateReadingItemAtURL:options:error:byAccessor:")
    public native void coordinateReadingItem(NSURL url, NSFileCoordinatorReadingOptions options, NSError.NSErrorPtr outError, @Block VoidBlock1<NSURL> reader);
    @Method(selector = "coordinateWritingItemAtURL:options:error:byAccessor:")
    public native void coordinateWritingItem(NSURL url, NSFileCoordinatorWritingOptions options, NSError.NSErrorPtr outError, @Block VoidBlock1<NSURL> writer);
    @Method(selector = "coordinateReadingItemAtURL:options:writingItemAtURL:options:error:byAccessor:")
    public native void coordinateReadingItem(NSURL readingURL, NSFileCoordinatorReadingOptions readingOptions, NSURL writingURL, NSFileCoordinatorWritingOptions writingOptions, NSError.NSErrorPtr outError, @Block VoidBlock2<NSURL, NSURL> readerWriter);
    @Method(selector = "coordinateWritingItemAtURL:options:writingItemAtURL:options:error:byAccessor:")
    public native void coordinateWritingItem(NSURL url1, NSFileCoordinatorWritingOptions options1, NSURL url2, NSFileCoordinatorWritingOptions options2, NSError.NSErrorPtr outError, @Block VoidBlock2<NSURL, NSURL> writer);
    @Method(selector = "prepareForReadingItemsAtURLs:options:writingItemsAtURLs:options:error:byAccessor:")
    public native void prepareForReadingItems(NSArray<NSURL> readingURLs, NSFileCoordinatorReadingOptions readingOptions, NSArray<NSURL> writingURLs, NSFileCoordinatorWritingOptions writingOptions, NSError.NSErrorPtr outError, @Block("(@Block)") VoidBlock1<Runnable> batchAccessor);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "itemAtURL:willMoveToURL:")
    public native void itemAtURLWillMoveToURL(NSURL oldURL, NSURL newURL);
    @Method(selector = "itemAtURL:didMoveToURL:")
    public native void itemAtURLDidMoveToURL(NSURL oldURL, NSURL newURL);
    @Method(selector = "cancel")
    public native void cancel();
    @Method(selector = "addFilePresenter:")
    public static native void addFilePresenter(NSFilePresenter filePresenter);
    @Method(selector = "removeFilePresenter:")
    public static native void removeFilePresenter(NSFilePresenter filePresenter);
    @Method(selector = "filePresenters")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<NSFilePresenter> getFilePresenters();
    /*</methods>*/
}
