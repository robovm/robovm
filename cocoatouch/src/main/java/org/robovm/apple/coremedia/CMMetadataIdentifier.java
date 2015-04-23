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
@Marshaler(/*<name>*/CMMetadataIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataIdentifier/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CMMetadataIdentifier/*</name>*/.class); }
    
    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataIdentifier toObject(Class<CMMetadataIdentifier> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMMetadataIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMMetadataIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMMetadataIdentifier> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMMetadataIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CMMetadataIdentifier.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMMetadataIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMMetadataIdentifier i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataIdentifier QuickTimeMetadataLocation_ISO6709 = new CMMetadataIdentifier("QuickTimeMetadataLocation_ISO6709");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataIdentifier QuickTimeMetadataDirection_Facing = new CMMetadataIdentifier("QuickTimeMetadataDirection_Facing");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataIdentifier QuickTimeMetadataPreferredAffineTransform = new CMMetadataIdentifier("QuickTimeMetadataPreferredAffineTransform");
    /*</constants>*/
    
    private static /*<name>*/CMMetadataIdentifier/*</name>*/[] values = new /*<name>*/CMMetadataIdentifier/*</name>*/[] {/*<value_list>*/QuickTimeMetadataLocation_ISO6709, QuickTimeMetadataDirection_Facing, QuickTimeMetadataPreferredAffineTransform/*</value_list>*/};
    
    /*<name>*/CMMetadataIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CMMetadataIdentifier/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CMMetadataIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMMetadataIdentifier/*</name>*/.class.getName());
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static String createIdentifier(CFType key, CMMetadataKeySpace keySpace) throws OSStatusException {
        if (keySpace == null) throw new NullPointerException("keySpace");
        return createIdentifier(key, keySpace.value().toString());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static String createIdentifier(CFType key, String keySpace) throws OSStatusException {
        CFString.CFStringPtr ptr = new CFString.CFStringPtr();
        OSStatus status = createIdentifier0(null, key, keySpace, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            return ptr.get().toString();
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CFType createKey(CMMetadataIdentifier identifier) throws OSStatusException {
        if (identifier == null) throw new NullPointerException("identifier");
        return createKey(identifier.value().toString());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CFType createKey(String identifier) throws OSStatusException {
        CFType.CFTypePtr ptr = new CFType.CFTypePtr();
        OSStatus status = createKey0(null, identifier, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static NSData createKeyAsData(CMMetadataIdentifier identifier) throws OSStatusException {
        if (identifier == null) throw new NullPointerException("identifier");
        return createKeyAsData(identifier.value().toString());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static NSData createKeyAsData(String identifier) throws OSStatusException {
        NSData.NSDataPtr ptr = new NSData.NSDataPtr();
        OSStatus status = createKeyAsData0(null, identifier, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public String createKeySpace(CMMetadataIdentifier identifier) throws OSStatusException {
        if (identifier == null) throw new NullPointerException("identifier");
        return createKeySpace(identifier.value().toString());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public String createKeySpace(String identifier) throws OSStatusException {
        CFString.CFStringPtr ptr = new CFString.CFStringPtr();
        OSStatus status = createKeySpace0(null, identifier, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            return ptr.get().toString();
        }
        return null;
    }

    
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataCreateIdentifierForKeyAndKeySpace", optional=true)
    protected static native OSStatus createIdentifier0(CFAllocator allocator, CFType key, String keySpace, CFString.CFStringPtr identifierOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataCreateKeyFromIdentifier", optional=true)
    protected static native OSStatus createKey0(CFAllocator allocator, String identifier, CFType.CFTypePtr keyOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataCreateKeyFromIdentifierAsCFData", optional=true)
    protected static native OSStatus createKeyAsData0(CFAllocator allocator, String identifier, NSData.NSDataPtr keyOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataCreateKeySpaceFromIdentifier", optional=true)
    protected static native OSStatus createKeySpace0(CFAllocator allocator, String identifier, CFString.CFStringPtr keySpaceOut);
    /*</methods>*/
    
    /*<annotations>*/@Library("CoreMedia")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataIdentifier_QuickTimeMetadataLocation_ISO6709", optional=true)
        public static native CFString QuickTimeMetadataLocation_ISO6709();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataIdentifier_QuickTimeMetadataDirection_Facing", optional=true)
        public static native CFString QuickTimeMetadataDirection_Facing();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataIdentifier_QuickTimeMetadataPreferredAffineTransform", optional=true)
        public static native CFString QuickTimeMetadataPreferredAffineTransform();
        /*</values>*/
    }
}
