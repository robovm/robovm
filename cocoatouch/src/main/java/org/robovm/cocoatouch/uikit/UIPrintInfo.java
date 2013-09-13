/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html">UIPrintInfo Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIPrintInfo /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintInfo /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPrintInfo /*</name>*/.class);

    /*<constructors>*/
    protected UIPrintInfo(SkipInit skipInit) { super(skipInit); }
    public UIPrintInfo() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector duplex = Selector.register("duplex");
    @Bridge private native static UIPrintInfoDuplex objc_getDuplex(UIPrintInfo __self__, Selector __cmd__);
    @Bridge private native static UIPrintInfoDuplex objc_getDuplexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/duplex">@property(nonatomic) UIPrintInfoDuplex duplex</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIPrintInfoDuplex getDuplex() {
        if (customClass) { return objc_getDuplexSuper(getSuper(), duplex); } else { return objc_getDuplex(this, duplex); }
    }
    
    private static final Selector setDuplex$ = Selector.register("setDuplex:");
    @Bridge private native static void objc_setDuplex(UIPrintInfo __self__, Selector __cmd__, UIPrintInfoDuplex duplex);
    @Bridge private native static void objc_setDuplexSuper(ObjCSuper __super__, Selector __cmd__, UIPrintInfoDuplex duplex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/duplex">@property(nonatomic) UIPrintInfoDuplex duplex</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setDuplex(UIPrintInfoDuplex duplex) {
        if (customClass) { objc_setDuplexSuper(getSuper(), setDuplex$, duplex); } else { objc_setDuplex(this, setDuplex$, duplex); }
    }
    
    private static final Selector jobName = Selector.register("jobName");
    @Bridge private native static String objc_getJobName(UIPrintInfo __self__, Selector __cmd__);
    @Bridge private native static String objc_getJobNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/jobName">@property(nonatomic, copy) NSString *jobName</a>
     * @since Available in iOS 4.2 and later.
     */
    public String getJobName() {
        if (customClass) { return objc_getJobNameSuper(getSuper(), jobName); } else { return objc_getJobName(this, jobName); }
    }
    
    private static final Selector setJobName$ = Selector.register("setJobName:");
    @Bridge private native static void objc_setJobName(UIPrintInfo __self__, Selector __cmd__, String jobName);
    @Bridge private native static void objc_setJobNameSuper(ObjCSuper __super__, Selector __cmd__, String jobName);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/jobName">@property(nonatomic, copy) NSString *jobName</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setJobName(String jobName) {
        if (customClass) { objc_setJobNameSuper(getSuper(), setJobName$, jobName); } else { objc_setJobName(this, setJobName$, jobName); }
    }
    
    private static final Selector orientation = Selector.register("orientation");
    @Bridge private native static UIPrintInfoOrientation objc_getOrientation(UIPrintInfo __self__, Selector __cmd__);
    @Bridge private native static UIPrintInfoOrientation objc_getOrientationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/orientation">@property(nonatomic) UIPrintInfoOrientation orientation</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIPrintInfoOrientation getOrientation() {
        if (customClass) { return objc_getOrientationSuper(getSuper(), orientation); } else { return objc_getOrientation(this, orientation); }
    }
    
    private static final Selector setOrientation$ = Selector.register("setOrientation:");
    @Bridge private native static void objc_setOrientation(UIPrintInfo __self__, Selector __cmd__, UIPrintInfoOrientation orientation);
    @Bridge private native static void objc_setOrientationSuper(ObjCSuper __super__, Selector __cmd__, UIPrintInfoOrientation orientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/orientation">@property(nonatomic) UIPrintInfoOrientation orientation</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setOrientation(UIPrintInfoOrientation orientation) {
        if (customClass) { objc_setOrientationSuper(getSuper(), setOrientation$, orientation); } else { objc_setOrientation(this, setOrientation$, orientation); }
    }
    
    private static final Selector outputType = Selector.register("outputType");
    @Bridge private native static UIPrintInfoOutputType objc_getOutputType(UIPrintInfo __self__, Selector __cmd__);
    @Bridge private native static UIPrintInfoOutputType objc_getOutputTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/outputType">@property(nonatomic) UIPrintInfoOutputType outputType</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIPrintInfoOutputType getOutputType() {
        if (customClass) { return objc_getOutputTypeSuper(getSuper(), outputType); } else { return objc_getOutputType(this, outputType); }
    }
    
    private static final Selector setOutputType$ = Selector.register("setOutputType:");
    @Bridge private native static void objc_setOutputType(UIPrintInfo __self__, Selector __cmd__, UIPrintInfoOutputType outputType);
    @Bridge private native static void objc_setOutputTypeSuper(ObjCSuper __super__, Selector __cmd__, UIPrintInfoOutputType outputType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/outputType">@property(nonatomic) UIPrintInfoOutputType outputType</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setOutputType(UIPrintInfoOutputType outputType) {
        if (customClass) { objc_setOutputTypeSuper(getSuper(), setOutputType$, outputType); } else { objc_setOutputType(this, setOutputType$, outputType); }
    }
    
    private static final Selector printerID = Selector.register("printerID");
    @Bridge private native static String objc_getPrinterID(UIPrintInfo __self__, Selector __cmd__);
    @Bridge private native static String objc_getPrinterIDSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/printerID">@property(nonatomic, copy) NSString *printerID</a>
     * @since Available in iOS 4.2 and later.
     */
    public String getPrinterID() {
        if (customClass) { return objc_getPrinterIDSuper(getSuper(), printerID); } else { return objc_getPrinterID(this, printerID); }
    }
    
    private static final Selector setPrinterID$ = Selector.register("setPrinterID:");
    @Bridge private native static void objc_setPrinterID(UIPrintInfo __self__, Selector __cmd__, String printerID);
    @Bridge private native static void objc_setPrinterIDSuper(ObjCSuper __super__, Selector __cmd__, String printerID);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPrintInfo/printerID">@property(nonatomic, copy) NSString *printerID</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setPrinterID(String printerID) {
        if (customClass) { objc_setPrinterIDSuper(getSuper(), setPrinterID$, printerID); } else { objc_setPrinterID(this, setPrinterID$, printerID); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector printInfoWithDictionary$ = Selector.register("printInfoWithDictionary:");
    @Bridge private native static UIPrintInfo objc_fromDictionary(ObjCClass __self__, Selector __cmd__, NSDictionary dictionary);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInfo/printInfoWithDictionary:">+ (UIPrintInfo *)printInfoWithDictionary:(NSDictionary *)dictionary</a>
     * @since Available in iOS 4.2 and later.
     */
    public static UIPrintInfo fromDictionary(NSDictionary dictionary) {
        return objc_fromDictionary(objCClass, printInfoWithDictionary$, dictionary);
    }
    
    private static final Selector printInfo = Selector.register("printInfo");
    @Bridge private native static UIPrintInfo objc_getPrintInfo(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/clm/UIPrintInfo/printInfo">+ (UIPrintInfo *)printInfo</a>
     * @since Available in iOS 4.2 and later.
     */
    public static UIPrintInfo getPrintInfo() {
        return objc_getPrintInfo(objCClass, printInfo);
    }
    
    private static final Selector dictionaryRepresentation = Selector.register("dictionaryRepresentation");
    @Bridge private native static NSDictionary objc_toDictionary(UIPrintInfo __self__, Selector __cmd__);
    @Bridge private native static NSDictionary objc_toDictionarySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInfo_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPrintInfo/dictionaryRepresentation">- (NSDictionary *)dictionaryRepresentation</a>
     * @since Available in iOS 4.2 and later.
     */
    public NSDictionary toDictionary() {
        if (customClass) { return objc_toDictionarySuper(getSuper(), dictionaryRepresentation); } else { return objc_toDictionary(this, dictionaryRepresentation); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("duplex") public static UIPrintInfoDuplex getDuplex(UIPrintInfo __self__, Selector __cmd__) { return __self__.getDuplex(); }
        @Callback @BindSelector("setDuplex:") public static void setDuplex(UIPrintInfo __self__, Selector __cmd__, UIPrintInfoDuplex duplex) { __self__.setDuplex(duplex); }
        @Callback @BindSelector("jobName") public static String getJobName(UIPrintInfo __self__, Selector __cmd__) { return __self__.getJobName(); }
        @Callback @BindSelector("setJobName:") public static void setJobName(UIPrintInfo __self__, Selector __cmd__, String jobName) { __self__.setJobName(jobName); }
        @Callback @BindSelector("orientation") public static UIPrintInfoOrientation getOrientation(UIPrintInfo __self__, Selector __cmd__) { return __self__.getOrientation(); }
        @Callback @BindSelector("setOrientation:") public static void setOrientation(UIPrintInfo __self__, Selector __cmd__, UIPrintInfoOrientation orientation) { __self__.setOrientation(orientation); }
        @Callback @BindSelector("outputType") public static UIPrintInfoOutputType getOutputType(UIPrintInfo __self__, Selector __cmd__) { return __self__.getOutputType(); }
        @Callback @BindSelector("setOutputType:") public static void setOutputType(UIPrintInfo __self__, Selector __cmd__, UIPrintInfoOutputType outputType) { __self__.setOutputType(outputType); }
        @Callback @BindSelector("printerID") public static String getPrinterID(UIPrintInfo __self__, Selector __cmd__) { return __self__.getPrinterID(); }
        @Callback @BindSelector("setPrinterID:") public static void setPrinterID(UIPrintInfo __self__, Selector __cmd__, String printerID) { __self__.setPrinterID(printerID); }
        @Callback @BindSelector("dictionaryRepresentation") public static NSDictionary toDictionary(UIPrintInfo __self__, Selector __cmd__) { return __self__.toDictionary(); }
    }
    /*</callbacks>*/

}
