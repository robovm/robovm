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

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableCharacterSet/*</name>*/ 
    extends /*<extends>*/CFCharacterSet/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableCharacterSetPtr extends Ptr<CFMutableCharacterSet, CFMutableCharacterSetPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableCharacterSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableCharacterSet() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFCharacterSetCreateMutable", optional=true)
    public static native CFMutableCharacterSet createMutable(CFAllocator alloc);
    @Bridge(symbol="CFCharacterSetCreateMutableCopy", optional=true)
    public static native CFMutableCharacterSet createMutableCopy(CFAllocator alloc, CFCharacterSet theSet);
    @Bridge(symbol="CFCharacterSetAddCharactersInRange", optional=true)
    public native void addCharactersInRange(@ByVal CFRange theRange);
    @Bridge(symbol="CFCharacterSetRemoveCharactersInRange", optional=true)
    public native void removeCharactersInRange(@ByVal CFRange theRange);
    @Bridge(symbol="CFCharacterSetAddCharactersInString", optional=true)
    public native void addCharactersInString(CFString theString);
    @Bridge(symbol="CFCharacterSetRemoveCharactersInString", optional=true)
    public native void removeCharactersInString(CFString theString);
    @Bridge(symbol="CFCharacterSetUnion", optional=true)
    public native void union(CFCharacterSet theOtherSet);
    @Bridge(symbol="CFCharacterSetIntersect", optional=true)
    public native void intersect(CFCharacterSet theOtherSet);
    @Bridge(symbol="CFCharacterSetInvert", optional=true)
    public native void invert();
    /*</methods>*/
}
