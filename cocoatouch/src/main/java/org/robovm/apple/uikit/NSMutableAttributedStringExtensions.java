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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
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
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSMutableAttributedStringExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMutableAttributedStringExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSMutableAttributedStringExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    public static boolean readFromFileURL(NSMutableAttributedString thiz, NSURL url, NSAttributedStringDocumentAttributes opts) throws NSErrorException {
        return readFromFileURL(thiz, url, opts, null);
    }
    public static boolean readFromData(NSMutableAttributedString thiz, NSData data, NSAttributedStringDocumentAttributes opts) throws NSErrorException {
        return readFromData(thiz, data, opts, null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "fixAttributesInRange:")
    public static native void fixAttributesInRange(NSMutableAttributedString thiz, @ByVal NSRange range);
    /**
     * @since Available in iOS 7.0 and later.
     */
    protected static boolean readFromFileURL(NSMutableAttributedString thiz, NSURL url, NSAttributedStringDocumentAttributes opts, NSDictionary.NSDictionaryPtr dict) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = readFromFileURL(thiz, url, opts, dict, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "readFromFileURL:options:documentAttributes:error:")
    private static native boolean readFromFileURL(NSMutableAttributedString thiz, NSURL url, NSAttributedStringDocumentAttributes opts, NSDictionary.NSDictionaryPtr dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    protected static boolean readFromData(NSMutableAttributedString thiz, NSData data, NSAttributedStringDocumentAttributes opts, NSDictionary.NSDictionaryPtr dict) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = readFromData(thiz, data, opts, dict, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "readFromData:options:documentAttributes:error:")
    private static native boolean readFromData(NSMutableAttributedString thiz, NSData data, NSAttributedStringDocumentAttributes opts, NSDictionary.NSDictionaryPtr dict, NSError.NSErrorPtr error);
    /*</methods>*/
}
