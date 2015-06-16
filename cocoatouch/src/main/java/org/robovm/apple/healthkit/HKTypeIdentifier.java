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
package org.robovm.apple.healthkit;

import java.util.LinkedList;
import java.util.List;

import org.robovm.apple.foundation.GlobalValueEnumeration;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.rt.VM;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.MarshalsPointer;

@Marshaler(HKTypeIdentifier.Marshaler.class)
public abstract class HKTypeIdentifier extends GlobalValueEnumeration<NSString> {

    protected HKTypeIdentifier(Class<?> clazz, String getterName) {
        super(clazz, getterName);
    }
    
    private static final List<Class<? extends HKTypeIdentifier>> allClasses = new LinkedList<>();
    private static final int ABSTRACT = 0x00000400;

    static {
        Bro.bind(HKTypeIdentifier.class);

        @SuppressWarnings("unchecked") Class<? extends HKTypeIdentifier>[] classes = (Class<? extends HKTypeIdentifier>[])
                VM.listClasses(HKTypeIdentifier.class, ClassLoader.getSystemClassLoader());
        final Class<?> typeIdentifierClass = HKTypeIdentifier.class;
        for (Class<? extends HKTypeIdentifier> cls : classes) {
            if (cls != typeIdentifierClass && (cls.getModifiers() & ABSTRACT) == 0) {
                allClasses.add(cls);
            }
        }
    }

    public static class Marshaler {
        @MarshalsPointer
        public static HKTypeIdentifier toObject(Class<HKTypeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            Class<?>[] args = new Class<?>[] { NSString.class };
            for (Class<?> clazz : allClasses) {
                try {
                    java.lang.reflect.Method m = clazz.getMethod("valueOf", args);
                    HKTypeIdentifier type = (HKTypeIdentifier) m.invoke(o);
                    if (type != null)
                        return type;
                } catch (Throwable e) {
                    System.err.println("WARN: Failed to call valueOf() for "
                            + "the HKTypeIdentifier subclass " + cls.getName());
                }
            }

            return null;
        }

        @MarshalsPointer
        public static long toNative(HKTypeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
}
