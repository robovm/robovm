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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVEdgeWidths/*</name>*/ 
    extends /*<extends>*/Struct<AVEdgeWidths>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVEdgeWidthsPtr extends Ptr<AVEdgeWidths, AVEdgeWidthsPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVEdgeWidths() {}
    public AVEdgeWidths(@MachineSizedFloat double left, @MachineSizedFloat double top, @MachineSizedFloat double right, @MachineSizedFloat double bottom) {
        this.left(left);
        this.top(top);
        this.right(right);
        this.bottom(bottom);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double left();
    @StructMember(0) public native AVEdgeWidths left(@MachineSizedFloat double left);
    @StructMember(1) public native @MachineSizedFloat double top();
    @StructMember(1) public native AVEdgeWidths top(@MachineSizedFloat double top);
    @StructMember(2) public native @MachineSizedFloat double right();
    @StructMember(2) public native AVEdgeWidths right(@MachineSizedFloat double right);
    @StructMember(3) public native @MachineSizedFloat double bottom();
    @StructMember(3) public native AVEdgeWidths bottom(@MachineSizedFloat double bottom);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
