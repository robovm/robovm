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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSMetadataItemDownloadingStatus/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMetadataItemDownloadingStatus/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSMetadataItemDownloadingStatus/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSMetadataItemDownloadingStatus toObject(Class<NSMetadataItemDownloadingStatus> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSMetadataItemDownloadingStatus.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSMetadataItemDownloadingStatus o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSMetadataItemDownloadingStatus> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSMetadataItemDownloadingStatus> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSMetadataItemDownloadingStatus.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSMetadataItemDownloadingStatus> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSMetadataItemDownloadingStatus o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemDownloadingStatus NotDownloaded = new NSMetadataItemDownloadingStatus("NotDownloaded");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemDownloadingStatus Downloaded = new NSMetadataItemDownloadingStatus("Downloaded");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemDownloadingStatus Current = new NSMetadataItemDownloadingStatus("Current");
    /*</constants>*/
    
    private static /*<name>*/NSMetadataItemDownloadingStatus/*</name>*/[] values = new /*<name>*/NSMetadataItemDownloadingStatus/*</name>*/[] {/*<value_list>*/NotDownloaded, Downloaded, Current/*</value_list>*/};
    
    /*<name>*/NSMetadataItemDownloadingStatus/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSMetadataItemDownloadingStatus/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSMetadataItemDownloadingStatus/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSMetadataItemDownloadingStatus/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
        public static native NSString NotDownloaded();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusDownloaded", optional=true)
        public static native NSString Downloaded();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusCurrent", optional=true)
        public static native NSString Current();
        /*</values>*/
    }
}
