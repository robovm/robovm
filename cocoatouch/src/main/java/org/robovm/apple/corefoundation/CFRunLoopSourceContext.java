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

/**
 *
 * <div class="javadoc"></div>
 */
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
        this.version(version);
        this.info(info);
        this.retain(retain);
        this.release(release);
        this.copyDescription(copyDescription);
        this.equal(equal);
        this.hash(hash);
        this.schedule(schedule);
        this.cancel(cancel);
        this.perform(perform);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long version();
    @StructMember(0) public native CFRunLoopSourceContext version(@MachineSizedSInt long version);
    @StructMember(1) public native VoidPtr info();
    @StructMember(1) public native CFRunLoopSourceContext info(VoidPtr info);
    @StructMember(2) public native FunctionPtr retain();
    @StructMember(2) public native CFRunLoopSourceContext retain(FunctionPtr retain);
    @StructMember(3) public native FunctionPtr release();
    @StructMember(3) public native CFRunLoopSourceContext release(FunctionPtr release);
    @StructMember(4) public native FunctionPtr copyDescription();
    @StructMember(4) public native CFRunLoopSourceContext copyDescription(FunctionPtr copyDescription);
    @StructMember(5) public native FunctionPtr equal();
    @StructMember(5) public native CFRunLoopSourceContext equal(FunctionPtr equal);
    @StructMember(6) public native FunctionPtr hash();
    @StructMember(6) public native CFRunLoopSourceContext hash(FunctionPtr hash);
    @StructMember(7) public native FunctionPtr schedule();
    @StructMember(7) public native CFRunLoopSourceContext schedule(FunctionPtr schedule);
    @StructMember(8) public native FunctionPtr cancel();
    @StructMember(8) public native CFRunLoopSourceContext cancel(FunctionPtr cancel);
    @StructMember(9) public native FunctionPtr perform();
    @StructMember(9) public native CFRunLoopSourceContext perform(FunctionPtr perform);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
