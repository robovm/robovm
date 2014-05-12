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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Foundation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Foundation.class); }/*</bind>*/
    /*<constants>*/
    public static final double VersionNumber10_0 = 397.40;
    public static final double VersionNumber10_1 = 425.00;
    public static final double VersionNumber10_1_1 = 425.00;
    public static final double VersionNumber10_1_2 = 425.00;
    public static final double VersionNumber10_1_3 = 425.00;
    public static final double VersionNumber10_1_4 = 425.00;
    public static final double VersionNumber10_2 = 462.00;
    public static final double VersionNumber10_2_1 = 462.00;
    public static final double VersionNumber10_2_2 = 462.00;
    public static final double VersionNumber10_2_3 = 462.00;
    public static final double VersionNumber10_2_4 = 462.00;
    public static final double VersionNumber10_2_5 = 462.00;
    public static final double VersionNumber10_2_6 = 462.00;
    public static final double VersionNumber10_2_7 = 462.70;
    public static final double VersionNumber10_2_8 = 462.70;
    public static final double VersionNumber10_3 = 500.00;
    public static final double VersionNumber10_3_1 = 500.00;
    public static final double VersionNumber10_3_2 = 500.30;
    public static final double VersionNumber10_3_3 = 500.54;
    public static final double VersionNumber10_3_4 = 500.56;
    public static final double VersionNumber10_3_5 = 500.56;
    public static final double VersionNumber10_3_6 = 500.56;
    public static final double VersionNumber10_3_7 = 500.56;
    public static final double VersionNumber10_3_8 = 500.56;
    public static final double VersionNumber10_3_9 = 500.58;
    public static final double VersionNumber10_4 = 567.00;
    public static final double VersionNumber10_4_1 = 567.00;
    public static final double VersionNumber10_4_2 = 567.12;
    public static final double VersionNumber10_4_3 = 567.21;
    public static final double VersionNumber10_4_4_Intel = 567.23;
    public static final double VersionNumber10_4_4_PowerPC = 567.21;
    public static final double VersionNumber10_4_5 = 567.25;
    public static final double VersionNumber10_4_6 = 567.26;
    public static final double VersionNumber10_4_7 = 567.27;
    public static final double VersionNumber10_4_8 = 567.28;
    public static final double VersionNumber10_4_9 = 567.29;
    public static final double VersionNumber10_4_10 = 567.29;
    public static final double VersionNumber10_4_11 = 567.36;
    public static final double VersionNumber10_5 = 677.00;
    public static final double VersionNumber10_5_1 = 677.10;
    public static final double VersionNumber10_5_2 = 677.15;
    public static final double VersionNumber10_5_3 = 677.19;
    public static final double VersionNumber10_5_4 = 677.19;
    public static final double VersionNumber10_5_5 = 677.21;
    public static final double VersionNumber10_5_6 = 677.22;
    public static final double VersionNumber10_5_7 = 677.24;
    public static final double VersionNumber10_5_8 = 677.26;
    public static final double VersionNumber10_6 = 751.00;
    public static final double VersionNumber10_6_1 = 751.00;
    public static final double VersionNumber10_6_2 = 751.14;
    public static final double VersionNumber10_6_3 = 751.21;
    public static final double VersionNumber10_6_4 = 751.29;
    public static final double VersionNumber10_6_5 = 751.42;
    public static final double VersionNumber10_6_6 = 751.53;
    public static final double VersionNumber10_6_7 = 751.53;
    public static final double VersionNumber10_6_8 = 751.62;
    public static final double VersionNumber10_7 = 833.10;
    public static final double VersionNumber10_7_1 = 833.10;
    public static final double VersionNumber10_7_2 = 833.20;
    public static final double VersionNumber10_7_3 = 833.24;
    public static final double VersionNumber10_7_4 = 833.25;
    public static final double VersionNumber10_8 = 945.00;
    public static final double VersionNumber10_8_1 = 945.00;
    public static final double VersionNumber10_8_2 = 945.11;
    public static final double VersionNumber10_8_3 = 945.16;
    public static final double VersionNumber10_8_4 = 945.18;
    public static final double VersionNumber_iPhoneOS_2_0 = 678.24;
    public static final double VersionNumber_iPhoneOS_2_1 = 678.26;
    public static final double VersionNumber_iPhoneOS_2_2 = 678.29;
    public static final double VersionNumber_iPhoneOS_3_0 = 678.47;
    public static final double VersionNumber_iPhoneOS_3_1 = 678.51;
    public static final double VersionNumber_iPhoneOS_3_2 = 678.60;
    public static final double VersionNumber_iOS_4_0 = 751.32;
    public static final double VersionNumber_iOS_4_1 = 751.37;
    public static final double VersionNumber_iOS_4_2 = 751.49;
    public static final double VersionNumber_iOS_4_3 = 751.49;
    public static final double VersionNumber_iOS_5_0 = 881.00;
    public static final double VersionNumber_iOS_5_1 = 890.10;
    public static final double VersionNumber_iOS_6_0 = 993.00;
    public static final double VersionNumber_iOS_6_1 = 993.00;
    public static final int NotFound = 2147483647;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSFoundationVersionNumber", optional=true)
    public static native double VersionNumber();
    /*</methods>*/
}
