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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/SCNSceneSourceProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSourceProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SCNSceneSourceProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNSceneSourceProperty toObject(Class<SCNSceneSourceProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNSceneSourceProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNSceneSourceProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SCNSceneSourceProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNSceneSourceProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SCNSceneSourceProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNSceneSourceProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (SCNSceneSourceProperty i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final SCNSceneSourceProperty Contributors = new SCNSceneSourceProperty("Contributors");
    public static final SCNSceneSourceProperty CreatedDate = new SCNSceneSourceProperty("CreatedDate");
    public static final SCNSceneSourceProperty ModifiedDate = new SCNSceneSourceProperty("ModifiedDate");
    public static final SCNSceneSourceProperty UpAxis = new SCNSceneSourceProperty("UpAxis");
    public static final SCNSceneSourceProperty Unit = new SCNSceneSourceProperty("Unit");
    /*</constants>*/
    
    private static /*<name>*/SCNSceneSourceProperty/*</name>*/[] values = new /*<name>*/SCNSceneSourceProperty/*</name>*/[] {/*<value_list>*/Contributors, CreatedDate, ModifiedDate, UpAxis, Unit/*</value_list>*/};
    
    /*<name>*/SCNSceneSourceProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SCNSceneSourceProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/SCNSceneSourceProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNSceneSourceProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="SCNSceneSourceAssetContributorsKey", optional=true)
        public static native NSString Contributors();
        @GlobalValue(symbol="SCNSceneSourceAssetCreatedDateKey", optional=true)
        public static native NSString CreatedDate();
        @GlobalValue(symbol="SCNSceneSourceAssetModifiedDateKey", optional=true)
        public static native NSString ModifiedDate();
        @GlobalValue(symbol="SCNSceneSourceAssetUpAxisKey", optional=true)
        public static native NSString UpAxis();
        @GlobalValue(symbol="SCNSceneSourceAssetUnitKey", optional=true)
        public static native NSString Unit();
        /*</values>*/
    }
}
