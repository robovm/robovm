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
package org.robovm.apple.accounts;

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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Accounts")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ACAccountTypeIdentifier/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ACAccountTypeIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ACAccountTypeIdentifier Twitter = new ACAccountTypeIdentifier("TwitterValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACAccountTypeIdentifier Facebook = new ACAccountTypeIdentifier("FacebookValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACAccountTypeIdentifier SinaWeibo = new ACAccountTypeIdentifier("SinaWeiboValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final ACAccountTypeIdentifier TencentWeibo = new ACAccountTypeIdentifier("TencentWeiboValue");
    private static ACAccountTypeIdentifier[] values = new ACAccountTypeIdentifier[] {Twitter, Facebook, SinaWeibo, TencentWeibo};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ACAccountTypeIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static ACAccountTypeIdentifier valueOf(NSString value) {
        if (value == null) throw new NullPointerException("value");
        for (ACAccountTypeIdentifier v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ACAccountTypeIdentifier/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierTwitter", optional=true)
    protected static native NSString TwitterValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierFacebook", optional=true)
    protected static native NSString FacebookValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierSinaWeibo", optional=true)
    protected static native NSString SinaWeiboValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierTencentWeibo", optional=true)
    protected static native NSString TencentWeiboValue();
    /*</methods>*/
}
