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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSRelationshipDescription/*</name>*/ 
    extends /*<extends>*/NSPropertyDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSRelationshipDescriptionPtr extends Ptr<NSRelationshipDescription, NSRelationshipDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSRelationshipDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSRelationshipDescription() {}
    protected NSRelationshipDescription(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "destinationEntity")
    public native NSEntityDescription destinationEntity();
    @Method(selector = "setDestinationEntity:")
    public native void setDestinationEntity$(NSEntityDescription entity);
    @Method(selector = "inverseRelationship")
    public native NSRelationshipDescription inverseRelationship();
    @Method(selector = "setInverseRelationship:")
    public native void setInverseRelationship$(NSRelationshipDescription relationship);
    @Method(selector = "maxCount")
    public native @MachineSizedUInt long maxCount();
    @Method(selector = "setMaxCount:")
    public native void setMaxCount$(@MachineSizedUInt long maxCount);
    @Method(selector = "minCount")
    public native @MachineSizedUInt long minCount();
    @Method(selector = "setMinCount:")
    public native void setMinCount$(@MachineSizedUInt long minCount);
    @Method(selector = "deleteRule")
    public native NSDeleteRule deleteRule();
    @Method(selector = "setDeleteRule:")
    public native void setDeleteRule$(NSDeleteRule rule);
    @Method(selector = "isToMany")
    public native boolean isToMany();
    @Method(selector = "versionHash")
    public native NSData versionHash();
    @Method(selector = "setOrdered:")
    public native void setOrdered$(boolean flag);
    @Method(selector = "isOrdered")
    public native boolean isOrdered();
    /*</methods>*/
}
