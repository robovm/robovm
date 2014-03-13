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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFData/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFDataPtr extends Ptr<CFData, CFDataPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFData() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFDataGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDataCreate")
    public static native CFData create(CFAllocator allocator, BytePtr bytes, @MachineSizedSInt long length);
    @Bridge(symbol="CFDataCreateWithBytesNoCopy")
    public static native CFData createWithBytesNoCopy(CFAllocator allocator, BytePtr bytes, @MachineSizedSInt long length, CFAllocator bytesDeallocator);
    @Bridge(symbol="CFDataCreateCopy")
    public static native CFData createCopy(CFAllocator allocator, CFData theData);
    @Bridge(symbol="CFDataGetLength")
    public native @MachineSizedSInt long getLength();
    @Bridge(symbol="CFDataGetBytePtr")
    public native BytePtr getBytePtr();
    @Bridge(symbol="CFDataGetBytes")
    public native void getBytes(@ByVal CFRange range, BytePtr buffer);
    @Bridge(symbol="CFDataFind")
    public native @ByVal CFRange find(CFData dataToFind, @ByVal CFRange searchRange, CFDataSearchFlags compareOptions);
    /*</methods>*/
}
