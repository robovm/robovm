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
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSOrderedSet/*</name>*/ <T extends NSObject>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFastEnumeration, Set<T>, List<T>/*</implements>*/ {

    public static class NSOrderedSetPtr<T extends NSObject> extends Ptr<NSOrderedSet<T>, NSOrderedSetPtr<T>> {}
    
    static class SetAdapter<U extends NSObject> extends AbstractSet<U> {
        protected final NSOrderedSet<U> set;

        SetAdapter(NSOrderedSet<U> set) {
            this.set = set;
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof NSObject) {
                return set.containsObject((NSObject) o);
            }
            return false;
        }

        @Override
        public Iterator<U> iterator() {
            return new NSEnumerator.Iterator<U>(set.objectEnumerator());
        }

        @Override
        public int size() {
            return (int) set.getCount();
        }
    }
    
    static class ListAdapter<U extends NSObject> extends AbstractList<U> {
        protected final NSOrderedSet<U> set;

        ListAdapter(NSOrderedSet<U> array) {
            this.set = array;
        }

        @Override
        public U get(int index) {
            checkIndex(index);
            return (U) set.getObjectAt(index);
        }

        protected void checkIndex(int index) {
            int size = (int) set.getCount();
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
            }
        }

        @Override
        public int size() {
            return (int) set.getCount();
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof NSObject) {
                return set.containsObject((NSObject) o);
            }
            return false;
        }
        
        @Override
        public int indexOf(Object o) {
            if (o instanceof NSObject) {
                return (int) set.indexOfObject((NSObject) o);
            }
            return -1;
        }
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSOrderedSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    
    private AbstractSet<T> setAdapter = createSetAdapter();
    private AbstractList<T> listAdapter = createListAdapter();

    /*<constructors>*/
    public NSOrderedSet() {}
    protected NSOrderedSet(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public NSOrderedSet(Collection<T> c) {
        super((SkipInit) null);
        if (c instanceof NSArray) {
            initObject(init((NSArray<T>) c));
        } else if (c instanceof NSSet) {
            initObject(init((NSOrderedSet<T>) c));
        } else {
            NSObject[] objects = c.toArray(new NSObject[c.size()]);
            initWithObjects(objects);
        }
    }
    
    public NSOrderedSet(T ... objects) {
        super((SkipInit) null);
        initWithObjects(objects);
    }

    
    /*<properties>*/
    @Property(selector = "count")
    protected native @MachineSizedUInt long getCount();
    @Property(selector = "firstObject")
    public native T getFirst();
    @Property(selector = "lastObject")
    public native T getLast();
    @Property(selector = "reversedOrderedSet")
    public native NSOrderedSet<T> getReversedOrderedSet();
    @Property(selector = "array")
    public native NSArray<T> getArray();
    @Property(selector = "set")
    public native NSSet<T> getSet();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    protected static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("null values are not allowed in NSOrderedSet. Use NSNull instead.");
        }
    }
    
    private void initWithObjects(NSObject[] objects) {
        VoidPtr.VoidPtrPtr ptr = Struct.allocate(VoidPtr.VoidPtrPtr.class, objects.length);
        for (int i = 0; i < objects.length; i++) {
            checkNull(objects[i]);
            ptr.set(objects[i].getHandle());
            ptr = ptr.next();
        }
        ptr = ptr.previous(objects.length);
        initObject(init(ptr.getHandle(), objects.length));
    }
    
    protected AbstractSet<T> createSetAdapter() {
        return new SetAdapter<T>(this);
    }
    protected AbstractList<T> createListAdapter() {
        return new ListAdapter<T>(this);
    }
    
    @Override
    protected void afterMarshaled() {
        if (setAdapter == null) {
            setAdapter = createSetAdapter();
        }
        if (listAdapter == null) {
            listAdapter = createListAdapter();
        }
        super.afterMarshaled();
    }
    
    public boolean remove(Object o) {
        return setAdapter.remove(o);
    }
    public boolean removeAll(Collection<?> c) {
        return setAdapter.removeAll(c);
    }
    public boolean retainAll(Collection<?> c) {
        return setAdapter.retainAll(c);
    }
    public void add(int index, T element) {
        listAdapter.add(index, element);
    }
    public boolean add(T e) {
        return listAdapter.add(e);
    }
    public boolean addAll(Collection<? extends T> c) {
        return listAdapter.addAll(c);
    }
    public boolean addAll(int index, Collection<? extends T> c) {
        return listAdapter.addAll(index, c);
    }
    public void clear() {
        listAdapter.clear();
    }
    public boolean contains(Object o) {
        return setAdapter.contains(o);
    }
    public boolean containsAll(Collection<?> c) {
        return setAdapter.containsAll(c);
    }
    public T get(int index) {
        return listAdapter.get(index);
    }
    public int indexOf(Object o) {
        return listAdapter.indexOf(o);
    }
    public boolean isEmpty() {
        return listAdapter.isEmpty();
    }
    public Iterator<T> iterator() {
        return listAdapter.iterator();
    }
    public int lastIndexOf(Object o) {
        return listAdapter.lastIndexOf(o);
    }
    public ListIterator<T> listIterator() {
        return listAdapter.listIterator();
    }
    public ListIterator<T> listIterator(int index) {
        return listAdapter.listIterator(index);
    }
    public T remove(int index) {
        return listAdapter.remove(index);
    }
    public T set(int index, T element) {
        return listAdapter.set(index, element);
    }
    public int size() {
        return listAdapter.size();
    }
    public NSOrderedSet<T> subList(int start, int end) {
        if (start >= 0 && end <= size()) {
            if (start <= end) {
                return new NSOrderedSet<T>(getObjectsAt(new NSIndexSet(new NSRange(start, end - start))));
            }
            throw new IllegalArgumentException();
        }
        throw new IndexOutOfBoundsException();
    }
    public Object[] toArray() {
        return listAdapter.toArray();
    }
    public <U> U[] toArray(U[] a) {
        return listAdapter.toArray(a);
    }    
    
    /*<methods>*/
    @Method(selector = "objectAtIndex:")
    protected native T getObjectAt(@MachineSizedUInt long idx);
    @Method(selector = "indexOfObject:")
    protected native @MachineSizedUInt long indexOfObject(NSObject object);
    @Method(selector = "initWithObjects:count:")
    protected native @Pointer long init(@Pointer long objects, @MachineSizedUInt long cnt);
    @Method(selector = "objectsAtIndexes:")
    protected native NSArray<T> getObjectsAt(NSIndexSet indexes);
    @Method(selector = "containsObject:")
    protected native boolean containsObject(NSObject object);
    @Method(selector = "objectEnumerator")
    protected native NSEnumerator<T> objectEnumerator();
    @Method(selector = "initWithOrderedSet:")
    protected native @Pointer long init(NSOrderedSet<?> set);
    @Method(selector = "initWithArray:")
    protected native @Pointer long init(NSArray<?> array);
    /*</methods>*/
}
