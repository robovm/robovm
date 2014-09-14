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
package org.robovm.apple.opengles;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(EAGLDrawableProperties.Marshaler.class)
/*<annotations>*/@Library("OpenGLES")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EAGLDrawableProperties/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    public EAGLDrawableProperties() {
        this.data = new NSMutableDictionary<NSString, NSObject>();
    }
    protected EAGLDrawableProperties(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(EAGLDrawableProperties.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    public boolean isRetainedBacking() {
        if (data.containsKey(RetainedBacking())) {
            NSNumber val = (NSNumber)data.get(RetainedBacking());
            return val.booleanValue();
        }
        return false;
    }
    public void setRetainedBacking(boolean retainedBacking) {
        data.put(RetainedBacking(), NSNumber.valueOf(retainedBacking));
    }
    public EAGLColorFormat getColorFormat() {
        if (data.containsKey(ColorFormat())) {
            NSString val = (NSString)data.get(ColorFormat());
            return EAGLColorFormat.valueOf(val);
        }
        return EAGLColorFormat.RGBA8;
    }
    public void setColorFormat(EAGLColorFormat colorFormat) {
        data.put(ColorFormat(), colorFormat.value());
    }
    /*<methods>*/
    @GlobalValue(symbol="kEAGLDrawablePropertyRetainedBacking", optional=true)
    protected static native NSString RetainedBacking();
    @GlobalValue(symbol="kEAGLDrawablePropertyColorFormat", optional=true)
    protected static native NSString ColorFormat();
    /*</methods>*/
}
