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
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSEntityMapping/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSEntityMappingPtr extends Ptr<NSEntityMapping, NSEntityMappingPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSEntityMapping.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSEntityMapping() {}
    protected NSEntityMapping(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "mappingType")
    public native NSEntityMappingType getMappingType();
    @Property(selector = "setMappingType:")
    public native void setMappingType(NSEntityMappingType v);
    @Property(selector = "sourceEntityName")
    public native String getSourceEntityName();
    @Property(selector = "setSourceEntityName:")
    public native void setSourceEntityName(String v);
    @Property(selector = "sourceEntityVersionHash")
    public native NSData getSourceEntityVersionHash();
    @Property(selector = "setSourceEntityVersionHash:")
    public native void setSourceEntityVersionHash(NSData v);
    @Property(selector = "destinationEntityName")
    public native String getDestinationEntityName();
    @Property(selector = "setDestinationEntityName:")
    public native void setDestinationEntityName(String v);
    @Property(selector = "destinationEntityVersionHash")
    public native NSData getDestinationEntityVersionHash();
    @Property(selector = "setDestinationEntityVersionHash:")
    public native void setDestinationEntityVersionHash(NSData v);
    @Property(selector = "attributeMappings")
    public native NSArray<NSPropertyMapping> getAttributeMappings();
    @Property(selector = "setAttributeMappings:")
    public native void setAttributeMappings(NSArray<NSPropertyMapping> v);
    @Property(selector = "relationshipMappings")
    public native NSArray<NSPropertyMapping> getRelationshipMappings();
    @Property(selector = "setRelationshipMappings:")
    public native void setRelationshipMappings(NSArray<NSPropertyMapping> v);
    @Property(selector = "sourceExpression")
    public native NSExpression getSourceExpression();
    @Property(selector = "setSourceExpression:")
    public native void setSourceExpression(NSExpression v);
    @Property(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    @Property(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> v);
    @Property(selector = "entityMigrationPolicyClassName")
    public native String getEntityMigrationPolicyClassName();
    @Property(selector = "setEntityMigrationPolicyClassName:")
    public native void setEntityMigrationPolicyClassName(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
