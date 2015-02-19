/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFDocument/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGPDFDocumentPtr extends Ptr<CGPDFDocument, CGPDFDocumentPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPDFDocument.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGPDFDocument() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public int getMajorVersion() {
        IntPtr major = new IntPtr();
        IntPtr minor = new IntPtr();
        getVersion(major, minor);
        return major.get();
    }

    public int getMinorVersion() {
        IntPtr major = new IntPtr();
        IntPtr minor = new IntPtr();
        getVersion(major, minor);
        return minor.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentCreateWithProvider", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPDFDocument create(CGDataProvider provider);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentCreateWithURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPDFDocument create(NSURL url);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentGetVersion", optional=true)
    private native void getVersion(IntPtr majorVersion, IntPtr minorVersion);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentIsEncrypted", optional=true)
    public native boolean isEncrypted();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentUnlockWithPassword", optional=true)
    public native boolean unlock(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String password);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentIsUnlocked", optional=true)
    public native boolean isUnlocked();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentAllowsPrinting", optional=true)
    public native boolean allowsPrinting();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentAllowsCopying", optional=true)
    public native boolean allowsCopying();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentGetNumberOfPages", optional=true)
    public native @MachineSizedUInt long getNumberOfPages();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentGetPage", optional=true)
    public native CGPDFPage getPage(@MachineSizedUInt long pageNumber);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDocumentGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /*</methods>*/
}
