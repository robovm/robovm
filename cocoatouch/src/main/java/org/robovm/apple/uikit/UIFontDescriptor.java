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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIFontDescriptorPtr extends Ptr<UIFontDescriptor, UIFontDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIFontDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIFontDescriptor() {}
    protected UIFontDescriptor(SkipInit skipInit) { super(skipInit); }
    public UIFontDescriptor(NSDictionary<?, ?> attributes) { super((SkipInit) null); initObject(initWithFontAttributes$(attributes)); }
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
    /*<methods>*/
    @Method(selector = "objectForKey:")
    public native NSObject objectForKey$(String anAttribute);
    @Method(selector = "fontAttributes")
    public native NSDictionary<?, ?> fontAttributes();
    @Method(selector = "matchingFontDescriptorsWithMandatoryKeys:")
    public native NSArray<?> matchingFontDescriptorsWithMandatoryKeys$(NSSet<?> mandatoryKeys);
    @Method(selector = "initWithFontAttributes:")
    protected native @Pointer long initWithFontAttributes$(NSDictionary<?, ?> attributes);
    @Method(selector = "fontDescriptorByAddingAttributes:")
    public native UIFontDescriptor fontDescriptorByAddingAttributes$(NSDictionary<?, ?> attributes);
    @Method(selector = "fontDescriptorWithSymbolicTraits:")
    public native UIFontDescriptor fontDescriptorWithSymbolicTraits$(UIFontDescriptorSymbolicTraits symbolicTraits);
    @Method(selector = "fontDescriptorWithSize:")
    public native UIFontDescriptor fontDescriptorWithSize$(@MachineSizedFloat double newPointSize);
    @Method(selector = "fontDescriptorWithMatrix:")
    public native UIFontDescriptor fontDescriptorWithMatrix$(@ByVal CGAffineTransform matrix);
    @Method(selector = "fontDescriptorWithFace:")
    public native UIFontDescriptor fontDescriptorWithFace$(String newFace);
    @Method(selector = "fontDescriptorWithFamily:")
    public native UIFontDescriptor fontDescriptorWithFamily$(String newFamily);
    @Method(selector = "fontDescriptorWithFontAttributes:")
    public static native UIFontDescriptor fontDescriptorWithFontAttributes$(NSDictionary<?, ?> attributes);
    @Method(selector = "fontDescriptorWithName:size:")
    public static native UIFontDescriptor fontDescriptorWithName$size$(String fontName, @MachineSizedFloat double size);
    @Method(selector = "fontDescriptorWithName:matrix:")
    public static native UIFontDescriptor fontDescriptorWithName$matrix$(String fontName, @ByVal CGAffineTransform matrix);
    @Method(selector = "preferredFontDescriptorWithTextStyle:")
    public static native UIFontDescriptor preferredFontDescriptorWithTextStyle$(String style);
    /*</methods>*/
}
