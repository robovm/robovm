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
package org.robovm.apple.newsstandkit;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("NewsstandKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NKIssue/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Notifications {
        public static NSObject observeDownloadCompleted(NKIssue object, final VoidBlock1<NKIssue> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DownloadCompletedNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NKIssue) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class NKIssuePtr extends Ptr<NKIssue, NKIssuePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NKIssue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NKIssue() {}
    protected NKIssue(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "downloadingAssets")
    public native NSArray<NKAssetDownload> getDownloadingAssets();
    @Property(selector = "contentURL")
    public native NSURL getContentURL();
    @Property(selector = "status")
    public native NKIssueContentStatus getStatus();
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "date")
    public native NSDate getDate();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NKIssueDownloadCompletedNotification", optional=true)
    public static native NSString DownloadCompletedNotification();
    
    @Method(selector = "addAssetWithRequest:")
    public native NKAssetDownload addAsset(NSURLRequest request);
    /*</methods>*/
}
