/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPrintInfo /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintInfo /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPrintInfo() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("duplex") public native @Type("UIPrintInfoDuplex") UIPrintInfoDuplex getDuplex();
    @Bind("setDuplex:") public native void setDuplex(@Type("UIPrintInfoDuplex") UIPrintInfoDuplex v);
    @Bind("jobName") public native @Type("NSString *") String getJobName();
    @Bind("setJobName:") public native void setJobName(@Type("NSString *") String v);
    @Bind("orientation") public native @Type("UIPrintInfoOrientation") UIPrintInfoOrientation getOrientation();
    @Bind("setOrientation:") public native void setOrientation(@Type("UIPrintInfoOrientation") UIPrintInfoOrientation v);
    @Bind("outputType") public native @Type("UIPrintInfoOutputType") UIPrintInfoOutputType getOutputType();
    @Bind("setOutputType:") public native void setOutputType(@Type("UIPrintInfoOutputType") UIPrintInfoOutputType v);
    @Bind("printerID") public native @Type("NSString *") String getPrinterID();
    @Bind("setPrinterID:") public native void setPrinterID(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    @Bind("printInfoWithDictionary:") public native static @Type("UIPrintInfo *") UIPrintInfo fromDictionary(@Type("NSDictionary *") NSDictionary dictionary);
    @Bind("printInfo") public native static @Type("UIPrintInfo *") UIPrintInfo getPrintInfo();
    @Bind("dictionaryRepresentation") public native @Type("NSDictionary *") NSDictionary toDictionary();
    /*</methods>*/

}
