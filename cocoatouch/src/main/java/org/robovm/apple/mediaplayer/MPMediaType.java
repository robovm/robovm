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
public final class /*<name>*/MPMediaType/*</name>*/ extends Bits</*<name>*/MPMediaType/*</name>*/> {
    /*<values>*/
    public static final MPMediaType None = new MPMediaType(0L);
    public static final MPMediaType Music = new MPMediaType(1L);
    public static final MPMediaType Podcast = new MPMediaType(2L);
    public static final MPMediaType AudioBook = new MPMediaType(4L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final MPMediaType AudioITunesU = new MPMediaType(8L);
    public static final MPMediaType AnyAudio = new MPMediaType(255L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final MPMediaType Movie = new MPMediaType(256L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final MPMediaType TVShow = new MPMediaType(512L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final MPMediaType VideoPodcast = new MPMediaType(1024L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final MPMediaType MusicVideo = new MPMediaType(2048L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final MPMediaType VideoITunesU = new MPMediaType(4096L);
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final MPMediaType HomeVideo = new MPMediaType(8192L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final MPMediaType AnyVideo = new MPMediaType(65280L);
    public static final MPMediaType Any = new MPMediaType(Bro.IS_32BIT ? 0xffffffffL : 0xffffffffffffffffL);
    /*</values>*/

    private static final /*<name>*/MPMediaType/*</name>*/[] values = _values(/*<name>*/MPMediaType/*</name>*/.class);

    public /*<name>*/MPMediaType/*</name>*/(long value) { super(value); }
    private /*<name>*/MPMediaType/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/MPMediaType/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/MPMediaType/*</name>*/(value, mask);
    }
    protected /*<name>*/MPMediaType/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/MPMediaType/*</name>*/[] values() {
        return values.clone();
    }
}
