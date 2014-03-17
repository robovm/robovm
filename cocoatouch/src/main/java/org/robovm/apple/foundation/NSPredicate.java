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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPredicate/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSPredicatePtr extends Ptr<NSPredicate, NSPredicatePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPredicate.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPredicate() {}
    protected NSPredicate(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "predicateFormat")
    public native String predicateFormat();
    @Method(selector = "predicateWithSubstitutionVariables:")
    public native NSPredicate predicateWithSubstitutionVariables$(NSDictionary<?, ?> variables);
    @Method(selector = "evaluateWithObject:")
    public native boolean evaluateWithObject$(NSObject object);
    @Method(selector = "evaluateWithObject:substitutionVariables:")
    public native boolean evaluateWithObject$substitutionVariables$(NSObject object, NSDictionary<?, ?> bindings);
    @Method(selector = "allowEvaluation")
    public native void allowEvaluation();
    @Method(selector = "predicateWithFormat:argumentArray:")
    public static native NSPredicate predicateWithFormat$argumentArray$(String predicateFormat, NSArray<?> arguments);
    @Method(selector = "predicateWithValue:")
    public static native NSPredicate predicateWithValue$(boolean value);
    @Method(selector = "predicateWithBlock:")
    public static native NSPredicate predicateWithBlock$(ObjCBlock block);
    /*</methods>*/
}
