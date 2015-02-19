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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableArray/*</name>*/ <T extends NSObject>
    extends /*<extends>*/NSArray/*</extends>*/ <T>
    /*<implements>*//*</implements>*/ {

    public static class NSMutableArrayPtr<T extends NSObject> extends Ptr<NSMutableArrayPtr<T>, NSMutableArrayPtr<T>> {}

    static class ListAdapter<U extends NSObject> extends NSArray.ListAdapter<U> {

        ListAdapter(NSArray<U> array) {
            super(array);
        }
        
        @Override
        public void clear() {
            ((NSMutableArray<U>) array).removeAllObjects();
        }
        
        @Override
        public U set(int index, U element) {
            checkNull(element);
            checkIndex(index);
            U old = (U) array.getObjectAt(index);
            ((NSMutableArray<U>) array).replaceObject(index, element);
            return old;
        }
        
        @Override
        public void add(int index, U element) {
            checkNull(element);
            if (index < 0 || index > array.getCount()) {
                checkIndex(index);
            }
            ((NSMutableArray<U>) array).insertObject(element, index);
        }
        
        @Override
        public U remove(int index) {
            checkIndex(index);
            U old = (U) array.getObjectAt(index);
            ((NSMutableArray<U>) array).removeObject(index);
            return old;
        }
    }

    /*<bind>*/static { ObjCRuntime.bind(NSMutableArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableArray() {}
    protected NSMutableArray(SkipInit skipInit) { super(skipInit); }
    public NSMutableArray(@MachineSizedUInt long numItems) { super((SkipInit) null); initObject(init(numItems)); }
    /*</constructors>*/
    
    public NSMutableArray(Collection<T> c) {
        super(c);
    }
    public NSMutableArray(T... objects) {
        super(objects);
    }    
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    protected AbstractList<T> createAdapter() {
        return new ListAdapter<T>(this);
    }
    
    public static NSArray<?> read(java.io.File file) {
        return read(file.getAbsolutePath());
    }
    
    public static NSMutableArray<NSString> toNSMutableArray (String... strings) {
        int length = strings.length;
        NSString[] nsStrings = new NSString[length];

        for (int i = 0; i < length; i++) {
            nsStrings[i] = new NSString(strings[i]);
        }
        return new NSMutableArray<NSString>(nsStrings);
    }

    public static NSMutableArray<NSString> toNSMutableArray (List<String> strings) {
        int size = strings.size();
        NSString[] nsStrings = new NSString[size];

        for (int i = 0; i < size; i++) {
            nsStrings[i] = new NSString(strings.get(i));
        }
        return new NSMutableArray<NSString>(nsStrings);
    }

    @Method(selector = "arrayWithContentsOfFile:")
    protected static native NSArray<? extends NSObject> read(String path);
    @Method(selector = "arrayWithContentsOfURL:")
    public static native NSArray<? extends NSObject> read(NSURL url);
    
    /*<methods>*/
    @Method(selector = "insertObject:atIndex:")
    protected native void insertObject(NSObject anObject, @MachineSizedUInt long index);
    @Method(selector = "removeObjectAtIndex:")
    protected native void removeObject(@MachineSizedUInt long index);
    @Method(selector = "replaceObjectAtIndex:withObject:")
    protected native void replaceObject(@MachineSizedUInt long index, NSObject anObject);
    @Method(selector = "initWithCapacity:")
    protected native @Pointer long init(@MachineSizedUInt long numItems);
    @Method(selector = "removeAllObjects")
    protected native void removeAllObjects();
    /*</methods>*/
}
