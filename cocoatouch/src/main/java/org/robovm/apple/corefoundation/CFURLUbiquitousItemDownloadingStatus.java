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
@Marshaler(/*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFURLUbiquitousItemDownloadingStatus toObject(Class<CFURLUbiquitousItemDownloadingStatus> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFURLUbiquitousItemDownloadingStatus.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFURLUbiquitousItemDownloadingStatus o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFURLUbiquitousItemDownloadingStatus> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFURLUbiquitousItemDownloadingStatus> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFURLUbiquitousItemDownloadingStatus.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFURLUbiquitousItemDownloadingStatus> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFURLUbiquitousItemDownloadingStatus i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CFURLUbiquitousItemDownloadingStatus NotDownloaded = new CFURLUbiquitousItemDownloadingStatus("NotDownloaded");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CFURLUbiquitousItemDownloadingStatus Downloaded = new CFURLUbiquitousItemDownloadingStatus("Downloaded");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CFURLUbiquitousItemDownloadingStatus Current = new CFURLUbiquitousItemDownloadingStatus("Current");
    /*</constants>*/
    
    private static /*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/[] values = new /*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/[] {/*<value_list>*/NotDownloaded, Downloaded, Current/*</value_list>*/};
    
    /*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFURLUbiquitousItemDownloadingStatus/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
        public static native CFString NotDownloaded();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
        public static native CFString Downloaded();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusCurrent", optional=true)
        public static native CFString Current();
        /*</values>*/
    }
}
