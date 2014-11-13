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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ abstract class /*<name>*/NSURLProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSURLProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            // Not needed.
            return null;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (NSURLProperty i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public abstract NSString value();
    /*<methods>*/
    public static NSURLProperty valueOf(NSString value) {
        NSURLProperty property = null;
        property = NSURLFileProperty.valueOf(value);
        if (property != null) return property;
        property = NSURLFileSystemProperty.valueOf(value);
        if (property != null) return property;
        property = NSURLVolumeProperty.valueOf(value);
        if (property != null) return property;
        property = NSURLUbiquitousItemProperty.valueOf(value);
        return property;
    }
    /*</methods>*/
}
