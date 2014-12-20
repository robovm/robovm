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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVTime/*</name>*/ 
    extends /*<extends>*/Struct<CVTime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVTimePtr extends Ptr<CVTime, CVTimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CVTime() {}
    public CVTime(long timeValue, int timeScale, int flags) {
        this.setTimevalue(timeValue);
        this.setTimescale(timeScale);
        this.setFlags(flags);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native long getTimevalue();
    @StructMember(0) public native CVTime setTimevalue(long timeValue);
    
    @Deprecated
    @StructMember(0) public native long timeValue();
    @Deprecated
    @StructMember(0) public native CVTime timeValue(long timeValue);
    
    @StructMember(1) public native int getTimescale();
    @StructMember(1) public native CVTime setTimescale(int timeScale);
    
    @Deprecated
    @StructMember(1) public native int timeScale();
    @Deprecated
    @StructMember(1) public native CVTime timeScale(int timeScale);
    
    @StructMember(2) public native int getFlags();
    @StructMember(2) public native CVTime setFlags(int flags);
    
    @Deprecated
    @StructMember(2) public native int flags();
    @Deprecated
    @StructMember(2) public native CVTime flags(int flags);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
