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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSSet_Class/Reference/Reference.html">NSSet Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSSet /*</name>*/ <T extends NSObject>
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ implements Set<T> {

    static {
        ObjCRuntime.bind(/*<name>*/ NSSet /*</name>*/.class);
    }

    static class SetAdapter<U extends NSObject> extends AbstractSet<U> {
        private final NSSet<U> set;

        SetAdapter(NSSet<U> set) {
            this.set = set;
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof NSObject) {
                return set.member((NSObject) o) != null;
            }
            return false;
        }

        @Override
        public Iterator<U> iterator() {
            return new NSEnumerator.Iterator<U>(set.objectEnumerator());
        }

        @Override
        public int size() {
            return set.count();
        }
    }
    
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSSet /*</name>*/.class);

    private AbstractSet<T> adapter = createAdapter();

    /*<constructors>*/
    protected NSSet(SkipInit skipInit) { super(skipInit); }
    public NSSet() {}
    
    /*</constructors>*/
    
    public NSSet(Collection<T> c) {
        super((SkipInit) null);
        if (c instanceof NSArray) {
            initObject(objc_initWithArray(this, initWithArray$, (NSArray) c));
        } else if (c instanceof NSSet) {
            initObject(objc_initWithSet(this, initWithSet$, (NSSet) c));
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
    public boolean removeAll(Collection<?> arg0) {
        return adapter.removeAll(arg0);
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
    
    private static final Selector count = Selector.register("count");
    @Bridge private native static int objc_count(NSSet __self__, Selector __cmd__);
    @Bridge private native static int objc_countSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSSet_Class/Reference/Reference.html#//apple_ref/occ/instm/NSSet/count">- (NSUInteger)count</a>
     * @since Available in iOS 2.0 and later.
     */
    protected int count() {
        if (customClass) { return objc_countSuper(getSuper(), count); } else { return objc_count(this, count); }
    }
    
    private static final Selector initWithArray$ = Selector.register("initWithArray:");
    @Bridge private native static @Pointer long objc_initWithArray(NSSet __self__, Selector __cmd__, NSArray array);
    @Bridge private native static @Pointer long objc_initWithArraySuper(ObjCSuper __super__, Selector __cmd__, NSArray array);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSSet_Class/Reference/Reference.html#//apple_ref/occ/instm/NSSet/initWithArray:">- (id)initWithArray:(NSArray *)array</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithArray(NSArray array) {
        if (customClass) { return objc_initWithArraySuper(getSuper(), initWithArray$, array); } else { return objc_initWithArray(this, initWithArray$, array); }
    }
    
    private static final Selector initWithObjects$count$ = Selector.register("initWithObjects:count:");
    @Bridge private native static @Pointer long objc_initWithObjects(NSSet __self__, Selector __cmd__, @Pointer long objects, int cnt);
    @Bridge private native static @Pointer long objc_initWithObjectsSuper(ObjCSuper __super__, Selector __cmd__, @Pointer long objects, int cnt);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSSet_Class/Reference/Reference.html#//apple_ref/occ/instm/NSSet/initWithObjects:count:">- (id)initWithObjects:(const id *)objects count:(NSUInteger)cnt</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithObjects(@Pointer long objects, int cnt) {
        if (customClass) { return objc_initWithObjectsSuper(getSuper(), initWithObjects$count$, objects, cnt); } else { return objc_initWithObjects(this, initWithObjects$count$, objects, cnt); }
    }
    
    private static final Selector initWithSet$ = Selector.register("initWithSet:");
    @Bridge private native static @Pointer long objc_initWithSet(NSSet __self__, Selector __cmd__, NSSet set);
    @Bridge private native static @Pointer long objc_initWithSetSuper(ObjCSuper __super__, Selector __cmd__, NSSet set);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSSet_Class/Reference/Reference.html#//apple_ref/occ/instm/NSSet/initWithSet:">- (id)initWithSet:(NSSet *)set</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithSet(NSSet set) {
        if (customClass) { return objc_initWithSetSuper(getSuper(), initWithSet$, set); } else { return objc_initWithSet(this, initWithSet$, set); }
    }
    
    private static final Selector member$ = Selector.register("member:");
    @Bridge private native static NSObject objc_member(NSSet __self__, Selector __cmd__, NSObject object);
    @Bridge private native static NSObject objc_memberSuper(ObjCSuper __super__, Selector __cmd__, NSObject object);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSSet_Class/Reference/Reference.html#//apple_ref/occ/instm/NSSet/member:">- (id)member:(id)object</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSObject member(NSObject object) {
        if (customClass) { return objc_memberSuper(getSuper(), member$, object); } else { return objc_member(this, member$, object); }
    }
    
    private static final Selector objectEnumerator = Selector.register("objectEnumerator");
    @Bridge private native static NSEnumerator objc_objectEnumerator(NSSet __self__, Selector __cmd__);
    @Bridge private native static NSEnumerator objc_objectEnumeratorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSSet_Class/Reference/Reference.html#//apple_ref/occ/instm/NSSet/objectEnumerator">- (NSEnumerator *)objectEnumerator</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSEnumerator objectEnumerator() {
        if (customClass) { return objc_objectEnumeratorSuper(getSuper(), objectEnumerator); } else { return objc_objectEnumerator(this, objectEnumerator); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("count") public static int count(NSSet __self__, Selector __cmd__) { return __self__.count(); }
        @Callback @BindSelector("initWithArray:") public static @Pointer long initWithArray(NSSet __self__, Selector __cmd__, NSArray array) { return __self__.initWithArray(array); }
        @Callback @BindSelector("initWithObjects:count:") public static @Pointer long initWithObjects(NSSet __self__, Selector __cmd__, @Pointer long objects, int cnt) { return __self__.initWithObjects(objects, cnt); }
        @Callback @BindSelector("initWithSet:") public static @Pointer long initWithSet(NSSet __self__, Selector __cmd__, NSSet set) { return __self__.initWithSet(set); }
        @Callback @BindSelector("member:") public static NSObject member(NSSet __self__, Selector __cmd__, NSObject object) { return __self__.member(object); }
        @Callback @BindSelector("objectEnumerator") public static NSEnumerator objectEnumerator(NSSet __self__, Selector __cmd__) { return __self__.objectEnumerator(); }
    }
    /*</callbacks>*/

}
