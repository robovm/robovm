/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
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
        this.setTop(top);
        this.setLeft(left);
        this.setBottom(bottom);
        this.setRight(right);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double getTop();
    @StructMember(0) public native UIEdgeInsets setTop(@MachineSizedFloat double top);
    @StructMember(1) public native @MachineSizedFloat double getLeft();
    @StructMember(1) public native UIEdgeInsets setLeft(@MachineSizedFloat double left);
    @StructMember(2) public native @MachineSizedFloat double getBottom();
    @StructMember(2) public native UIEdgeInsets setBottom(@MachineSizedFloat double bottom);
    @StructMember(3) public native @MachineSizedFloat double getRight();
    @StructMember(3) public native UIEdgeInsets setRight(@MachineSizedFloat double right);
    /*</members>*/

    public boolean equalsTo(UIEdgeInsets other) {
        return getLeft() == other.getLeft() && getTop() == other.getTop() && getRight() == other.getRight() && getBottom() == other.getBottom();
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof UIEdgeInsets && equalsTo((UIEdgeInsets)obj);
    }
    
    @Override
    public String toString() {
        return toString(this);
    }
    /*<methods>*/
    @GlobalValue(symbol="UIEdgeInsetsZero", optional=true)
    public static native @ByVal UIEdgeInsets Zero();
    
    @Bridge(symbol="NSStringFromUIEdgeInsets", optional=true)
    protected static native String toString(@ByVal UIEdgeInsets insets);
    @Bridge(symbol="UIEdgeInsetsFromString", optional=true)
    public static native @ByVal UIEdgeInsets fromString(String string);
    /*</methods>*/
}
