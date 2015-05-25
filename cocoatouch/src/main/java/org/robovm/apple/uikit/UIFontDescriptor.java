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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIFontDescriptorPtr extends Ptr<UIFontDescriptor, UIFontDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIFontDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIFontDescriptor() {}
    protected UIFontDescriptor(SkipInit skipInit) { super(skipInit); }
    public UIFontDescriptor(UIFontDescriptorAttributes attributes) { super((SkipInit) null); initObject(init(attributes)); }
    public UIFontDescriptor(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "postscriptName")
    public native String getPostscriptName();
    @Property(selector = "pointSize")
    public native @MachineSizedFloat double getPointSize();
    @Property(selector = "matrix")
    public native @ByVal CGAffineTransform getMatrix();
    @Property(selector = "symbolicTraits")
    public native UIFontDescriptorSymbolicTraits getSymbolicTraits();
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSObject getValue(String attribute) {
        return getValue(new NSString(attribute));
    }
    public NSObject getValue(UIFontDescriptorAttribute attribute) {
        return getValue(attribute.value());
    }
    public NSArray<UIFontDescriptor> getMatchingFontDescriptors(String...mandatoryAttributes) {
        return getMatchingFontDescriptors(NSSet.fromStrings(mandatoryAttributes));
    }
    public NSArray<UIFontDescriptor> getMatchingFontDescriptors(Set<UIFontDescriptorAttribute> mandatoryAttributes) {
        NSSet<NSString> set = new NSMutableSet<>();
        for (UIFontDescriptorAttribute attr : mandatoryAttributes) {
            set.add(attr.value());
        }
        return getMatchingFontDescriptors(set);
    }
    public static UIFontDescriptor getPreferredFontDescriptor(UIFontTextStyle style) {
        return getPreferredFontDescriptor(style.value());
    }
    /*<methods>*/
    @Method(selector = "objectForKey:")
    protected native NSObject getValue(NSString anAttribute);
    @Method(selector = "fontAttributes")
    public native UIFontDescriptorAttributes getFontAttributes();
    @Method(selector = "matchingFontDescriptorsWithMandatoryKeys:")
    protected native NSArray<UIFontDescriptor> getMatchingFontDescriptors(NSSet<NSString> mandatoryKeys);
    @Method(selector = "initWithFontAttributes:")
    protected native @Pointer long init(UIFontDescriptorAttributes attributes);
    @Method(selector = "fontDescriptorByAddingAttributes:")
    public native UIFontDescriptor newWithAttributes(UIFontDescriptorAttributes attributes);
    @Method(selector = "fontDescriptorWithSymbolicTraits:")
    public native UIFontDescriptor newWithSymbolicTraits(UIFontDescriptorSymbolicTraits symbolicTraits);
    @Method(selector = "fontDescriptorWithSize:")
    public native UIFontDescriptor newWithSize(@MachineSizedFloat double newPointSize);
    @Method(selector = "fontDescriptorWithMatrix:")
    public native UIFontDescriptor newWithMatrix(@ByVal CGAffineTransform matrix);
    @Method(selector = "fontDescriptorWithFace:")
    public native UIFontDescriptor newWithFace(String newFace);
    @Method(selector = "fontDescriptorWithFamily:")
    public native UIFontDescriptor newWithFamily(String newFamily);
    @Method(selector = "fontDescriptorWithFontAttributes:")
    public static native UIFontDescriptor create(UIFontDescriptorAttributes attributes);
    @Method(selector = "fontDescriptorWithName:size:")
    public static native UIFontDescriptor create(String fontName, @MachineSizedFloat double size);
    @Method(selector = "fontDescriptorWithName:matrix:")
    public static native UIFontDescriptor create(String fontName, @ByVal CGAffineTransform matrix);
    @Method(selector = "preferredFontDescriptorWithTextStyle:")
    protected static native UIFontDescriptor getPreferredFontDescriptor(NSString style);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
