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
package org.robovm.apple.coremotion;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMCalibratedMagneticField/*</name>*/ 
    extends /*<extends>*/Struct<CMCalibratedMagneticField>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMCalibratedMagneticFieldPtr extends Ptr<CMCalibratedMagneticField, CMCalibratedMagneticFieldPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMCalibratedMagneticField() {}
    public CMCalibratedMagneticField(CMMagneticField field, CMMagneticFieldCalibrationAccuracy accuracy) {
        this.field(field);
        this.accuracy(accuracy);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CMMagneticField field();
    @StructMember(0) public native CMCalibratedMagneticField field(@ByVal CMMagneticField field);
    @StructMember(1) public native CMMagneticFieldCalibrationAccuracy accuracy();
    @StructMember(1) public native CMCalibratedMagneticField accuracy(CMMagneticFieldCalibrationAccuracy accuracy);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
