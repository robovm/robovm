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
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersonNameComponents/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSPersonNameComponentsPtr extends Ptr<NSPersonNameComponents, NSPersonNameComponentsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPersonNameComponents.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPersonNameComponents() {}
    protected NSPersonNameComponents(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "namePrefix")
    public native String getNamePrefix();
    @Property(selector = "setNamePrefix:")
    public native void setNamePrefix(String v);
    @Property(selector = "givenName")
    public native String getGivenName();
    @Property(selector = "setGivenName:")
    public native void setGivenName(String v);
    @Property(selector = "middleName")
    public native String getMiddleName();
    @Property(selector = "setMiddleName:")
    public native void setMiddleName(String v);
    @Property(selector = "familyName")
    public native String getFamilyName();
    @Property(selector = "setFamilyName:")
    public native void setFamilyName(String v);
    @Property(selector = "nameSuffix")
    public native String getNameSuffix();
    @Property(selector = "setNameSuffix:")
    public native void setNameSuffix(String v);
    @Property(selector = "nickname")
    public native String getNickname();
    @Property(selector = "setNickname:")
    public native void setNickname(String v);
    @Property(selector = "phoneticRepresentation")
    public native NSPersonNameComponents getPhoneticRepresentation();
    @Property(selector = "setPhoneticRepresentation:")
    public native void setPhoneticRepresentation(NSPersonNameComponents v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
