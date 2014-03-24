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
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "name")
    public native String name();
    @Method(selector = "setName:")
    public native void setName$(String name);
    @Method(selector = "mappingType")
    public native NSEntityMappingType mappingType();
    @Method(selector = "setMappingType:")
    public native void setMappingType$(NSEntityMappingType type);
    @Method(selector = "sourceEntityName")
    public native String sourceEntityName();
    @Method(selector = "setSourceEntityName:")
    public native void setSourceEntityName$(String name);
    @Method(selector = "sourceEntityVersionHash")
    public native NSData sourceEntityVersionHash();
    @Method(selector = "setSourceEntityVersionHash:")
    public native void setSourceEntityVersionHash$(NSData vhash);
    @Method(selector = "destinationEntityName")
    public native String destinationEntityName();
    @Method(selector = "setDestinationEntityName:")
    public native void setDestinationEntityName$(String name);
    @Method(selector = "destinationEntityVersionHash")
    public native NSData destinationEntityVersionHash();
    @Method(selector = "setDestinationEntityVersionHash:")
    public native void setDestinationEntityVersionHash$(NSData vhash);
    @Method(selector = "attributeMappings")
    public native NSArray<?> attributeMappings();
    @Method(selector = "setAttributeMappings:")
    public native void setAttributeMappings$(NSArray<?> mappings);
    @Method(selector = "relationshipMappings")
    public native NSArray<?> relationshipMappings();
    @Method(selector = "setRelationshipMappings:")
    public native void setRelationshipMappings$(NSArray<?> mappings);
    @Method(selector = "sourceExpression")
    public native NSExpression sourceExpression();
    @Method(selector = "setSourceExpression:")
    public native void setSourceExpression$(NSExpression source);
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "setUserInfo:")
    public native void setUserInfo$(NSDictionary<?, ?> dict);
    @Method(selector = "entityMigrationPolicyClassName")
    public native String entityMigrationPolicyClassName();
    @Method(selector = "setEntityMigrationPolicyClassName:")
    public native void setEntityMigrationPolicyClassName$(String name);
    /*</methods>*/
}
