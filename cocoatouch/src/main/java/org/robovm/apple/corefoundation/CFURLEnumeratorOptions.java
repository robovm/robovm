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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFURLEnumeratorOptions/*</name>*/ extends Bits</*<name>*/CFURLEnumeratorOptions/*</name>*/> {
    /*<values>*/
    public static final CFURLEnumeratorOptions None = new CFURLEnumeratorOptions(0L);
    public static final CFURLEnumeratorOptions DefaultBehavior = new CFURLEnumeratorOptions(0L);
    public static final CFURLEnumeratorOptions DescendRecursively = new CFURLEnumeratorOptions(1L);
    public static final CFURLEnumeratorOptions SkipInvisibles = new CFURLEnumeratorOptions(2L);
    public static final CFURLEnumeratorOptions GenerateFileReferenceURLs = new CFURLEnumeratorOptions(4L);
    public static final CFURLEnumeratorOptions SkipPackageContents = new CFURLEnumeratorOptions(8L);
    public static final CFURLEnumeratorOptions IncludeDirectoriesPreOrder = new CFURLEnumeratorOptions(16L);
    public static final CFURLEnumeratorOptions IncludeDirectoriesPostOrder = new CFURLEnumeratorOptions(32L);
    /*</values>*/

    private static final /*<name>*/CFURLEnumeratorOptions/*</name>*/[] values = _values(/*<name>*/CFURLEnumeratorOptions/*</name>*/.class);

    public /*<name>*/CFURLEnumeratorOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CFURLEnumeratorOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFURLEnumeratorOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFURLEnumeratorOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CFURLEnumeratorOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFURLEnumeratorOptions/*</name>*/[] values() {
        return values.clone();
    }
}
