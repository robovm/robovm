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
package org.robovm.apple.iad;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.mediaplayer.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("iAd")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/IAd/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(IAd.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="ADErrorDomain", optional=true)
    public static native String ErrorDomain();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 4.2.
     */
    @Deprecated
    @GlobalValue(symbol="ADBannerContentSizeIdentifier320x50", optional=true)
    public static native String ADBannerContentSizeIdentifier320x50();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 4.2.
     */
    @Deprecated
    @GlobalValue(symbol="ADBannerContentSizeIdentifier480x32", optional=true)
    public static native String ADBannerContentSizeIdentifier480x32();
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="ADBannerContentSizeIdentifierPortrait", optional=true)
    public static native String ADBannerContentSizeIdentifierPortrait();
    /**
     * @since Available in iOS 4.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="ADBannerContentSizeIdentifierLandscape", optional=true)
    public static native String ADBannerContentSizeIdentifierLandscape();
    /*</methods>*/
}
