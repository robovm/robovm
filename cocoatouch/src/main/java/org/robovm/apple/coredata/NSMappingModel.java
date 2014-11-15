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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMappingModel/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMappingModelPtr extends Ptr<NSMappingModel, NSMappingModelPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMappingModel.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMappingModel() {}
    protected NSMappingModel(SkipInit skipInit) { super(skipInit); }
    public NSMappingModel(NSURL url) { super((SkipInit) null); initObject(init(url)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "entityMappings")
    public native NSArray<NSEntityMapping> getEntityMappings();
    @Property(selector = "setEntityMappings:")
    public native void setEntityMappings(NSArray<NSEntityMapping> v);
    @Property(selector = "entityMappingsByName")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSEntityMapping> getEntityMappingsByName();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param sourceModel
     * @param destinationModel
     * @return
     * @since Available in iOS 3.0 and later.
     * @throws NSErrorException
     */
    public static NSMappingModel createInferred(NSManagedObjectModel sourceModel, NSManagedObjectModel destinationModel) {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSMappingModel result = createInferred(sourceModel, destinationModel, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }

    /*<methods>*/
    @Method(selector = "initWithContentsOfURL:")
    protected native @Pointer long init(NSURL url);
    @Method(selector = "mappingModelFromBundles:forSourceModel:destinationModel:")
    public static native NSMappingModel create(NSArray<NSBundle> bundles, NSManagedObjectModel sourceModel, NSManagedObjectModel destinationModel);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "inferredMappingModelForSourceModel:destinationModel:error:")
    protected static native NSMappingModel createInferred(NSManagedObjectModel sourceModel, NSManagedObjectModel destinationModel, NSError.NSErrorPtr error);
    /*</methods>*/
}
