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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFObject/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGPDFObjectPtr extends Ptr<CGPDFObject, CGPDFObjectPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPDFObject.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGPDFObject() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public boolean isNull() {
        return getValue(CGPDFObjectType.Null, new VoidPtr());
    }
    public boolean booleanValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.Boolean, ptr)) {
            return ptr.as(BooleanPtr.class).get();
        }
        return false;
    }
    public long longValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.Integer, ptr)) {
            return ptr.as(LongPtr.class).get();
        }
        return 0;
    }
    public double doubleValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.Real, ptr)) {
            return ptr.as(DoublePtr.class).get();
        }
        return 0;
    }
    public String nameValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.Name, ptr)) {
            return ptr.as(BytePtr.class).toStringZ();
        }
        return null;
    }
    public String stringValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.String, ptr)) {
            return ptr.as(BytePtr.class).toStringZ();
        }
        return null;
    }
    public CGPDFArray arrayValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.Array, ptr)) {
            return ptr.as(CGPDFArray.CGPDFArrayPtr.class).get();
        }
        return null;
    }
    public CGPDFDictionary dictionaryValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.Dictionary, ptr)) {
            return ptr.as(CGPDFDictionary.CGPDFDictionaryPtr.class).get();
        }
        return null;
    }
    public CGPDFStream streamValue() {
        VoidPtr ptr = new VoidPtr();
        if (getValue(CGPDFObjectType.Stream, ptr)) {
            return ptr.as(CGPDFStream.CGPDFStreamPtr.class).get();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFObjectGetType", optional=true)
    public native CGPDFObjectType getType();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFObjectGetValue", optional=true)
    private native boolean getValue(CGPDFObjectType type, VoidPtr value);
    /*</methods>*/
}
