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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/MTLTextureUsage/*</name>*/ extends Bits</*<name>*/MTLTextureUsage/*</name>*/> {
    /*<values>*/
    public static final MTLTextureUsage None = new MTLTextureUsage(0L);
    public static final MTLTextureUsage Unknown = new MTLTextureUsage(0L);
    public static final MTLTextureUsage ShaderRead = new MTLTextureUsage(1L);
    public static final MTLTextureUsage ShaderWrite = new MTLTextureUsage(2L);
    public static final MTLTextureUsage RenderTarget = new MTLTextureUsage(4L);
    public static final MTLTextureUsage PixelFormatView = new MTLTextureUsage(16L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/MTLTextureUsage/*</name>*/[] values = _values(/*<name>*/MTLTextureUsage/*</name>*/.class);

    public /*<name>*/MTLTextureUsage/*</name>*/(long value) { super(value); }
    private /*<name>*/MTLTextureUsage/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/MTLTextureUsage/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/MTLTextureUsage/*</name>*/(value, mask);
    }
    protected /*<name>*/MTLTextureUsage/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/MTLTextureUsage/*</name>*/[] values() {
        return values.clone();
    }
}
