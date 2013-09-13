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

import java.lang.reflect.Array;
import org.robovm.rt.VM;
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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html">NSArray Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSArray /*</name>*/ <T extends NSObject> 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ implements List<T> {

    static {
        ObjCRuntime.bind(/*<name>*/ NSArray /*</name>*/.class);
    }

    static class ListAdapter<U extends NSObject> extends AbstractList<U> {
        private final NSArray<U> array;

        ListAdapter(NSArray<U> array) {
            this.array = array;
        }

        @SuppressWarnings("unchecked")
        @Override
        public U get(int index) {
            int size = array.count();
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
            }
            return (U) array.objectAtIndex(index);
        }

        @Override
        public int size() {
            return array.count();
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof NSObject) {
                return array.containsObject((NSObject) o);
            }
            return false;
        }
    }
    
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSArray /*</name>*/.class);

    private AbstractList<T> adapter = createAdapter();
    
    /*<constructors>*/
    protected NSArray(SkipInit skipInit) { super(skipInit); }
    public NSArray() {}
    
    /*</constructors>*/
    
    public NSArray(Collection<T> c) {
        super((SkipInit) null);
        if (c instanceof NSArray) {
            initObject(objc_initWithArray(this, initWithArray$, (NSArray<T>) c));
        } else {
            NSObject[] objects = c.toArray(new NSObject[c.size()]);
            initWithObjects(objects);
        }
    }
    
    public NSArray(T ... objects) {
        super((SkipInit) null);
        initWithObjects(objects);
    }
    
    /*<properties>*/
    
    /*</properties>*/
    
    private void initWithObjects(NSObject[] objects) {
        VoidPtrPtr ptr = Struct.allocate(VoidPtrPtr.class, objects.length);
        for (int i = 0; i < objects.length; i++) {
            ptr.set(objects[i].getHandle());
            ptr = ptr.next();
        }
        ptr = ptr.previous(objects.length);
        initObject(objc_initWithObjects(this, initWithObjects$count$, ptr.getHandle(), objects.length));
    }
    
    protected AbstractList<T> createAdapter() {
        return new ListAdapter<T>(this);
    }
    
    @Override
    protected void afterMarshaled() {
        if (adapter == null) {
            adapter = createAdapter();
        }
        super.afterMarshaled();
    }
    
    public void add(int index, T element) {
        adapter.add(index, element);
    }
    public boolean add(T e) {
        return adapter.add(e);
    }
    public boolean addAll(Collection<? extends T> c) {
        return adapter.addAll(c);
    }
    public boolean addAll(int index, Collection<? extends T> c) {
        return adapter.addAll(index, c);
    }
    public void clear() {
        adapter.clear();
    }
    public boolean contains(Object o) {
        return adapter.contains(o);
    }
    public boolean containsAll(Collection<?> c) {
        return adapter.containsAll(c);
    }
    public T get(int index) {
        return adapter.get(index);
    }
    public int indexOf(Object o) {
        return adapter.indexOf(o);
    }
    public boolean isEmpty() {
        return adapter.isEmpty();
    }
    public Iterator<T> iterator() {
        return adapter.iterator();
    }
    public int lastIndexOf(Object o) {
        return adapter.lastIndexOf(o);
    }
    public ListIterator<T> listIterator() {
        return adapter.listIterator();
    }
    public ListIterator<T> listIterator(int index) {
        return adapter.listIterator(index);
    }
    public T remove(int index) {
        return adapter.remove(index);
    }
    public boolean remove(Object o) {
        return adapter.remove(o);
    }
    public boolean removeAll(Collection<?> c) {
        return adapter.removeAll(c);
    }
    public boolean retainAll(Collection<?> c) {
        return adapter.retainAll(c);
    }
    public T set(int index, T element) {
        return adapter.set(index, element);
    }
    public int size() {
        return adapter.size();
    }
    @SuppressWarnings("unchecked")
    public NSArray<T> subList(int start, int end) {
        if (start >= 0 && end <= size()) {
            if (start <= end) {
                return (NSArray<T>) subarrayWithRange(new NSRange(start, end - start));
            }
            throw new IllegalArgumentException();
        }
        throw new IndexOutOfBoundsException();
    }
    public Object[] toArray() {
        return adapter.toArray();
    }
    public <U> U[] toArray(U[] a) {
        return adapter.toArray(a);
    }

    /*<methods>*/
    
    private static final Selector containsObject$ = Selector.register("containsObject:");
    @Bridge private native static boolean objc_containsObject(NSArray __self__, Selector __cmd__, NSObject anObject);
    @Bridge private native static boolean objc_containsObjectSuper(ObjCSuper __super__, Selector __cmd__, NSObject anObject);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html#//apple_ref/occ/instm/NSArray/containsObject:">- (BOOL)containsObject:(id)anObject</a>
     * @since Available in iOS 2.0 and later.
     */
    protected boolean containsObject(NSObject anObject) {
        if (customClass) { return objc_containsObjectSuper(getSuper(), containsObject$, anObject); } else { return objc_containsObject(this, containsObject$, anObject); }
    }
    
    private static final Selector count = Selector.register("count");
    @Bridge private native static int objc_count(NSArray __self__, Selector __cmd__);
    @Bridge private native static int objc_countSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html#//apple_ref/occ/instm/NSArray/count">- (NSUInteger)count</a>
     * @since Available in iOS 2.0 and later.
     */
    protected int count() {
        if (customClass) { return objc_countSuper(getSuper(), count); } else { return objc_count(this, count); }
    }
    
    private static final Selector indexOfObject$ = Selector.register("indexOfObject:");
    @Bridge private native static int objc_indexOfObject(NSArray __self__, Selector __cmd__, NSObject anObject);
    @Bridge private native static int objc_indexOfObjectSuper(ObjCSuper __super__, Selector __cmd__, NSObject anObject);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html#//apple_ref/occ/instm/NSArray/indexOfObject:">- (NSUInteger)indexOfObject:(id)anObject</a>
     * @since Available in iOS 2.0 and later.
     */
    protected int indexOfObject(NSObject anObject) {
        if (customClass) { return objc_indexOfObjectSuper(getSuper(), indexOfObject$, anObject); } else { return objc_indexOfObject(this, indexOfObject$, anObject); }
    }
    
    private static final Selector initWithArray$ = Selector.register("initWithArray:");
    @Bridge private native static @Pointer long objc_initWithArray(NSArray __self__, Selector __cmd__, NSArray anArray);
    @Bridge private native static @Pointer long objc_initWithArraySuper(ObjCSuper __super__, Selector __cmd__, NSArray anArray);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html#//apple_ref/occ/instm/NSArray/initWithArray:">- (id)initWithArray:(NSArray *)anArray</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithArray(NSArray anArray) {
        if (customClass) { return objc_initWithArraySuper(getSuper(), initWithArray$, anArray); } else { return objc_initWithArray(this, initWithArray$, anArray); }
    }
    
    private static final Selector initWithObjects$count$ = Selector.register("initWithObjects:count:");
    @Bridge private native static @Pointer long objc_initWithObjects(NSArray __self__, Selector __cmd__, @Pointer long objects, int count);
    @Bridge private native static @Pointer long objc_initWithObjectsSuper(ObjCSuper __super__, Selector __cmd__, @Pointer long objects, int count);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html#//apple_ref/occ/instm/NSArray/initWithObjects:count:">- (id)initWithObjects:(const id[])objects count:(NSUInteger)count</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithObjects(@Pointer long objects, int count) {
        if (customClass) { return objc_initWithObjectsSuper(getSuper(), initWithObjects$count$, objects, count); } else { return objc_initWithObjects(this, initWithObjects$count$, objects, count); }
    }
    
    private static final Selector objectAtIndex$ = Selector.register("objectAtIndex:");
    @Bridge private native static NSObject objc_objectAtIndex(NSArray __self__, Selector __cmd__, int index);
    @Bridge private native static NSObject objc_objectAtIndexSuper(ObjCSuper __super__, Selector __cmd__, int index);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html#//apple_ref/occ/instm/NSArray/objectAtIndex:">- (id)objectAtIndex:(NSUInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSObject objectAtIndex(int index) {
        if (customClass) { return objc_objectAtIndexSuper(getSuper(), objectAtIndex$, index); } else { return objc_objectAtIndex(this, objectAtIndex$, index); }
    }
    
    private static final Selector subarrayWithRange$ = Selector.register("subarrayWithRange:");
    @Bridge private native static NSArray objc_subarrayWithRange(NSArray __self__, Selector __cmd__, @ByVal NSRange range);
    @Bridge private native static NSArray objc_subarrayWithRangeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal NSRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSArray_Class/NSArray.html#//apple_ref/occ/instm/NSArray/subarrayWithRange:">- (NSArray *)subarrayWithRange:(NSRange)range</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSArray subarrayWithRange(NSRange range) {
        if (customClass) { return objc_subarrayWithRangeSuper(getSuper(), subarrayWithRange$, range); } else { return objc_subarrayWithRange(this, subarrayWithRange$, range); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("containsObject:") public static boolean containsObject(NSArray __self__, Selector __cmd__, NSObject anObject) { return __self__.containsObject(anObject); }
        @Callback @BindSelector("count") public static int count(NSArray __self__, Selector __cmd__) { return __self__.count(); }
        @Callback @BindSelector("indexOfObject:") public static int indexOfObject(NSArray __self__, Selector __cmd__, NSObject anObject) { return __self__.indexOfObject(anObject); }
        @Callback @BindSelector("initWithArray:") public static @Pointer long initWithArray(NSArray __self__, Selector __cmd__, NSArray anArray) { return __self__.initWithArray(anArray); }
        @Callback @BindSelector("initWithObjects:count:") public static @Pointer long initWithObjects(NSArray __self__, Selector __cmd__, @Pointer long objects, int count) { return __self__.initWithObjects(objects, count); }
        @Callback @BindSelector("objectAtIndex:") public static NSObject objectAtIndex(NSArray __self__, Selector __cmd__, int index) { return __self__.objectAtIndex(index); }
        @Callback @BindSelector("subarrayWithRange:") public static NSArray subarrayWithRange(NSArray __self__, Selector __cmd__, @ByVal NSRange range) { return __self__.subarrayWithRange(range); }
    }
    /*</callbacks>*/

}
