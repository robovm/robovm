/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSFileType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSFileType Directory = new NSFileType("DirectoryValue");
    public static final NSFileType Regular = new NSFileType("RegularValue");
    public static final NSFileType SymbolicLink = new NSFileType("SymbolicLinkValue");
    public static final NSFileType Socket = new NSFileType("SocketValue");
    public static final NSFileType CharacterSpecial = new NSFileType("CharacterSpecialValue");
    public static final NSFileType BlockSpecial = new NSFileType("BlockSpecialValue");
    public static final NSFileType Unknown = new NSFileType("UnknownValue");
    private static NSFileType[] values = new NSFileType[] {Directory, Regular, SymbolicLink, Socket, CharacterSpecial, BlockSpecial, Unknown};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSFileType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSFileType valueOf(NSString value) {
        for (NSFileType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSFileType/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSFileTypeDirectory", optional=true)
    protected static native NSString DirectoryValue();
    @GlobalValue(symbol="NSFileTypeRegular", optional=true)
    protected static native NSString RegularValue();
    @GlobalValue(symbol="NSFileTypeSymbolicLink", optional=true)
    protected static native NSString SymbolicLinkValue();
    @GlobalValue(symbol="NSFileTypeSocket", optional=true)
    protected static native NSString SocketValue();
    @GlobalValue(symbol="NSFileTypeCharacterSpecial", optional=true)
    protected static native NSString CharacterSpecialValue();
    @GlobalValue(symbol="NSFileTypeBlockSpecial", optional=true)
    protected static native NSString BlockSpecialValue();
    @GlobalValue(symbol="NSFileTypeUnknown", optional=true)
    protected static native NSString UnknownValue();
    /*</methods>*/
}
