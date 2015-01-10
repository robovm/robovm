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
package org.robovm.apple.coretext;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*//*</visibility>*/ class /*<name>*/CTRunDelegateCallbacks/*</name>*/ 
    extends /*<extends>*/Struct<CTRunDelegateCallbacks>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTRunDelegateCallbacksPtr extends Ptr<CTRunDelegateCallbacks, CTRunDelegateCallbacksPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CTRunDelegateCallbacks() {}
    public CTRunDelegateCallbacks(@MachineSizedSInt long version, FunctionPtr dealloc, FunctionPtr getAscent, FunctionPtr getDescent, FunctionPtr getWidth) {
        this.setVersion(version);
        this.setDealloc(dealloc);
        this.setGetAscent(getAscent);
        this.setGetDescent(getDescent);
        this.setGetWidth(getWidth);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getVersion();
    @StructMember(0) public native CTRunDelegateCallbacks setVersion(@MachineSizedSInt long version);
    @StructMember(1) public native FunctionPtr getDealloc();
    @StructMember(1) public native CTRunDelegateCallbacks setDealloc(FunctionPtr dealloc);
    @StructMember(2) public native FunctionPtr getGetAscent();
    @StructMember(2) public native CTRunDelegateCallbacks setGetAscent(FunctionPtr getAscent);
    @StructMember(3) public native FunctionPtr getGetDescent();
    @StructMember(3) public native CTRunDelegateCallbacks setGetDescent(FunctionPtr getDescent);
    @StructMember(4) public native FunctionPtr getGetWidth();
    @StructMember(4) public native CTRunDelegateCallbacks setGetWidth(FunctionPtr getWidth);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
