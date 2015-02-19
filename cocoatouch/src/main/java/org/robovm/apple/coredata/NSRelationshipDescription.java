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

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
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
    @Property(selector = "destinationEntity")
    public native NSEntityDescription getDestinationEntity();
    @Property(selector = "setDestinationEntity:", strongRef = true)
    public native void setDestinationEntity(NSEntityDescription v);
    @Property(selector = "inverseRelationship")
    public native NSRelationshipDescription getInverseRelationship();
    @Property(selector = "setInverseRelationship:", strongRef = true)
    public native void setInverseRelationship(NSRelationshipDescription v);
    @Property(selector = "maxCount")
    public native @MachineSizedUInt long getMaxCount();
    @Property(selector = "setMaxCount:")
    public native void setMaxCount(@MachineSizedUInt long v);
    @Property(selector = "minCount")
    public native @MachineSizedUInt long getMinCount();
    @Property(selector = "setMinCount:")
    public native void setMinCount(@MachineSizedUInt long v);
    @Property(selector = "deleteRule")
    public native NSDeleteRule getDeleteRule();
    @Property(selector = "setDeleteRule:")
    public native void setDeleteRule(NSDeleteRule v);
    @Property(selector = "isToMany")
    public native boolean isToMany();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "versionHash")
    public native NSData getVersionHash();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isOrdered")
    public native boolean isOrdered();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setOrdered:")
    public native void setOrdered(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
