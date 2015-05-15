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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
@Marshaler(/*<name>*/CFURLFileProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFURLFileProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFURLFileProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFURLFileProperty toObject(Class<CFURLFileProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFURLFileProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFURLFileProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFURLFileProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFURLFileProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFURLFileProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFURLFileProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFURLFileProperty i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileProperty IsMountTrigger = new CFURLFileProperty("IsMountTrigger");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileProperty FileResourceType = new CFURLFileProperty("FileResourceType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileProperty FileSize = new CFURLFileProperty("FileSize");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileProperty FileAllocatedSize = new CFURLFileProperty("FileAllocatedSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileProperty TotalFileSize = new CFURLFileProperty("TotalFileSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileProperty TotalFileAllocatedSize = new CFURLFileProperty("TotalFileAllocatedSize");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileProperty IsAliasFile = new CFURLFileProperty("IsAliasFile");
    /*</constants>*/
    
    private static /*<name>*/CFURLFileProperty/*</name>*/[] values = new /*<name>*/CFURLFileProperty/*</name>*/[] {/*<value_list>*/IsMountTrigger, FileResourceType, FileSize, FileAllocatedSize, TotalFileSize, TotalFileAllocatedSize, IsAliasFile/*</value_list>*/};
    
    /*<name>*/CFURLFileProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFURLFileProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFURLFileProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFURLFileProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsMountTriggerKey", optional=true)
        public static native CFString IsMountTrigger();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLFileResourceTypeKey", optional=true)
        public static native CFString FileResourceType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLFileSizeKey", optional=true)
        public static native CFString FileSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLFileAllocatedSizeKey", optional=true)
        public static native CFString FileAllocatedSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLTotalFileSizeKey", optional=true)
        public static native CFString TotalFileSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLTotalFileAllocatedSizeKey", optional=true)
        public static native CFString TotalFileAllocatedSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsAliasFileKey", optional=true)
        public static native CFString IsAliasFile();
        /*</values>*/
    }
}
