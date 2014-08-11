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
package org.robovm.apple.coremidi;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMIDI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIThruConnection/*</name>*/ 
    extends /*<extends>*/MIDIObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIThruConnectionPtr extends Ptr<MIDIThruConnection, MIDIThruConnectionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIThruConnection.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MIDIThruConnection() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static MIDIThruConnection create(String inPersistentOwnerID, NSData inConnectionParams) {
        MIDIThruConnectionPtr ptr = new MIDIThruConnectionPtr();
        create(inPersistentOwnerID, inConnectionParams, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public NSData getParams() {
        NSData.NSDataPtr ptr = new NSData.NSDataPtr();
        getParams(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public NSData find(String persistentOwnerID) {
        NSData.NSDataPtr ptr = new NSData.NSDataPtr();
        find(persistentOwnerID, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIThruConnectionCreate", optional=true)
    protected static native MIDIError create(String inPersistentOwnerID, NSData inConnectionParams, MIDIThruConnection.MIDIThruConnectionPtr outConnection);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIThruConnectionDispose", optional=true)
    public native MIDIError dispose();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIThruConnectionGetParams", optional=true)
    protected native MIDIError getParams(NSData.NSDataPtr outConnectionParams);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIThruConnectionSetParams", optional=true)
    public native MIDIError setParams(NSData inConnectionParams);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIThruConnectionFind", optional=true)
    protected static native MIDIError find(String inPersistentOwnerID, NSData.NSDataPtr outConnectionList);
    /*</methods>*/
}
