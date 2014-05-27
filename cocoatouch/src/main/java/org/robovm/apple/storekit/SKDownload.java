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
package org.robovm.apple.storekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("StoreKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKDownload/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKDownloadPtr extends Ptr<SKDownload, SKDownloadPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKDownload.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKDownload() {}
    protected SKDownload(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "downloadState")
    public native SKDownloadState getDownloadState();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "contentLength")
    public native long getContentLength();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "contentIdentifier")
    public native String getContentIdentifier();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "contentURL")
    public native NSURL getContentURL();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "contentVersion")
    public native String getContentVersion();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "error")
    public native NSError getError();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "progress")
    public native float getProgress();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "timeRemaining")
    public native double getTimeRemaining();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "transaction")
    public native SKPaymentTransaction getTransaction();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
