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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStreamClientContext/*</name>*/ 
    extends /*<extends>*/Struct<CFStreamClientContext>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFStreamClientContextPtr extends Ptr<CFStreamClientContext, CFStreamClientContextPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFStreamClientContext() {}
    public CFStreamClientContext(@MachineSizedSInt long version, @Pointer long info, FunctionPtr retain, FunctionPtr release, FunctionPtr copyDescription) {
        this.setVersion(version);
        this.setInfo(info);
        this.setRetain(retain);
        this.setRelease(release);
        this.setCopydescription(copyDescription);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getVersion();
    @StructMember(0) public native CFStreamClientContext setVersion(@MachineSizedSInt long version);
    
    @Deprecated
    @StructMember(0) public native @MachineSizedSInt long version();
    @Deprecated
    @StructMember(0) public native CFStreamClientContext version(@MachineSizedSInt long version);
    
    @StructMember(1) public native @Pointer long getInfo();
    @StructMember(1) public native CFStreamClientContext setInfo(@Pointer long info);
    
    @Deprecated
    @StructMember(1) public native @Pointer long info();
    @Deprecated
    @StructMember(1) public native CFStreamClientContext info(@Pointer long info);
    
    @StructMember(2) public native FunctionPtr getRetain();
    @StructMember(2) public native CFStreamClientContext setRetain(FunctionPtr retain);
    
    @Deprecated
    @StructMember(2) public native FunctionPtr retain();
    @Deprecated
    @StructMember(2) public native CFStreamClientContext retain(FunctionPtr retain);
    
    @StructMember(3) public native FunctionPtr getRelease();
    @StructMember(3) public native CFStreamClientContext setRelease(FunctionPtr release);
    
    @Deprecated
    @StructMember(3) public native FunctionPtr release();
    @Deprecated
    @StructMember(3) public native CFStreamClientContext release(FunctionPtr release);
    
    @StructMember(4) public native FunctionPtr getCopydescription();
    @StructMember(4) public native CFStreamClientContext setCopydescription(FunctionPtr copyDescription);
    
    @Deprecated
    @StructMember(4) public native FunctionPtr copyDescription();
    @Deprecated
    @StructMember(4) public native CFStreamClientContext copyDescription(FunctionPtr copyDescription);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
