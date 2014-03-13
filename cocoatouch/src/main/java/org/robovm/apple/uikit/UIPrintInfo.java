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
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPrintInfo/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIPrintInfoPtr extends Ptr<UIPrintInfo, UIPrintInfoPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPrintInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPrintInfo() {}
    protected UIPrintInfo(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "printerID")
    public native String getPrinterID();
    @Property(selector = "setPrinterID:")
    public native void setPrinterID(String v);
    @Property(selector = "jobName")
    public native String getJobName();
    @Property(selector = "setJobName:")
    public native void setJobName(String v);
    @Property(selector = "outputType")
    public native UIPrintInfoOutputType getOutputType();
    @Property(selector = "setOutputType:")
    public native void setOutputType(UIPrintInfoOutputType v);
    @Property(selector = "orientation")
    public native UIPrintInfoOrientation getOrientation();
    @Property(selector = "setOrientation:")
    public native void setOrientation(UIPrintInfoOrientation v);
    @Property(selector = "duplex")
    public native UIPrintInfoDuplex getDuplex();
    @Property(selector = "setDuplex:")
    public native void setDuplex(UIPrintInfoDuplex v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "dictionaryRepresentation")
    public native NSDictionary<?, ?> toDictionary();
    @Method(selector = "printInfo")
    public static native UIPrintInfo getPrintInfo();
    @Method(selector = "printInfoWithDictionary:")
    public static native UIPrintInfo fromDictionary(NSDictionary<?, ?> dictionary);
    /*</methods>*/
}
