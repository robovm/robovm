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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSEntityDescription/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSEntityDescriptionPtr extends Ptr<NSEntityDescription, NSEntityDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSEntityDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSEntityDescription() {}
    protected NSEntityDescription(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "managedObjectModel")
    public native NSManagedObjectModel managedObjectModel();
    @Method(selector = "managedObjectClassName")
    public native String managedObjectClassName();
    @Method(selector = "setManagedObjectClassName:")
    public native void setManagedObjectClassName(String name);
    @Method(selector = "name")
    public native String name();
    @Method(selector = "setName:")
    public native void setName(String name);
    @Method(selector = "isAbstract")
    public native boolean isAbstract();
    @Method(selector = "setAbstract:")
    public native void setAbstract(boolean flag);
    @Method(selector = "subentitiesByName")
    public native NSDictionary<?, ?> subentitiesByName();
    @Method(selector = "subentities")
    public native NSArray<?> subentities();
    @Method(selector = "setSubentities:")
    public native void setSubentities(NSArray<?> array);
    @Method(selector = "superentity")
    public native NSEntityDescription superentity();
    @Method(selector = "propertiesByName")
    public native NSDictionary<?, ?> propertiesByName();
    @Method(selector = "properties")
    public native NSArray<?> properties();
    @Method(selector = "setProperties:")
    public native void setProperties(NSArray<?> properties);
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> dictionary);
    @Method(selector = "attributesByName")
    public native NSDictionary<?, ?> attributesByName();
    @Method(selector = "relationshipsByName")
    public native NSDictionary<?, ?> relationshipsByName();
    @Method(selector = "relationshipsWithDestinationEntity:")
    public native NSArray<?> relationshipsWithDestinationEntity$(NSEntityDescription entity);
    @Method(selector = "isKindOfEntity:")
    public native boolean isKindOfEntity$(NSEntityDescription entity);
    @Method(selector = "versionHash")
    public native NSData versionHash();
    @Method(selector = "versionHashModifier")
    public native String versionHashModifier();
    @Method(selector = "setVersionHashModifier:")
    public native void setVersionHashModifier(String modifierString);
    @Method(selector = "renamingIdentifier")
    public native String renamingIdentifier();
    @Method(selector = "setRenamingIdentifier:")
    public native void setRenamingIdentifier(String value);
    @Method(selector = "compoundIndexes")
    public native NSArray<?> compoundIndexes();
    @Method(selector = "setCompoundIndexes:")
    public native void setCompoundIndexes(NSArray<?> value);
    @Method(selector = "entityForName:inManagedObjectContext:")
    public static native NSEntityDescription entityForName$inManagedObjectContext$(String entityName, NSManagedObjectContext context);
    @Method(selector = "insertNewObjectForEntityForName:inManagedObjectContext:")
    public static native NSObject insertNewObjectForEntityForName$inManagedObjectContext$(String entityName, NSManagedObjectContext context);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
