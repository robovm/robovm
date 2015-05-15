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
    public NSPersistentStore(NSPersistentStoreCoordinator root, String name, NSURL url, NSPersistentStoreOptions options) { super((SkipInit) null); initObject(init(root, name, url, options)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "persistentStoreCoordinator")
    public native NSPersistentStoreCoordinator getPersistentStoreCoordinator();
    @Property(selector = "configurationName")
    public native String getConfigurationName();
    @Property(selector = "options")
    public native NSPersistentStoreOptions getOptions();
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "setURL:")
    public native void setURL(NSURL v);
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "setIdentifier:")
    public native void setIdentifier(String v);
    @Property(selector = "type")
    public native String getType();
    @Property(selector = "isReadOnly")
    public native boolean isReadOnly();
    @Property(selector = "setReadOnly:")
    public native void setReadOnly(boolean v);
    @Property(selector = "metadata")
    public native NSPersistentStoreMetadata getMetadata();
    @Property(selector = "setMetadata:")
    public native void setMetadata(NSPersistentStoreMetadata v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPersistentStoreCoordinator:configurationName:URL:options:")
    protected native @Pointer long init(NSPersistentStoreCoordinator root, String name, NSURL url, NSPersistentStoreOptions options);
    public boolean loadMetadata() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = loadMetadata(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "loadMetadata:")
    private native boolean loadMetadata(NSError.NSErrorPtr error);
    @Method(selector = "didAddToPersistentStoreCoordinator:")
    public native void didAddToPersistentStoreCoordinator(NSPersistentStoreCoordinator coordinator);
    @Method(selector = "willRemoveFromPersistentStoreCoordinator:")
    public native void willRemoveFromPersistentStoreCoordinator(NSPersistentStoreCoordinator coordinator);
    public static NSPersistentStoreMetadata getMetadataForPersistentStore(NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSPersistentStoreMetadata result = getMetadataForPersistentStore(url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "metadataForPersistentStoreWithURL:error:")
    private static native NSPersistentStoreMetadata getMetadataForPersistentStore(NSURL url, NSError.NSErrorPtr error);
    public static boolean setMetadataForPersistentStore(NSPersistentStoreMetadata metadata, NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setMetadataForPersistentStore(metadata, url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "setMetadata:forPersistentStoreWithURL:error:")
    private static native boolean setMetadataForPersistentStore(NSPersistentStoreMetadata metadata, NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "migrationManagerClass")
    public static native Class<? extends NSMigrationManager> getMigrationManagerClass();
    /*</methods>*/
}
