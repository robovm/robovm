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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIOffset/*</name>*/ 
    extends /*<extends>*/Struct<UIOffset>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIOffsetPtr extends Ptr<UIOffset, UIOffsetPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(UIOffset.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIOffset() {}
    public UIOffset(@MachineSizedFloat double horizontal, @MachineSizedFloat double vertical) {
        this.setHorizontal(horizontal);
        this.setVertical(vertical);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double getHorizontal();
    @StructMember(0) public native UIOffset setHorizontal(@MachineSizedFloat double horizontal);
    
    @Deprecated
    @StructMember(0) public native @MachineSizedFloat double horizontal();
    @Deprecated
    @StructMember(0) public native UIOffset horizontal(@MachineSizedFloat double horizontal);
    
    @StructMember(1) public native @MachineSizedFloat double getVertical();
    @StructMember(1) public native UIOffset setVertical(@MachineSizedFloat double vertical);
    
    @Deprecated
    @StructMember(1) public native @MachineSizedFloat double vertical();
    @Deprecated
    @StructMember(1) public native UIOffset vertical(@MachineSizedFloat double vertical);
    
    /*</members>*/
    
    @Override
    public String toString() {
        return toString(this);
    }
    
    /*<methods>*/
    @GlobalValue(symbol="UIOffsetZero", optional=true)
    public static native @ByVal UIOffset Zero();
    
    @Bridge(symbol="NSStringFromUIOffset", optional=true)
    protected static native String toString(@ByVal UIOffset offset);
    @Bridge(symbol="UIOffsetFromString", optional=true)
    public static native @ByVal UIOffset fromString(String string);
    /*</methods>*/
}
