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

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextContainer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, NSTextLayoutOrientationProvider/*</implements>*/ {

    /*<ptr>*/public static class NSTextContainerPtr extends Ptr<NSTextContainer, NSTextContainerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSTextContainer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSTextContainer() {}
    protected NSTextContainer(SkipInit skipInit) { super(skipInit); }
    public NSTextContainer(@ByVal CGSize size) { super((SkipInit) null); initObject(initWithSize$(size)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "layoutManager")
    public native NSLayoutManager getLayoutManager();
    @Property(selector = "setLayoutManager:", strongRef = true)
    public native void setLayoutManager(NSLayoutManager v);
    @Property(selector = "size")
    public native @ByVal CGSize getSize();
    @Property(selector = "setSize:")
    public native void setSize(@ByVal CGSize v);
    @Property(selector = "exclusionPaths")
    public native NSArray<UIBezierPath> getExclusionPaths();
    @Property(selector = "setExclusionPaths:")
    public native void setExclusionPaths(NSArray<UIBezierPath> v);
    @Property(selector = "lineBreakMode")
    public native NSLineBreakMode getLineBreakMode();
    @Property(selector = "setLineBreakMode:")
    public native void setLineBreakMode(NSLineBreakMode v);
    @Property(selector = "lineFragmentPadding")
    public native @MachineSizedFloat double getLineFragmentPadding();
    @Property(selector = "setLineFragmentPadding:")
    public native void setLineFragmentPadding(@MachineSizedFloat double v);
    @Property(selector = "maximumNumberOfLines")
    public native @MachineSizedUInt long getMaximumNumberOfLines();
    @Property(selector = "setMaximumNumberOfLines:")
    public native void setMaximumNumberOfLines(@MachineSizedUInt long v);
    @Property(selector = "widthTracksTextView")
    public native boolean isWidthTracksTextView();
    @Property(selector = "setWidthTracksTextView:")
    public native void setWidthTracksTextView(boolean v);
    @Property(selector = "heightTracksTextView")
    public native boolean isHeightTracksTextView();
    @Property(selector = "setHeightTracksTextView:")
    public native void setHeightTracksTextView(boolean v);
    @Property(selector = "layoutOrientation")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native NSTextLayoutOrientation getLayoutOrientation();
    @Property(selector = "setLayoutOrientation:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setLayoutOrientation(NSTextLayoutOrientation v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSize:")
    protected native @Pointer long initWithSize$(@ByVal CGSize size);
    @Method(selector = "lineFragmentRectForProposedRect:atIndex:writingDirection:remainingRect:")
    public native @ByVal CGRect getLineFragmentRect(@ByVal CGRect proposedRect, @MachineSizedUInt long characterIndex, NSWritingDirection baseWritingDirection, CGRect remainingRect);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
