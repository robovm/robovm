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
package org.robovm.apple.safariservices;

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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SafariServices") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SSReadingList/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SSReadingListPtr extends Ptr<SSReadingList, SSReadingListPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SSReadingList.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SSReadingList() {}
    protected SSReadingList(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param URL
     * @param title
     * @param previewText
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    public boolean addReadingListItem(NSURL URL, String title, String previewText) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = addReadingListItem(URL, title, previewText, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addReadingListItemWithURL:title:previewText:error:")
    protected native boolean addReadingListItem(NSURL URL, String title, String previewText, NSError.NSErrorPtr error);
    @Method(selector = "defaultReadingList")
    public static native SSReadingList getDefaultReadingList();
    @Method(selector = "supportsURL:")
    public static native boolean supportsURL(NSURL URL);
    /*</methods>*/
}
