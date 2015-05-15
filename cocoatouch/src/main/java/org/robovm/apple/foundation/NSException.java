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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;

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
    public NSException(String aName, String aReason, NSDictionary<?, ?> aUserInfo) { super((SkipInit) null); initObject(init(aName, aReason, aUserInfo)); }
    public NSException(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "reason")
    public native String getReason();
    @Property(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "callStackReturnAddresses")
    public native NSArray<NSNumber> getCallStackReturnAddresses();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "callStackSymbols")
    public native NSArray<NSString> getCallStackSymbols();
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
    
    /**
     * Registers a default java uncaught exception handler that forwards exceptions to RoboVM's signal handlers.
     * Use this if you want Java exceptions to be logged by crash reporters.
     */
    public static void registerDefaultJavaUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new java.lang.Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException (Thread thread, Throwable ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                pw.flush();
                Foundation.log(sw.toString());
                NSException exception = new NSException(ex.getClass().getName(), sw.toString(), new NSDictionary<>());
                if (NSThread.getCurrentThread().isMainThread()) {
                    exception.raise();
                } else {
                    long handler = getUncaughtExceptionHandler();
                    callUncaughtExceptionHandler(handler, exception);
                    // We should never get to this line!
                }
            }
        });
    }
    
    @Bridge(symbol = "NSGetUncaughtExceptionHandler", optional = true)
    private static native @Pointer long getUncaughtExceptionHandler ();

    @Bridge(dynamic = true)
    private static native void callUncaughtExceptionHandler (@Pointer long fn, NSException ex);
    /*<methods>*/
    @Bridge(symbol="NSSetUncaughtExceptionHandler", optional=true)
    private static native void setUncaughtExceptionHandler(FunctionPtr p0);
    
    @Method(selector = "initWithName:reason:userInfo:")
    protected native @Pointer long init(String aName, String aReason, NSDictionary<?, ?> aUserInfo);
    @Method(selector = "raise")
    public native void raise();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
