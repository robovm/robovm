/*
 * Copyright (C) 2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
@Marshaler(/*<name>*/NSCocoaErrorUserInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCocoaErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSCocoaErrorUserInfoKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSCocoaErrorUserInfoKey toObject(Class<NSCocoaErrorUserInfoKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSCocoaErrorUserInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSCocoaErrorUserInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSCocoaErrorUserInfoKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSCocoaErrorUserInfoKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSCocoaErrorUserInfoKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSCocoaErrorUserInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSCocoaErrorUserInfoKey i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final NSCocoaErrorUserInfoKey UnderlyingError = new NSCocoaErrorUserInfoKey("UnderlyingError");
    public static final NSCocoaErrorUserInfoKey LocalizedDescription = new NSCocoaErrorUserInfoKey("LocalizedDescription");
    public static final NSCocoaErrorUserInfoKey LocalizedFailureReason = new NSCocoaErrorUserInfoKey("LocalizedFailureReason");
    public static final NSCocoaErrorUserInfoKey LocalizedRecoverySuggestion = new NSCocoaErrorUserInfoKey("LocalizedRecoverySuggestion");
    public static final NSCocoaErrorUserInfoKey LocalizedRecoveryOptions = new NSCocoaErrorUserInfoKey("LocalizedRecoveryOptions");
    public static final NSCocoaErrorUserInfoKey RecoveryAttempter = new NSCocoaErrorUserInfoKey("RecoveryAttempter");
    public static final NSCocoaErrorUserInfoKey HelpAnchor = new NSCocoaErrorUserInfoKey("HelpAnchor");
    public static final NSCocoaErrorUserInfoKey StringEncoding = new NSCocoaErrorUserInfoKey("StringEncoding");
    public static final NSCocoaErrorUserInfoKey URL = new NSCocoaErrorUserInfoKey("URL");
    public static final NSCocoaErrorUserInfoKey FilePath = new NSCocoaErrorUserInfoKey("FilePath");
    /*</constants>*/
    
    private static /*<name>*/NSCocoaErrorUserInfoKey/*</name>*/[] values = new /*<name>*/NSCocoaErrorUserInfoKey/*</name>*/[] {/*<value_list>*/UnderlyingError, LocalizedDescription, LocalizedFailureReason, LocalizedRecoverySuggestion, LocalizedRecoveryOptions, RecoveryAttempter, HelpAnchor, StringEncoding, URL, FilePath/*</value_list>*/};
    
    /*<name>*/NSCocoaErrorUserInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSCocoaErrorUserInfoKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSCocoaErrorUserInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSCocoaErrorUserInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="NSUnderlyingErrorKey", optional=true)
        public static native NSString UnderlyingError();
        @GlobalValue(symbol="NSLocalizedDescriptionKey", optional=true)
        public static native NSString LocalizedDescription();
        @GlobalValue(symbol="NSLocalizedFailureReasonErrorKey", optional=true)
        public static native NSString LocalizedFailureReason();
        @GlobalValue(symbol="NSLocalizedRecoverySuggestionErrorKey", optional=true)
        public static native NSString LocalizedRecoverySuggestion();
        @GlobalValue(symbol="NSLocalizedRecoveryOptionsErrorKey", optional=true)
        public static native NSString LocalizedRecoveryOptions();
        @GlobalValue(symbol="NSRecoveryAttempterErrorKey", optional=true)
        public static native NSString RecoveryAttempter();
        @GlobalValue(symbol="NSHelpAnchorErrorKey", optional=true)
        public static native NSString HelpAnchor();
        @GlobalValue(symbol="NSStringEncodingErrorKey", optional=true)
        public static native NSString StringEncoding();
        @GlobalValue(symbol="NSURLErrorKey", optional=true)
        public static native NSString URL();
        @GlobalValue(symbol="NSFilePathErrorKey", optional=true)
        public static native NSString FilePath();
        /*</values>*/
    }
}
