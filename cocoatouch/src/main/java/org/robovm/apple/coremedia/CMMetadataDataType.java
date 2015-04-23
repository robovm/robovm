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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
@Marshaler(CMMetadataDataType.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataDataType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataDataType toObject(Class<CMMetadataDataType> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMMetadataDataType(o.toString());
        }
        @MarshalsPointer
        public static long toNative(CMMetadataDataType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(new CFString(o.dataType), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CMMetadataDataType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    private String dataType;
    
    public CMMetadataDataType(String dataType) {
        this.dataType = dataType;
    }
    
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public void register(String description, List<String> conformingDataTypes) throws OSStatusException {
        OSStatus status = registerDataType0(dataType, description, conformingDataTypes);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static void registerDataType(String dataType, String description, List<String> conformingDataTypes) throws OSStatusException {
        OSStatus status = registerDataType0(dataType, description, conformingDataTypes);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isRegistered() {
        return isDataTypeRegistered(dataType);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getDescription() {
        return getDataTypeDescription(dataType);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public List<String> getConformingDataTypes() {
        return getConformingDataTypes(dataType);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean conformsToDataType(String conformsToDataType) {
        return doesDataTypeConformToDataType(dataType, conformsToDataType);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isBaseDataType() {
        return isDataTypeBaseDataType(dataType);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMMetadataBaseDataType getConformingBaseDataType() {
        return getBaseDataTypeForConformingDataType(dataType);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryRegisterDataType", optional=true)
    protected static native OSStatus registerDataType0(String dataType, String description, @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> conformingDataTypes);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryDataTypeIsRegistered", optional=true)
    public static native boolean isDataTypeRegistered(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetDataTypeDescription", optional=true)
    public static native String getDataTypeDescription(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetConformingDataTypes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getConformingDataTypes(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryDataTypeConformsToDataType", optional=true)
    public static native boolean doesDataTypeConformToDataType(String dataType, String conformsToDataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetBaseDataTypes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CMMetadataBaseDataType.AsListMarshaler.class) List<CMMetadataBaseDataType> getBaseDataTypes();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryDataTypeIsBaseDataType", optional=true)
    public static native boolean isDataTypeBaseDataType(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetBaseDataTypeForConformingDataType", optional=true)
    public static native CMMetadataBaseDataType getBaseDataTypeForConformingDataType(String dataType);
    /*</methods>*/
    
    @Override
    public String toString() {
        return dataType;
    }
}
