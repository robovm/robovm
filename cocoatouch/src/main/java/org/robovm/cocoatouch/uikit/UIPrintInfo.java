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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html">UIPrintInfo Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/duplex">@property(nonatomic) UIPrintInfoDuplex duplex</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("duplex") public native @Type("UIPrintInfoDuplex") UIPrintInfoDuplex getDuplex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/duplex">@property(nonatomic) UIPrintInfoDuplex duplex</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setDuplex:") public native void setDuplex(@Type("UIPrintInfoDuplex") UIPrintInfoDuplex v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/jobName">@property(nonatomic, copy) NSString *jobName</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("jobName") public native @Type("NSString *") String getJobName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/jobName">@property(nonatomic, copy) NSString *jobName</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setJobName:") public native void setJobName(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/orientation">@property(nonatomic) UIPrintInfoOrientation orientation</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("orientation") public native @Type("UIPrintInfoOrientation") UIPrintInfoOrientation getOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/orientation">@property(nonatomic) UIPrintInfoOrientation orientation</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setOrientation:") public native void setOrientation(@Type("UIPrintInfoOrientation") UIPrintInfoOrientation v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/outputType">@property(nonatomic) UIPrintInfoOutputType outputType</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("outputType") public native @Type("UIPrintInfoOutputType") UIPrintInfoOutputType getOutputType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/outputType">@property(nonatomic) UIPrintInfoOutputType outputType</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setOutputType:") public native void setOutputType(@Type("UIPrintInfoOutputType") UIPrintInfoOutputType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/printerID">@property(nonatomic, copy) NSString *printerID</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printerID") public native @Type("NSString *") String getPrinterID();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/printerID">@property(nonatomic, copy) NSString *printerID</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setPrinterID:") public native void setPrinterID(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInfo/printInfoWithDictionary:">+ (UIPrintInfo *)printInfoWithDictionary:(NSDictionary *)dictionary</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInfoWithDictionary:") public native static @Type("UIPrintInfo *") UIPrintInfo fromDictionary(@Type("NSDictionary *") NSDictionary dictionary);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInfo/printInfo">+ (UIPrintInfo *)printInfo</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInfo") public native static @Type("UIPrintInfo *") UIPrintInfo getPrintInfo();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintInfo/dictionaryRepresentation">- (NSDictionary *)dictionaryRepresentation</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("dictionaryRepresentation") public native @Type("NSDictionary *") NSDictionary toDictionary();
    /*</methods>*/

}
