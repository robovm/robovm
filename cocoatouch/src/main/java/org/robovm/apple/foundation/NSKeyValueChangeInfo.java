/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*</javadoc>*/
@Marshaler(NSKeyValueChangeInfo.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSKeyValueChangeInfo/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSKeyValueChangeInfo toObject(Class<NSKeyValueChangeInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSKeyValueChangeInfo(o);
        }
        @MarshalsPointer
        public static long toNative(NSKeyValueChangeInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSKeyValueChangeInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(NSKeyValueChangeInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    public NSKeyValueChange getKind() {
        if (data.containsKey(KindKey())) {
            NSNumber val = (NSNumber)data.get(KindKey());
            return NSKeyValueChange.valueOf(val.intValue());
        }
        return null;
    }
    public NSObject getNew() {
        if (data.containsKey(NewKey())) {
            NSObject val = data.get(NewKey());
            return val;
        }
        return null;
    }
    public NSObject getOld() {
        if (data.containsKey(OldKey())) {
            NSObject val = data.get(OldKey());
            return val;
        }
        return null;
    }
    public NSIndexSet getIndexes(){
        if (data.containsKey(IndexesKey())) {
            NSIndexSet val = (NSIndexSet)data.get(IndexesKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isNotificationSentPriorToChange() {
        if (data.containsKey(NotificationIsPriorKey())) {
            NSNumber val = (NSNumber)data.get(NotificationIsPriorKey());
            return val.booleanValue();
        }
        return false;
    }
    /*<methods>*/
    @GlobalValue(symbol="NSKeyValueChangeKindKey", optional=true)
    protected static native NSString KindKey();
    @GlobalValue(symbol="NSKeyValueChangeNewKey", optional=true)
    protected static native NSString NewKey();
    @GlobalValue(symbol="NSKeyValueChangeOldKey", optional=true)
    protected static native NSString OldKey();
    @GlobalValue(symbol="NSKeyValueChangeIndexesKey", optional=true)
    protected static native NSString IndexesKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSKeyValueChangeNotificationIsPriorKey", optional=true)
    protected static native NSString NotificationIsPriorKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
