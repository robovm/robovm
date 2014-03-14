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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/
/*</annotations>*/
public final class /*<name>*/CAEdgeAntialiasingMask/*</name>*/ extends Bits</*<name>*/CAEdgeAntialiasingMask/*</name>*/> {
    /*<values>*/
    public static final CAEdgeAntialiasingMask LeftEdge = new CAEdgeAntialiasingMask(1L);
    public static final CAEdgeAntialiasingMask RightEdge = new CAEdgeAntialiasingMask(2L);
    public static final CAEdgeAntialiasingMask BottomEdge = new CAEdgeAntialiasingMask(4L);
    public static final CAEdgeAntialiasingMask TopEdge = new CAEdgeAntialiasingMask(8L);
    /*</values>*/

    private static final /*<name>*/CAEdgeAntialiasingMask/*</name>*/[] values = _values(/*<name>*/CAEdgeAntialiasingMask/*</name>*/.class);

    public /*<name>*/CAEdgeAntialiasingMask/*</name>*/(long value) { super(value); }
    private /*<name>*/CAEdgeAntialiasingMask/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CAEdgeAntialiasingMask/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CAEdgeAntialiasingMask/*</name>*/(value, mask);
    }
    protected /*<name>*/CAEdgeAntialiasingMask/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CAEdgeAntialiasingMask/*</name>*/[] values() {
        return values.clone();
    }
}
