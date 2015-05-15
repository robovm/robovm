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
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreFoundationVersionNumber/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreFoundationVersionNumber.class); }/*</bind>*/
    /*<constants>*/
    public static final double VersionNumber10_0 = 196.40;
    public static final double VersionNumber10_0_3 = 196.50;
    public static final double VersionNumber10_1 = 226.00;
    public static final double VersionNumber10_1_1 = 226.00;
    public static final double VersionNumber10_1_2 = 227.20;
    public static final double VersionNumber10_1_3 = 227.20;
    public static final double VersionNumber10_1_4 = 227.30;
    public static final double VersionNumber10_2 = 263.00;
    public static final double VersionNumber10_2_1 = 263.10;
    public static final double VersionNumber10_2_2 = 263.10;
    public static final double VersionNumber10_2_3 = 263.30;
    public static final double VersionNumber10_2_4 = 263.30;
    public static final double VersionNumber10_2_5 = 263.50;
    public static final double VersionNumber10_2_6 = 263.50;
    public static final double VersionNumber10_2_7 = 263.50;
    public static final double VersionNumber10_2_8 = 263.50;
    public static final double VersionNumber10_3 = 299.00;
    public static final double VersionNumber10_3_1 = 299.00;
    public static final double VersionNumber10_3_2 = 299.00;
    public static final double VersionNumber10_3_3 = 299.30;
    public static final double VersionNumber10_3_4 = 299.31;
    public static final double VersionNumber10_3_5 = 299.31;
    public static final double VersionNumber10_3_6 = 299.32;
    public static final double VersionNumber10_3_7 = 299.33;
    public static final double VersionNumber10_3_8 = 299.33;
    public static final double VersionNumber10_3_9 = 299.35;
    public static final double VersionNumber10_4 = 368.00;
    public static final double VersionNumber10_4_1 = 368.10;
    public static final double VersionNumber10_4_2 = 368.11;
    public static final double VersionNumber10_4_3 = 368.18;
    public static final double VersionNumber10_4_4_Intel = 368.26;
    public static final double VersionNumber10_4_4_PowerPC = 368.25;
    public static final double VersionNumber10_4_5_Intel = 368.26;
    public static final double VersionNumber10_4_5_PowerPC = 368.25;
    public static final double VersionNumber10_4_6_Intel = 368.26;
    public static final double VersionNumber10_4_6_PowerPC = 368.25;
    public static final double VersionNumber10_4_7 = 368.27;
    public static final double VersionNumber10_4_8 = 368.27;
    public static final double VersionNumber10_4_9 = 368.28;
    public static final double VersionNumber10_4_10 = 368.28;
    public static final double VersionNumber10_4_11 = 368.31;
    public static final double VersionNumber10_5 = 476.00;
    public static final double VersionNumber10_5_1 = 476.00;
    public static final double VersionNumber10_5_2 = 476.10;
    public static final double VersionNumber10_5_3 = 476.13;
    public static final double VersionNumber10_5_4 = 476.14;
    public static final double VersionNumber10_5_5 = 476.15;
    public static final double VersionNumber10_5_6 = 476.17;
    public static final double VersionNumber10_5_7 = 476.18;
    public static final double VersionNumber10_5_8 = 476.19;
    public static final double VersionNumber10_6 = 550.00;
    public static final double VersionNumber10_6_1 = 550.00;
    public static final double VersionNumber10_6_2 = 550.13;
    public static final double VersionNumber10_6_3 = 550.19;
    public static final double VersionNumber10_6_4 = 550.29;
    public static final double VersionNumber10_6_5 = 550.42;
    public static final double VersionNumber10_6_6 = 550.42;
    public static final double VersionNumber10_6_7 = 550.42;
    public static final double VersionNumber10_6_8 = 550.43;
    public static final double VersionNumber10_7 = 635.00;
    public static final double VersionNumber10_7_1 = 635.00;
    public static final double VersionNumber10_7_2 = 635.15;
    public static final double VersionNumber10_7_3 = 635.19;
    public static final double VersionNumber10_7_4 = 635.21;
    public static final double VersionNumber10_7_5 = 635.21;
    public static final double VersionNumber10_8 = 744.00;
    public static final double VersionNumber10_8_1 = 744.00;
    public static final double VersionNumber10_8_2 = 744.12;
    public static final double VersionNumber10_8_3 = 744.18;
    public static final double VersionNumber10_8_4 = 744.19;
    public static final double VersionNumber10_9 = 855.11;
    public static final double VersionNumber10_9_1 = 855.11;
    public static final double VersionNumber10_9_2 = 855.14;
    public static final double VersionNumber_iPhoneOS_2_0 = 478.23;
    public static final double VersionNumber_iPhoneOS_2_1 = 478.26;
    public static final double VersionNumber_iPhoneOS_2_2 = 478.29;
    public static final double VersionNumber_iPhoneOS_3_0 = 478.47;
    public static final double VersionNumber_iPhoneOS_3_1 = 478.52;
    public static final double VersionNumber_iPhoneOS_3_2 = 478.61;
    public static final double VersionNumber_iOS_4_0 = 550.32;
    public static final double VersionNumber_iOS_4_1 = 550.38;
    public static final double VersionNumber_iOS_4_2 = 550.52;
    public static final double VersionNumber_iOS_4_3 = 550.52;
    public static final double VersionNumber_iOS_5_0 = 675.00;
    public static final double VersionNumber_iOS_5_1 = 690.10;
    public static final double VersionNumber_iOS_6_0 = 793.00;
    public static final double VersionNumber_iOS_6_1 = 793.00;
    public static final double VersionNumber_iOS_7_0 = 847.20;
    public static final double VersionNumber_iOS_7_1 = 847.24;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFCoreFoundationVersionNumber", optional=true)
    public static native double getVersion();
    /*</methods>*/
}
