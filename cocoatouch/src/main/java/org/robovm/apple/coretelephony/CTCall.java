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
package org.robovm.apple.coretelephony;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreTelephony") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTCall/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTCallPtr extends Ptr<CTCall, CTCallPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CTCall.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CTCall() {}
    protected CTCall(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CTCallState getCallState() {
       return CTCallState.valueOf(getCallState0()); 
    } 
    /*<properties>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "callState")
    protected native NSString getCallState0();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "callID")
    public native String getCallID();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
