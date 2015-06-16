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
@Marshaler(/*<name>*/NSURLFileProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLFileProperty/*</name>*/ 
    extends /*<extends>*/NSURLProperty/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSURLFileProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSURLFileProperty toObject(Class<NSURLFileProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLFileProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLFileProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSURLFileProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSURLFileProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSURLFileProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLFileProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSURLFileProperty o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileProperty FileSize = new NSURLFileProperty("FileSize");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileProperty FileAllocatedSize = new NSURLFileProperty("FileAllocatedSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileProperty TotalFileSize = new NSURLFileProperty("TotalFileSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileProperty TotalFileAllocatedSize = new NSURLFileProperty("TotalFileAllocatedSize");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileProperty IsAliasFile = new NSURLFileProperty("IsAliasFile");
    /*</constants>*/
    
    private static /*<name>*/NSURLFileProperty/*</name>*/[] values = new /*<name>*/NSURLFileProperty/*</name>*/[] {/*<value_list>*/FileSize, FileAllocatedSize, TotalFileSize, TotalFileAllocatedSize, IsAliasFile/*</value_list>*/};
    
    /*<name>*/NSURLFileProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSURLFileProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSURLFileProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLFileProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLFileSizeKey", optional=true)
        public static native NSString FileSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLFileAllocatedSizeKey", optional=true)
        public static native NSString FileAllocatedSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLTotalFileSizeKey", optional=true)
        public static native NSString TotalFileSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLTotalFileAllocatedSizeKey", optional=true)
        public static native NSString TotalFileAllocatedSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsAliasFileKey", optional=true)
        public static native NSString IsAliasFile();
        /*</values>*/
    }
}
