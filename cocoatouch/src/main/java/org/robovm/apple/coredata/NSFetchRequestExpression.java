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
package org.robovm.apple.coredata;

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
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFetchRequestExpression/*</name>*/ 
    extends /*<extends>*/NSExpression/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFetchRequestExpressionPtr extends Ptr<NSFetchRequestExpression, NSFetchRequestExpressionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFetchRequestExpression.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFetchRequestExpression() {}
    protected NSFetchRequestExpression(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "requestExpression")
    public native NSExpression requestExpression();
    @Method(selector = "contextExpression")
    public native NSExpression contextExpression();
    @Method(selector = "isCountOnlyRequest")
    public native boolean isCountOnlyRequest();
    @Method(selector = "expressionForFetch:context:countOnly:")
    public static native NSExpression expressionForFetch$context$countOnly$(NSExpression fetch, NSExpression context, boolean countFlag);
    /*</methods>*/
}
