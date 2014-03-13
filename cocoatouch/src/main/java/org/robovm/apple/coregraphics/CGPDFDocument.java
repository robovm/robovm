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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @Bridge(symbol="CGPDFDocumentCreateWithProvider")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPDFDocument createWithProvider(CGDataProvider provider);
    @Bridge(symbol="CGPDFDocumentCreateWithURL")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPDFDocument createWithURL(NSURL url);
    @Bridge(symbol="CGPDFDocumentGetVersion")
    protected native void getVersion(IntPtr majorVersion, IntPtr minorVersion);
    @Bridge(symbol="CGPDFDocumentIsEncrypted")
    public native boolean isEncrypted();
    @Bridge(symbol="CGPDFDocumentUnlockWithPassword")
    public native boolean unlockWithPassword(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String password);
    @Bridge(symbol="CGPDFDocumentIsUnlocked")
    public native boolean isUnlocked();
    @Bridge(symbol="CGPDFDocumentAllowsPrinting")
    public native boolean allowsPrinting();
    @Bridge(symbol="CGPDFDocumentAllowsCopying")
    public native boolean allowsCopying();
    @Bridge(symbol="CGPDFDocumentGetNumberOfPages")
    public native @MachineSizedUInt long getNumberOfPages();
    @Bridge(symbol="CGPDFDocumentGetPage")
    public native CGPDFPage getPage(@MachineSizedUInt long pageNumber);
    @Bridge(symbol="CGPDFDocumentGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CGPDFDocumentGetMediaBox")
    public native @ByVal CGRect getMediaBox(int page);
    @Bridge(symbol="CGPDFDocumentGetCropBox")
    public native @ByVal CGRect getCropBox(int page);
    @Bridge(symbol="CGPDFDocumentGetBleedBox")
    public native @ByVal CGRect getBleedBox(int page);
    @Bridge(symbol="CGPDFDocumentGetTrimBox")
    public native @ByVal CGRect getTrimBox(int page);
    @Bridge(symbol="CGPDFDocumentGetArtBox")
    public native @ByVal CGRect getArtBox(int page);
    @Bridge(symbol="CGPDFDocumentGetRotationAngle")
    public native int getRotationAngle(int page);
    /*</methods>*/
}
