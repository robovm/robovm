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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNotificationCenter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface NotificationCallback {
        void invoke(CFNotificationCenter center, CFType observer, String name, CFType object, NSDictionary<NSString, ?> userInfo);
    }
    
    private static final Map<Long, NotificationCallback> callbacks = new HashMap<>();
    private static final java.lang.reflect.Method cbNotification;
    
    static {
        try {
            cbNotification = CFNotificationCenter.class.getDeclaredMethod("cbNotification", CFNotificationCenter.class, CFType.class, String.class, CFType.class, NSDictionary.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<ptr>*/public static class CFNotificationCenterPtr extends Ptr<CFNotificationCenter, CFNotificationCenterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFNotificationCenter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNotificationCenter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbNotification(CFNotificationCenter center, CFType observer, String name, CFType object, NSDictionary<NSString, ?> userInfo) {
        NotificationCallback callback = null;
        synchronized (callbacks) {
            long id = getCallbackIdForNotification(observer, name, object);
            callback = callbacks.get(id);
        }
        // We don't store observer callbacks for every possible combination of observer, name and object.
        // Therefore a callback for the current combination is not registered and will be null.
        if (callback != null) {
            callback.invoke(center, observer, name, object, userInfo);
        }
    }
    
    public void addObserver(CFType observer, NotificationCallback callBack, String name, CFType object, CFNotificationSuspensionBehavior suspensionBehavior) {
        synchronized(callbacks) {
            long id = getCallbackIdForNotification(observer, name, object);
            callbacks.put(id, callBack);
        }
        addObserver(observer, new FunctionPtr(cbNotification), name, object, suspensionBehavior);
    }
    
    private static long getCallbackIdForNotification(CFType observer, String name, CFType object) {
        final int prime = 31;
        long id = name.hashCode();
        if (observer != null) {
            id = id * prime + observer.hash();
        }
        if (object != null) {
            id = id * prime + object.hash();
        }
        return id;
    }
    /*<methods>*/
    @Bridge(symbol="CFNotificationCenterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFNotificationCenterGetLocalCenter", optional=true)
    public static native CFNotificationCenter getLocalCenter();
    @Bridge(symbol="CFNotificationCenterGetDarwinNotifyCenter", optional=true)
    public static native CFNotificationCenter getDarwinNotifyCenter();
    @Bridge(symbol="CFNotificationCenterAddObserver", optional=true)
    private native void addObserver(CFType observer, FunctionPtr callBack, String name, CFType object, CFNotificationSuspensionBehavior suspensionBehavior);
    @Bridge(symbol="CFNotificationCenterRemoveObserver", optional=true)
    public native void removeObserver(CFType observer, String name, CFType object);
    @Bridge(symbol="CFNotificationCenterRemoveEveryObserver", optional=true)
    public native void removeEveryObserver(CFType observer);
    @Bridge(symbol="CFNotificationCenterPostNotification", optional=true)
    public native void postNotification(String name, CFType object, NSDictionary<NSString, ?> userInfo, boolean deliverImmediately);
    @Bridge(symbol="CFNotificationCenterPostNotificationWithOptions", optional=true)
    public native void postNotification(String name, CFType object, NSDictionary<NSString, ?> userInfo, CFNotificationPostingOptions options);
    /*</methods>*/
}
