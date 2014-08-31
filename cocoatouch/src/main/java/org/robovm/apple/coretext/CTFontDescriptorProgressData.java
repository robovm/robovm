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
package org.robovm.apple.coretext;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontDescriptorProgressData/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CTFontDescriptorProgressData(CFDictionary data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CTFontDescriptorProgressData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CTFontDescriptor getSourceDescriptor() {
        if (data.containsKey(SourceDescriptor())) {
            CTFontDescriptor val = data.get(SourceDescriptor(), CTFontDescriptor.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public List<CTFontDescriptor> getDescriptors() {
        if (data.containsKey(Descriptors())) {
            CFArray val = data.get(Descriptors(), CFArray.class);
            return val.toList(CTFontDescriptor.class);
        }
        return new ArrayList<>();
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public List<CTFontDescriptor> getResult() {
        if (data.containsKey(Result())) {
            CFArray val = data.get(Result(), CFArray.class);
            return val.toList(CTFontDescriptor.class);
        }
        return new ArrayList<>();
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getPercentage() {
        if (data.containsKey(Percentage())) {
            CFNumber val = data.get(Percentage(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getCurrentAssetSize() {
        if (data.containsKey(CurrentAssetSize())) {
            CFNumber val = data.get(CurrentAssetSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getTotalDownloadedSize() {
        if (data.containsKey(TotalDownloadedSize())) {
            CFNumber val = data.get(TotalDownloadedSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getTotalAssetSize() {
        if (data.containsKey(TotalAssetSize())) {
            CFNumber val = data.get(TotalAssetSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CFError getError() {
        if (data.containsKey(Error())) {
            CFError val = data.get(Error(), CFError.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingSourceDescriptor", optional=true)
    protected static native CFString SourceDescriptor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingDescriptors", optional=true)
    protected static native CFString Descriptors();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingResult", optional=true)
    protected static native CFString Result();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingPercentage", optional=true)
    protected static native CFString Percentage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingCurrentAssetSize", optional=true)
    protected static native CFString CurrentAssetSize();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingTotalDownloadedSize", optional=true)
    protected static native CFString TotalDownloadedSize();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingTotalAssetSize", optional=true)
    protected static native CFString TotalAssetSize();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptorMatchingError", optional=true)
    protected static native CFString Error();
    /*</methods>*/
}
