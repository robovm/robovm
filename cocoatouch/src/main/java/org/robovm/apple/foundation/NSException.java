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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSException/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSExceptionPtr extends Ptr<NSException, NSExceptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSException.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected NSException(SkipInit skipInit) { super(skipInit); }
    public NSException(String aName, String aReason, NSDictionary<?, ?> aUserInfo) { super((SkipInit) null); initObject(initWithName$reason$userInfo$(aName, aReason, aUserInfo)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    private static VoidBlock1<NSException> handler = null;
    
    @Callback
    private static void handler(NSException e) {
        handler.invoke(e);
    }
    
    public static void setUncaughtExceptionHandler(VoidBlock1<NSException> handler) {
        if (handler == null) {
            throw new NullPointerException("handler");
        }
        NSException.handler = handler;
        try {
            setUncaughtExceptionHandler(new FunctionPtr(NSException.class.getDeclaredMethod("handler", NSException.class)));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
    
    /*<methods>*/
    @Bridge(symbol="NSSetUncaughtExceptionHandler", optional=true)
    private static native void setUncaughtExceptionHandler(FunctionPtr p0);
    
    @Method(selector = "initWithName:reason:userInfo:")
    protected native @Pointer long initWithName$reason$userInfo$(String aName, String aReason, NSDictionary<?, ?> aUserInfo);
    @Method(selector = "name")
    public native String getName();
    @Method(selector = "reason")
    public native String getReason();
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "callStackReturnAddresses")
    public native NSArray<NSNumber> getCallStackReturnAddresses();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "callStackSymbols")
    public native NSArray<NSString> getCallStackSymbols();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
