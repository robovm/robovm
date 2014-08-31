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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontDescriptor/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface ProgressHandler {
        boolean invoke(CTFontDescriptorMatchingState state, CTFontDescriptorProgressData data);
    }
    private static final java.lang.reflect.Method cbProgress;
    private static ProgressHandler progressHandler;
    
    static {
        try {
            cbProgress = CTFontDescriptor.class.getDeclaredMethod("cbProgress", CTFontDescriptorMatchingState.class, CFDictionary.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<ptr>*/public static class CTFontDescriptorPtr extends Ptr<CTFontDescriptor, CTFontDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CTFontDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTFontDescriptor() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static boolean cbProgress(CTFontDescriptorMatchingState state, CFDictionary data) {
        return progressHandler.invoke(state, new CTFontDescriptorProgressData(data));
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static CTFontDescriptor create(CTFontAttributes attributes) {
        return create(attributes.getDictionary());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontDescriptor createCopy(CTFontAttributes attributes) {
        return createCopy(attributes.getDictionary());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontDescriptor createCopy(long variationIdentifier, @MachineSizedFloat double variationValue) {
        return createCopy(CFNumber.valueOf(variationIdentifier), variationValue);
    }
//    /**
//     * @since Available in iOS 3.2 and later.
//     */
//    @Bridge(symbol="CTFontDescriptorCreateCopyWithFeature", optional=true) TODO
//    protected native CTFontDescriptor createCopy(CFNumber featureTypeIdentifier, CFNumber featureSelectorIdentifier);
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<CTFontDescriptor> getMatchingFontDescriptors(List<CTFontAttribute> mandatoryAttributes) {
        CFSet set = CFMutableSet.create();
        for (CTFontAttribute attr : mandatoryAttributes) {
            set.add(attr.value());
        }
        return getMatchingFontDescriptors(set).toList(CTFontDescriptor.class);
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontDescriptor getMatchingFontDescriptor(List<CTFontAttribute> mandatoryAttributes) {
        CFSet set = CFMutableSet.create();
        for (CTFontAttribute attr : mandatoryAttributes) {
            set.add(attr.value());
        }
        return getMatchingFontDescriptor(set);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static boolean matchFontDescriptors(List<CTFontDescriptor> descriptors, List<CTFontAttribute> mandatoryAttributes, ProgressHandler progressHandler) {
        CTFontDescriptor.progressHandler = progressHandler;
        CFSet set = CFMutableSet.create();
        for (CTFontAttribute attr : mandatoryAttributes) {
            set.add(attr.value());
        }
        return matchFontDescriptors(CFArray.create(descriptors), set, new FunctionPtr(cbProgress));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontAttributes getAttributes() {
        return new CTFontAttributes(getAttributes0());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CFType getAttribute(CTFontAttribute attribute) {
        return getAttribute(attribute.value());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CFType getLocalizedAttribute(CTFontAttribute attribute) {
        return getLocalizedAttribute(attribute.value(), null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateWithNameAndSize", optional=true)
    public static native CTFontDescriptor create(String name, @MachineSizedFloat double size);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateWithAttributes", optional=true)
    protected static native CTFontDescriptor create(CFDictionary attributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithAttributes", optional=true)
    protected native CTFontDescriptor createCopy(CFDictionary attributes);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithFamily", optional=true)
    public native CTFontDescriptor createCopy(String family);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithSymbolicTraits", optional=true)
    public native CTFontDescriptor createCopy(CTFontSymbolicTraits symTraitValue, CTFontSymbolicTraits symTraitMask);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithVariation", optional=true)
    protected native CTFontDescriptor createCopy(CFNumber variationIdentifier, @MachineSizedFloat double variationValue);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithFeature", optional=true)
    protected native CTFontDescriptor createCopy(CFNumber featureTypeIdentifier, CFNumber featureSelectorIdentifier);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateMatchingFontDescriptors", optional=true)
    protected native CFArray getMatchingFontDescriptors(CFSet mandatoryAttributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateMatchingFontDescriptor", optional=true)
    protected native CTFontDescriptor getMatchingFontDescriptor(CFSet mandatoryAttributes);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CTFontDescriptorMatchFontDescriptorsWithProgressHandler", optional=true)
    protected static native boolean matchFontDescriptors(CFArray descriptors, CFSet mandatoryAttributes, FunctionPtr progressBlock);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCopyAttributes", optional=true)
    protected native CFDictionary getAttributes0();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCopyAttribute", optional=true)
    protected native CFType getAttribute(CFString attribute);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCopyLocalizedAttribute", optional=true)
    protected native CFType getLocalizedAttribute(CFString attribute, CFString.CFStringPtr language);
    /*</methods>*/
}
