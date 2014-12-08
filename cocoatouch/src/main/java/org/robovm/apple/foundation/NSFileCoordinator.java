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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
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
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "purposeIdentifier")
    public native String getPurposeIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setPurposeIdentifier:")
    public native void setPurposeIdentifier(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    
    /**
     * 
     * @param url
     * @param options
     * @param reader
     * @throws NSErrorException
     */
    public void coordinateReadingItem(NSURL url, NSFileCoordinatorReadingOptions options, @Block VoidBlock1<NSURL> reader) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        coordinateReadingItem(url, options, err, reader);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
     * 
     * @param url
     * @param options
     * @param writer
     * @throws NSErrorException
     */
    public void coordinateWritingItem(NSURL url, NSFileCoordinatorWritingOptions options, @Block VoidBlock1<NSURL> writer) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        coordinateWritingItem(url, options, err, writer);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
     * 
     * @param readingURL
     * @param readingOptions
     * @param writingURL
     * @param writingOptions
     * @param readerWriter
     * @throws NSErrorException
     */
    public void coordinateReadingItem(NSURL readingURL, NSFileCoordinatorReadingOptions readingOptions, NSURL writingURL, NSFileCoordinatorWritingOptions writingOptions, @Block VoidBlock2<NSURL, NSURL> readerWriter) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        coordinateReadingItem(readingURL, readingOptions, writingURL, writingOptions, err, readerWriter);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
     * 
     * @param url1
     * @param options1
     * @param url2
     * @param options2
     * @param writer
     * @throws NSErrorException
     */
    public void coordinateWritingItem(NSURL url1, NSFileCoordinatorWritingOptions options1, NSURL url2, NSFileCoordinatorWritingOptions options2, @Block VoidBlock2<NSURL, NSURL> writer) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        coordinateWritingItem(url1, options1, url2, options2, err, writer);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
     * 
     * @param readingURLs
     * @param readingOptions
     * @param writingURLs
     * @param writingOptions
     * @param batchAccessor
     * @throws NSErrorException
     */
    public void prepareForReadingItems(NSArray<NSURL> readingURLs, NSFileCoordinatorReadingOptions readingOptions, NSArray<NSURL> writingURLs, NSFileCoordinatorWritingOptions writingOptions, @Block("(@Block)") VoidBlock1<Runnable> batchAccessor) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        prepareForReadingItems(readingURLs, readingOptions, writingURLs, writingOptions, err, batchAccessor);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }

    
    /*<methods>*/
    @Method(selector = "initWithFilePresenter:")
    protected native @Pointer long initWithFilePresenter$(NSFilePresenter filePresenterOrNil);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "coordinateAccessWithIntents:queue:byAccessor:")
    public native void coordinateAccessWithIntents(NSArray<NSFileAccessIntent> intents, NSOperationQueue queue, @Block VoidBlock1<NSError> accessor);
    @Method(selector = "coordinateReadingItemAtURL:options:error:byAccessor:")
    protected native void coordinateReadingItem(NSURL url, NSFileCoordinatorReadingOptions options, NSError.NSErrorPtr outError, @Block VoidBlock1<NSURL> reader);
    @Method(selector = "coordinateWritingItemAtURL:options:error:byAccessor:")
    protected native void coordinateWritingItem(NSURL url, NSFileCoordinatorWritingOptions options, NSError.NSErrorPtr outError, @Block VoidBlock1<NSURL> writer);
    @Method(selector = "coordinateReadingItemAtURL:options:writingItemAtURL:options:error:byAccessor:")
    protected native void coordinateReadingItem(NSURL readingURL, NSFileCoordinatorReadingOptions readingOptions, NSURL writingURL, NSFileCoordinatorWritingOptions writingOptions, NSError.NSErrorPtr outError, @Block VoidBlock2<NSURL, NSURL> readerWriter);
    @Method(selector = "coordinateWritingItemAtURL:options:writingItemAtURL:options:error:byAccessor:")
    protected native void coordinateWritingItem(NSURL url1, NSFileCoordinatorWritingOptions options1, NSURL url2, NSFileCoordinatorWritingOptions options2, NSError.NSErrorPtr outError, @Block VoidBlock2<NSURL, NSURL> writer);
    @Method(selector = "prepareForReadingItemsAtURLs:options:writingItemsAtURLs:options:error:byAccessor:")
    protected native void prepareForReadingItems(NSArray<NSURL> readingURLs, NSFileCoordinatorReadingOptions readingOptions, NSArray<NSURL> writingURLs, NSFileCoordinatorWritingOptions writingOptions, NSError.NSErrorPtr outError, @Block("(@Block)") VoidBlock1<Runnable> batchAccessor);
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
