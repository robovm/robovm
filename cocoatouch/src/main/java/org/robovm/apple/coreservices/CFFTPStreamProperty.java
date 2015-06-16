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
package org.robovm.apple.coreservices;

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
/*<annotations>*/@Library("CFNetwork") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CFFTPStreamProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFFTPStreamProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFFTPStreamProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFFTPStreamProperty toObject(Class<CFFTPStreamProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFFTPStreamProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFFTPStreamProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFFTPStreamProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFFTPStreamProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFFTPStreamProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFFTPStreamProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFFTPStreamProperty o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty UserName = new CFFTPStreamProperty("UserName");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty Password = new CFFTPStreamProperty("Password");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty UsePassiveMode = new CFFTPStreamProperty("UsePassiveMode");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty ResourceSize = new CFFTPStreamProperty("ResourceSize");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty FetchResourceInfo = new CFFTPStreamProperty("FetchResourceInfo");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty FileTransferOffset = new CFFTPStreamProperty("FileTransferOffset");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty AttemptPersistentConnection = new CFFTPStreamProperty("AttemptPersistentConnection");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty Proxy = new CFFTPStreamProperty("Proxy");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty ProxyHost = new CFFTPStreamProperty("ProxyHost");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty ProxyPort = new CFFTPStreamProperty("ProxyPort");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty ProxyUser = new CFFTPStreamProperty("ProxyUser");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFFTPStreamProperty ProxyPassword = new CFFTPStreamProperty("ProxyPassword");
    /*</constants>*/
    
    private static /*<name>*/CFFTPStreamProperty/*</name>*/[] values = new /*<name>*/CFFTPStreamProperty/*</name>*/[] {/*<value_list>*/UserName, Password, UsePassiveMode, ResourceSize, FetchResourceInfo, FileTransferOffset, AttemptPersistentConnection, Proxy, ProxyHost, ProxyPort, ProxyUser, ProxyPassword/*</value_list>*/};
    
    /*<name>*/CFFTPStreamProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFFTPStreamProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFFTPStreamProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFFTPStreamProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CFNetwork") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPUserName", optional=true)
        public static native CFString UserName();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPPassword", optional=true)
        public static native CFString Password();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPUsePassiveMode", optional=true)
        public static native CFString UsePassiveMode();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPResourceSize", optional=true)
        public static native CFString ResourceSize();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPFetchResourceInfo", optional=true)
        public static native CFString FetchResourceInfo();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPFileTransferOffset", optional=true)
        public static native CFString FileTransferOffset();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPAttemptPersistentConnection", optional=true)
        public static native CFString AttemptPersistentConnection();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPProxy", optional=true)
        public static native CFString Proxy();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPProxyHost", optional=true)
        public static native CFString ProxyHost();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPProxyPort", optional=true)
        public static native CFString ProxyPort();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPProxyUser", optional=true)
        public static native CFString ProxyUser();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyFTPProxyPassword", optional=true)
        public static native CFString ProxyPassword();
        /*</values>*/
    }
}
