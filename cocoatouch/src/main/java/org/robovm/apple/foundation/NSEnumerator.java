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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSEnumerator/*</name>*/ <T extends NSObject>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class NSEnumeratorPtr<T extends NSObject> extends Ptr<NSEnumerator<T>, NSEnumeratorPtr<T>> {}
    
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
    
    /*<bind>*/static { ObjCRuntime.bind(NSEnumerator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSEnumerator() {}
    protected NSEnumerator(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "nextObject")
    public native T nextObject();
    @Method(selector = "allObjects")
    public native NSArray<?> allObjects();
    /*</methods>*/
}
