/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.objc;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.MachineSizedFloat;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Marshalers;
import org.robovm.rt.bro.annotation.Pointer;

/**
 * Defines {@link Bridge} methods for common ObjC method signatures. The
 * {@code ObjCMemberPlugin} will use these whenever possible to avoid creating
 * excess {@link Bridge} methods.
 */
@Marshalers({
    @Marshaler(NSString.AsStringMarshaler.class),
    @Marshaler(NSArray.AsListMarshaler.class),
    @Marshaler(NSObject.Marshaler.class)
})
@Library("objc")
public class $M {

    static {
        Bro.bind($M.class);
    }

    @Bridge(symbol = "objc_msgSend")
    public static native void void_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native boolean boolean_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native byte byte_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native short short_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native char char_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native int int_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native long long_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native float float_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native double double_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native @Pointer long ptr_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native @MachineSizedFloat float mfloat_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native @MachineSizedFloat double mdouble_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native @MachineSizedSInt long msint_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native @MachineSizedUInt long muint_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native NSObject object_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native String string_objc_msgSend(NSObject self, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void void_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native boolean boolean_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native byte byte_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native short short_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native char char_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native int int_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native long long_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native float float_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native double double_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native @Pointer long ptr_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native @MachineSizedFloat float mfloat_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native @MachineSizedFloat double mdouble_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native @MachineSizedSInt long msint_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native @MachineSizedUInt long muint_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native NSObject object_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native String string_objc_msgSendSuper(ObjCSuper zuper, Selector selector);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_boolean(NSObject self, Selector selector, boolean v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_byte(NSObject self, Selector selector, byte v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_short(NSObject self, Selector selector, short v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_char(NSObject self, Selector selector, char v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_int(NSObject self, Selector selector, int v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_long(NSObject self, Selector selector, long v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_float(NSObject self, Selector selector, float v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_double(NSObject self, Selector selector, double v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_ptr(NSObject self, Selector selector, @Pointer long v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_mfloat(NSObject self, Selector selector, @MachineSizedFloat float v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_mdouble(NSObject self, Selector selector, @MachineSizedFloat double v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_msint(NSObject self, Selector selector, @MachineSizedSInt long v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_muint(NSObject self, Selector selector, @MachineSizedUInt long v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_object(NSObject self, Selector selector, NSObject v);

    @Bridge(symbol = "objc_msgSend")
    public static native void objc_msgSend_string(NSObject self, Selector selector, String v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_boolean(ObjCSuper zuper, Selector selector, boolean v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_byte(ObjCSuper zuper, Selector selector, byte v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_short(ObjCSuper zuper, Selector selector, short v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_char(ObjCSuper zuper, Selector selector, char v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_int(ObjCSuper zuper, Selector selector, int v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_long(ObjCSuper zuper, Selector selector, long v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_float(ObjCSuper zuper, Selector selector, float v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_double(ObjCSuper zuper, Selector selector, double v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_ptr(ObjCSuper zuper, Selector selector, @Pointer long v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_mfloat(ObjCSuper zuper, Selector selector, @MachineSizedFloat float v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_mdouble(ObjCSuper zuper, Selector selector, @MachineSizedFloat double v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_msint(ObjCSuper zuper, Selector selector, @MachineSizedSInt long v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_muint(ObjCSuper zuper, Selector selector, @MachineSizedUInt long v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_object(ObjCSuper zuper, Selector selector, NSObject v);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void objc_msgSendSuper_string(ObjCSuper zuper, Selector selector, String v);

    public static void void_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            void_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            void_objc_msgSend(self, selector);
        }
    }

    public static boolean boolean_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return boolean_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return boolean_objc_msgSend(self, selector);
        }
    }

    public static byte byte_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return byte_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return byte_objc_msgSend(self, selector);
        }
    }

    public static short short_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return short_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return short_objc_msgSend(self, selector);
        }
    }

    public static char char_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return char_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return char_objc_msgSend(self, selector);
        }
    }

    public static int int_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return int_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return int_objc_msgSend(self, selector);
        }
    }

    public static long long_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return long_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return long_objc_msgSend(self, selector);
        }
    }

    public static float float_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return float_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return float_objc_msgSend(self, selector);
        }
    }

    public static double double_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return double_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return double_objc_msgSend(self, selector);
        }
    }

    public static long ptr_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return ptr_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return ptr_objc_msgSend(self, selector);
        }
    }

    public static float mfloat_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return mfloat_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return mfloat_objc_msgSend(self, selector);
        }
    }

    public static double mdouble_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return mdouble_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return mdouble_objc_msgSend(self, selector);
        }
    }

    public static long msint_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return msint_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return msint_objc_msgSend(self, selector);
        }
    }

    public static long muint_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return muint_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return muint_objc_msgSend(self, selector);
        }
    }

    public static NSObject object_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return object_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return object_objc_msgSend(self, selector);
        }
    }

    public static String string_objc_msgSend_instance(NSObject self, Selector selector) {
        if (self.customClass) {
            return string_objc_msgSendSuper(self.getSuper(), selector);
        } else {
            return string_objc_msgSend(self, selector);
        }
    }

    public static void objc_msgSend_boolean_instance(NSObject self, Selector selector, boolean v) {
        if (self.customClass) {
            objc_msgSendSuper_boolean(self.getSuper(), selector, v);
        } else {
            objc_msgSend_boolean(self, selector, v);
        }
    }

    public static void objc_msgSend_byte_instance(NSObject self, Selector selector, byte v) {
        if (self.customClass) {
            objc_msgSendSuper_byte(self.getSuper(), selector, v);
        } else {
            objc_msgSend_byte(self, selector, v);
        }
    }

    public static void objc_msgSend_short_instance(NSObject self, Selector selector, short v) {
        if (self.customClass) {
            objc_msgSendSuper_short(self.getSuper(), selector, v);
        } else {
            objc_msgSend_short(self, selector, v);
        }
    }

    public static void objc_msgSend_char_instance(NSObject self, Selector selector, char v) {
        if (self.customClass) {
            objc_msgSendSuper_char(self.getSuper(), selector, v);
        } else {
            objc_msgSend_char(self, selector, v);
        }
    }

    public static void objc_msgSend_int_instance(NSObject self, Selector selector, int v) {
        if (self.customClass) {
            objc_msgSendSuper_int(self.getSuper(), selector, v);
        } else {
            objc_msgSend_int(self, selector, v);
        }
    }

    public static void objc_msgSend_long_instance(NSObject self, Selector selector, long v) {
        if (self.customClass) {
            objc_msgSendSuper_long(self.getSuper(), selector, v);
        } else {
            objc_msgSend_long(self, selector, v);
        }
    }

    public static void objc_msgSend_float_instance(NSObject self, Selector selector, float v) {
        if (self.customClass) {
            objc_msgSendSuper_float(self.getSuper(), selector, v);
        } else {
            objc_msgSend_float(self, selector, v);
        }
    }

    public static void objc_msgSend_double_instance(NSObject self, Selector selector, double v) {
        if (self.customClass) {
            objc_msgSendSuper_double(self.getSuper(), selector, v);
        } else {
            objc_msgSend_double(self, selector, v);
        }
    }

    public static void objc_msgSend_ptr_instance(NSObject self, Selector selector, long v) {
        if (self.customClass) {
            objc_msgSendSuper_ptr(self.getSuper(), selector, v);
        } else {
            objc_msgSend_ptr(self, selector, v);
        }
    }

    public static void objc_msgSend_mfloat_instance(NSObject self, Selector selector, float v) {
        if (self.customClass) {
            objc_msgSendSuper_mfloat(self.getSuper(), selector, v);
        } else {
            objc_msgSend_mfloat(self, selector, v);
        }
    }

    public static void objc_msgSend_mdouble_instance(NSObject self, Selector selector, double v) {
        if (self.customClass) {
            objc_msgSendSuper_mdouble(self.getSuper(), selector, v);
        } else {
            objc_msgSend_mdouble(self, selector, v);
        }
    }

    public static void objc_msgSend_msint_instance(NSObject self, Selector selector, long v) {
        if (self.customClass) {
            objc_msgSendSuper_msint(self.getSuper(), selector, v);
        } else {
            objc_msgSend_msint(self, selector, v);
        }
    }

    public static void objc_msgSend_muint_instance(NSObject self, Selector selector, long v) {
        if (self.customClass) {
            objc_msgSendSuper_muint(self.getSuper(), selector, v);
        } else {
            objc_msgSend_muint(self, selector, v);
        }
    }

    public static void objc_msgSend_object_instance(NSObject self, Selector selector, NSObject v) {
        if (self.customClass) {
            objc_msgSendSuper_object(self.getSuper(), selector, v);
        } else {
            objc_msgSend_object(self, selector, v);
        }
    }

    public static void objc_msgSend_string_instance(NSObject self, Selector selector, String v) {
        if (self.customClass) {
            objc_msgSendSuper_string(self.getSuper(), selector, v);
        } else {
            objc_msgSend_string(self, selector, v);
        }
    }
}
