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
package org.robovm.apple.webkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("WebKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/WKWebsiteDataType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKWebsiteDataType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/WKWebsiteDataType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static WKWebsiteDataType toObject(Class<WKWebsiteDataType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return WKWebsiteDataType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(WKWebsiteDataType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<WKWebsiteDataType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<WKWebsiteDataType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(WKWebsiteDataType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<WKWebsiteDataType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (WKWebsiteDataType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/
    public static class AsSetMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static Set<WKWebsiteDataType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSSet<NSString> o = (NSSet<NSString>) NSObject.Marshaler.toObject(NSSet.class, handle, flags);
            if (o == null) {
                return null;
            }
            Set<WKWebsiteDataType> set = new HashSet<>();
            for (NSString v : o) {
                set.add(WKWebsiteDataType.valueOf(v));
            }
            return set;
        }
        @MarshalsPointer
        public static long toNative(Set<WKWebsiteDataType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSSet<NSString> set = new NSMutableSet<>();
            for (WKWebsiteDataType o : l) {
                set.add(o.value());
            }
            return NSObject.Marshaler.toNative(set, flags);
        }
    }

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType DiskCache = new WKWebsiteDataType("DiskCache");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType MemoryCache = new WKWebsiteDataType("MemoryCache");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType OfflineWebApplicationCache = new WKWebsiteDataType("OfflineWebApplicationCache");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType Cookies = new WKWebsiteDataType("Cookies");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType SessionStorage = new WKWebsiteDataType("SessionStorage");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType LocalStorage = new WKWebsiteDataType("LocalStorage");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType WebSQLDatabases = new WKWebsiteDataType("WebSQLDatabases");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final WKWebsiteDataType IndexedDBDatabases = new WKWebsiteDataType("IndexedDBDatabases");
    /*</constants>*/
    
    private static /*<name>*/WKWebsiteDataType/*</name>*/[] values = new /*<name>*/WKWebsiteDataType/*</name>*/[] {/*<value_list>*/DiskCache, MemoryCache, OfflineWebApplicationCache, Cookies, SessionStorage, LocalStorage, WebSQLDatabases, IndexedDBDatabases/*</value_list>*/};
    
    /*<name>*/WKWebsiteDataType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/WKWebsiteDataType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/WKWebsiteDataType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/WKWebsiteDataType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("WebKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeDiskCache", optional=true)
        public static native NSString DiskCache();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeMemoryCache", optional=true)
        public static native NSString MemoryCache();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeOfflineWebApplicationCache", optional=true)
        public static native NSString OfflineWebApplicationCache();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeCookies", optional=true)
        public static native NSString Cookies();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeSessionStorage", optional=true)
        public static native NSString SessionStorage();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeLocalStorage", optional=true)
        public static native NSString LocalStorage();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeWebSQLDatabases", optional=true)
        public static native NSString WebSQLDatabases();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="WKWebsiteDataTypeIndexedDBDatabases", optional=true)
        public static native NSString IndexedDBDatabases();
        /*</values>*/
    }
}
