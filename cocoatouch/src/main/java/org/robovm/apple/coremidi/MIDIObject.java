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
package org.robovm.apple.coremidi;

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
/*<annotations>*/@Library("CoreMIDI") @Marshaler(CFString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIObject/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIObjectPtr extends Ptr<MIDIObject, MIDIObjectPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIObject.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    public int getIntegerProperty(String propertyID) {
        IntPtr ptr = new IntPtr();
        getIntegerProperty(propertyID, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public String getStringProperty(String propertyID) {
        NSString.NSStringPtr ptr = new NSString.NSStringPtr();
        getStringProperty(propertyID, ptr);
        NSString str = ptr.get();
        if (str != null) return str.toString();
        return null;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public NSData getDataProperty(String propertyID) {
        NSData.NSDataPtr ptr = new NSData.NSDataPtr();
        getDataProperty(propertyID, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public NSDictionary<?, ?> getDictionaryProperty(String propertyID) {
        NSDictionary.NSDictionaryPtr ptr = new NSDictionary.NSDictionaryPtr();
        getDictionaryProperty(propertyID, ptr);
        return (NSDictionary<?, ?>)ptr.get();
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CFPropertyList getProperties(boolean deep) {
        CFPropertyList.CFPropertyListPtr ptr = new CFPropertyList.CFPropertyListPtr();
        getProperties(ptr, deep);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static MIDIObject findObjectById(int uniqueID) {
        MIDIObjectPtr ptr = new MIDIObjectPtr();
        findByUniqueID(uniqueID, ptr, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static MIDIObjectType findObjectTypeById(int uniqueID) {
        IntPtr ptr = new IntPtr();
        findByUniqueID(uniqueID, null, ptr);
        return MIDIObjectType.valueOf(ptr.get());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectGetIntegerProperty", optional=true)
    protected native MIDIError getIntegerProperty(String propertyID, IntPtr outValue);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectSetIntegerProperty", optional=true)
    public native MIDIError setIntegerProperty(String propertyID, int value);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectGetStringProperty", optional=true)
    protected native MIDIError getStringProperty(String propertyID, NSString.NSStringPtr str);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectSetStringProperty", optional=true)
    public native MIDIError setStringProperty(String propertyID, String str);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectGetDataProperty", optional=true)
    protected native MIDIError getDataProperty(String propertyID, NSData.NSDataPtr outData);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectSetDataProperty", optional=true)
    public native MIDIError setDataProperty(String propertyID, NSData data);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectGetDictionaryProperty", optional=true)
    protected native MIDIError getDictionaryProperty(String propertyID, NSDictionary.NSDictionaryPtr outDict);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectSetDictionaryProperty", optional=true)
    public native MIDIError setDictionaryProperty(String propertyID, NSDictionary data);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectGetProperties", optional=true)
    protected native MIDIError getProperties(CFPropertyList.CFPropertyListPtr outProperties, boolean deep);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectRemoveProperty", optional=true)
    public native MIDIError removeProperty(String propertyID);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIObjectFindByUniqueID", optional=true)
    protected static native MIDIError findByUniqueID(int inUniqueID, MIDIObject.MIDIObjectPtr outObject, IntPtr outObjectType);
    /*</methods>*/
}
