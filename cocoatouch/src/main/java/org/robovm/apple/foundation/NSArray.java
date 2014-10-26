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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSArray/*</name>*/ <T extends NSObject>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSPropertyList, List<T>/*</implements>*/ {

    public static class NSArrayPtr<T extends NSObject> extends Ptr<NSArray<T>, NSArrayPtr<T>> {}
    
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<?> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            return (NSArray<?>) NSObject.Marshaler.toObject(cls, handle, flags);
        }
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static long toNative(List<?> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<?> o = null;
            if (l instanceof NSArray) {
                o = (NSArray<?>) l;
            } else {
                o = new NSArray<NSObject>((List<NSObject>) l);
            }
            return NSObject.Marshaler.toNative(o, flags);
        }
    }
    
    public static class AsIntegerListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<Integer> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<Integer> list = new ArrayList<>();
            for (NSNumber n : o) {
                list.add(n.intValue());
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<Integer> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSNumber> array = new NSMutableArray<>();
            for (Integer i : l) {
                array.add(NSNumber.valueOf(i));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    public static class AsStringListMarshaler {
        @MarshalsPointer
        public static List<String> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<?> o = (NSArray<?>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.asStringList();
        }
        @MarshalsPointer
        public static long toNative(List<String> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSArray.fromStrings(l), flags);
        }
    }

    static class ListAdapter<U extends NSObject> extends AbstractList<U> {
        protected final NSArray<U> array;

        ListAdapter(NSArray<U> array) {
            this.array = array;
        }

        @Override
        public U get(int index) {
            checkIndex(index);
            return (U) array.objectAtIndex$(index);
        }

        protected void checkIndex(int index) {
            int size = (int) array.getCount();
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
            }
        }

        @Override
        public int size() {
            return (int) array.getCount();
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof NSObject) {
                return array.containsObject$((NSObject) o);
            }
            return false;
        }
        
        @Override
        public int indexOf(Object o) {
            if (o instanceof NSObject) {
                return (int) array.indexOfObject$((NSObject) o);
            }
            return -1;
        }
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    
    private AbstractList<T> adapter = createAdapter();
    
    /*<constructors>*/
    public NSArray() {}
    protected NSArray(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public NSArray(Collection<T> c) {
        super((SkipInit) null);
        if (c == null) {
            throw new NullPointerException("c");
        }
        if (c instanceof NSArray) {
            initObject(initWithArray$((NSArray<T>) c));
        } else {
            NSObject[] objects = c.toArray(new NSObject[c.size()]);
            initWithObjects(objects);
        }
    }
    
    @SuppressWarnings("unchecked")
    public NSArray(T ... objects) {
        super((SkipInit) null);
        if (objects == null) {
            throw new NullPointerException("objects");
        }
        initWithObjects(objects);
    }
    
    /*<properties>*/
    @Property(selector = "count")
    protected native @MachineSizedUInt long getCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "firstObject")
    public native T first();
    @Property(selector = "lastObject")
    public native T last();
    @Property(selector = "sortedArrayHint")
    public native NSData getSortedArrayHint();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    protected static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("null values are not allowed in NSArray. Use NSNull instead.");
        }
    }
    
    private void initWithObjects(NSObject[] objects) {
        VoidPtr.VoidPtrPtr ptr = null;
        if (objects.length > 0) {
            ptr = Struct.allocate(VoidPtr.VoidPtrPtr.class, objects.length);
            for (int i = 0; i < objects.length; i++) {
                checkNull(objects[i]);
                ptr.set(objects[i].getHandle());
                ptr = ptr.next();
            }
            ptr = ptr.previous(objects.length);
        }
        initObject(initWithObjects$count$(ptr != null ? ptr.getHandle() : 0, objects.length));
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
    public NSArray<T> subList(int start, int end) {
        if (start >= 0 && end <= size()) {
            if (start <= end) {
                return (NSArray<T>) subarrayWithRange$(new NSRange(start, end - start));
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
    
    public static NSArray<?> read(java.io.File file) {
        return arrayWithContentsOfFile$(file.getAbsolutePath());
    }

    public void write(java.io.File file, boolean atomically) {
        writeToFile$atomically$(file.getAbsolutePath(), atomically);
    }
    
    /**
     * Use this method to convert a NSArray of NSString items to a List of String items. 
     * Elements of this NSArray must be of type NSString, otherwise an exception will be thrown.
     * @return
     * @throws UnsupportedOperationException when the array items are not of type NSString.
     */
    public List<String> asStringList() {
        List<String> list = new ArrayList<>();
        if (size() == 0) 
            return list;
        if (!(get(0) instanceof NSString)) 
            throw new UnsupportedOperationException("items must be of type NSString");
        
        for (T str : this) {
            list.add(str.toString());
        }
        return list;
    }
    
    public static NSArray<NSString> fromStrings (String... strings) {
        int length = strings.length;
        NSString[] nsStrings = new NSString[length];

        for (int i = 0; i < length; i++) {
            nsStrings[i] = new NSString(strings[i]);
        }
        return new NSArray<NSString>(nsStrings);
    }

    public static NSArray<NSString> fromStrings (Collection<String> strings) {
        NSString[] nsStrings = new NSString[strings.size()];

        int i = 0;
        for (String s : strings) {
            nsStrings[i] = new NSString(s);
            i++;
        }
        return new NSArray<NSString>(nsStrings);
    }

    /*<methods>*/
    @Method(selector = "objectAtIndex:")
    protected native T objectAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "initWithObjects:count:")
    protected native @Pointer long initWithObjects$count$(@Pointer long objects, @MachineSizedUInt long cnt);
    @Method(selector = "containsObject:")
    protected native boolean containsObject$(NSObject anObject);
    @Method(selector = "indexOfObject:")
    protected native @MachineSizedUInt long indexOfObject$(NSObject anObject);
    @Method(selector = "subarrayWithRange:")
    protected native NSArray<T> subarrayWithRange$(@ByVal NSRange range);
    @Method(selector = "writeToFile:atomically:")
    protected native boolean writeToFile$atomically$(String path, boolean useAuxiliaryFile);
    @Method(selector = "writeToURL:atomically:")
    public native boolean write(NSURL url, boolean atomically);
    @Method(selector = "initWithArray:")
    protected native @Pointer long initWithArray$(NSArray<?> array);
    @Method(selector = "arrayWithContentsOfFile:")
    protected static native NSArray<?> arrayWithContentsOfFile$(String path);
    @Method(selector = "arrayWithContentsOfURL:")
    public static native NSArray<?> read(NSURL url);
    @Method(selector = "addObserver:toObjectsAtIndexes:forKeyPath:options:context:")
    public native void addObserver(NSObject observer, NSIndexSet indexes, String keyPath, NSKeyValueObservingOptions options, VoidPtr context);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "removeObserver:fromObjectsAtIndexes:forKeyPath:context:")
    public native void removeObserver(NSObject observer, NSIndexSet indexes, String keyPath, VoidPtr context);
    /*</methods>*/
}
