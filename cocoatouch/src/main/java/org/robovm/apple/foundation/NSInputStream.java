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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSInputStream/*</name>*/ 
    extends /*<extends>*/NSStream/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSInputStreamPtr extends Ptr<NSInputStream, NSInputStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSInputStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSInputStream() {}
    protected NSInputStream(SkipInit skipInit) { super(skipInit); }
    public NSInputStream(NSData data) { super((SkipInit) null); initObject(initWithData$(data)); }
    public NSInputStream(String path) { super((SkipInit) null); initObject(initWithFileAtPath$(path)); }
    public NSInputStream(NSURL url) { super((SkipInit) null); initObject(initWithURL$(url)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "read:maxLength:")
    public native @MachineSizedSInt long read$maxLength$(BytePtr buffer, @MachineSizedUInt long len);
    @Method(selector = "getBuffer:length:")
    public native boolean getBuffer$length$(BytePtr.BytePtrPtr buffer, MachineSizedUIntPtr len);
    @Method(selector = "hasBytesAvailable")
    public native boolean hasBytesAvailable();
    @Method(selector = "initWithData:")
    protected native @Pointer long initWithData$(NSData data);
    @Method(selector = "initWithFileAtPath:")
    protected native @Pointer long initWithFileAtPath$(String path);
    @Method(selector = "initWithURL:")
    protected native @Pointer long initWithURL$(NSURL url);
    @Method(selector = "inputStreamWithData:")
    public static native NSObject inputStreamWithData$(NSData data);
    @Method(selector = "inputStreamWithFileAtPath:")
    public static native NSObject inputStreamWithFileAtPath$(String path);
    @Method(selector = "inputStreamWithURL:")
    public static native NSObject inputStreamWithURL$(NSURL url);
    /*</methods>*/
}
