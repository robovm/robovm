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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setName:")
    public native void setName$(String n);
    @Method(selector = "name")
    public native String getName();
    @Method(selector = "setDelegate:")
    public native void setSetDelegate(NSCacheDelegate d);
    @Method(selector = "delegate")
    public native NSCacheDelegate getDelegate();
    @Method(selector = "objectForKey:")
    public native NSObject objectForKey$(NSObject key);
    @Method(selector = "setObject:forKey:")
    public native void setObject$forKey$(NSObject obj, NSObject key);
    @Method(selector = "setObject:forKey:cost:")
    public native void setObject$forKey$cost$(NSObject obj, NSObject key, @MachineSizedUInt long g);
    @Method(selector = "removeObjectForKey:")
    public native void removeObjectForKey$(NSObject key);
    @Method(selector = "removeAllObjects")
    public native void removeAllObjects();
    @Method(selector = "setTotalCostLimit:")
    public native void setTotalCostLimit$(@MachineSizedUInt long lim);
    @Method(selector = "totalCostLimit")
    public native @MachineSizedUInt long getTotalCostLimit();
    @Method(selector = "setCountLimit:")
    public native void setCountLimit$(@MachineSizedUInt long lim);
    @Method(selector = "countLimit")
    public native @MachineSizedUInt long getCountLimit();
    @Method(selector = "evictsObjectsWithDiscardedContent")
    public native boolean isEvictsObjectsWithDiscardedContent();
    @Method(selector = "setEvictsObjectsWithDiscardedContent:")
    public native void setEvictsObjectsWithDiscardedContent$(boolean b);
    /*</methods>*/
}
