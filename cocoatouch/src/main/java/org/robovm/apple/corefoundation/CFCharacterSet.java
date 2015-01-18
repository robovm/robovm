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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFCharacterSet/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFCharacterSetPtr extends Ptr<CFCharacterSet, CFCharacterSetPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFCharacterSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFCharacterSet() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFCharacterSet create(CFRange theRange) {
        return create(null, theRange);
    }
    public static CFCharacterSet create(String theString) {
        return create(null, theString);
    }
    public static CFCharacterSet create(CFData theData) {
        return create(null, theData);
    }
    public CFCharacterSet getInvertedSet() {
        return createInvertedSet(null, this);
    }
    public CFData getBitmapRepresentation() {
        return createBitmapRepresentation(null, this);
    }
    /*<methods>*/
    @Bridge(symbol="CFCharacterSetGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFCharacterSetGetPredefined", optional=true)
    public static native CFCharacterSet getPredefined(CFCharacterSetPredefinedSet theSetIdentifier);
    @Bridge(symbol="CFCharacterSetCreateWithCharactersInRange", optional=true)
    protected static native CFCharacterSet create(CFAllocator alloc, @ByVal CFRange theRange);
    @Bridge(symbol="CFCharacterSetCreateWithCharactersInString", optional=true)
    protected static native CFCharacterSet create(CFAllocator alloc, String theString);
    @Bridge(symbol="CFCharacterSetCreateWithBitmapRepresentation", optional=true)
    protected static native CFCharacterSet create(CFAllocator alloc, CFData theData);
    @Bridge(symbol="CFCharacterSetCreateInvertedSet", optional=true)
    protected static native CFCharacterSet createInvertedSet(CFAllocator alloc, CFCharacterSet theSet);
    @Bridge(symbol="CFCharacterSetIsSupersetOfSet", optional=true)
    public native boolean isSupersetOfSet(CFCharacterSet theOtherset);
    @Bridge(symbol="CFCharacterSetHasMemberInPlane", optional=true)
    public native boolean hasMemberInPlane(@MachineSizedSInt long thePlane);
    @Bridge(symbol="CFCharacterSetIsCharacterMember", optional=true)
    public native boolean isCharacterMember(short theChar);
    @Bridge(symbol="CFCharacterSetIsLongCharacterMember", optional=true)
    public native boolean isLongCharacterMember(int theChar);
    @Bridge(symbol="CFCharacterSetCreateBitmapRepresentation", optional=true)
    protected static native CFData createBitmapRepresentation(CFAllocator alloc, CFCharacterSet theSet);
    /*</methods>*/
}
