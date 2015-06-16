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
package org.robovm.apple.opengles;

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
/*<annotations>*/@Library("OpenGLES") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/EAGLColorFormat/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EAGLColorFormat/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/EAGLColorFormat/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static EAGLColorFormat toObject(Class<EAGLColorFormat> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return EAGLColorFormat.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(EAGLColorFormat o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<EAGLColorFormat> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<EAGLColorFormat> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(EAGLColorFormat.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<EAGLColorFormat> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (EAGLColorFormat o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final EAGLColorFormat RGBA8 = new EAGLColorFormat("RGBA8");
    public static final EAGLColorFormat RGB565 = new EAGLColorFormat("RGB565");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final EAGLColorFormat SRGBA8 = new EAGLColorFormat("SRGBA8");
    /*</constants>*/
    
    private static /*<name>*/EAGLColorFormat/*</name>*/[] values = new /*<name>*/EAGLColorFormat/*</name>*/[] {/*<value_list>*/RGBA8, RGB565, SRGBA8/*</value_list>*/};
    
    /*<name>*/EAGLColorFormat/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/EAGLColorFormat/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/EAGLColorFormat/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/EAGLColorFormat/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("OpenGLES") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kEAGLColorFormatRGBA8", optional=true)
        public static native NSString RGBA8();
        @GlobalValue(symbol="kEAGLColorFormatRGB565", optional=true)
        public static native NSString RGB565();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kEAGLColorFormatSRGBA8", optional=true)
        public static native NSString SRGBA8();
        /*</values>*/
    }
}
