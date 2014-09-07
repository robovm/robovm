/*
 * Copyright (C) 2012 Trillian Mobile AB
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
package org.robovm.rt.bro;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.MarshalsValue;

/**
 * 
 */
@Marshaler(Bits.AsIntMarshaler.class)
public abstract class Bits<T extends Bits<T>> implements Iterable<T>, Comparable<T> {
    private long value;
    private long mask;
    private String name;

    protected Bits(long value) {
        this.value = value;
        this.mask = value == 0 ? -1 : value;
    }
    
    protected Bits(long value, long mask) {
        this.value = value;
        this.mask = mask;
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Bits<T>> T with(T t) {
        try {
            return (T) t.getClass().getDeclaredConstructor(long.class).newInstance(t.value());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
    
    public static <T extends Bits<T>> T with(T t1, T t2) {
        T t = with(t1);
        return t.set(t2);
    }
    
    public static <T extends Bits<T>> T with(T t1, T t2, T t3) {
        T t = with(t1, t2);
        return t.set(t3);
    }
    
    public static <T extends Bits<T>> T with(T t1, T t2, T t3, T t4) {
        T t = with(t1, t2, t3);
        return t.set(t4);
    }
    
    public static <T extends Bits<T>> T with(T t1, T t2, T t3, T t4, T t5) {
        T t = with(t1, t2, t3, t4);
        return t.set(t5);
    }
    
    @SafeVarargs
    public static <T extends Bits<T>> T with(T start, T... others) {
        T t = with(start);
        for (T e : others) {
            t = t.set(e);
        }
        return t;
    }
    
    public long value() {
        return value;
    }
    
    protected abstract T wrap(long value, long mask);
    protected abstract T[] _values();
    
    public T set(T bits) {
        Bits<?> bits_ = bits; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
        return wrap((this.value & ~bits_.mask) | bits_.value, this.mask | bits_.mask);
    }

    @SuppressWarnings("unchecked")
    public T clear(T bits) {
        Bits<?> bits_ = bits; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
        if (bits_.value == 0) {
            return (T) this;
        }
        return wrap(this.value & ~bits_.mask, this.mask & ~bits_.mask);
    }    

    public boolean contains(T bits) {
        Bits<?> bits_ = bits; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
        return (bits_.mask & this.value) == bits_.value;
    }
    
    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) asSet().iterator();
    }

    private static int cardinality(long l) {
        int c = 0;
        for (int i = 0; i < 64; i++) {
            if ((l & (1L << i)) != 0) {
                c++;
            }
        }
        return c;
    }
    
    @SuppressWarnings("unchecked")
    public Set<T> asSet() {
        T[] all = _values();
        Arrays.sort(all, new Comparator<T>() {
            public int compare(T lhs, T rhs) {
                Bits<?> lhs_ = lhs; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
                Bits<?> rhs_ = rhs; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
                return Long.compare(cardinality(rhs_.mask), cardinality(lhs_.mask));
            }
        });
        Set<T> values = new TreeSet<T>();
        long value = this.value;
        for (T bits : all) {
            Bits<?> bits_ = bits; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
            if ((value & bits_.mask) == bits_.value) {
                values.add(bits);
                value &= ~bits_.mask;
                if (value == 0) {
                    break;
                }
            }
        }
        if (value != 0) {
            values.add(wrap(value, value));
        } else if (values.isEmpty()) {
            values.add((T) this);
        }
        return values;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (value ^ (value >>> 32));
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Bits other = (Bits) obj;
        if (value != other.value) {
            return false;
        }
        return true;
    }

    public int compareTo(T another) {
        Bits<?> another_ = another; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
        int c = Long.compare(mask, another_.mask);
        return c == 0 ? Long.compare(value, another_.value) : c;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("(0x");
        sb.append(Long.toHexString(value));
        sb.append(" = ");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            Bits<?> bits = it.next();
            if (bits.name != null) {
                sb.append(bits.name);
                sb.append("(0x");
                sb.append(Long.toHexString(bits.value));
                sb.append(')');
            } else {
                sb.append("0x");
                sb.append(Long.toHexString(bits.value));
            }
            if (it.hasNext()) {
                sb.append(" | ");                
            }
        }
        sb.append(')');
        return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    protected static <T extends Bits<T>> T[] _values(Class<T> cls) {
        if (!Bits.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException(cls.getName() + " is not a subclass of " + Bits.class);
        }
        try {
            Set<T> values = new TreeSet<T>();
            for (Field field : cls.getDeclaredFields()) {
                int mod = field.getModifiers();
                if (Modifier.isPublic(mod) && Modifier.isStatic(mod) && Modifier.isFinal(mod) && field.getType() == cls) {
                    T bits = (T) field.get(null);
                    Bits<?> bits_ = bits; // Avoids "... has private access in Bits" error with javac from OpenJDK 1.7
                    bits_.name = field.getName();
                    if (bits_.mask != 0) {
                        values.add(bits);
                    }
                }
            }
            return (T[]) values.toArray((T[]) Array.newInstance(cls, values.size()));
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }
    
    /**
     * Marshals a {@link Bits} as an 8-bit value.
     */
    public static class AsByteMarshaler {
        @MarshalsValue
        public static Bits<?> toObject(Class<?> cls, byte value, long flags) {
            return AsLongMarshaler.toObject(cls, value & 0xffL, flags);
        }
        @MarshalsValue
        public static byte toNative(Bits<?> o, long flags) {
            return (byte) o.value;
        }
    }
    
    /**
     * Marshals a {@link Bits} as a 16-bit value.
     */
    public static class AsShortMarshaler {
        @MarshalsValue
        public static Bits<?> toObject(Class<?> cls, short value, long flags) {
            return AsLongMarshaler.toObject(cls, value & 0xffffL, flags);
        }
        @MarshalsValue
        public static short toNative(Bits<?> o, long flags) {
            return (short) o.value;
        }
    }
    
    /**
     * Marshals a {@link Bits} as a 32-bit value.
     */
    public static class AsIntMarshaler {
        @MarshalsValue
        public static Bits<?> toObject(Class<?> cls, int value, long flags) {
            return AsLongMarshaler.toObject(cls, value & 0xffffffffL, flags);
        }
        @MarshalsValue
        public static int toNative(Bits<?> o, long flags) {
            return (int) o.value;
        }
    }
    
    /**
     * Marshals a {@link Bits} as a 64-bit value.
     */
    public static class AsLongMarshaler {
        @MarshalsValue
        public static Bits<?> toObject(Class<?> cls, long value, long flags) {
            Bits<?> f = (Bits<?>) VM.allocateObject(cls);
            f.value = value;
            f.mask = f.value == 0 ? -1 : f.value;
            return f;
        }
        @MarshalsValue
        public static long toNative(Bits<?> o, long flags) {
            return o.value;
        }
    }
    
    /**
     * Marshals a {@link Bits} as a 32-bit or 64-bit value depending on the
     * machine word size.
     */
    public static class AsMachineSizedIntMarshaler {
        @MarshalsValue
        public static Bits<?> toObject(Class<?> cls, @MachineSizedUInt long value, long flags) {
            return AsLongMarshaler.toObject(cls, value, flags);
        }
        @MarshalsValue
        public static @MachineSizedUInt long toNative(Bits<?> o, long flags) {
            return o.value;
        }
    }
}
