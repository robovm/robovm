/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTRubyAnnotation/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTRubyAnnotationPtr extends Ptr<CTRubyAnnotation, CTRubyAnnotationPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CTRubyAnnotation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTRubyAnnotation() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CTRubyAnnotation create(CTRubyAlignment alignment, CTRubyOverhang overhang, @MachineSizedFloat double sizeFactor, String...text) {
        int n = CTRubyPosition.values().length;
        if (text.length != n) throw new IllegalArgumentException(String.format("array 'text' needs to have exactly %d elements", n));
        
        CFString[] strings = new CFString[n];
        for (int i = 0; i < n; i++) {
            strings[i] = new CFString(text[i]);
        }
        CFString.CFStringPtr ptr = new CFString.CFStringPtr();
        ptr.set(strings);
        return create(alignment, overhang, sizeFactor, ptr);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CTRubyAnnotationGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CTRubyAnnotationCreate", optional=true)
    private static native CTRubyAnnotation create(CTRubyAlignment alignment, CTRubyOverhang overhang, @MachineSizedFloat double sizeFactor, CFString.CFStringPtr text);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CTRubyAnnotationCreateCopy", optional=true)
    public static native CTRubyAnnotation createCopy(CTRubyAnnotation rubyAnnotation);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CTRubyAnnotationGetAlignment", optional=true)
    public native CTRubyAlignment getAlignment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CTRubyAnnotationGetOverhang", optional=true)
    public native CTRubyOverhang getOverhang();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CTRubyAnnotationGetSizeFactor", optional=true)
    public native @MachineSizedFloat double getSizeFactor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CTRubyAnnotationGetTextForPosition", optional=true)
    public native String getTextForPosition(CTRubyPosition position);
    /*</methods>*/
}
