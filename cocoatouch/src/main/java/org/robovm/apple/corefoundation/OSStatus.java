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

package org.robovm.apple.corefoundation;

import org.robovm.rt.bro.ValuedEnum;
import org.robovm.rt.bro.annotation.MarshalsValue;
import org.robovm.rt.bro.annotation.Marshaler;

@Marshaler(OSStatus.Marshaler.class)
public final class OSStatus {
    public static final OSStatus NO_ERR = new OSStatus(0);

    public static class Marshaler {
        @MarshalsValue
        public static OSStatus toObject(Class<?> cls, int value, long flags) {
            return new OSStatus(value);
        }

        @MarshalsValue
        public static int toNative(OSStatus v, long flags) {
            return (int) v.getStatusCode();
        }
    }

    private int n;

    protected OSStatus(int value) {
        this.n = value;
    }

    public int getStatusCode() {
        return n;
    }
    
    public static OSStatus valueOf(int value) {
        return new OSStatus(value);
    }
    
    public static OSStatus valueOf(ValuedEnum value) {
        return new OSStatus((int)value.value());
    }
    
    @Override
    public boolean equals(Object o) {
        if ((o instanceof OSStatus) && ((OSStatus) o).n == n) {
            return true;
        }
        if ((o instanceof ValuedEnum) && ((ValuedEnum) o).value() == n) {
            return true;
        }
        return (o instanceof Number) && ((Number) o).intValue() == n;
    }
    
    @Override
    public String toString() {
        return String.valueOf(n);
    }
}
