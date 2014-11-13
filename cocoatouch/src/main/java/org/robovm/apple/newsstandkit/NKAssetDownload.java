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
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("NewsstandKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NKAssetDownload/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NKAssetDownloadPtr extends Ptr<NKAssetDownload, NKAssetDownloadPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NKAssetDownload.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NKAssetDownload() {}
    protected NKAssetDownload(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "issue")
    public native NKIssue getIssue();
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "userInfo")
    public native NSDictionary<NSString, ?> getUserInfo();
    @Property(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<NSString, ?> v);
    @Property(selector = "URLRequest")
    public native NSURLRequest getURLRequest();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "downloadWithDelegate:")
    public native NSURLConnection download(NSURLConnectionDownloadDelegate delegate);
    /*</methods>*/
}
