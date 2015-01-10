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
package org.robovm.apple.mobilecoreservices;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UTTypeDeclaration.Marshaler.class)
/*<annotations>*/@Library("MobileCoreServices")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UTTypeDeclaration/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected UTTypeDeclaration(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public UTTypeDeclaration() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(UTTypeDeclaration.class); }/*</bind>*/
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
    @SuppressWarnings("unchecked")
    public List<String> getExportedTypeDeclarations() {
        if (data.containsKey(ExportedTypeDeclarationsKey())) {
            NSArray<NSString> val = (NSArray<NSString>) data.get(ExportedTypeDeclarationsKey());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setExportedTypeDeclarations(List<String> typeDeclarations) {
        data.put(ExportedTypeDeclarationsKey(), NSArray.fromStrings(typeDeclarations));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<String> getImportedTypeDeclarations() {
        if (data.containsKey(ImportedTypeDeclarationsKey())) {
            NSArray<NSString> val = (NSArray<NSString>) data.get(ImportedTypeDeclarationsKey());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setImportedTypeDeclarations(List<String> typeDeclarations) {
        data.put(ImportedTypeDeclarationsKey(), NSArray.fromStrings(typeDeclarations));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getTypeIdentifier() {
        if (data.containsKey(TypeIdentifierKey())) {
            NSString val = (NSString) data.get(TypeIdentifierKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setTypeIdentifier(String identifier) {
        data.put(TypeIdentifierKey(), new NSString(identifier));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSDictionary<NSString, NSObject> getTagSpecification() {
        if (data.containsKey(TypeTagSpecificationKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) data.get(TypeTagSpecificationKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setTagSpecification(NSDictionary<NSString, NSObject> specification) {
        data.put(TypeTagSpecificationKey(), specification);
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<String> getConformingTypes() {
        if (data.containsKey(TypeConformsToKey())) {
            NSArray<NSString> val = (NSArray<NSString>) data.get(TypeConformsToKey());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setConformingTypes(List<String> conformingTypes) {
        data.put(TypeConformsToKey(), NSArray.fromStrings(conformingTypes));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getDescription() {
        if (data.containsKey(TypeDescriptionKey())) {
            NSString val = (NSString) data.get(TypeDescriptionKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setDescription(String description) {
        data.put(TypeDescriptionKey(), new NSString(description));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getIconFileName() {
        if (data.containsKey(TypeIconFileKey())) {
            NSString val = (NSString) data.get(TypeIconFileKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setIconFileName(String fileName) {
        data.put(TypeIconFileKey(), new NSString(fileName));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getReferenceURL() {
        if (data.containsKey(TypeReferenceURLKey())) {
            NSString val = (NSString) data.get(TypeReferenceURLKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setReferenceURL(String url) {
        data.put(TypeReferenceURLKey(), new NSString(url));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getVersion() {
        if (data.containsKey(TypeVersionKey())) {
            NSString val = (NSString) data.get(TypeVersionKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UTTypeDeclaration setVersion(String version) {
        data.put(TypeVersionKey(), new NSString(version));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTExportedTypeDeclarationsKey", optional=true)
    protected static native NSString ExportedTypeDeclarationsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTImportedTypeDeclarationsKey", optional=true)
    protected static native NSString ImportedTypeDeclarationsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTypeIdentifierKey", optional=true)
    protected static native NSString TypeIdentifierKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTypeTagSpecificationKey", optional=true)
    protected static native NSString TypeTagSpecificationKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTypeConformsToKey", optional=true)
    protected static native NSString TypeConformsToKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTypeDescriptionKey", optional=true)
    protected static native NSString TypeDescriptionKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTypeIconFileKey", optional=true)
    protected static native NSString TypeIconFileKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTypeReferenceURLKey", optional=true)
    protected static native NSString TypeReferenceURLKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTypeVersionKey", optional=true)
    protected static native NSString TypeVersionKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
