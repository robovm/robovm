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
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static CTParagraphStyle create(CTParagraphStyleSettings settings) {
        CTParagraphStyleSetting setting = null;
        int count = 0;
        if (settings != null) {
            setting = Struct.allocate(CTParagraphStyleSetting.class);
            count = settings.getSettings().size();
            setting.update(settings.getSettings().values().toArray(new CTParagraphStyleSetting[count]));
        }
        return create(setting, count);
    }
    

    public CTTextAlignment getAlignment() {
        BytePtr ptr = new BytePtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.Alignment, BytePtr.sizeOf(), ptr.as(VoidPtr.class));
        return CTTextAlignment.valueOf(ptr.get());
    }
    public CTLineBreakMode getLineBreakMode() {
        BytePtr ptr = new BytePtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.LineBreakMode, BytePtr.sizeOf(), ptr.as(VoidPtr.class));
        return CTLineBreakMode.valueOf(ptr.get());
    }
    public CTWritingDirection getBaseWritingDirection() {
        BytePtr ptr = new BytePtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.BaseWritingDirection, BytePtr.sizeOf(), ptr.as(VoidPtr.class));
        return CTWritingDirection.valueOf(ptr.get());
    }
    public double getFirstLineHeadIndent() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.FirstLineHeadIndent, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getHeadIndent() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.HeadIndent, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getTailIndent() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.TailIndent, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getDefaultTabInterval() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.DefaultTabInterval, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getLineHeightMultiple() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.LineHeightMultiple, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getMaximumLineHeight() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.MaximumLineHeight, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getMinimumLineHeight() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.MinimumLineHeight, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getLineSpacing() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.LineSpacing, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getParagraphSpacing() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.ParagraphSpacing, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getParagraphSpacingBefore() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.ParagraphSpacingBefore, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getMaximumLineSpacing() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.MaximumLineSpacing, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getMinimumLineSpacing() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.MinimumLineSpacing, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public double getLineSpacingAdjustment() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.LineSpacingAdjustment, MachineSizedFloatPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get();
    }
    public List<CTTextTab> getTabStops() {
        CFArray.CFArrayPtr ptr = new CFArray.CFArrayPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.TabStops, CFArray.CFArrayPtr.sizeOf(), ptr.as(VoidPtr.class));
        return ptr.get().toList(CTTextTab.class);
    }
    public CTLineBoundsOptions getLineBoundsOptions() {
        LongPtr ptr = new LongPtr();
        getValueForSpecifier(CTParagraphStyleSpecifier.LineBoundsOptions, LongPtr.sizeOf(), ptr.as(VoidPtr.class));
        return new CTLineBoundsOptions(ptr.get());
    }
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
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTParagraphStyle create(CTParagraphStyleSetting settings, @MachineSizedUInt long settingCount);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTParagraphStyleCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTParagraphStyle createCopy(CTParagraphStyle paragraphStyle);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTParagraphStyleGetValueForSpecifier", optional=true)
    protected native boolean getValueForSpecifier(CTParagraphStyleSpecifier spec, @MachineSizedUInt long valueBufferSize, VoidPtr valueBuffer);
    /*</methods>*/
}
