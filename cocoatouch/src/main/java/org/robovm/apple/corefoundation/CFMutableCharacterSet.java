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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableCharacterSet/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableCharacterSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFCharacterSetCreateMutable")
    public static native CFMutableCharacterSet createMutable(CFAllocator alloc);
    @Bridge(symbol="CFCharacterSetCreateMutableCopy")
    public static native CFMutableCharacterSet createMutableCopy(CFAllocator alloc, CFCharacterSet theSet);
    @Bridge(symbol="CFCharacterSetAddCharactersInRange")
    public static native void addCharactersInRange(CFCharacterSet theSet, @ByVal CFRange theRange);
    @Bridge(symbol="CFCharacterSetRemoveCharactersInRange")
    public static native void removeCharactersInRange(CFCharacterSet theSet, @ByVal CFRange theRange);
    @Bridge(symbol="CFCharacterSetAddCharactersInString")
    public static native void addCharactersInString(CFCharacterSet theSet, CFString theString);
    @Bridge(symbol="CFCharacterSetRemoveCharactersInString")
    public static native void removeCharactersInString(CFCharacterSet theSet, CFString theString);
    @Bridge(symbol="CFCharacterSetUnion")
    public static native void union(CFCharacterSet theSet, CFCharacterSet theOtherSet);
    @Bridge(symbol="CFCharacterSetIntersect")
    public static native void intersect(CFCharacterSet theSet, CFCharacterSet theOtherSet);
    @Bridge(symbol="CFCharacterSetInvert")
    public static native void invert(CFCharacterSet theSet);
    /*</methods>*/
}
