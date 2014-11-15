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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSUserDefaultsDomain/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSUserDefaultsDomain.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSUserDefaultsDomain Global = new NSUserDefaultsDomain("GlobalValue");
    public static final NSUserDefaultsDomain Argument = new NSUserDefaultsDomain("ArgumentValue");
    public static final NSUserDefaultsDomain Registration = new NSUserDefaultsDomain("RegistrationValue");
    
    private static NSUserDefaultsDomain[] values = new NSUserDefaultsDomain[] {Global, Argument, Registration};
    private final LazyGlobalValue<String> lazyGlobalValue;
    
    private NSUserDefaultsDomain(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public String value() {
        return lazyGlobalValue.value();
    }
    
    public static NSUserDefaultsDomain valueOf(String value) {
        for (NSUserDefaultsDomain v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSUserDefaultsDomain/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSGlobalDomain", optional=true)
    protected static native String GlobalValue();
    @GlobalValue(symbol="NSArgumentDomain", optional=true)
    protected static native String ArgumentValue();
    @GlobalValue(symbol="NSRegistrationDomain", optional=true)
    protected static native String RegistrationValue();
    /*</methods>*/
}
