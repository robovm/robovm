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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSEntityDescription/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, NSFastEnumeration/*</implements>*/ {

    /*<ptr>*/public static class NSEntityDescriptionPtr extends Ptr<NSEntityDescription, NSEntityDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSEntityDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSEntityDescription() {}
    protected NSEntityDescription(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "managedObjectModel")
    public native NSManagedObjectModel getManagedObjectModel();
    @Property(selector = "managedObjectClassName")
    public native String getManagedObjectClassName();
    @Property(selector = "setManagedObjectClassName:")
    public native void setManagedObjectClassName(String v);
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "isAbstract")
    public native boolean isAbstract();
    @Property(selector = "setAbstract:")
    public native void setAbstract(boolean v);
    @Property(selector = "subentitiesByName")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSEntityDescription> getSubentitiesByName();
    @Property(selector = "subentities")
    public native NSArray<NSEntityDescription> getSubentities();
    @Property(selector = "setSubentities:")
    public native void setSubentities(NSArray<NSEntityDescription> v);
    @Property(selector = "superentity")
    public native NSEntityDescription getSuperentity();
    @Property(selector = "propertiesByName")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSPropertyDescription> getPropertiesByName();
    @Property(selector = "properties")
    public native NSArray<NSPropertyDescription> getProperties();
    @Property(selector = "setProperties:")
    public native void setProperties(NSArray<NSPropertyDescription> v);
    @Property(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    @Property(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> v);
    @Property(selector = "attributesByName")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSAttributeDescription> getAttributesByName();
    @Property(selector = "relationshipsByName")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSRelationshipDescription> getRelationshipsByName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "versionHash")
    public native NSData getVersionHash();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "versionHashModifier")
    public native String getVersionHashModifier();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setVersionHashModifier:")
    public native void setVersionHashModifier(String v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "renamingIdentifier")
    public native String getRenamingIdentifier();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setRenamingIdentifier:")
    public native void setRenamingIdentifier(String v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "compoundIndexes")
    public native NSArray<?> getCompoundIndexes();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setCompoundIndexes:")
    public native void setCompoundIndexes(NSArray<?> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "relationshipsWithDestinationEntity:")
    public native NSArray<NSRelationshipDescription> getRelationshipsWith(NSEntityDescription entity);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "isKindOfEntity:")
    public native boolean isKindOfEntity(NSEntityDescription entity);
    @Method(selector = "entityForName:inManagedObjectContext:")
    public static native NSEntityDescription getEntityByNameInContext(String entityName, NSManagedObjectContext context);
    @Method(selector = "insertNewObjectForEntityForName:inManagedObjectContext:")
    public static native NSEntityDescription insertNewEntityInContext(String entityName, NSManagedObjectContext context);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
