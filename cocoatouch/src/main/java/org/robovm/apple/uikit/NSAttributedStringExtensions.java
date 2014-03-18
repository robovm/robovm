/*
 * Copyright (C) 2014 Trillian AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    
    public static NSAttributedString createFromURL(NSURL url, NSDictionary<?, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error) {
        NSAttributedString thiz = alloc(NSAttributedString.class);
        initObject(thiz, initWithFileURL$options$documentAttributes$error$(thiz, url, options, dict, error));
        return thiz;
    }

    public static NSAttributedString createFromData(NSData data, NSDictionary<?, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error) {
        NSAttributedString thiz = alloc(NSAttributedString.class);
        initObject(thiz, initWithData$options$documentAttributes$error$(thiz, data, options, dict, error));
        return thiz;
    }

    /*<methods>*/
    @Method(selector = "initWithFileURL:options:documentAttributes:error:")
    protected static native @Pointer long initWithFileURL$options$documentAttributes$error$(NSAttributedString thiz, NSURL url, NSDictionary<?, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "initWithData:options:documentAttributes:error:")
    protected static native @Pointer long initWithData$options$documentAttributes$error$(NSAttributedString thiz, NSData data, NSDictionary<?, ?> options, NSDictionary.NSDictionaryPtr<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "dataFromRange:documentAttributes:error:")
    public static native NSData dataFromRange$documentAttributes$error$(NSAttributedString thiz, @ByVal NSRange range, NSDictionary<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "fileWrapperFromRange:documentAttributes:error:")
    public static native NSFileWrapper fileWrapperFromRange$documentAttributes$error$(NSAttributedString thiz, @ByVal NSRange range, NSDictionary<?, ?> dict, NSError.NSErrorPtr error);
    @Method(selector = "size")
    public static native @ByVal CGSize size(NSAttributedString thiz);
    @Method(selector = "drawAtPoint:")
    public static native void drawAtPoint$(NSAttributedString thiz, @ByVal CGPoint point);
    @Method(selector = "drawInRect:")
    public static native void drawInRect$(NSAttributedString thiz, @ByVal CGRect rect);
    @Method(selector = "drawWithRect:options:context:")
    public static native void drawWithRect$options$context$(NSAttributedString thiz, @ByVal CGRect rect, NSStringDrawingOptions options, NSStringDrawingContext context);
    @Method(selector = "boundingRectWithSize:options:context:")
    public static native @ByVal CGRect boundingRectWithSize$options$context$(NSAttributedString thiz, @ByVal CGSize size, NSStringDrawingOptions options, NSStringDrawingContext context);
    @Method(selector = "attributedStringWithAttachment:")
    public static native NSAttributedString attributedStringWithAttachment$(NSAttributedString thiz, NSTextAttachment attachment);
    /*</methods>*/
}
