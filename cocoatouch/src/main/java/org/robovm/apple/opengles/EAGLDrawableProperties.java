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
/*<annotations>*/@Library("OpenGLES")/*</annotations>*/
@Marshaler(/*<name>*/EAGLDrawableProperties/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EAGLDrawableProperties/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static EAGLDrawableProperties toObject(Class<EAGLDrawableProperties> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new EAGLDrawableProperties(o);
        }
        @MarshalsPointer
        public static long toNative(EAGLDrawableProperties o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<EAGLDrawableProperties> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<EAGLDrawableProperties> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new EAGLDrawableProperties(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<EAGLDrawableProperties> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (EAGLDrawableProperties i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    EAGLDrawableProperties(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public EAGLDrawableProperties() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public EAGLDrawableProperties set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public boolean isRetainedBacking() {
        if (has(Keys.RetainedBacking())) {
            NSNumber val = (NSNumber) get(Keys.RetainedBacking());
            return val.booleanValue();
        }
        return false;
    }
    public EAGLDrawableProperties setRetainedBacking(boolean retainedBacking) {
        set(Keys.RetainedBacking(), NSNumber.valueOf(retainedBacking));
        return this;
    }
    public EAGLColorFormat getColorFormat() {
        if (has(Keys.ColorFormat())) {
            NSString val = (NSString) get(Keys.ColorFormat());
            return EAGLColorFormat.valueOf(val);
        }
        return null;
    }
    public EAGLDrawableProperties setColorFormat(EAGLColorFormat colorFormat) {
        set(Keys.ColorFormat(), colorFormat.value());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("OpenGLES")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="kEAGLDrawablePropertyRetainedBacking", optional=true)
        public static native NSString RetainedBacking();
        @GlobalValue(symbol="kEAGLDrawablePropertyColorFormat", optional=true)
        public static native NSString ColorFormat();
    }
    /*</keys>*/
}
