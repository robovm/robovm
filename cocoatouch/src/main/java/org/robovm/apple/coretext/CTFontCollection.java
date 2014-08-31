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
    public static CTFontCollection createFromAvailableFonts(CTFontCollectionOptions options) {
        return createFromAvailableFonts(options.getDictionary());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static CTFontCollection create(List<CTFontDescriptor> queryDescriptors, CTFontCollectionOptions options) {
        return create(CFArray.create(queryDescriptors), options.getDictionary());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontCollection createCopy(List<CTFontDescriptor> queryDescriptors, CTFontCollectionOptions options) {
        return createCopy(CFArray.create(queryDescriptors), options.getDictionary());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<CTFontDescriptor> getMatchingFontDescriptors() {
        return getMatchingFontDescriptors0().toList(CTFontDescriptor.class);
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public List<CTFontDescriptor> getMatchingFontDescriptorsSorted(SortCallback sortCallback) {
        long refconId = CTFontCollection.refconId.getAndIncrement();
        synchronized (sortCallbacks) {
            sortCallbacks.put(refconId, sortCallback);
        }
        return getMatchingFontDescriptorsSorted(new FunctionPtr(cbSort), refconId).toList(CTFontDescriptor.class);
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
    protected static native CTFontCollection createFromAvailableFonts(CFDictionary options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateWithFontDescriptors", optional=true)
    protected static native CTFontCollection create(CFArray queryDescriptors, CFDictionary options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateCopyWithFontDescriptors", optional=true)
    protected native CTFontCollection createCopy(CFArray queryDescriptors, CFDictionary options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateMatchingFontDescriptors", optional=true)
    protected native CFArray getMatchingFontDescriptors0();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCollectionCreateMatchingFontDescriptorsSortedWithCallback", optional=true)
    protected native CFArray getMatchingFontDescriptorsSorted(FunctionPtr sortCallback, @Pointer long refCon);
    /*</methods>*/
}
