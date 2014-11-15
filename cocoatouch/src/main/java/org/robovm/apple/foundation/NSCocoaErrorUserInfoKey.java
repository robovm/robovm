/*
 * Copyright (C) 2014 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class NSCocoaErrorUserInfoKey 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSCocoaErrorUserInfoKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSCocoaErrorUserInfoKey UnderlyingError = new NSCocoaErrorUserInfoKey("UnderlyingErrorKey");
    public static final NSCocoaErrorUserInfoKey LocalizedDescription = new NSCocoaErrorUserInfoKey("LocalizedDescriptionKey");
    public static final NSCocoaErrorUserInfoKey LocalizedFailureReason = new NSCocoaErrorUserInfoKey("LocalizedFailureReasonErrorKey");
    public static final NSCocoaErrorUserInfoKey LocalizedRecoverySuggestion = new NSCocoaErrorUserInfoKey("LocalizedRecoverySuggestionErrorKey");
    public static final NSCocoaErrorUserInfoKey LocalizedRecoveryOptions = new NSCocoaErrorUserInfoKey("LocalizedRecoveryOptionsErrorKey");
    public static final NSCocoaErrorUserInfoKey RecoveryAttempter = new NSCocoaErrorUserInfoKey("RecoveryAttempterErrorKey");
    public static final NSCocoaErrorUserInfoKey HelpAnchor = new NSCocoaErrorUserInfoKey("HelpAnchorErrorKey");
    public static final NSCocoaErrorUserInfoKey StringEncoding = new NSCocoaErrorUserInfoKey("StringEncodingErrorKey");
    public static final NSCocoaErrorUserInfoKey FilePath = new NSCocoaErrorUserInfoKey("FilePathErrorKey");
    public static final NSCocoaErrorUserInfoKey URL = new NSCocoaErrorUserInfoKey("URLErrorKey");
    
    private static NSCocoaErrorUserInfoKey[] values = new NSCocoaErrorUserInfoKey[] {UnderlyingError, LocalizedDescription, LocalizedFailureReason, LocalizedRecoverySuggestion, 
        LocalizedRecoveryOptions, RecoveryAttempter, HelpAnchor, StringEncoding, FilePath, URL};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSCocoaErrorUserInfoKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSCocoaErrorUserInfoKey valueOf(NSString value) {
        for (NSCocoaErrorUserInfoKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSCocoaErrorUserInfoKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSUnderlyingErrorKey", optional=true)
    protected static native NSString UnderlyingErrorKey();
    @GlobalValue(symbol="NSLocalizedDescriptionKey", optional=true)
    protected static native NSString LocalizedDescriptionKey();
    @GlobalValue(symbol="NSLocalizedFailureReasonErrorKey", optional=true)
    protected static native NSString LocalizedFailureReasonErrorKey();
    @GlobalValue(symbol="NSLocalizedRecoverySuggestionErrorKey", optional=true)
    protected static native NSString LocalizedRecoverySuggestionErrorKey();
    @GlobalValue(symbol="NSLocalizedRecoveryOptionsErrorKey", optional=true)
    protected static native NSString LocalizedRecoveryOptionsErrorKey();
    @GlobalValue(symbol="NSRecoveryAttempterErrorKey", optional=true)
    protected static native NSString RecoveryAttempterErrorKey();
    @GlobalValue(symbol="NSHelpAnchorErrorKey", optional=true)
    protected static native NSString HelpAnchorErrorKey();
    @GlobalValue(symbol="NSStringEncodingErrorKey", optional=true)
    protected static native NSString StringEncodingErrorKey();
    @GlobalValue(symbol="NSURLErrorKey", optional=true)
    protected static native NSString URLErrorKey();
    @GlobalValue(symbol="NSFilePathErrorKey", optional=true)
    protected static native NSString FilePathErrorKey();
    /*</methods>*/
}
