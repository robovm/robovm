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
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSAttributedStringExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSAttributedStringExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSAttributedStringExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    public static NSAttributedString createFromURL(NSURL url, NSAttributedStringDocumentAttributes options) throws NSErrorException {
        NSAttributedString thiz = alloc(NSAttributedString.class);
        initObject(thiz, init(thiz, url, options, null));
        return thiz;
    }
    public static NSAttributedString createFromData(NSData data, NSAttributedStringDocumentAttributes options) throws NSErrorException {
        NSAttributedString thiz = alloc(NSAttributedString.class);
        initObject(thiz, init(thiz, data, options, null));
        return thiz;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    protected static @Pointer long init(NSAttributedString thiz, NSURL url, NSAttributedStringDocumentAttributes options, NSDictionary.NSDictionaryPtr dict) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long result = init(thiz, url, options, dict, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithFileURL:options:documentAttributes:error:")
    private static native @Pointer long init(NSAttributedString thiz, NSURL url, NSAttributedStringDocumentAttributes options, NSDictionary.NSDictionaryPtr dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    protected static @Pointer long init(NSAttributedString thiz, NSData data, NSAttributedStringDocumentAttributes options, NSDictionary.NSDictionaryPtr dict) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long result = init(thiz, data, options, dict, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithData:options:documentAttributes:error:")
    private static native @Pointer long init(NSAttributedString thiz, NSData data, NSAttributedStringDocumentAttributes options, NSDictionary.NSDictionaryPtr dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static NSData getData(NSAttributedString thiz, @ByVal NSRange range, NSAttributedStringDocumentAttributes dict) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSData result = getData(thiz, range, dict, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "dataFromRange:documentAttributes:error:")
    private static native NSData getData(NSAttributedString thiz, @ByVal NSRange range, NSAttributedStringDocumentAttributes dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static NSFileWrapper getFileWrapper(NSAttributedString thiz, @ByVal NSRange range, NSAttributedStringDocumentAttributes dict) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSFileWrapper result = getFileWrapper(thiz, range, dict, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "fileWrapperFromRange:documentAttributes:error:")
    private static native NSFileWrapper getFileWrapper(NSAttributedString thiz, @ByVal NSRange range, NSAttributedStringDocumentAttributes dict, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "size")
    public static native @ByVal CGSize getSize(NSAttributedString thiz);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "drawAtPoint:")
    public static native void draw(NSAttributedString thiz, @ByVal CGPoint point);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "drawInRect:")
    public static native void draw(NSAttributedString thiz, @ByVal CGRect rect);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "drawWithRect:options:context:")
    public static native void draw(NSAttributedString thiz, @ByVal CGRect rect, NSStringDrawingOptions options, NSStringDrawingContext context);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "boundingRectWithSize:options:context:")
    public static native @ByVal CGRect getBoundingRect(NSAttributedString thiz, @ByVal CGSize size, NSStringDrawingOptions options, NSStringDrawingContext context);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "attributedStringWithAttachment:")
    protected static native NSAttributedString create(ObjCClass clazz, NSTextAttachment attachment);
    public static NSAttributedString create(NSTextAttachment attachment) { return create(ObjCClass.getByType(NSAttributedString.class), attachment); }
    /*</methods>*/
}
