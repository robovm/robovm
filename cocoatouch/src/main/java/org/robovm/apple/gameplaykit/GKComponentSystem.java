/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.gameplaykit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GameplayKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKComponentSystem/*</name>*/ <T extends GKComponent>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFastEnumeration/*</implements>*/, Iterable<T> {

    /*<ptr>*/public static class GKComponentSystemPtr extends Ptr<GKComponentSystem, GKComponentSystemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKComponentSystem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected GKComponentSystem(SkipInit skipInit) { super(skipInit); }
    public GKComponentSystem(Class<? extends T> cls) { super((SkipInit) null); initObject(init(cls)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "componentClass")
    public native Class<? extends T> getComponentClass();
    @Property(selector = "components")
    public native NSArray<T> getComponents();
    /*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public java.util.Iterator<T> iterator() {
        return new GKComponentSystem.Iterator<T>(this);
    }
    /*<methods>*/
    @Method(selector = "objectAtIndexedSubscript:")
    protected native T get(@MachineSizedUInt long idx);
    @Method(selector = "initWithComponentClass:")
    protected native @Pointer long init(Class<? extends T> cls);
    @Method(selector = "addComponent:")
    public native void addComponent(T component);
    @Method(selector = "addComponentWithEntity:")
    public native void addComponent(GKEntity entity);
    @Method(selector = "removeComponentWithEntity:")
    public native void removeComponent(GKEntity entity);
    @Method(selector = "removeComponent:")
    public native void removeComponent(T component);
    @Method(selector = "updateWithDeltaTime:")
    public native void update(double seconds);
    /*</methods>*/
    
    private static class Iterator<T extends GKComponent> implements java.util.Iterator<T> {
        GKComponentSystem<T> enumerator;
        int index;
        T current;
        T next;

        Iterator(GKComponentSystem<T> enumerator) {
            this.enumerator = enumerator;
            index = -1;
            current = null;
            next = enumerator.get(0);
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
            next = enumerator.get(index + 1);
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
}
