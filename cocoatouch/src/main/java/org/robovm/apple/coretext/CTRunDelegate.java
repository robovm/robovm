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
package org.robovm.apple.coretext;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTRunDelegate/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*//*</ptr>*/
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static Map<Long, CTRunDelegate> runCallbacks = new HashMap<Long, CTRunDelegate>();
    
    private static final java.lang.reflect.Method cbGetAscent;
    private static final java.lang.reflect.Method cbGetDescent;
    private static final java.lang.reflect.Method cbGetWidth;
    
    static {
        try {
            cbGetAscent = CTRunDelegate.class.getDeclaredMethod("cbGetAscent", long.class);
            cbGetDescent = CTRunDelegate.class.getDeclaredMethod("cbGetDescent", long.class);
            cbGetWidth = CTRunDelegate.class.getDeclaredMethod("cbGetWidth", long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    private CTRunDelegateIntern delegate;
    
    /*<bind>*/static { Bro.bind(CTRunDelegate.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public CTRunDelegate() {
        CTRunDelegateCallbacks callbacks = new CTRunDelegateCallbacks();
        callbacks.setGetAscent(new FunctionPtr(cbGetAscent));
        callbacks.setGetDescent(new FunctionPtr(cbGetDescent));
        callbacks.setGetWidth(new FunctionPtr(cbGetWidth));
        long refconId = CTRunDelegate.refconId.getAndIncrement();
        delegate = CTRunDelegateIntern.create(callbacks, refconId);
        synchronized(runCallbacks) {
            runCallbacks.put(refconId, this);
        }
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected CTRunDelegateIntern getDelegate() {
        return delegate;
    }
    
    @Callback
    private static @MachineSizedFloat double cbGetAscent(@Pointer long refcon) {
        CTRunDelegate callback = null;
        synchronized (runCallbacks) {
            callback = runCallbacks.get(refcon);
        }
        return callback.getAscent();
    }
    @Callback
    private static @MachineSizedFloat double cbGetDescent(@Pointer long refcon) {
        CTRunDelegate callback = null;
        synchronized (runCallbacks) {
            callback = runCallbacks.get(refcon);
        }
        return callback.getDescent();
    }
    @Callback
    private static @MachineSizedFloat double cbGetWidth(@Pointer long refcon) {
        CTRunDelegate callback = null;
        synchronized (runCallbacks) {
            callback = runCallbacks.get(refcon);
        }
        return callback.getWidth();
    }
    
    public @MachineSizedFloat double getAscent() {
        return 0;
    }
    public @MachineSizedFloat double getDescent() {
        return 0;
    }
    public @MachineSizedFloat double getWidth() {
        return 0;
    }
    /*<methods>*/
    /*</methods>*/
}
