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
package org.robovm.apple.coretext;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontCollection/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTFontCollectionPtr extends Ptr<CTFontCollection, CTFontCollectionPtr> {}/*</ptr>*/
    
    public interface SortCallback {
        CFComparisonResult sort(CTFontDescriptor first, CTFontDescriptor second);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static Map<Long, SortCallback> sortCallbacks = new HashMap<>();
    private static final java.lang.reflect.Method cbSort;
    
    static {
        try {
            cbSort = CTFontCollection.class.getDeclaredMethod("cbSort", CTFontDescriptor.class, CTFontDescriptor.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CTFontCollection.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTFontCollection() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static CFComparisonResult cbSort(CTFontDescriptor first, CTFontDescriptor second, @Pointer long refcon) {
        SortCallback callback = null;
        synchronized (sortCallbacks) {
            callback = sortCallbacks.get(refcon);
        }
        return callback.sort(first, second);
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<CTFontDescriptor> getMatchingFontDescriptorsSorted(SortCallback sortCallback) {
        long refconId = CTFontCollection.refconId.getAndIncrement();
        synchronized (sortCallbacks) {
            sortCallbacks.put(refconId, sortCallback);
        }
        return getMatchingFontDescriptorsSorted(new FunctionPtr(cbSort), refconId);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateFromAvailableFonts", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontCollection createFromAvailableFonts(CTFontCollectionOptions options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateWithFontDescriptors", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontCollection create(@org.robovm.rt.bro.annotation.Marshaler(CTFontDescriptor.AsListMarshaler.class) List<CTFontDescriptor> queryDescriptors, CTFontCollectionOptions options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateCopyWithFontDescriptors", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CTFontCollection createCopy(@org.robovm.rt.bro.annotation.Marshaler(CTFontDescriptor.AsListMarshaler.class) List<CTFontDescriptor> queryDescriptors, CTFontCollectionOptions options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateMatchingFontDescriptors", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CTFontDescriptor.AsListMarshaler.class) List<CTFontDescriptor> getMatchingFontDescriptors();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateMatchingFontDescriptorsSortedWithCallback", optional=true)
    protected native @org.robovm.rt.bro.annotation.Marshaler(CTFontDescriptor.AsListMarshaler.class) List<CTFontDescriptor> getMatchingFontDescriptorsSorted(FunctionPtr sortCallback, @Pointer long refCon);
    /*</methods>*/
}
