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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFRunLoopSourceContext/*</name>*/ 
    extends /*<extends>*/Struct<CFRunLoopSourceContext>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFRunLoopSourceContextPtr extends Ptr<CFRunLoopSourceContext, CFRunLoopSourceContextPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFRunLoopSourceContext() {}
    public CFRunLoopSourceContext(@MachineSizedSInt long version, VoidPtr info, FunctionPtr retain, FunctionPtr release, FunctionPtr copyDescription, FunctionPtr equal, FunctionPtr hash, FunctionPtr schedule, FunctionPtr cancel, FunctionPtr perform) {
        this.setVersion(version);
        this.setInfo(info);
        this.setRetain(retain);
        this.setRelease(release);
        this.setCopyDescription(copyDescription);
        this.setEqual(equal);
        this.setHash(hash);
        this.setSchedule(schedule);
        this.setCancel(cancel);
        this.setPerform(perform);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getVersion();
    @StructMember(0) public native CFRunLoopSourceContext setVersion(@MachineSizedSInt long version);
    @StructMember(1) public native VoidPtr getInfo();
    @StructMember(1) public native CFRunLoopSourceContext setInfo(VoidPtr info);
    @StructMember(2) public native FunctionPtr getRetain();
    @StructMember(2) public native CFRunLoopSourceContext setRetain(FunctionPtr retain);
    @StructMember(3) public native FunctionPtr getRelease();
    @StructMember(3) public native CFRunLoopSourceContext setRelease(FunctionPtr release);
    @StructMember(4) public native FunctionPtr getCopyDescription();
    @StructMember(4) public native CFRunLoopSourceContext setCopyDescription(FunctionPtr copyDescription);
    @StructMember(5) public native FunctionPtr getEqual();
    @StructMember(5) public native CFRunLoopSourceContext setEqual(FunctionPtr equal);
    @StructMember(6) public native FunctionPtr getHash();
    @StructMember(6) public native CFRunLoopSourceContext setHash(FunctionPtr hash);
    @StructMember(7) public native FunctionPtr getSchedule();
    @StructMember(7) public native CFRunLoopSourceContext setSchedule(FunctionPtr schedule);
    @StructMember(8) public native FunctionPtr getCancel();
    @StructMember(8) public native CFRunLoopSourceContext setCancel(FunctionPtr cancel);
    @StructMember(9) public native FunctionPtr getPerform();
    @StructMember(9) public native CFRunLoopSourceContext setPerform(FunctionPtr perform);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
