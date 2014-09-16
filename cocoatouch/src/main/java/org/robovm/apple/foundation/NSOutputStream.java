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
package org.robovm.apple.foundation;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSOutputStream/*</name>*/ 
    extends /*<extends>*/NSStream/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSOutputStreamPtr extends Ptr<NSOutputStream, NSOutputStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSOutputStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected NSOutputStream(SkipInit skipInit) { super(skipInit); }
    public NSOutputStream() { super((SkipInit) null); initObject(initToMemory()); }
    public NSOutputStream(BytePtr buffer, @MachineSizedUInt long capacity) { super((SkipInit) null); initObject(initToBuffer$capacity$(buffer, capacity)); }
    public NSOutputStream(String path, boolean shouldAppend) { super((SkipInit) null); initObject(initToFileAtPath$append$(path, shouldAppend)); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSOutputStream(NSURL url, boolean shouldAppend) { super((SkipInit) null); initObject(initWithURL$append$(url, shouldAppend)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/

    public long write(BytePtr buffer, long len) {
        return write$maxLength$(buffer.getHandle(), len);
    }

    public long write(ByteBuffer bytes) {
        long handle = NSData.getEffectiveAddress(bytes) + bytes.position();
        return write$maxLength$(handle, bytes.remaining());
    }

    public long write(byte[] bytes) {
        return write(bytes, 0, bytes.length);
    }
    
    public long write(byte[] bytes, int offset, int length) {
        NSMutableData.checkOffsetAndCount(bytes.length, offset, length);
        if (length == 0) {
            return 0;
        }
        return write$maxLength$(VM.getArrayValuesAddress(bytes) + offset, length);
    }
    
    /*<methods>*/
    @Method(selector = "write:maxLength:")
    protected native @MachineSizedSInt long write$maxLength$(@Pointer long buffer, @MachineSizedUInt long len);
    @Method(selector = "hasSpaceAvailable")
    public native boolean hasSpaceAvailable();
    @Method(selector = "initToMemory")
    protected native @Pointer long initToMemory();
    @Method(selector = "initToBuffer:capacity:")
    protected native @Pointer long initToBuffer$capacity$(BytePtr buffer, @MachineSizedUInt long capacity);
    @Method(selector = "initToFileAtPath:append:")
    protected native @Pointer long initToFileAtPath$append$(String path, boolean shouldAppend);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithURL:append:")
    protected native @Pointer long initWithURL$append$(NSURL url, boolean shouldAppend);
    /*</methods>*/
}
