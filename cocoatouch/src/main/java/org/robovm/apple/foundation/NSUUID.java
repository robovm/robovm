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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSUUID/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSUUIDPtr extends Ptr<NSUUID, NSUUIDPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSUUID.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSUUID() {}
    protected NSUUID(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public NSUUID(UUID uuid) {
        this(uuid.toString());
    }
    
    public NSUUID(String string) {
        super((SkipInit) null);
        initObject(initWithUUIDString$(string));
    }

    public NSUUID(byte[] bytes) {
        super((SkipInit) null);
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        if (bytes.length != 16) {
            throw new IllegalArgumentException("bytes.length != 16 (" + bytes.length + ")");
        }
        initObject(initWithUUIDBytes$(VM.getArrayValuesAddress(bytes)));
    }

    /*<properties>*/
    @Property(selector = "UUIDString")
    public native String asString();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public byte[] getBytes() {
        byte[] bytes = new byte[16];
        getUUIDBytes$(VM.getArrayValuesAddress(bytes));
        return bytes;
    }
    
    public UUID toUUID() {
        return UUID.fromString(asString());
    }
    
    /*<methods>*/
    @Method(selector = "initWithUUIDString:")
    protected native @Pointer long initWithUUIDString$(String string);
    @Method(selector = "initWithUUIDBytes:")
    protected native @Pointer long initWithUUIDBytes$(@Pointer long bytes);
    @Method(selector = "getUUIDBytes:")
    protected native void getUUIDBytes$(@Pointer long uuid);
    /*</methods>*/
}
