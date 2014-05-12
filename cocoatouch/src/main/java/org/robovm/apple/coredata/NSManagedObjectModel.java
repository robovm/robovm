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

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSManagedObjectModel/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSManagedObjectModelPtr extends Ptr<NSManagedObjectModel, NSManagedObjectModelPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSManagedObjectModel.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSManagedObjectModel() {}
    protected NSManagedObjectModel(SkipInit skipInit) { super(skipInit); }
    public NSManagedObjectModel(NSURL url) { super((SkipInit) null); initObject(initWithContentsOfURL$(url)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithContentsOfURL:")
    protected native @Pointer long initWithContentsOfURL$(NSURL url);
    @Method(selector = "entitiesByName")
    public native NSDictionary<?, ?> entitiesByName();
    @Method(selector = "entities")
    public native NSArray<?> entities();
    @Method(selector = "setEntities:")
    public native void setEntities(NSArray<?> entities);
    @Method(selector = "configurations")
    public native NSArray<?> configurations();
    @Method(selector = "entitiesForConfiguration:")
    public native NSArray<?> entitiesForConfiguration$(String configuration);
    @Method(selector = "setEntities:forConfiguration:")
    public native void setEntities$forConfiguration$(NSArray<?> entities, String configuration);
    @Method(selector = "setFetchRequestTemplate:forName:")
    public native void setFetchRequestTemplate$forName$(NSFetchRequest fetchRequestTemplate, String name);
    @Method(selector = "fetchRequestTemplateForName:")
    public native NSFetchRequest fetchRequestTemplateForName$(String name);
    @Method(selector = "fetchRequestFromTemplateWithName:substitutionVariables:")
    public native NSFetchRequest fetchRequestFromTemplateWithName$substitutionVariables$(String name, NSDictionary<?, ?> variables);
    @Method(selector = "localizationDictionary")
    public native NSDictionary<?, ?> localizationDictionary();
    @Method(selector = "setLocalizationDictionary:")
    public native void setLocalizationDictionary(NSDictionary<?, ?> localizationDictionary);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "fetchRequestTemplatesByName")
    public native NSDictionary<?, ?> fetchRequestTemplatesByName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "versionIdentifiers")
    public native NSSet<?> versionIdentifiers();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setVersionIdentifiers:")
    public native void setVersionIdentifiers(NSSet<?> identifiers);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "isConfiguration:compatibleWithStoreMetadata:")
    public native boolean isConfiguration$compatibleWithStoreMetadata$(String configuration, NSDictionary<?, ?> metadata);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "entityVersionHashesByName")
    public native NSDictionary<?, ?> entityVersionHashesByName();
    @Method(selector = "mergedModelFromBundles:")
    public static native NSManagedObjectModel mergedModelFromBundles$(NSArray<?> bundles);
    @Method(selector = "modelByMergingModels:")
    public static native NSManagedObjectModel modelByMergingModels$(NSArray<?> models);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "mergedModelFromBundles:forStoreMetadata:")
    public static native NSManagedObjectModel mergedModelFromBundles$forStoreMetadata$(NSArray<?> bundles, NSDictionary<?, ?> metadata);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "modelByMergingModels:forStoreMetadata:")
    public static native NSManagedObjectModel modelByMergingModels$forStoreMetadata$(NSArray<?> models, NSDictionary<?, ?> metadata);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
