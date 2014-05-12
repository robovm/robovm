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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableData/*</name>*/ 
    extends /*<extends>*/CFData/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableDataPtr extends Ptr<CFMutableData, CFMutableDataPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableData() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFDataCreateMutable", optional=true)
    public static native CFMutableData createMutable(CFAllocator allocator, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFDataCreateMutableCopy", optional=true)
    public static native CFMutableData createMutableCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFData theData);
    @Bridge(symbol="CFDataGetMutableBytePtr", optional=true)
    public native BytePtr getMutableBytePtr();
    @Bridge(symbol="CFDataSetLength", optional=true)
    public native void setLength(@MachineSizedSInt long length);
    @Bridge(symbol="CFDataIncreaseLength", optional=true)
    public native void increaseLength(@MachineSizedSInt long extraLength);
    @Bridge(symbol="CFDataAppendBytes", optional=true)
    public native void appendBytes(BytePtr bytes, @MachineSizedSInt long length);
    @Bridge(symbol="CFDataReplaceBytes", optional=true)
    public native void replaceBytes(@ByVal CFRange range, BytePtr newBytes, @MachineSizedSInt long newLength);
    @Bridge(symbol="CFDataDeleteBytes", optional=true)
    public native void deleteBytes(@ByVal CFRange range);
    /*</methods>*/
}
