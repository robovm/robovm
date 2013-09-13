/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.foundation;

/*<imports>*/
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSObject_Class/Reference/Reference.html">NSObject Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@Marshalers({
    @Marshaler(type = String.class, value = NSString.AsStringMarshaler.class)    
})
@NativeClass public class /*<name>*/ NSObject /*</name>*/ 
    extends /*<extends>*/ ObjCObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSObject /*</name>*/.class);
    }

    protected static class SkipInit {}
    
    public NSObject() {
        initObject(init());
    }

    protected NSObject(SkipInit skipInit) {
    }
    
    @Override
    protected void afterMarshaled() {
        super.afterMarshaled();
        retain();
    }
    
    @Override
    protected final void finalize() throws Throwable {
        dispose(true);
    }
    
    public final void dispose() throws Throwable {
        dispose(false);
    }
    
    protected void dispose(boolean finalizing) throws Throwable {
        release();
        if (finalizing) {
            super.finalize();
        }
    }
    
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSObject /*</name>*/.class);

    @Override
    public String toString() {
        return description();
    }
    
    /*<constructors>*/
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    
    protected long alloc() {
        return alloc(getObjCClass());
    }

    private static final Selector alloc = Selector.register("alloc");
    private static long alloc(ObjCClass c) {
        long h = ObjCRuntime.ptr_objc_msgSend(c.getHandle(), alloc.getHandle());
        if (h == 0L) {
            throw new OutOfMemoryError();
        }
        return h;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NSObject)) {
            return false;
        }
        return isEqual((NSObject) obj) ;
    }
    
    @Override
    public int hashCode() {
        return hash();
    }
    
    /*<methods>*/
    
    private static final Selector description = Selector.register("description");
    @Bridge private native static String objc_description(NSObject __self__, Selector __cmd__);
    @Bridge private native static String objc_descriptionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/description">- (NSString *)description</a>
     * @since Available in iOS 2.0 and later.
     */
    public String description() {
        if (customClass) { return objc_descriptionSuper(getSuper(), description); } else { return objc_description(this, description); }
    }
    
    private static final Selector hash = Selector.register("hash");
    @Bridge private native static int objc_hash(NSObject __self__, Selector __cmd__);
    @Bridge private native static int objc_hashSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/hash">- (NSUInteger)hash</a>
     * @since Available in iOS 2.0 and later.
     */
    protected int hash() {
        if (customClass) { return objc_hashSuper(getSuper(), hash); } else { return objc_hash(this, hash); }
    }
    
    private static final Selector init = Selector.register("init");
    @Bridge private native static @Pointer long objc_init(NSObject __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSObject_Class/Reference/Reference.html#//apple_ref/occ/instm/NSObject/init">- (id)init</a>
     * @since Available in iOS 2.0 and later.
     */
    private @Pointer long init() {
        return objc_init(this, init);
    }
    
    private static final Selector isEqual$ = Selector.register("isEqual:");
    @Bridge private native static boolean objc_isEqual(NSObject __self__, Selector __cmd__, NSObject anObject);
    @Bridge private native static boolean objc_isEqualSuper(ObjCSuper __super__, Selector __cmd__, NSObject anObject);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/isEqual:">- (BOOL)isEqual:(id)anObject</a>
     * @since Available in iOS 2.0 and later.
     */
    protected boolean isEqual(NSObject anObject) {
        if (customClass) { return objc_isEqualSuper(getSuper(), isEqual$, anObject); } else { return objc_isEqual(this, isEqual$, anObject); }
    }
    
    private static final Selector release = Selector.register("release");
    @Bridge private native static void objc_release(NSObject __self__, Selector __cmd__);
    @Bridge private native static void objc_releaseSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/release">- (oneway void)release</a>
     * @since Available in iOS 2.0 and later.
     */
    protected void release() {
        if (customClass) { objc_releaseSuper(getSuper(), release); } else { objc_release(this, release); }
    }
    
    private static final Selector retain = Selector.register("retain");
    @Bridge private native static NSObject objc_retain(NSObject __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_retainSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/retain">- (id)retain</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSObject retain() {
        if (customClass) { return objc_retainSuper(getSuper(), retain); } else { return objc_retain(this, retain); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("description") public static String description(NSObject __self__, Selector __cmd__) { return __self__.description(); }
        @Callback @BindSelector("hash") public static int hash(NSObject __self__, Selector __cmd__) { return __self__.hash(); }
        @Callback @BindSelector("isEqual:") public static boolean isEqual(NSObject __self__, Selector __cmd__, NSObject anObject) { return __self__.isEqual(anObject); }
        @Callback @BindSelector("release") public static void release(NSObject __self__, Selector __cmd__) { __self__.release(); }
        @Callback @BindSelector("retain") public static NSObject retain(NSObject __self__, Selector __cmd__) { return __self__.retain(); }
    }
    /*</callbacks>*/

}
