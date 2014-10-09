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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableString/*</name>*/ 
    extends /*<extends>*/NSString/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMutableStringPtr extends Ptr<NSMutableString, NSMutableStringPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMutableString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableString() {}
    protected NSMutableString(SkipInit skipInit) { super(skipInit); }
    public NSMutableString(@MachineSizedUInt long capacity) { super((SkipInit) null); initObject(initWithCapacity$(capacity)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "replaceCharactersInRange:withString:")
    public native void replaceCharacters(@ByVal NSRange range, String aString);
    @Method(selector = "insertString:atIndex:")
    public native void insert(String aString, @MachineSizedUInt long loc);
    @Method(selector = "deleteCharactersInRange:")
    public native void deleteCharacters(@ByVal NSRange range);
    @Method(selector = "appendString:")
    public native void append(String aString);
    @Method(selector = "setString:")
    public native void setString(String aString);
    @Method(selector = "initWithCapacity:")
    protected native @Pointer long initWithCapacity$(@MachineSizedUInt long capacity);
    @Method(selector = "replaceOccurrencesOfString:withString:options:range:")
    public native @MachineSizedUInt long replaceAll(String target, String replacement, NSStringCompareOptions options, @ByVal NSRange searchRange);
    /*</methods>*/
}
