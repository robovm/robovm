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
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIViewAnimationOptions/*</name>*/ extends Bits</*<name>*/UIViewAnimationOptions/*</name>*/> {
    /*<values>*/
    public static final UIViewAnimationOptions None = new UIViewAnimationOptions(0L);
    public static final UIViewAnimationOptions LayoutSubviews = new UIViewAnimationOptions(1L);
    public static final UIViewAnimationOptions AllowUserInteraction = new UIViewAnimationOptions(2L);
    public static final UIViewAnimationOptions BeginFromCurrentState = new UIViewAnimationOptions(4L);
    public static final UIViewAnimationOptions Repeat = new UIViewAnimationOptions(8L);
    public static final UIViewAnimationOptions Autoreverse = new UIViewAnimationOptions(16L);
    public static final UIViewAnimationOptions OverrideInheritedDuration = new UIViewAnimationOptions(32L);
    public static final UIViewAnimationOptions OverrideInheritedCurve = new UIViewAnimationOptions(64L);
    public static final UIViewAnimationOptions AllowAnimatedContent = new UIViewAnimationOptions(128L);
    public static final UIViewAnimationOptions ShowHideTransitionViews = new UIViewAnimationOptions(256L);
    public static final UIViewAnimationOptions OverrideInheritedOptions = new UIViewAnimationOptions(512L);
    public static final UIViewAnimationOptions CurveEaseInOut = new UIViewAnimationOptions(0L);
    public static final UIViewAnimationOptions CurveEaseIn = new UIViewAnimationOptions(65536L);
    public static final UIViewAnimationOptions CurveEaseOut = new UIViewAnimationOptions(131072L);
    public static final UIViewAnimationOptions CurveLinear = new UIViewAnimationOptions(196608L);
    public static final UIViewAnimationOptions TransitionNone = new UIViewAnimationOptions(0L);
    public static final UIViewAnimationOptions TransitionFlipFromLeft = new UIViewAnimationOptions(1048576L);
    public static final UIViewAnimationOptions TransitionFlipFromRight = new UIViewAnimationOptions(2097152L);
    public static final UIViewAnimationOptions TransitionCurlUp = new UIViewAnimationOptions(3145728L);
    public static final UIViewAnimationOptions TransitionCurlDown = new UIViewAnimationOptions(4194304L);
    public static final UIViewAnimationOptions TransitionCrossDissolve = new UIViewAnimationOptions(5242880L);
    public static final UIViewAnimationOptions TransitionFlipFromTop = new UIViewAnimationOptions(6291456L);
    public static final UIViewAnimationOptions TransitionFlipFromBottom = new UIViewAnimationOptions(7340032L);
    /*</values>*/

    private static final /*<name>*/UIViewAnimationOptions/*</name>*/[] values = _values(/*<name>*/UIViewAnimationOptions/*</name>*/.class);

    public /*<name>*/UIViewAnimationOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/UIViewAnimationOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIViewAnimationOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIViewAnimationOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/UIViewAnimationOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIViewAnimationOptions/*</name>*/[] values() {
        return values.clone();
    }
}
