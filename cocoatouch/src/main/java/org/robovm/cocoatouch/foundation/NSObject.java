/*
 * Copyright (C) 2012 RoboVM
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
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSObject_Class/Reference/Reference.html">NSObject Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@Marshalers({
    @Marshaler(type = String.class, value = NSString.StringMarshaler.class)    
})
public class /*<name>*/ NSObject /*</name>*/ 
    extends /*<extends>*/ ObjCObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSObject /*</name>*/.class);
    }

    protected static class SkipInit {}
    
    public NSObject() {
        init();
    }

    protected NSObject(SkipInit skipInit) {
    }
    
    @Override
    protected void afterMarshaled() {
        super.afterMarshaled();
        retain();
    }
    
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSObject /*</name>*/.class);

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
    
    /*<methods>*/
    
    private static final Selector description = Selector.register("description");
    @Bridge(symbol = "objc_msgSend") private native static String objc_description(NSObject __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_descriptionSuper(ObjCSuper __super__, NSObject __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/description">- (NSString *)description</a>
     * @since Available in iOS 2.0 and later.
     */
    public String description() {
        if (customClass) { return objc_descriptionSuper(getSuper(), this, description); } else { return objc_description(this, description); }
    }
    
    private static final Selector init = Selector.register("init");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_init(NSObject __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSObject_Class/Reference/Reference.html#//apple_ref/occ/instm/NSObject/init">- (id)init</a>
     * @since Available in iOS 2.0 and later.
     */
    private NSObject init() {
        return objc_init(this, init);
    }
    
    private static final Selector release = Selector.register("release");
    @Bridge(symbol = "objc_msgSend") private native static void objc_release(NSObject __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_releaseSuper(ObjCSuper __super__, NSObject __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/release">- (oneway void)release</a>
     * @since Available in iOS 2.0 and later.
     */
    protected void release() {
        if (customClass) { objc_releaseSuper(getSuper(), this, release); } else { objc_release(this, release); }
    }
    
    private static final Selector retain = Selector.register("retain");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_retain(NSObject __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_retainSuper(ObjCSuper __super__, NSObject __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Protocols/NSObject_Protocol/Reference/NSObject.html#//apple_ref/occ/intfm/NSObject/retain">- (id)retain</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSObject retain() {
        if (customClass) { return objc_retainSuper(getSuper(), this, retain); } else { return objc_retain(this, retain); }
    }
    /*</methods>*/

}
