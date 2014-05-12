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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFContext/*</name>*/ 
    extends /*<extends>*/CGContext/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPDFContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPDFContext create(CGDataConsumer consumer, CGRect mediaBox, NSDictionary<?, ?> auxiliaryInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextCreateWithURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPDFContext createWithURL(NSURL url, CGRect mediaBox, NSDictionary<?, ?> auxiliaryInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextClose", optional=true)
    public native void closeContext();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextBeginPage", optional=true)
    public native void beginPage(NSDictionary<?, ?> pageInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextEndPage", optional=true)
    public native void endPage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGPDFContextAddDocumentMetadata", optional=true)
    public native void addDocumentMetadata(NSData metadata);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextSetURLForRect", optional=true)
    public native void setURLForRect(NSURL url, @ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextAddDestinationAtPoint", optional=true)
    public native void addDestinationAtPoint(String name, @ByVal CGPoint point);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFContextSetDestinationForRect", optional=true)
    public native void setDestinationForRect(String name, @ByVal CGRect rect);
    /*</methods>*/
}
