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
package org.robovm.apple.localauthentication;

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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("LocalAuthentication") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/LAContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class LAContextPtr extends Ptr<LAContext, LAContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(LAContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public LAContext() {}
    protected LAContext(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "localizedFallbackTitle")
    public native String getLocalizedFallbackTitle();
    @Property(selector = "setLocalizedFallbackTitle:")
    public native void setLocalizedFallbackTitle(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param policy
     * @return
     * @throws NSErrorException
     */
    public boolean canEvaluatePolicy(LAPolicy policy) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = canEvaluatePolicy(policy, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "canEvaluatePolicy:error:")
    protected native boolean canEvaluatePolicy(LAPolicy policy, NSError.NSErrorPtr error);
    @Method(selector = "evaluatePolicy:localizedReason:reply:")
    public native void evaluatePolicy(LAPolicy policy, String localizedReason, @Block VoidBlock2<Boolean, NSError> reply);
    /*</methods>*/
}
