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
package org.robovm.apple.coremidi;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMIDI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIEndpoint/*</name>*/ 
    extends /*<extends>*/MIDIObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIEndpointPtr extends Ptr<MIDIEndpoint, MIDIEndpointPtr> {}/*</ptr>*/
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static LongMap<MIDIReadProc> readProcs = new LongMap<>();
    private static final java.lang.reflect.Method cbReadProc;
    
    static {
        try {
            cbReadProc = MIDIEndpoint.class.getDeclaredMethod("cbReadProc", MIDIPacketList.class, long.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(MIDIEndpoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbReadProc(MIDIPacketList pktlist, @Pointer long readProcRefCon, @Pointer long srcConnRefCon) {
        MIDIReadProc callback = null;
        synchronized (readProcs) {
            callback = readProcs.get(readProcRefCon);
        }
        callback.read(pktlist);
    }
    public MIDIEntity getEntity() {
        MIDIEntity.MIDIEntityPtr ptr = new MIDIEntity.MIDIEntityPtr();
        getEntity(ptr);
        return ptr.get();
    }
    public static MIDIEndpoint createDestination(MIDIClient client, String name, MIDIReadProc readProc) {
        long refconId = MIDIEndpoint.refconId.getAndIncrement();
        MIDIEndpointPtr ptr = new MIDIEndpointPtr();
        MIDIError err = createDestination(client, name, new FunctionPtr(cbReadProc), refconId, ptr);
        if (err == MIDIError.No) {
            synchronized (readProcs) {
                readProcs.put(refconId, readProc);
            }
            return ptr.get();
        }
        return null;
    }
    public static MIDIEndpoint createSource(MIDIClient client, String name) {
        MIDIEndpointPtr ptr = new MIDIEndpointPtr();
        createSource(client, name, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEndpointGetEntity", optional=true)
    protected native MIDIError getEntity(MIDIEntity.MIDIEntityPtr outEntity);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIDestinationCreate", optional=true)
    protected static native MIDIError createDestination(MIDIClient client, String name, FunctionPtr readProc, @Pointer long refCon, MIDIEndpoint.MIDIEndpointPtr outDest);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDISourceCreate", optional=true)
    protected static native MIDIError createSource(MIDIClient client, String name, MIDIEndpoint.MIDIEndpointPtr outSrc);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEndpointDispose", optional=true)
    public native MIDIError dispose();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIReceived", optional=true)
    public native MIDIError received(MIDIPacketList pktlist);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEndpointSetRefCons", optional=true)
    protected native MIDIError setRefCons(@Pointer long ref1, @Pointer long ref2);
    /*</methods>*/
}
