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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
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
            U old = (U) array.objectAtIndex$(index);
            ((NSMutableArray<U>) array).replaceObjectAtIndex$withObject$(index, element);
            return old;
        }
        
        @Override
        public void add(int index, U element) {
            checkNull(element);
            if (index < 0 || index > array.count()) {
                checkIndex(index);
            }
            ((NSMutableArray<U>) array).insertObject$atIndex$(element, index);
        }
        
        @Override
        public U remove(int index) {
            checkIndex(index);
            U old = (U) array.objectAtIndex$(index);
            ((NSMutableArray<U>) array).removeObjectAtIndex$(index);
            return old;
        }
    }

    /*<bind>*/static { ObjCRuntime.bind(NSMutableArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableArray() {}
    protected NSMutableArray(SkipInit skipInit) { super(skipInit); }
    public NSMutableArray(@MachineSizedUInt long numItems) { super((SkipInit) null); initObject(initWithCapacity$(numItems)); }
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
        return arrayWithContentsOfFile$(file.getAbsolutePath());
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
    protected static native NSArray<? extends NSObject> arrayWithContentsOfFile$(String path);
    @Method(selector = "arrayWithContentsOfURL:")
    public static native NSArray<? extends NSObject> read(NSURL url);
    
    /*<methods>*/
    @Method(selector = "insertObject:atIndex:")
    protected native void insertObject$atIndex$(NSObject anObject, @MachineSizedUInt long index);
    @Method(selector = "removeObjectAtIndex:")
    protected native void removeObjectAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "replaceObjectAtIndex:withObject:")
    protected native void replaceObjectAtIndex$withObject$(@MachineSizedUInt long index, NSObject anObject);
    @Method(selector = "removeAllObjects")
    protected native void removeAllObjects();
    @Method(selector = "initWithCapacity:")
    protected native @Pointer long initWithCapacity$(@MachineSizedUInt long numItems);
    /*</methods>*/
}
