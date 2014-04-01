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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIEdgeInsets/*</name>*/ 
    extends /*<extends>*/Struct<UIEdgeInsets>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIEdgeInsetsPtr extends Ptr<UIEdgeInsets, UIEdgeInsetsPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(UIEdgeInsets.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIEdgeInsets() {}
    public UIEdgeInsets(@MachineSizedFloat double top, @MachineSizedFloat double left, @MachineSizedFloat double bottom, @MachineSizedFloat double right) {
        this.top(top);
        this.left(left);
        this.bottom(bottom);
        this.right(right);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double top();
    @StructMember(0) public native UIEdgeInsets top(@MachineSizedFloat double top);
    @StructMember(1) public native @MachineSizedFloat double left();
    @StructMember(1) public native UIEdgeInsets left(@MachineSizedFloat double left);
    @StructMember(2) public native @MachineSizedFloat double bottom();
    @StructMember(2) public native UIEdgeInsets bottom(@MachineSizedFloat double bottom);
    @StructMember(3) public native @MachineSizedFloat double right();
    @StructMember(3) public native UIEdgeInsets right(@MachineSizedFloat double right);
    /*</members>*/
    
    @Override
    public String toString() {
        return toString(this);
    }
    
    /*<methods>*/
    @GlobalValue(symbol="UIEdgeInsetsZero")
    public static native @ByVal UIEdgeInsets Zero();
    
    @Bridge(symbol="NSStringFromUIEdgeInsets")
    protected static native String toString(@ByVal UIEdgeInsets insets);
    @Bridge(symbol="UIEdgeInsetsFromString")
    public static native @ByVal UIEdgeInsets fromString(String string);
    /*</methods>*/
}
