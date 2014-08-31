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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTParagraphStyle/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTParagraphStylePtr extends Ptr<CTParagraphStyle, CTParagraphStylePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CTParagraphStyle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTParagraphStyle() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTParagraphStyleGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTParagraphStyleCreate", optional=true)
    public static native CTParagraphStyle create(CTParagraphStyleSetting settings, @MachineSizedUInt long settingCount);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTParagraphStyleCreateCopy", optional=true)
    public native CTParagraphStyle createCopy();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTParagraphStyleGetValueForSpecifier", optional=true)
    public native boolean getValueForSpecifier(CTParagraphStyleSpecifier spec, @MachineSizedUInt long valueBufferSize, VoidPtr valueBuffer);
    /*</methods>*/
}
