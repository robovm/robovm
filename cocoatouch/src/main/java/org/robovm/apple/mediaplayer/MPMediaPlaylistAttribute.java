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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/MPMediaPlaylistAttribute/*</name>*/ extends Bits</*<name>*/MPMediaPlaylistAttribute/*</name>*/> {
    /*<values>*/
    public static final MPMediaPlaylistAttribute None = new MPMediaPlaylistAttribute(0L);
    public static final MPMediaPlaylistAttribute OnTheGo = new MPMediaPlaylistAttribute(1L);
    public static final MPMediaPlaylistAttribute Smart = new MPMediaPlaylistAttribute(2L);
    public static final MPMediaPlaylistAttribute Genius = new MPMediaPlaylistAttribute(4L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/MPMediaPlaylistAttribute/*</name>*/[] values = _values(/*<name>*/MPMediaPlaylistAttribute/*</name>*/.class);

    public /*<name>*/MPMediaPlaylistAttribute/*</name>*/(long value) { super(value); }
    private /*<name>*/MPMediaPlaylistAttribute/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/MPMediaPlaylistAttribute/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/MPMediaPlaylistAttribute/*</name>*/(value, mask);
    }
    protected /*<name>*/MPMediaPlaylistAttribute/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/MPMediaPlaylistAttribute/*</name>*/[] values() {
        return values.clone();
    }
}
