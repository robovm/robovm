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
package org.robovm.apple.coretext;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontDescriptor/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<?> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.toList(CTFontDescriptor.class);
        }
        @MarshalsPointer
        public static long toNative(List<? extends CFType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray o = null;
            if (l instanceof CFArray) {
                o = (CFArray) l;
            } else {
                o = CFArray.create((List<? extends CFType>) l);
            }
            return CFType.Marshaler.toNative(o, flags);
        }
    }
    
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
    public List<CTFontDescriptor> createMatchingFontDescriptors(List<CTFontAttribute> mandatoryAttributes) {
        CFSet set = CFMutableSet.create();
        for (CTFontAttribute attr : mandatoryAttributes) {
            set.add(attr.value());
        }
        return createMatchingFontDescriptors(set).toList(CTFontDescriptor.class);
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontDescriptor createMatchingFontDescriptor(List<CTFontAttribute> mandatoryAttributes) {
        CFSet set = CFMutableSet.create();
        for (CTFontAttribute attr : mandatoryAttributes) {
            set.add(attr.value());
        }
        return createMatchingFontDescriptor(set);
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
    public CFType getLocalizedAttribute(CTFontAttribute attribute) {
        return getLocalizedAttribute(attribute, null);
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
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontDescriptor create(String name, @MachineSizedFloat double size);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateWithAttributes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontDescriptor create(CTFontAttributes attributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithAttributes", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontDescriptor createCopy(CTFontAttributes attributes);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithFamily", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontDescriptor createCopy(String family);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateCopyWithSymbolicTraits", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontDescriptor createCopy(CTFontSymbolicTraits symTraitValue, CTFontSymbolicTraits symTraitMask);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateMatchingFontDescriptors", optional=true)
    protected native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray createMatchingFontDescriptors(CFSet mandatoryAttributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCreateMatchingFontDescriptor", optional=true)
    protected native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontDescriptor createMatchingFontDescriptor(CFSet mandatoryAttributes);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CTFontDescriptorMatchFontDescriptorsWithProgressHandler", optional=true)
    protected static native boolean matchFontDescriptors(CFArray descriptors, CFSet mandatoryAttributes, FunctionPtr progressBlock);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCopyAttributes", optional=true)
    public native CTFontAttributes getAttributes();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCopyAttribute", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getAttribute(CTFontAttribute attribute);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontDescriptorCopyLocalizedAttribute", optional=true)
    protected native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getLocalizedAttribute(CTFontAttribute attribute, CFString.CFStringPtr language);
    /*</methods>*/
}
