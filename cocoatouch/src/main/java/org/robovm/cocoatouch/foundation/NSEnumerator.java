/*
 * Copyright (C) 2013 Trillian AB
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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSEnumerator_Class/Reference/Reference.html">NSEnumerator Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSEnumerator /*</name>*/ <T extends NSObject>
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSEnumerator /*</name>*/.class);
    }

    static class Iterator<T extends NSObject> implements java.util.Iterator<T> {
        private final NSEnumerator<T> enumerator;
        int index;
        T current;
        T next;

        Iterator(NSEnumerator<T> enumerator) {
            this.enumerator = enumerator;
            index = -1;
            current = null;
            next = enumerator.nextObject();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            current = next;
            index++;
            next = enumerator.nextObject();
            return current;
        }

        void remove(int index, T o) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void remove() {
            if (current == null || next == null) {
                throw new IllegalStateException();
            }
            remove(index, current);
        }
        
    }
    
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSEnumerator /*</name>*/.class);

    /*<constructors>*/
    protected NSEnumerator(SkipInit skipInit) { super(skipInit); }
    public NSEnumerator() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    
    @SuppressWarnings("unchecked")
    public T nextObject() {
        return (T) _nextObject();
    }
    
    /*<methods>*/
    
    private static final Selector nextObject = Selector.register("nextObject");
    @Bridge private native static NSObject objc__nextObject(NSEnumerator __self__, Selector __cmd__);
    @Bridge private native static NSObject objc__nextObjectSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSEnumerator_Class/Reference/Reference.html#//apple_ref/occ/instm/NSEnumerator/nextObject">- (id)nextObject</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSObject _nextObject() {
        if (customClass) { return objc__nextObjectSuper(getSuper(), nextObject); } else { return objc__nextObject(this, nextObject); }
    }
    
    private static final Selector allObjects = Selector.register("allObjects");
    @Bridge private native static NSArray objc_allObjects(NSEnumerator __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_allObjectsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSEnumerator_Class/Reference/Reference.html#//apple_ref/occ/instm/NSEnumerator/allObjects">- (NSArray *)allObjects</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray<T> allObjects() {
        if (customClass) { return objc_allObjectsSuper(getSuper(), allObjects); } else { return objc_allObjects(this, allObjects); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("nextObject") public static NSObject _nextObject(NSEnumerator __self__, Selector __cmd__) { return __self__._nextObject(); }
        @Callback @BindSelector("allObjects") public static NSArray allObjects(NSEnumerator __self__, Selector __cmd__) { return __self__.allObjects(); }
    }
    /*</callbacks>*/

}
