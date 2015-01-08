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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStreamPair/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFStreamPair.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    private CFReadStream readStream;
    private CFWriteStream writeStream;
    
    public static CFStreamPair create(@MachineSizedSInt long transferBufferSize) {
        CFReadStream.CFReadStreamPtr readPtr = new CFReadStream.CFReadStreamPtr();
        CFWriteStream.CFWriteStreamPtr writePtr = new CFWriteStream.CFWriteStreamPtr();
        create(null, readPtr, writePtr, transferBufferSize);
        CFStreamPair result = new CFStreamPair();
        result.readStream = readPtr.get();
        result.writeStream = writePtr.get();
        return result;
    }
    public static CFStreamPair create(int sock) {
        CFReadStream.CFReadStreamPtr readPtr = new CFReadStream.CFReadStreamPtr();
        CFWriteStream.CFWriteStreamPtr writePtr = new CFWriteStream.CFWriteStreamPtr();
        create(null, sock, readPtr, writePtr);
        CFStreamPair result = new CFStreamPair();
        result.readStream = readPtr.get();
        result.writeStream = writePtr.get();
        return result;
    }
    public static CFStreamPair create(String host, int port) {
        CFReadStream.CFReadStreamPtr readPtr = new CFReadStream.CFReadStreamPtr();
        CFWriteStream.CFWriteStreamPtr writePtr = new CFWriteStream.CFWriteStreamPtr();
        create(null, host, port, readPtr, writePtr);
        CFStreamPair result = new CFStreamPair();
        result.readStream = readPtr.get();
        result.writeStream = writePtr.get();
        return result;
    }
    public static CFStreamPair create(CFSocketSignature signature) {
        CFReadStream.CFReadStreamPtr readPtr = new CFReadStream.CFReadStreamPtr();
        CFWriteStream.CFWriteStreamPtr writePtr = new CFWriteStream.CFWriteStreamPtr();
        create(null, signature, readPtr, writePtr);
        CFStreamPair result = new CFStreamPair();
        result.readStream = readPtr.get();
        result.writeStream = writePtr.get();
        return result;
    }

    /*<methods>*/
    @Bridge(symbol="CFStreamCreateBoundPair", optional=true)
    protected static native void create(CFAllocator alloc, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream, @MachineSizedSInt long transferBufferSize);
    @Bridge(symbol="CFStreamCreatePairWithSocket", optional=true)
    protected static native void create(CFAllocator alloc, int sock, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream);
    @Bridge(symbol="CFStreamCreatePairWithSocketToHost", optional=true)
    protected static native void create(CFAllocator alloc, String host, int port, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream);
    @Bridge(symbol="CFStreamCreatePairWithPeerSocketSignature", optional=true)
    protected static native void create(CFAllocator alloc, CFSocketSignature signature, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream);
    /*</methods>*/
}
