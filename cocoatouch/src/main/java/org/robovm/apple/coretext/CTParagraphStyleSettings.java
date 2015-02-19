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
import org.robovm.apple.corefoundation.CFArray.CFArrayPtr;
import org.robovm.apple.coregraphics.*;

public class CTParagraphStyleSettings {
    private Map<CTParagraphStyleSpecifier, CTParagraphStyleSetting> settings = new HashMap<>();
    
    public void setAlignment(CTTextAlignment alignment) {
        BytePtr ptr = new BytePtr((byte)alignment.value());
        setValueForSpecifier(CTParagraphStyleSpecifier.Alignment, ptr.as(VoidPtr.class));
    }
    public void setLineBreakMode(CTLineBreakMode lineBreakMode) {
        BytePtr ptr = new BytePtr((byte)lineBreakMode.value());
        setValueForSpecifier(CTParagraphStyleSpecifier.LineBreakMode, ptr.as(VoidPtr.class));
    }
    public void setBaseWritingDirection(CTWritingDirection writingDirection) {
        BytePtr ptr = new BytePtr((byte)writingDirection.value());
        setValueForSpecifier(CTParagraphStyleSpecifier.BaseWritingDirection, ptr.as(VoidPtr.class));
    }
    public void setFirstLineHeadIndent(double lineHeadIndent) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(lineHeadIndent);
        setValueForSpecifier(CTParagraphStyleSpecifier.FirstLineHeadIndent, ptr.as(VoidPtr.class));
    }
    public void setHeadIndent(double headIndent) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(headIndent);
        setValueForSpecifier(CTParagraphStyleSpecifier.HeadIndent, ptr.as(VoidPtr.class));
    }
    public void setTailIndent(double tailIndent) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(tailIndent);
        setValueForSpecifier(CTParagraphStyleSpecifier.TailIndent, ptr.as(VoidPtr.class));
    }
    public void setDefaultTabInterval(double defaultTabInterval) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(defaultTabInterval);
        setValueForSpecifier(CTParagraphStyleSpecifier.DefaultTabInterval, ptr.as(VoidPtr.class));
    }
    public void setLineHeightMultiple(double lineHeightMultiple) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(lineHeightMultiple);
        setValueForSpecifier(CTParagraphStyleSpecifier.LineHeightMultiple, ptr.as(VoidPtr.class));
    }
    public void setMaximumLineHeight(double maximumLineHeight) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(maximumLineHeight);
        setValueForSpecifier(CTParagraphStyleSpecifier.MaximumLineHeight, ptr.as(VoidPtr.class));
    }
    public void setMinimumLineHeight(double minimumLineHeight) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(minimumLineHeight);
        setValueForSpecifier(CTParagraphStyleSpecifier.MinimumLineHeight, ptr.as(VoidPtr.class));
    }
    public void setLineSpacing(double lineSpacing) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(lineSpacing);
        setValueForSpecifier(CTParagraphStyleSpecifier.LineSpacing, ptr.as(VoidPtr.class));
    }
    public void setParagraphSpacing(double paragraphSpacing) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(paragraphSpacing);
        setValueForSpecifier(CTParagraphStyleSpecifier.ParagraphSpacing, ptr.as(VoidPtr.class));
    }
    public void setParagraphSpacingBefore(double paragraphSpacingBefore) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(paragraphSpacingBefore);
        setValueForSpecifier(CTParagraphStyleSpecifier.ParagraphSpacingBefore, ptr.as(VoidPtr.class));
    }
    public void setMaximumLineSpacing(double maximumLineSpacing) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(maximumLineSpacing);
        setValueForSpecifier(CTParagraphStyleSpecifier.MaximumLineSpacing, ptr.as(VoidPtr.class));
    }
    public void setMinimumLineSpacing(double minimumLineSpacing) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(minimumLineSpacing);
        setValueForSpecifier(CTParagraphStyleSpecifier.MinimumLineSpacing, ptr.as(VoidPtr.class));
    }
    public void setLineSpacingAdjustment(double lineSpacingAdjustment) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        ptr.set(lineSpacingAdjustment);
        setValueForSpecifier(CTParagraphStyleSpecifier.LineSpacingAdjustment, ptr.as(VoidPtr.class));
    }
    public void setTabStops(List<CTTextTab> tabStops) {
        CFArray array = CFArray.create(tabStops);
        CFArray.CFArrayPtr ptr = new CFArray.CFArrayPtr();
        ptr.set(array);
        setValueForSpecifier(CTParagraphStyleSpecifier.TabStops, ptr.as(VoidPtr.class));
    }
    public void setLineBoundsOptions(CTLineBoundsOptions lineBoundsOptions) {
        LongPtr ptr = new LongPtr(lineBoundsOptions.value());
        setValueForSpecifier(CTParagraphStyleSpecifier.LineBoundsOptions, ptr.as(VoidPtr.class));
    }
    
    public void setValueForSpecifier(CTParagraphStyleSpecifier spec, VoidPtr value) {
        if (spec == null) throw new NullPointerException("spec");
        CTParagraphStyleSetting setting = settings.get(spec);
        if (setting == null) {
            setting = createSettingForSpecifier(spec);
            settings.put(spec, setting);
        }
        setting.setValue(value);
    }
    
    private CTParagraphStyleSetting createSettingForSpecifier(CTParagraphStyleSpecifier spec) {
        CTParagraphStyleSetting setting = new CTParagraphStyleSetting();
        setting.setSpec(spec);
        
        int size = 0;
        
        switch (spec) {
        case Alignment:
        case LineBreakMode:
        case BaseWritingDirection:
            size = BytePtr.sizeOf();
            break;
        case FirstLineHeadIndent:
        case HeadIndent:
        case TailIndent:
        case DefaultTabInterval:
        case LineHeightMultiple:
        case MaximumLineHeight:
        case MinimumLineHeight:
        case LineSpacing:
        case ParagraphSpacing:
        case ParagraphSpacingBefore:
        case MaximumLineSpacing:
        case MinimumLineSpacing:
        case LineSpacingAdjustment:
            size = MachineSizedFloatPtr.sizeOf();
            break;
        case TabStops:
            size = CFArrayPtr.sizeOf();
            break;
        case LineBoundsOptions:
            size = LongPtr.sizeOf();
            break;
        default:
            break;
        }
        setting.setValueSize(size);

        return setting;
    }
    
    public Map<CTParagraphStyleSpecifier, CTParagraphStyleSetting> getSettings () {
        return settings;
    }
}
