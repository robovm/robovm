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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFFileSecurity/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFFileSecurityPtr extends Ptr<CFFileSecurity, CFFileSecurityPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFFileSecurity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFFileSecurity() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityCreate", optional=true)
    public static native CFFileSecurity create(CFAllocator allocator);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityCreateCopy", optional=true)
    public static native CFFileSecurity createCopy(CFAllocator allocator, CFFileSecurity fileSec);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityCopyOwnerUUID", optional=true)
    public native boolean copyOwnerUUID(CFUUID.CFUUIDPtr ownerUUID);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecuritySetOwnerUUID", optional=true)
    public native boolean setOwnerUUID(CFUUID ownerUUID);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityCopyGroupUUID", optional=true)
    public native boolean copyGroupUUID(CFUUID.CFUUIDPtr groupUUID);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecuritySetGroupUUID", optional=true)
    public native boolean setGroupUUID(CFUUID groupUUID);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityGetOwner", optional=true)
    public native boolean getOwner(IntPtr owner);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecuritySetOwner", optional=true)
    public native boolean setOwner(int owner);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityGetGroup", optional=true)
    public native boolean getGroup(IntPtr group);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecuritySetGroup", optional=true)
    public native boolean setGroup(int group);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecurityGetMode", optional=true)
    public native boolean getMode(ShortPtr mode);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFFileSecuritySetMode", optional=true)
    public native boolean setMode(short mode);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CFFileSecurityClearProperties", optional=true)
    public native boolean clearProperties(CFFileSecurityClearOptions clearPropertyMask);
    /*</methods>*/
}
