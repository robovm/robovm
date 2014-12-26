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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFastEnumerationState/*</name>*/ 
    extends /*<extends>*/Struct<NSFastEnumerationState>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFastEnumerationStatePtr extends Ptr<NSFastEnumerationState, NSFastEnumerationStatePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFastEnumerationState() {}
    public NSFastEnumerationState(@MachineSizedUInt long state, NSObject itemsPtr, MachineSizedUIntPtr mutationsPtr, MachineSizedUIntPtr extra) {
        this.setState(state);
        this.setItemsptr(itemsPtr);
        this.setMutationsptr(mutationsPtr);
        this.setExtra(extra);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedUInt long getState();
    @StructMember(0) public native NSFastEnumerationState setState(@MachineSizedUInt long state);
    
    @Deprecated
    @StructMember(0) public native @MachineSizedUInt long state();
    @Deprecated
    @StructMember(0) public native NSFastEnumerationState state(@MachineSizedUInt long state);
    
    @StructMember(1) public native NSObject getItemsptr();
    @StructMember(1) public native NSFastEnumerationState setItemsptr(NSObject itemsPtr);
    
    @Deprecated
    @StructMember(1) public native NSObject itemsPtr();
    @Deprecated
    @StructMember(1) public native NSFastEnumerationState itemsPtr(NSObject itemsPtr);
    
    @StructMember(2) public native MachineSizedUIntPtr getMutationsptr();
    @StructMember(2) public native NSFastEnumerationState setMutationsptr(MachineSizedUIntPtr mutationsPtr);
    
    @Deprecated
    @StructMember(2) public native MachineSizedUIntPtr mutationsPtr();
    @Deprecated
    @StructMember(2) public native NSFastEnumerationState mutationsPtr(MachineSizedUIntPtr mutationsPtr);
    
    @StructMember(3) public native @Array({5}) MachineSizedUIntPtr getExtra();
    @StructMember(3) public native NSFastEnumerationState setExtra(@Array({5}) MachineSizedUIntPtr extra);
    
    @Deprecated
    @StructMember(3) public native @Array({5}) MachineSizedUIntPtr extra();
    @Deprecated
    @StructMember(3) public native NSFastEnumerationState extra(@Array({5}) MachineSizedUIntPtr extra);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
