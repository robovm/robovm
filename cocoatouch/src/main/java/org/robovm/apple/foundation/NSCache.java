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
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCache/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSCachePtr extends Ptr<NSCache, NSCachePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCache.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSCache() {}
    protected NSCache(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "delegate")
    public native NSCacheDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSCacheDelegate v);
    @Property(selector = "totalCostLimit")
    public native @MachineSizedUInt long getTotalCostLimit();
    @Property(selector = "setTotalCostLimit:")
    public native void setTotalCostLimit(@MachineSizedUInt long v);
    @Property(selector = "countLimit")
    public native @MachineSizedUInt long getCountLimit();
    @Property(selector = "setCountLimit:")
    public native void setCountLimit(@MachineSizedUInt long v);
    @Property(selector = "evictsObjectsWithDiscardedContent")
    public native boolean evictsObjectsWithDiscardedContent();
    @Property(selector = "setEvictsObjectsWithDiscardedContent:")
    public native void setEvictsObjectsWithDiscardedContent(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSObject get(String key) {
        return get(new NSString(key));
    }
    
    public void put(NSObject key, NSObject obj) {
        setObject(obj, key);
    }
    public void put(String key, NSObject obj) {
        setObject(obj, new NSString(key));
    }
    public void put(NSObject key, NSObject obj, @MachineSizedUInt long g) {
        setObject(obj, key, g);
    }
    public void put(String key, NSObject obj, @MachineSizedUInt long g) {
        setObject(obj, new NSString(key), g);
    }
    
    public void remove(String key) {
        remove(new NSString(key));
    }
    /*<methods>*/
    @Method(selector = "objectForKey:")
    public native NSObject get(NSObject key);
    @Method(selector = "setObject:forKey:")
    protected native void setObject(NSObject obj, NSObject key);
    @Method(selector = "setObject:forKey:cost:")
    protected native void setObject(NSObject obj, NSObject key, @MachineSizedUInt long g);
    @Method(selector = "removeObjectForKey:")
    public native void remove(NSObject key);
    @Method(selector = "removeAllObjects")
    public native void clear();
    /*</methods>*/
}
