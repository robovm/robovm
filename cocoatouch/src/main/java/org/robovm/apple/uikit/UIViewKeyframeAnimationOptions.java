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
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/ extends Bits</*<name>*/UIViewKeyframeAnimationOptions/*</name>*/> {
    /*<values>*/
    public static final UIViewKeyframeAnimationOptions LayoutSubviews = new UIViewKeyframeAnimationOptions(1L);
    public static final UIViewKeyframeAnimationOptions AllowUserInteraction = new UIViewKeyframeAnimationOptions(2L);
    public static final UIViewKeyframeAnimationOptions BeginFromCurrentState = new UIViewKeyframeAnimationOptions(4L);
    public static final UIViewKeyframeAnimationOptions Repeat = new UIViewKeyframeAnimationOptions(8L);
    public static final UIViewKeyframeAnimationOptions Autoreverse = new UIViewKeyframeAnimationOptions(16L);
    public static final UIViewKeyframeAnimationOptions OverrideInheritedDuration = new UIViewKeyframeAnimationOptions(32L);
    public static final UIViewKeyframeAnimationOptions OverrideInheritedOptions = new UIViewKeyframeAnimationOptions(512L);
    public static final UIViewKeyframeAnimationOptions CalculationModeLinear = new UIViewKeyframeAnimationOptions(0L);
    public static final UIViewKeyframeAnimationOptions CalculationModeDiscrete = new UIViewKeyframeAnimationOptions(1024L);
    public static final UIViewKeyframeAnimationOptions CalculationModePaced = new UIViewKeyframeAnimationOptions(2048L);
    public static final UIViewKeyframeAnimationOptions CalculationModeCubic = new UIViewKeyframeAnimationOptions(3072L);
    public static final UIViewKeyframeAnimationOptions CalculationModeCubicPaced = new UIViewKeyframeAnimationOptions(4096L);
    /*</values>*/

    private static final /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/[] values = _values(/*<name>*/UIViewKeyframeAnimationOptions/*</name>*/.class);

    public /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIViewKeyframeAnimationOptions/*</name>*/[] values() {
        return values.clone();
    }
}
