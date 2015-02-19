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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(ABProperty.Marshaler.class)
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/abstract class /*<name>*/ABProperty/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsValue
        public static ABProperty toObject(Class<?> cls, int v, long flags) {
            return ABProperty.valueOf(v);
        }
        @MarshalsValue
        public static int toNative(ABProperty o, long flags) {
            return o.value();
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<ABProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABProperty> list = new ArrayList<>();
            for (NSNumber n : o) {
                list.add(ABProperty.valueOf(n.intValue()));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSNumber> array = new NSMutableArray<>();
            for (ABProperty i : l) {
                array.add(NSNumber.valueOf(i.value()));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    private static final List<Class<? extends ABProperty>> allSubClasses = new ArrayList<>();
    private static final int ABSTRACT = 0x00000400;
    
    static {
        @SuppressWarnings("unchecked")
        Class<? extends ABProperty>[] classes = (Class<? extends ABProperty>[]) 
                VM.listClasses(ABProperty.class, ClassLoader.getSystemClassLoader());
        final Class<?> baseClass = ABProperty.class;
        for (Class<? extends ABProperty> cls : classes) {
            if (cls != baseClass && (cls.getModifiers() & ABSTRACT) == 0) {
                allSubClasses.add(cls);
            }
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*/
    public static final int InvalidID = -1;
    /*</constants>*/
    
    private final LazyGlobalValue<Integer> lazyGlobalValue;
    
    protected ABProperty(Class<? extends ABProperty> clazz, String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(clazz, getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public int value() {
        return lazyGlobalValue.value().intValue();
    }
    
    public static ABProperty valueOf(int value) {
        Class<?>[] args = new Class<?>[] {int.class};
        for (Class<? extends ABProperty> cls : allSubClasses) {
            try {
                Bro.bind(cls); // Global values need to be bound.
                java.lang.reflect.Method m = cls.getMethod("valueOf", args);
                ABProperty key = (ABProperty) m.invoke(null, value);
                if (key != null) return key;
            } catch (Throwable e) {
                System.err.println("WARN: Failed to call valueOf() for " 
                        + "the ABProperty subclass " + cls.getName());
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
                + /*<name>*/ABProperty/*</name>*/.class.getName());
    }
    /*<methods>*//*</methods>*/
}
