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
package org.robovm.apple.coredata;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSBatchUpdateRequest/*</name>*/ 
    extends /*<extends>*/NSPersistentStoreRequest/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSBatchUpdateRequestPtr extends Ptr<NSBatchUpdateRequest, NSBatchUpdateRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSBatchUpdateRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSBatchUpdateRequest() {}
    protected NSBatchUpdateRequest(SkipInit skipInit) { super(skipInit); }
    public NSBatchUpdateRequest(String entityName) { super((SkipInit) null); initObject(init(entityName)); }
    public NSBatchUpdateRequest(NSEntityDescription entity) { super((SkipInit) null); initObject(init(entity)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "entityName")
    public native String getEntityName();
    @Property(selector = "entity")
    public native NSEntityDescription getEntity();
    @Property(selector = "predicate")
    public native NSPredicate getPredicate();
    @Property(selector = "setPredicate:")
    public native void setPredicate(NSPredicate v);
    @Property(selector = "includesSubentities")
    public native boolean includesSubentities();
    @Property(selector = "setIncludesSubentities:")
    public native void setIncludesSubentities(boolean v);
    @Property(selector = "resultType")
    public native NSBatchUpdateRequestResultType getResultType();
    @Property(selector = "setResultType:")
    public native void setResultType(NSBatchUpdateRequestResultType v);
    @Property(selector = "propertiesToUpdate")
    public native NSDictionary<NSString, NSExpression> getPropertiesToUpdate();
    @Property(selector = "setPropertiesToUpdate:")
    public native void setPropertiesToUpdate(NSDictionary<NSString, NSExpression> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithEntityName:")
    protected native @Pointer long init(String entityName);
    @Method(selector = "initWithEntity:")
    protected native @Pointer long init(NSEntityDescription entity);
    /*</methods>*/
}
