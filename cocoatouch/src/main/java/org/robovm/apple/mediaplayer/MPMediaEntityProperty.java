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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(MPMediaEntityProperty.Marshaler.class)
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaEntityProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static MPMediaEntityProperty toObject(Class<MPMediaEntityProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return MPMediaEntityProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(MPMediaEntityProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MPMediaEntityProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaEntityProperty EntityPersistentID = new MPMediaEntityProperty("EntityPersistentIDValue", true);
    private static MPMediaEntityProperty[] values = new MPMediaEntityProperty[] {EntityPersistentID};
    
    final LazyGlobalValue<NSString> lazyGlobalValue;
    boolean filterable;
    
    protected MPMediaEntityProperty(String getterName, boolean filterable) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
        this.filterable = filterable;
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public boolean isFilterable() {
        return filterable;
    }
    
    public static MPMediaEntityProperty valueOf(NSString value) {
        for (MPMediaEntityProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        // Search in all subclasses of MPMediaEntityProperty.
        MPMediaItemProperty item = MPMediaItemProperty.findValue(value);
        if (item != null) return item;
        MPMediaPlaylistProperty playlist = MPMediaPlaylistProperty.findValue(value);
        if (playlist != null) return playlist;
        
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/MPMediaEntityProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaEntityPropertyPersistentID", optional=true)
    protected static native NSString EntityPersistentIDValue();
    /*</methods>*/
}
