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
package org.robovm.apple.security;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecAccessControl/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SecAccessControlPtr extends Ptr<SecAccessControl, SecAccessControlPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecAccessControl.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SecAccessControl() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static SecAccessControl create(SecAttrAccessible protection, SecAccessControlCreateFlags flags) throws NSErrorException {
        return create(null, protection, flags);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="SecAccessControlGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static SecAccessControl create(CFAllocator allocator, SecAttrAccessible protection, SecAccessControlCreateFlags flags) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       SecAccessControl result = create(allocator, protection, flags, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="SecAccessControlCreateWithFlags", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) SecAccessControl create(CFAllocator allocator, SecAttrAccessible protection, SecAccessControlCreateFlags flags, NSError.NSErrorPtr error);
    /*</methods>*/
}
