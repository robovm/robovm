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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/abstract class /*<name>*/AVMetadataIdentifier/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataIdentifier> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVMetadataIdentifier.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataIdentifier i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    private static final List<Class<? extends AVMetadataIdentifier>> allSubClasses = new ArrayList<>();
    private static final int ABSTRACT = 0x00000400;
    
    static {
        @SuppressWarnings("unchecked")
        Class<? extends AVMetadataIdentifier>[] classes = (Class<? extends AVMetadataIdentifier>[]) 
                VM.listClasses(AVMetadataIdentifier.class, ClassLoader.getSystemClassLoader());
        final Class<?> baseClass = AVMetadataIdentifier.class;
        for (Class<? extends AVMetadataIdentifier> cls : classes) {
            if (cls != baseClass && (cls.getModifiers() & ABSTRACT) == 0) {
                allSubClasses.add(cls);
            }
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    public abstract NSString value();
    
    public static AVMetadataIdentifier valueOf(NSString value) {
        Class<?>[] args = new Class<?>[] {NSString.class};
        for (Class<? extends AVMetadataIdentifier> cls : allSubClasses) {
            try {
                Bro.bind(cls); // Global values need to be bound.
                java.lang.reflect.Method m = cls.getMethod("valueOf", args);
                AVMetadataIdentifier key = (AVMetadataIdentifier) m.invoke(null);
                if (key != null) return key;
            } catch (Throwable e) {
                System.err.println("WARN: Failed to call valueOf() for " 
                        + "the AVMetadataIdentifier subclass " + cls.getName());
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
                + /*<name>*/AVMetadataIdentifier/*</name>*/.class.getName());
    }
    /*<members>*//*</members>*/
    /*<methods>*/
    /*</methods>*/
}
