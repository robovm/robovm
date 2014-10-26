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
import org.robovm.apple.foundation.NSSet.NSSetPtr;
import org.robovm.apple.foundation.NSSet.SetAdapter;

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSHashTable/*</name>*/ <T extends NSObject>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/, Set<T> {
    
    public static class NSHashTablePtr<T extends NSObject> extends Ptr<NSHashTable<T>, NSHashTablePtr<T>> {}

    static class SetAdapter<U extends NSObject> extends AbstractSet<U> {
        protected final NSHashTable<U> set;

        SetAdapter(NSHashTable<U> set) {
            this.set = set;
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof NSObject) {
                return set.member$((NSObject) o) != null;
            }
            return false;
        }

        @Override
        public Iterator<U> iterator() {
            return new NSEnumerator.Iterator<U>(set.objectEnumerator()) {
                void remove(int index, U o) {
                    set.removeObject$(o);
                }
            };
        }

        @Override
        public int size() {
            return (int) set.getCount();
        }
        
        @Override
        public boolean add(U e) {
            checkNull(e);
            boolean replaced = contains(e);
            set.addObject$(e);
            return replaced;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public boolean remove(Object o) {
            if (contains(o)) {
                set.removeObject$((U) o);
                return true;
            }
            return false;
        }
        
        @Override
        public void clear() {
            set.removeAllObjects();
        }
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSHashTable.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    
    private AbstractSet<T> adapter = createAdapter();
    
    /*<constructors>*/
    public NSHashTable() {}
    protected NSHashTable(SkipInit skipInit) { super(skipInit); }
    public NSHashTable(NSHashTableOptions options, @MachineSizedUInt long initialCapacity) { super((SkipInit) null); initObject(init(options, initialCapacity)); }
    /*</constructors>*/
    
    private static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("null values are not allowed in NSHashTable. Use NSNull instead.");
        }
    }
    
    public NSHashTable(Collection<T> c) {
        addAll(c);
    }
    
    public NSHashTable(T ... objects) {
        for (T obj : objects) {
            add(obj);
        }
    }

    public NSHashTable(Collection<T> c, NSHashTableOptions options, @MachineSizedUInt long initialCapacity) {
        super((SkipInit) null);
        initObject(init(options, initialCapacity));
        addAll(c);
    }
    
    /*<properties>*/
    @Property(selector = "count")
    protected native @MachineSizedUInt long getCount();
    @Property(selector = "allObjects")
    public native NSArray<T> getValues();
    @Property(selector = "anyObject")
    public native T any();
    @Property(selector = "setRepresentation")
    public native NSSet<T> asSet();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    protected AbstractSet<T> createAdapter() {
        return new SetAdapter<T>(this);
    }
    
    @Override
    protected void afterMarshaled() {
        if (adapter == null) {
            adapter = createAdapter();
        }
        super.afterMarshaled();
    }
    
    public boolean add(T e) {
        return adapter.add(e);
    }
    public boolean addAll(Collection<? extends T> c) {
        return adapter.addAll(c);
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
    public boolean isEmpty() {
        return adapter.isEmpty();
    }
    public Iterator<T> iterator() {
        return adapter.iterator();
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
    public int size() {
        return adapter.size();
    }
    public Object[] toArray() {
        return adapter.toArray();
    }
    public <U> U[] toArray(U[] a) {
        return adapter.toArray(a);
    }
    
    /*<methods>*/
    @Method(selector = "initWithOptions:capacity:")
    protected native @Pointer long init(NSHashTableOptions options, @MachineSizedUInt long initialCapacity);
    @Method(selector = "member:")
    protected native NSObject member$(NSObject object);
    @Method(selector = "objectEnumerator")
    protected native NSEnumerator<T> objectEnumerator();
    @Method(selector = "addObject:")
    protected native void addObject$(NSObject object);
    @Method(selector = "removeObject:")
    protected native void removeObject$(NSObject object);
    @Method(selector = "removeAllObjects")
    protected native void removeAllObjects();
    @Method(selector = "containsObject:")
    protected native boolean containsObject$(NSObject anObject);
    @Method(selector = "intersectsHashTable:")
    public native boolean intersects(NSHashTable<T> other);
    @Method(selector = "isEqualToHashTable:")
    public native boolean isEqualTo(NSHashTable<T> other);
    @Method(selector = "isSubsetOfHashTable:")
    public native boolean isSubsetOf(NSHashTable<T> other);
    @Method(selector = "intersectHashTable:")
    public native void intersect(NSHashTable<T> other);
    @Method(selector = "unionHashTable:")
    public native void union(NSHashTable<T> other);
    @Method(selector = "minusHashTable:")
    public native void minus(NSHashTable<T> other);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "weakObjectsHashTable")
    protected static native NSHashTable<?> createWithWeakObjects();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
