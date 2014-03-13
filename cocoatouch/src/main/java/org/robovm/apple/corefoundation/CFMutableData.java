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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableData/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFDataCreateMutable")
    public static native CFMutableData createMutable(CFAllocator allocator, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFDataCreateMutableCopy")
    public static native CFMutableData createMutableCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFData theData);
    @Bridge(symbol="CFDataGetMutableBytePtr")
    public static native BytePtr getMutableBytePtr(CFData theData);
    @Bridge(symbol="CFDataSetLength")
    public static native void setLength(CFData theData, @MachineSizedSInt long length);
    @Bridge(symbol="CFDataIncreaseLength")
    public static native void increaseLength(CFData theData, @MachineSizedSInt long extraLength);
    @Bridge(symbol="CFDataAppendBytes")
    public static native void appendBytes(CFData theData, BytePtr bytes, @MachineSizedSInt long length);
    @Bridge(symbol="CFDataReplaceBytes")
    public static native void replaceBytes(CFData theData, @ByVal CFRange range, BytePtr newBytes, @MachineSizedSInt long newLength);
    @Bridge(symbol="CFDataDeleteBytes")
    public static native void deleteBytes(CFData theData, @ByVal CFRange range);
    /*</methods>*/
}
