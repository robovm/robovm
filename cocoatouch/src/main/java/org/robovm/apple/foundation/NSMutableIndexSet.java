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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableIndexSet/*</name>*/ 
    extends /*<extends>*/NSIndexSet/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMutableIndexSetPtr extends Ptr<NSMutableIndexSet, NSMutableIndexSetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMutableIndexSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableIndexSet() {}
    protected NSMutableIndexSet(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addIndexes:")
    public native void addIndexes$(NSIndexSet indexSet);
    @Method(selector = "removeIndexes:")
    public native void removeIndexes$(NSIndexSet indexSet);
    @Method(selector = "removeAllIndexes")
    public native void removeAllIndexes();
    @Method(selector = "addIndex:")
    public native void addIndex$(@MachineSizedUInt long value);
    @Method(selector = "removeIndex:")
    public native void removeIndex$(@MachineSizedUInt long value);
    @Method(selector = "addIndexesInRange:")
    public native void addIndexesInRange$(@ByVal NSRange range);
    @Method(selector = "removeIndexesInRange:")
    public native void removeIndexesInRange$(@ByVal NSRange range);
    @Method(selector = "shiftIndexesStartingAtIndex:by:")
    public native void shiftIndexesStartingAtIndex$by$(@MachineSizedUInt long index, @MachineSizedSInt long delta);
    /*</methods>*/
}
