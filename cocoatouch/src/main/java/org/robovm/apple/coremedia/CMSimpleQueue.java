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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSimpleQueue/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMSimpleQueuePtr extends Ptr<CMSimpleQueue, CMSimpleQueuePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMSimpleQueue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CMSimpleQueue() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public static CMSimpleQueue create(int capacity) throws OSStatusException {
        return create(null, capacity);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public static CMSimpleQueue create(CFAllocator allocator, int capacity) throws OSStatusException {
        CMSimpleQueuePtr ptr = new CMSimpleQueuePtr();
        OSStatus status = create0(allocator, capacity, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void enqueue(VoidPtr element) throws OSStatusException {
        OSStatus status = enqueue0(element);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void reset() throws OSStatusException {
        OSStatus status = reset0();
        OSStatusException.throwIfNecessary(status);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueCreate", optional=true)
    protected static native OSStatus create0(CFAllocator allocator, int capacity, CMSimpleQueue.CMSimpleQueuePtr queueOut);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueEnqueue", optional=true)
    protected native OSStatus enqueue0(VoidPtr element);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueDequeue", optional=true)
    public native VoidPtr dequeue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueGetHead", optional=true)
    public native VoidPtr getHead();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueReset", optional=true)
    protected native OSStatus reset0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueGetCapacity", optional=true)
    public native int getCapacity();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CMSimpleQueueGetCount", optional=true)
    public native int getCount();
    /*</methods>*/
}
