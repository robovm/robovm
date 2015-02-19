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
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CTFontUIFontType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    UIFontNone(-1L),
    UIFontUser(0L),
    UIFontUserFixedPitch(1L),
    UIFontSystem(2L),
    UIFontEmphasizedSystem(3L),
    UIFontSmallSystem(4L),
    UIFontSmallEmphasizedSystem(5L),
    UIFontMiniSystem(6L),
    UIFontMiniEmphasizedSystem(7L),
    UIFontViews(8L),
    UIFontApplication(9L),
    UIFontLabel(10L),
    UIFontMenuTitle(11L),
    UIFontMenuItem(12L),
    UIFontMenuItemMark(13L),
    UIFontMenuItemCmdKey(14L),
    UIFontWindowTitle(15L),
    UIFontPushButton(16L),
    UIFontUtilityWindowTitle(17L),
    UIFontAlertHeader(18L),
    UIFontSystemDetail(19L),
    UIFontEmphasizedSystemDetail(20L),
    UIFontToolbar(21L),
    UIFontSmallToolbar(22L),
    UIFontMessage(23L),
    UIFontPalette(24L),
    UIFontToolTip(25L),
    UIFontControlContent(26L),
    NoFontType(-1L),
    UserFontType(0L),
    UserFixedPitchFontType(1L),
    SystemFontType(2L),
    EmphasizedSystemFontType(3L),
    SmallSystemFontType(4L),
    SmallEmphasizedSystemFontType(5L),
    MiniSystemFontType(6L),
    MiniEmphasizedSystemFontType(7L),
    ViewsFontType(8L),
    ApplicationFontType(9L),
    LabelFontType(10L),
    MenuTitleFontType(11L),
    MenuItemFontType(12L),
    MenuItemMarkFontType(13L),
    MenuItemCmdKeyFontType(14L),
    WindowTitleFontType(15L),
    PushButtonFontType(16L),
    UtilityWindowTitleFontType(17L),
    AlertHeaderFontType(18L),
    SystemDetailFontType(19L),
    EmphasizedSystemDetailFontType(20L),
    ToolbarFontType(21L),
    SmallToolbarFontType(22L),
    MessageFontType(23L),
    PaletteFontType(24L),
    ToolTipFontType(25L),
    ControlContentFontType(26L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CTFontUIFontType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CTFontUIFontType/*</name>*/ valueOf(long n) {
        for (/*<name>*/CTFontUIFontType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CTFontUIFontType/*</name>*/.class.getName());
    }
}
