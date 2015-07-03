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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/MPMediaPlaylistProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaPlaylistProperty/*</name>*/ 
    extends /*<extends>*/MPMediaEntityProperty/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/MPMediaPlaylistProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static MPMediaPlaylistProperty toObject(Class<MPMediaPlaylistProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return MPMediaPlaylistProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(MPMediaPlaylistProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<MPMediaPlaylistProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MPMediaPlaylistProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(MPMediaPlaylistProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MPMediaPlaylistProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (MPMediaPlaylistProperty o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final MPMediaPlaylistProperty PlaylistPersistendID = new MPMediaPlaylistProperty("PlaylistPersistendID");
    public static final MPMediaPlaylistProperty Name = new MPMediaPlaylistProperty("Name");
    public static final MPMediaPlaylistProperty PlaylistAttributes = new MPMediaPlaylistProperty("PlaylistAttributes");
    public static final MPMediaPlaylistProperty SeedItems = new MPMediaPlaylistProperty("SeedItems");
    /*</constants>*/
    
    private static /*<name>*/MPMediaPlaylistProperty/*</name>*/[] values = new /*<name>*/MPMediaPlaylistProperty/*</name>*/[] {/*<value_list>*/PlaylistPersistendID, Name, PlaylistAttributes, SeedItems/*</value_list>*/};
    
    /*<name>*/MPMediaPlaylistProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/MPMediaPlaylistProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/MPMediaPlaylistProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/MPMediaPlaylistProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("MediaPlayer") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="MPMediaPlaylistPropertyPersistentID", optional=true)
        public static native NSString PlaylistPersistendID();
        @GlobalValue(symbol="MPMediaPlaylistPropertyName", optional=true)
        public static native NSString Name();
        @GlobalValue(symbol="MPMediaPlaylistPropertyPlaylistAttributes", optional=true)
        public static native NSString PlaylistAttributes();
        @GlobalValue(symbol="MPMediaPlaylistPropertySeedItems", optional=true)
        public static native NSString SeedItems();
        /*</values>*/
    }
}
