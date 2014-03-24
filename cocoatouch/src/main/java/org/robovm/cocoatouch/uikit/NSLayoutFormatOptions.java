/*
 * Copyright (C) 2012 Trillian Mobile AB
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
package org.robovm.cocoatouch.uikit;

import org.robovm.rt.bro.Bits;

public final class NSLayoutFormatOptions extends Bits<NSLayoutFormatOptions> {
    public static final NSLayoutFormatOptions AlignAllLeft = new NSLayoutFormatOptions(NSLayoutAttribute.Left.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllRight = new NSLayoutFormatOptions(NSLayoutAttribute.Right.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllTop = new NSLayoutFormatOptions(NSLayoutAttribute.Top.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllBottom = new NSLayoutFormatOptions(NSLayoutAttribute.Bottom.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllLeading = new NSLayoutFormatOptions(NSLayoutAttribute.Leading.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllTrailing = new NSLayoutFormatOptions(NSLayoutAttribute.Trailing.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllCenterX = new NSLayoutFormatOptions(NSLayoutAttribute.CenterX.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllCenterY = new NSLayoutFormatOptions(NSLayoutAttribute.CenterY.value(), 0xff);
    public static final NSLayoutFormatOptions AlignAllBaseline = new NSLayoutFormatOptions(NSLayoutAttribute.Baseline.value(), 0xff);
//    public static final NSLayoutFormatOptions AlignmentMask = new NSLayoutFormatOptions(0xFF);
    public static final NSLayoutFormatOptions DirectionLeadingToTrailing = new NSLayoutFormatOptions(0 << 8, 0x3 << 8);
    public static final NSLayoutFormatOptions DirectionLeftToRight = new NSLayoutFormatOptions(1 << 8, 0x3 << 8);
    public static final NSLayoutFormatOptions DirectionRightToLeft = new NSLayoutFormatOptions(2 << 8, 0x3 << 8);
//    public static final NSLayoutFormatOptions DirectionMask = new NSLayoutFormatOptions(0x3 << 8);

    private static final NSLayoutFormatOptions[] values = _values(NSLayoutFormatOptions.class);
    
    private NSLayoutFormatOptions(long value) { super(value); }
    private NSLayoutFormatOptions(long value, long mask) { super(value, mask); }
    protected NSLayoutFormatOptions wrap(long value, long mask) {
        return new NSLayoutFormatOptions(value, mask);
    }
    protected NSLayoutFormatOptions[] _values() {
        return values;
    }
}
