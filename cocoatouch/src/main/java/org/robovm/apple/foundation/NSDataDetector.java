/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDataDetector/*</name>*/ 
    extends /*<extends>*/NSRegularExpression/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDataDetectorPtr extends Ptr<NSDataDetector, NSDataDetectorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDataDetector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSDataDetector() {}
    protected NSDataDetector(SkipInit skipInit) { super(skipInit); }
    public NSDataDetector(long checkingTypes, NSError.NSErrorPtr error) { super((SkipInit) null); initObject(initWithTypes$error$(checkingTypes, error)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "checkingTypes")
    public native long getCheckingTypes();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTypes:error:")
    protected native @Pointer long initWithTypes$error$(long checkingTypes, NSError.NSErrorPtr error);
    @Method(selector = "dataDetectorWithTypes:error:")
    public static native NSDataDetector dataDetectorWithTypes$error$(long checkingTypes, NSError.NSErrorPtr error);
    /*</methods>*/
}
