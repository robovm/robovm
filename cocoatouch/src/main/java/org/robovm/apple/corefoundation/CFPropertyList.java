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
/*<visibility>*/public/*</visibility>*/ abstract class /*<name>*/CFPropertyList/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFPropertyListPtr extends Ptr<CFPropertyList, CFPropertyListPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPropertyList.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFPropertyList createDeepCopy(CFPropertyList propertyList, CFPropertyListMutabilityOptions mutabilityOption) {
        return createDeepCopy(null, propertyList, mutabilityOption);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CFPropertyList create(CFData data, @MachineSizedUInt long options) throws NSErrorException {
       return create(null, data, options, null);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CFPropertyList create(CFAllocator allocator, CFData data, @MachineSizedUInt long options) throws NSErrorException {
       return create(allocator, data, options, null);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CFPropertyList create(CFReadStream stream, @MachineSizedSInt long streamLength, @MachineSizedUInt long options) throws NSErrorException {
        return create(null, stream, streamLength, options, null);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CFPropertyList create(CFAllocator allocator, CFReadStream stream, @MachineSizedSInt long streamLength, @MachineSizedUInt long options) throws NSErrorException {
        return create(allocator, stream, streamLength, options, null);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CFData asData(CFPropertyListFormat format, @MachineSizedUInt long options) {
        return asData(null, this, format, options);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CFData asData(CFAllocator allocator, CFPropertyListFormat format, @MachineSizedUInt long options) {
        return asData(allocator, this, format, options);
    }
    /*<methods>*/
    @Bridge(symbol="CFPropertyListCreateDeepCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFPropertyList createDeepCopy(CFAllocator allocator, CFPropertyList propertyList, CFPropertyListMutabilityOptions mutabilityOption);
    @Bridge(symbol="CFPropertyListIsValid", optional=true)
    public native boolean isValid(CFPropertyListFormat format);
    /**
     * @since Available in iOS 4.0 and later.
     */
    protected static @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFPropertyList create(CFAllocator allocator, CFData data, @MachineSizedUInt long options, MachineSizedSIntPtr format) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       CFPropertyList result = create(allocator, data, options, format, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListCreateWithData", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFPropertyList create(CFAllocator allocator, CFData data, @MachineSizedUInt long options, MachineSizedSIntPtr format, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    protected static @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFPropertyList create(CFAllocator allocator, CFReadStream stream, @MachineSizedSInt long streamLength, @MachineSizedUInt long options, MachineSizedSIntPtr format) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       CFPropertyList result = create(allocator, stream, streamLength, options, format, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListCreateWithStream", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFPropertyList create(CFAllocator allocator, CFReadStream stream, @MachineSizedSInt long streamLength, @MachineSizedUInt long options, MachineSizedSIntPtr format, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public @MachineSizedSInt long write(CFWriteStream stream, CFPropertyListFormat format, @MachineSizedUInt long options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long result = write(stream, format, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListWrite", optional=true)
    private native @MachineSizedSInt long write(CFWriteStream stream, CFPropertyListFormat format, @MachineSizedUInt long options, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    private static @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFData asData(CFAllocator allocator, CFPropertyList propertyList, CFPropertyListFormat format, @MachineSizedUInt long options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       CFData result = asData(allocator, propertyList, format, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListCreateData", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFData asData(CFAllocator allocator, CFPropertyList propertyList, CFPropertyListFormat format, @MachineSizedUInt long options, NSError.NSErrorPtr error);
    /*</methods>*/
}
