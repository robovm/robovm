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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UICollectionViewScrollPosition/*</name>*/ extends Bits</*<name>*/UICollectionViewScrollPosition/*</name>*/> {
    /*<values>*/
    public static final UICollectionViewScrollPosition None = new UICollectionViewScrollPosition(0L);
    public static final UICollectionViewScrollPosition Top = new UICollectionViewScrollPosition(1L);
    public static final UICollectionViewScrollPosition CenteredVertically = new UICollectionViewScrollPosition(2L);
    public static final UICollectionViewScrollPosition Bottom = new UICollectionViewScrollPosition(4L);
    public static final UICollectionViewScrollPosition Left = new UICollectionViewScrollPosition(8L);
    public static final UICollectionViewScrollPosition CenteredHorizontally = new UICollectionViewScrollPosition(16L);
    public static final UICollectionViewScrollPosition Right = new UICollectionViewScrollPosition(32L);
    /*</values>*/

    private static final /*<name>*/UICollectionViewScrollPosition/*</name>*/[] values = _values(/*<name>*/UICollectionViewScrollPosition/*</name>*/.class);

    public /*<name>*/UICollectionViewScrollPosition/*</name>*/(long value) { super(value); }
    private /*<name>*/UICollectionViewScrollPosition/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UICollectionViewScrollPosition/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UICollectionViewScrollPosition/*</name>*/(value, mask);
    }
    protected /*<name>*/UICollectionViewScrollPosition/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UICollectionViewScrollPosition/*</name>*/[] values() {
        return values.clone();
    }
}
