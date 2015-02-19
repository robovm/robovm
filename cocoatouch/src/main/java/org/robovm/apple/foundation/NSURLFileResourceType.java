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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLFileResourceType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLFileResourceType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType NamedPipe = new NSURLFileResourceType("NamedPipeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType CharacterSpecial = new NSURLFileResourceType("CharacterSpecialValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Directory = new NSURLFileResourceType("DirectoryValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType BlockSpecial = new NSURLFileResourceType("BlockSpecialValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Regular = new NSURLFileResourceType("RegularValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType SymbolicLink = new NSURLFileResourceType("SymbolicLinkValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Socket = new NSURLFileResourceType("SocketValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileResourceType Unknown = new NSURLFileResourceType("UnknownValue");
    
    private static NSURLFileResourceType[] values = new NSURLFileResourceType[] {NamedPipe, CharacterSpecial, Directory, BlockSpecial, Regular,
        SymbolicLink, Socket, Unknown};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLFileResourceType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLFileResourceType valueOf(NSString value) {
        for (NSURLFileResourceType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLFileResourceType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeNamedPipe", optional=true)
    protected static native NSString NamedPipeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeCharacterSpecial", optional=true)
    protected static native NSString CharacterSpecialValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeDirectory", optional=true)
    protected static native NSString DirectoryValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeBlockSpecial", optional=true)
    protected static native NSString BlockSpecialValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeRegular", optional=true)
    protected static native NSString RegularValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSymbolicLink", optional=true)
    protected static native NSString SymbolicLinkValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSocket", optional=true)
    protected static native NSString SocketValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeUnknown", optional=true)
    protected static native NSString UnknownValue();
    /*</methods>*/
}
