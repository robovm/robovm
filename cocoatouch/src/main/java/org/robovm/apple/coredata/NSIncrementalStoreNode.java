/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.coredata;

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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSIncrementalStoreNode/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSIncrementalStoreNodePtr extends Ptr<NSIncrementalStoreNode, NSIncrementalStoreNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSIncrementalStoreNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSIncrementalStoreNode() {}
    protected NSIncrementalStoreNode(SkipInit skipInit) { super(skipInit); }
    public NSIncrementalStoreNode(NSManagedObjectID objectID, NSDictionary<?, ?> values, long version) { super((SkipInit) null); initObject(initWithObjectID$withValues$version$(objectID, values, version)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithObjectID:withValues:version:")
    protected native @Pointer long initWithObjectID$withValues$version$(NSManagedObjectID objectID, NSDictionary<?, ?> values, long version);
    @Method(selector = "updateWithValues:version:")
    public native void updateWithValues$version$(NSDictionary<?, ?> values, long version);
    @Method(selector = "objectID")
    public native NSManagedObjectID objectID();
    @Method(selector = "version")
    public native long version();
    @Method(selector = "valueForPropertyDescription:")
    public native NSObject valueForPropertyDescription$(NSPropertyDescription prop);
    /*</methods>*/
}
