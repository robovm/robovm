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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFramesetter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTFramesetterPtr extends Ptr<CTFramesetter, CTFramesetterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CTFramesetter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTFramesetter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFramesetterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFramesetterCreateWithAttributedString", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFramesetter create(NSAttributedString string);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFramesetterCreateFrame", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFrame createFrame(@ByVal CFRange stringRange, CGPath path, CTFrameAttributes frameAttributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFramesetterGetTypesetter", optional=true)
    public native CTTypesetter getTypesetter();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFramesetterSuggestFrameSizeWithConstraints", optional=true)
    public native @ByVal CGSize suggestFrameSize(@ByVal CFRange stringRange, CTFrameAttributes frameAttributes, @ByVal CGSize constraints, CFRange fitRange);
    /*</methods>*/
}
