/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.corelocation;

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
import org.robovm.apple.addressbook.*;
import org.robovm.apple.corebluetooth.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreLocation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLError/*</name>*/ 
    extends /*<extends>*/NSError/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    protected CLError(SkipInit skipInit) {
        super(skipInit);
    }
    
    /*<ptr>*/public static class CLErrorPtr extends Ptr<CLError, CLErrorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CLError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public CLErrorCode getErrorCode() {
        CLErrorCode code = null;
        try {
            code = CLErrorCode.valueOf(getCode());
        } catch (IllegalArgumentException e) {
            // ignore
        }
        return code;
    }
    
    public CLRegion getAlternateRegion() {
        NSErrorUserInfo userInfo = getUserInfo();
        if (userInfo.contains(CLErrorUserInfoKey.AlternateRegion)) {
            CLRegion val = (CLRegion)userInfo.get(CLErrorUserInfoKey.AlternateRegion);
            return val;
        }
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="kCLErrorDomain", optional=true)
    public static native String getClassDomain();
    /*</methods>*/
}
