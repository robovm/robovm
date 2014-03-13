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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStream/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFStreamPropertyDataWritten")
    public static native CFString PropertyDataWritten();
    @GlobalValue(symbol="kCFStreamPropertyAppendToFile")
    public static native CFString PropertyAppendToFile();
    @GlobalValue(symbol="kCFStreamPropertyFileCurrentOffset")
    public static native CFString PropertyFileCurrentOffset();
    @GlobalValue(symbol="kCFStreamPropertySocketNativeHandle")
    public static native CFString PropertySocketNativeHandle();
    @GlobalValue(symbol="kCFStreamPropertySocketRemoteHostName")
    public static native CFString PropertySocketRemoteHostName();
    @GlobalValue(symbol="kCFStreamPropertySocketRemotePortNumber")
    public static native CFString PropertySocketRemotePortNumber();
    
    @Bridge(symbol="CFStreamCreateBoundPair")
    public static native void createBoundPair(CFAllocator alloc, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream, @MachineSizedSInt long transferBufferSize);
    @Bridge(symbol="CFStreamCreatePairWithSocket")
    public static native void createPairWithSocket(CFAllocator alloc, int sock, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream);
    @Bridge(symbol="CFStreamCreatePairWithSocketToHost")
    public static native void createPairWithSocketToHost(CFAllocator alloc, CFString host, int port, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream);
    @Bridge(symbol="CFStreamCreatePairWithPeerSocketSignature")
    public static native void createPairWithPeerSocketSignature(CFAllocator alloc, CFSocketSignature signature, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream);
    /*</methods>*/
}
