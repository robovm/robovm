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
/*</javadoc>*/
@Marshaler(NSPersistentStoreOptions.Marshaler.class)
/*<annotations>*/@Library("CoreData")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStoreOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSPersistentStoreOptions toObject(Class<NSPersistentStoreOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSPersistentStoreOptions(o);
        }
        @MarshalsPointer
        public static long toNative(NSPersistentStoreOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSPersistentStoreOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSPersistentStoreOptions() {
    	this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSPersistentStoreOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean isReadOnly() {
        if (data.containsKey(ReadOnlyOption())) {
            NSNumber val = (NSNumber)data.get(ReadOnlyOption());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreOptions setReadOnly(boolean readOnly) {
        data.put(ReadOnlyOption(), NSNumber.valueOf(readOnly));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public long getTimeout() {
        if (data.containsKey(TimeoutOption())) {
            NSNumber val = (NSNumber)data.get(TimeoutOption());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreOptions setTimeout(long timeout) {
        data.put(TimeoutOption(), NSNumber.valueOf(timeout));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public Map<String, NSObject> getSQLitePragmas() {
        if (data.containsKey(SQLitePragmasOption())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>)data.get(SQLitePragmasOption());
            return val.asStringMap();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreOptions setSQLitePragmas(Map<String, NSObject> pragmas) {
        data.put(SQLitePragmasOption(), NSDictionary.fromStringMap(pragmas));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean isSQLiteAnalyzeEnabled() {
        if (data.containsKey(SQLiteAnalyzeOption())) {
            NSNumber val = (NSNumber)data.get(SQLiteAnalyzeOption());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreOptions setSQLiteAnalyzeEnabled(boolean enable) {
        data.put(SQLiteAnalyzeOption(), NSNumber.valueOf(enable));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean isSQLiteManualVacuumEnabled() {
        if (data.containsKey(SQLiteManualVacuumOption())) {
            NSNumber val = (NSNumber)data.get(SQLiteManualVacuumOption());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreOptions setSQLiteManualVacuumEnabled(boolean enable) {
        data.put(SQLiteManualVacuumOption(), NSNumber.valueOf(enable));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSFileProtection getFileProtection() {
        if (data.containsKey(FileProtectionOption())) {
            NSString val = (NSString)data.get(FileProtectionOption());
            return NSFileProtection.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSPersistentStoreOptions setFileProtection(NSFileProtection fileProtection) {
        data.put(FileProtectionOption(), fileProtection.value());
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getUbiquitousContentName() {
        if (data.containsKey(UbiquitousContentNameOption())) {
            NSString val = (NSString)data.get(UbiquitousContentNameOption());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSPersistentStoreOptions setUbiquitousContentName(String name) {
        data.put(UbiquitousContentNameOption(), new NSString(name));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getUbiquitousContentURL() {
        if (data.containsKey(UbiquitousContentURLOption())) {
            NSString val = (NSString)data.get(UbiquitousContentURLOption());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSPersistentStoreOptions setUbiquitousContentURL(String url) {
        data.put(UbiquitousContentURLOption(), new NSString(url));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getUbiquitousPeerToken() {
        if (data.containsKey(UbiquitousPeerTokenOption())) {
            NSString val = (NSString)data.get(UbiquitousPeerTokenOption());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSPersistentStoreOptions setUbiquitousPeerToken(String peerToken) {
        data.put(UbiquitousPeerTokenOption(), new NSString(peerToken));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean shouldRemoveUbiquitousMetadata() {
        if (data.containsKey(RemoveUbiquitousMetadataOption())) {
            NSNumber val = (NSNumber)data.get(RemoveUbiquitousMetadataOption());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSPersistentStoreOptions setShouldRemoveUbiquitousMetadata(boolean remove) {
        data.put(RemoveUbiquitousMetadataOption(), NSNumber.valueOf(remove));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getUbiquitousContainerIdentifier() {
        if (data.containsKey(UbiquitousContainerIdentifierOption())) {
            NSString val = (NSString)data.get(UbiquitousContainerIdentifierOption());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSPersistentStoreOptions setUbiquitousContainerIdentifier(String identifier) {
        data.put(UbiquitousContainerIdentifierOption(), new NSString(identifier));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean shouldRebuildFromUbiquitousContent() {
        if (data.containsKey(RebuildFromUbiquitousContentOption())) {
            NSNumber val = (NSNumber)data.get(RebuildFromUbiquitousContentOption());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSPersistentStoreOptions setShouldRebuildFromUbiquitousContent(boolean rebuild) {
        data.put(RebuildFromUbiquitousContentOption(), NSNumber.valueOf(rebuild));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSReadOnlyPersistentStoreOption", optional=true)
    protected static native NSString ReadOnlyOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreTimeoutOption", optional=true)
    protected static native NSString TimeoutOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLitePragmasOption", optional=true)
    protected static native NSString SQLitePragmasOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteAnalyzeOption", optional=true)
    protected static native NSString SQLiteAnalyzeOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteManualVacuumOption", optional=true)
    protected static native NSString SQLiteManualVacuumOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIgnorePersistentStoreVersioningOption", optional=true)
    protected static native NSString IgnorePersistentStoreVersioningOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigratePersistentStoresAutomaticallyOption", optional=true)
    protected static native NSString MigratePersistentStoresAutomaticallyOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSInferMappingModelAutomaticallyOption", optional=true)
    protected static native NSString InferMappingModelAutomaticallyOption();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousContentNameKey", optional=true)
    protected static native NSString UbiquitousContentNameOption();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousContentURLKey", optional=true)
    protected static native NSString UbiquitousContentURLOption();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousPeerTokenOption", optional=true)
    protected static native NSString UbiquitousPeerTokenOption();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreRemoveUbiquitousMetadataOption", optional=true)
    protected static native NSString RemoveUbiquitousMetadataOption();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousContainerIdentifierKey", optional=true)
    protected static native NSString UbiquitousContainerIdentifierOption();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreRebuildFromUbiquitousContentOption", optional=true)
    protected static native NSString RebuildFromUbiquitousContentOption();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreFileProtectionKey", optional=true)
    protected static native NSString FileProtectionOption();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
