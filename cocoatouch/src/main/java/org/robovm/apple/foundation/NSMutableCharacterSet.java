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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableCharacterSet/*</name>*/ 
    extends /*<extends>*/NSCharacterSet/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMutableCharacterSetPtr extends Ptr<NSMutableCharacterSet, NSMutableCharacterSetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMutableCharacterSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableCharacterSet() {}
    protected NSMutableCharacterSet(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addCharactersInRange:")
    public native void addCharactersInRange$(@ByVal NSRange aRange);
    @Method(selector = "removeCharactersInRange:")
    public native void removeCharactersInRange$(@ByVal NSRange aRange);
    @Method(selector = "addCharactersInString:")
    public native void addCharactersInString$(String aString);
    @Method(selector = "removeCharactersInString:")
    public native void removeCharactersInString$(String aString);
    @Method(selector = "formUnionWithCharacterSet:")
    public native void formUnionWithCharacterSet$(NSCharacterSet otherSet);
    @Method(selector = "formIntersectionWithCharacterSet:")
    public native void formIntersectionWithCharacterSet$(NSCharacterSet otherSet);
    @Method(selector = "invert")
    public native void invert();
    /*</methods>*/
}
