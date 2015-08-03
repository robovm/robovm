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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UIRemoteNotification.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIRemoteNotification/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UIRemoteNotification toObject(Class<UIRemoteNotification> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIRemoteNotification(o);
        }
        @MarshalsPointer
        public static long toNative(UIRemoteNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    private static final NSString ALERT = new NSString("alert");
    private static final NSString BODY = new NSString("body");
    private static final NSString SHOW_VIEW = new NSString("show-view");
    private static final NSString BADGE = new NSString("badge");
    private static final NSString SOUND = new NSString("sound");
    
    protected UIRemoteNotification(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    static { Bro.bind(UIRemoteNotification.class); }
    
    public String getAlert() {
        if (data.containsKey(ALERT)) {
            NSObject val = data.get(ALERT);
            if (val instanceof NSString) {
                return val.toString();
            }
            if (val instanceof NSDictionary) {
                NSDictionary<NSString, NSObject> alert = (NSDictionary<NSString, NSObject>) val;
                if (alert.containsKey(BODY)) {
                    NSString body = (NSString) alert.get(BODY);
                    return body.toString();
                }
            }
        }
        return null;
    }
    public boolean isViewButtonShown() {
        if (data.containsKey(ALERT)) {
            NSObject val = data.get(ALERT);
            if (val instanceof NSDictionary) {
                NSDictionary<NSString, NSObject> alert = (NSDictionary<NSString, NSObject>) val;
                if (alert.containsKey(SHOW_VIEW)) {
                    NSNumber showView = (NSNumber) alert.get(SHOW_VIEW);
                    return showView.booleanValue();
                }
            }
        }
        return true;
    }
    public long getBadgeNumber() {
        if (data.containsKey(BADGE)) {
            NSString val = (NSString) data.get(BADGE);
            return Long.valueOf(val.toString());
        }
        return -1;
    }
    public String getSound() {
        if (data.containsKey(SOUND)) {
            NSString val = (NSString) data.get(SOUND);
            return val.toString();
        }
        return null;
    }
    
    public NSObject get(String key) {
        return data.get(new NSString(key));
    }
    
    public String getString(String key) {
        return String.valueOf(get(key));
    }
    
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
}
