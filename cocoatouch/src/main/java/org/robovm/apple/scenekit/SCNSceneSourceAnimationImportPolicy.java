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
@Marshaler(/*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNSceneSourceAnimationImportPolicy toObject(Class<SCNSceneSourceAnimationImportPolicy> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNSceneSourceAnimationImportPolicy.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNSceneSourceAnimationImportPolicy o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SCNSceneSourceAnimationImportPolicy> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNSceneSourceAnimationImportPolicy> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SCNSceneSourceAnimationImportPolicy.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNSceneSourceAnimationImportPolicy> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (SCNSceneSourceAnimationImportPolicy o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNSceneSourceAnimationImportPolicy Play = new SCNSceneSourceAnimationImportPolicy("Play");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNSceneSourceAnimationImportPolicy PlayRepeatedly = new SCNSceneSourceAnimationImportPolicy("PlayRepeatedly");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNSceneSourceAnimationImportPolicy DoNotPlay = new SCNSceneSourceAnimationImportPolicy("DoNotPlay");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNSceneSourceAnimationImportPolicy PlayUsingSceneTimeBase = new SCNSceneSourceAnimationImportPolicy("PlayUsingSceneTimeBase");
    /*</constants>*/
    
    private static /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/[] values = new /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/[] {/*<value_list>*/Play, PlayRepeatedly, DoNotPlay, PlayUsingSceneTimeBase/*</value_list>*/};
    
    /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyPlay", optional=true)
        public static native NSString Play();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyPlayRepeatedly", optional=true)
        public static native NSString PlayRepeatedly();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyDoNotPlay", optional=true)
        public static native NSString DoNotPlay();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyPlayUsingSceneTimeBase", optional=true)
        public static native NSString PlayUsingSceneTimeBase();
        /*</values>*/
    }
}
