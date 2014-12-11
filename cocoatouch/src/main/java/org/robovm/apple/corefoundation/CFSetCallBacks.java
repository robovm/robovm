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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSetCallBacks/*</name>*/ 
    extends /*<extends>*/Struct<CFSetCallBacks>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFSetCallBacksPtr extends Ptr<CFSetCallBacks, CFSetCallBacksPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFSetCallBacks() {}
    public CFSetCallBacks(@MachineSizedSInt long version, FunctionPtr retain, FunctionPtr release, FunctionPtr copyDescription, FunctionPtr equal, FunctionPtr hash) {
        this.setVersion(version);
        this.setRetain(retain);
        this.setRelease(release);
        this.setCopydescription(copyDescription);
        this.setEqual(equal);
        this.setHash(hash);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getVersion();
    @StructMember(0) public native CFSetCallBacks setVersion(@MachineSizedSInt long version);
    
    @Deprecated
    @StructMember(0) public native @MachineSizedSInt long version();
    @Deprecated
    @StructMember(0) public native CFSetCallBacks version(@MachineSizedSInt long version);
    
    @StructMember(1) public native FunctionPtr getRetain();
    @StructMember(1) public native CFSetCallBacks setRetain(FunctionPtr retain);
    
    @Deprecated
    @StructMember(1) public native FunctionPtr retain();
    @Deprecated
    @StructMember(1) public native CFSetCallBacks retain(FunctionPtr retain);
    
    @StructMember(2) public native FunctionPtr getRelease();
    @StructMember(2) public native CFSetCallBacks setRelease(FunctionPtr release);
    
    @Deprecated
    @StructMember(2) public native FunctionPtr release();
    @Deprecated
    @StructMember(2) public native CFSetCallBacks release(FunctionPtr release);
    
    @StructMember(3) public native FunctionPtr getCopydescription();
    @StructMember(3) public native CFSetCallBacks setCopydescription(FunctionPtr copyDescription);
    
    @Deprecated
    @StructMember(3) public native FunctionPtr copyDescription();
    @Deprecated
    @StructMember(3) public native CFSetCallBacks copyDescription(FunctionPtr copyDescription);
    
    @StructMember(4) public native FunctionPtr getEqual();
    @StructMember(4) public native CFSetCallBacks setEqual(FunctionPtr equal);
    
    @Deprecated
    @StructMember(4) public native FunctionPtr equal();
    @Deprecated
    @StructMember(4) public native CFSetCallBacks equal(FunctionPtr equal);
    
    @StructMember(5) public native FunctionPtr getHash();
    @StructMember(5) public native CFSetCallBacks setHash(FunctionPtr hash);
    
    @Deprecated
    @StructMember(5) public native FunctionPtr hash();
    @Deprecated
    @StructMember(5) public native CFSetCallBacks hash(FunctionPtr hash);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
