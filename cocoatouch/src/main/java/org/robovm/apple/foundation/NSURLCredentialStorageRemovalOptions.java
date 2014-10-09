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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSURLCredentialStorageRemovalOptions.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLCredentialStorageRemovalOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSURLCredentialStorageRemovalOptions toObject(Class<NSURLCredentialStorageRemovalOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSURLCredentialStorageRemovalOptions(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLCredentialStorageRemovalOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSURLCredentialStorageRemovalOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSURLCredentialStorageRemovalOptions() {
        this.data = new NSMutableDictionary<>();
    }   
    /*<bind>*/static { Bro.bind(NSURLCredentialStorageRemovalOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public boolean shouldRemoveSynchronizableCredentials() {
        if (data.containsKey(RemoveSynchronizableCredentials())) {
            NSNumber val = (NSNumber)data.get(RemoveSynchronizableCredentials());
            return val.booleanValue();
        }
        return false;
    }
    public NSURLCredentialStorageRemovalOptions setShouldRemoveSynchronizableCredentials(boolean remove) {
        data.put(RemoveSynchronizableCredentials(), NSNumber.valueOf(remove));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLCredentialStorageRemoveSynchronizableCredentials", optional=true)
    protected static native NSString RemoveSynchronizableCredentials();
    /*</methods>*/
}
