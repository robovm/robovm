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
package org.robovm.apple.mobilecoreservices;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MobileCoreServices")/*</annotations>*/
@Marshaler(/*<name>*/UTTypeDeclaration/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UTTypeDeclaration/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UTTypeDeclaration toObject(Class<UTTypeDeclaration> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UTTypeDeclaration(o);
        }
        @MarshalsPointer
        public static long toNative(UTTypeDeclaration o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<UTTypeDeclaration> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<UTTypeDeclaration> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new UTTypeDeclaration(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UTTypeDeclaration> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (UTTypeDeclaration i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    UTTypeDeclaration(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public UTTypeDeclaration() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public UTTypeDeclaration set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.0 and later.
     */
    public List<String> getExportedTypeDeclarations() {
        if (has(Keys.ExportedTypeDeclarations())) {
            NSArray<NSString> val = (NSArray<NSString>) get(Keys.ExportedTypeDeclarations());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setExportedTypeDeclarations(List<String> exportedTypeDeclarations) {
        set(Keys.ExportedTypeDeclarations(), NSArray.fromStrings(exportedTypeDeclarations));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public List<String> getImportedTypeDeclarations() {
        if (has(Keys.ImportedTypeDeclarations())) {
            NSArray<NSString> val = (NSArray<NSString>) get(Keys.ImportedTypeDeclarations());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setImportedTypeDeclarations(List<String> importedTypeDeclarations) {
        set(Keys.ImportedTypeDeclarations(), NSArray.fromStrings(importedTypeDeclarations));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getTypeIdentifier() {
        if (has(Keys.TypeIdentifier())) {
            NSString val = (NSString) get(Keys.TypeIdentifier());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setTypeIdentifier(String typeIdentifier) {
        set(Keys.TypeIdentifier(), new NSString(typeIdentifier));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSDictionary<NSString, NSObject> getTagSpecification() {
        if (has(Keys.TypeTagSpecification())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) get(Keys.TypeTagSpecification());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setTagSpecification(NSDictionary<NSString, NSObject> tagSpecification) {
        set(Keys.TypeTagSpecification(), tagSpecification);
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public List<String> getConformingTypes() {
        if (has(Keys.TypeConformsTo())) {
            NSArray<NSString> val = (NSArray<NSString>) get(Keys.TypeConformsTo());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setConformingTypes(List<String> conformingTypes) {
        set(Keys.TypeConformsTo(), NSArray.fromStrings(conformingTypes));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getDescription() {
        if (has(Keys.TypeDescription())) {
            NSString val = (NSString) get(Keys.TypeDescription());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setDescription(String description) {
        set(Keys.TypeDescription(), new NSString(description));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getIconFileName() {
        if (has(Keys.TypeIconFile())) {
            NSString val = (NSString) get(Keys.TypeIconFile());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setIconFileName(String iconFileName) {
        set(Keys.TypeIconFile(), new NSString(iconFileName));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getReferenceURL() {
        if (has(Keys.TypeReferenceURL())) {
            NSString val = (NSString) get(Keys.TypeReferenceURL());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setReferenceURL(String referenceURL) {
        set(Keys.TypeReferenceURL(), new NSString(referenceURL));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getVersion() {
        if (has(Keys.TypeVersion())) {
            NSString val = (NSString) get(Keys.TypeVersion());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setVersion(String version) {
        set(Keys.TypeVersion(), new NSString(version));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("MobileCoreServices")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTExportedTypeDeclarationsKey", optional=true)
        public static native NSString ExportedTypeDeclarations();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTImportedTypeDeclarationsKey", optional=true)
        public static native NSString ImportedTypeDeclarations();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeIdentifierKey", optional=true)
        public static native NSString TypeIdentifier();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeTagSpecificationKey", optional=true)
        public static native NSString TypeTagSpecification();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeConformsToKey", optional=true)
        public static native NSString TypeConformsTo();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeDescriptionKey", optional=true)
        public static native NSString TypeDescription();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeIconFileKey", optional=true)
        public static native NSString TypeIconFile();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeReferenceURLKey", optional=true)
        public static native NSString TypeReferenceURL();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kUTTypeVersionKey", optional=true)
        public static native NSString TypeVersion();
    }
    /*</keys>*/
}
