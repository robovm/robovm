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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNError/*</name>*/ 
    extends /*<extends>*/NSError/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    protected SCNError(SkipInit skipInit) {
        super(skipInit);
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public NSErrorCode getErrorCode() {
        NSErrorCode code = null;
        try {
            code = SCNConsistencyErrorCode.valueOf(getCode());
        } catch (IllegalArgumentException e) {
            try {
                code = SCNErrorCode.valueOf(getCode());
            } catch (IllegalArgumentException e2) {
                // ignored
            }
        }
        return code;
    }
    
    @SuppressWarnings("unchecked")
    public SCNConsistencyErrorUserInfo getConsistencyError() {
        NSErrorUserInfo data = getUserInfo();
        if (data.contains(SCNErrorUserInfoKey.DetailedErrors)) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) data.get(SCNErrorUserInfoKey.DetailedErrors);
            return new SCNConsistencyErrorUserInfo(val);
        }
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNErrorDomain", optional=true)
    public static native String getClassDomain();
    /*</methods>*/
}
