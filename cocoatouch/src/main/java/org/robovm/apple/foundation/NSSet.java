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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSSet/*</name>*/ <T extends NSObject>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ implements Set<T> {

    public static class NSSetPtr<T extends NSObject> extends Ptr<NSSet<T>, NSSetPtr<T>> {}
    
    public static class AsStringSetMarshaler {
        @MarshalsPointer
        public static Set<String> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSSet<?> o = (NSSet<?>) NSObject.Marshaler.toObject(cls, handle, flags);
            return o.asStringSet();
        }
        @MarshalsPointer
        public static long toNative(Set<String> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSSet.fromStrings(l), flags);
        }
    }
    
    public static class AsStringListMarshaler {
        @MarshalsPointer
        public static List<String> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSSet<?> o = (NSSet<?>) NSObject.Marshaler.toObject(cls, handle, flags);
            return o.asStringList();
        }
        @MarshalsPointer
        public static long toNative(List<String> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSSet.fromStrings(l), flags);
        }
    }
    
    static class SetAdapter<U extends NSObject> extends AbstractSet<U> {
        protected final NSSet<U> set;

        SetAdapter(NSSet<U> set) {
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
            return new NSEnumerator.Iterator<U>(set.objectEnumerator());
        }

        @Override
        public int size() {
            return (int) set.getCount();
        }
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/

    private AbstractSet<T> adapter = createAdapter();
    
    /*<constructors>*/
    public NSSet() {}
    protected NSSet(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public NSSet(Collection<T> c) {
        super((SkipInit) null);
        if (c instanceof NSArray) {
            initObject(initWithArray$((NSArray<T>) c));
        } else if (c instanceof NSSet) {
            initObject(initWithSet$((NSSet<T>) c));
        } else {
            NSObject[] objects = c.toArray(new NSObject[c.size()]);
            initWithObjects(objects);
        }
    }
    
    public NSSet(T ... objects) {
        super((SkipInit) null);
        initWithObjects(objects);
    }
    
    /*<properties>*/
    @Property(selector = "count")
    protected native @MachineSizedUInt long getCount();
    @Property(selector = "allObjects")
    public native NSArray<T> getValues();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    protected static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("null values are not allowed in NSSet. Use NSNull instead.");
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
        initObject(initWithObjects$count$(ptr.getHandle(), objects.length));
    }

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
    public T any() {
        return anyObject();
    }
    
    /**
     * Use this method to convert a NSSet of NSString items to a Set of String items. 
     * Elements of this NSASet must be of type NSString, otherwise an exception will be thrown.
     * @return
     * @throws UnsupportedOperationException when the set items are not of type NSString.
     */
    public Set<String> asStringSet() {
        Set<String> set = new HashSet<>();
        if (size() == 0) 
            return set;
        if (!(any() instanceof NSString)) 
            throw new UnsupportedOperationException("items must be of type NSString");
        
        for (T str : this) {
            set.add(str.toString());
        }
        return set;
    }
    
    public List<String> asStringList() {
        List<String> list = new ArrayList<>();
        if (size() == 0)
            return list;
        if (!(any() instanceof NSString)) 
            throw new UnsupportedOperationException("items must be of type NSString");
        
        for (T str : this) {
            list.add(str.toString());
        }
        return list;
    }
    
    public static NSSet<NSString> fromStrings (String... strings) {
        int length = strings.length;
        NSString[] nsStrings = new NSString[length];

        for (int i = 0; i < length; i++) {
            nsStrings[i] = new NSString(strings[i]);
        }
        return new NSSet<NSString>(nsStrings);
    }

    public static NSSet<NSString> fromStrings (Collection<String> strings) {
        NSString[] nsStrings = new NSString[strings.size()];

        int i = 0;
        for (String s : strings) {
            nsStrings[i] = new NSString(s);
            i++;
        }
        return new NSSet<NSString>(nsStrings);
    }
    
    /*<methods>*/
    @Method(selector = "member:")
    protected native NSObject member$(NSObject object);
    @Method(selector = "objectEnumerator")
    protected native NSEnumerator<T> objectEnumerator();
    @Method(selector = "initWithObjects:count:")
    protected native @Pointer long initWithObjects$count$(@Pointer long objects, @MachineSizedUInt long cnt);
    @Method(selector = "anyObject")
    protected native T anyObject();
    @Method(selector = "initWithSet:")
    protected native @Pointer long initWithSet$(NSSet<?> set);
    @Method(selector = "initWithArray:")
    protected native @Pointer long initWithArray$(NSArray<?> array);
    /*</methods>*/
}
