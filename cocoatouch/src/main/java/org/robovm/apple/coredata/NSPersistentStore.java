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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStore/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSPersistentStorePtr extends Ptr<NSPersistentStore, NSPersistentStorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPersistentStore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPersistentStore() {}
    protected NSPersistentStore(SkipInit skipInit) { super(skipInit); }
    public NSPersistentStore(NSPersistentStoreCoordinator root, String name, NSURL url, NSDictionary<?, ?> options) { super((SkipInit) null); initObject(initWithPersistentStoreCoordinator$configurationName$URL$options$(root, name, url, options)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPersistentStoreCoordinator:configurationName:URL:options:")
    protected native @Pointer long initWithPersistentStoreCoordinator$configurationName$URL$options$(NSPersistentStoreCoordinator root, String name, NSURL url, NSDictionary<?, ?> options);
    @Method(selector = "loadMetadata:")
    public native boolean loadMetadata$(NSError.NSErrorPtr error);
    @Method(selector = "persistentStoreCoordinator")
    public native NSPersistentStoreCoordinator persistentStoreCoordinator();
    @Method(selector = "configurationName")
    public native String configurationName();
    @Method(selector = "options")
    public native NSDictionary<?, ?> options();
    @Method(selector = "URL")
    public native NSURL URL();
    @Method(selector = "setURL:")
    public native void setURL(NSURL url);
    @Method(selector = "identifier")
    public native String identifier();
    @Method(selector = "setIdentifier:")
    public native void setIdentifier(String identifier);
    @Method(selector = "type")
    public native String type();
    @Method(selector = "isReadOnly")
    public native boolean isReadOnly();
    @Method(selector = "setReadOnly:")
    public native void setReadOnly(boolean flag);
    @Method(selector = "metadata")
    public native NSDictionary<?, ?> metadata();
    @Method(selector = "setMetadata:")
    public native void setMetadata(NSDictionary<?, ?> storeMetadata);
    @Method(selector = "didAddToPersistentStoreCoordinator:")
    public native void didAddToPersistentStoreCoordinator$(NSPersistentStoreCoordinator coordinator);
    @Method(selector = "willRemoveFromPersistentStoreCoordinator:")
    public native void willRemoveFromPersistentStoreCoordinator$(NSPersistentStoreCoordinator coordinator);
    @Method(selector = "metadataForPersistentStoreWithURL:error:")
    public static native NSDictionary<?, ?> metadataForPersistentStoreWithURL$error$(NSURL url, NSError.NSErrorPtr error);
    @Method(selector = "setMetadata:forPersistentStoreWithURL:error:")
    public static native boolean setMetadata$forPersistentStoreWithURL$error$(NSDictionary<?, ?> metadata, NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "migrationManagerClass")
    public static native ObjCClass migrationManagerClass();
    /*</methods>*/
}
