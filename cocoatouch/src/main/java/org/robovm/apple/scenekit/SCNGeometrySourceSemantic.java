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
@Marshaler(/*<name>*/SCNGeometrySourceSemantic/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNGeometrySourceSemantic/*</name>*/ 
    extends /*<extends>*/SCNProgramSemantic/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SCNGeometrySourceSemantic/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNGeometrySourceSemantic toObject(Class<SCNGeometrySourceSemantic> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNGeometrySourceSemantic.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNGeometrySourceSemantic o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SCNGeometrySourceSemantic> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNGeometrySourceSemantic> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SCNGeometrySourceSemantic.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNGeometrySourceSemantic> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (SCNGeometrySourceSemantic i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final SCNGeometrySourceSemantic Vertex = new SCNGeometrySourceSemantic("Vertex");
    public static final SCNGeometrySourceSemantic Normal = new SCNGeometrySourceSemantic("Normal");
    public static final SCNGeometrySourceSemantic Color = new SCNGeometrySourceSemantic("Color");
    public static final SCNGeometrySourceSemantic Texcoord = new SCNGeometrySourceSemantic("Texcoord");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNGeometrySourceSemantic VertexCrease = new SCNGeometrySourceSemantic("VertexCrease");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNGeometrySourceSemantic EdgeCrease = new SCNGeometrySourceSemantic("EdgeCrease");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNGeometrySourceSemantic BoneWeights = new SCNGeometrySourceSemantic("BoneWeights");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNGeometrySourceSemantic BoneIndices = new SCNGeometrySourceSemantic("BoneIndices");
    /*</constants>*/
    
    private static /*<name>*/SCNGeometrySourceSemantic/*</name>*/[] values = new /*<name>*/SCNGeometrySourceSemantic/*</name>*/[] {/*<value_list>*/Vertex, Normal, Color, Texcoord, VertexCrease, EdgeCrease, BoneWeights, BoneIndices/*</value_list>*/};
    
    /*<name>*/SCNGeometrySourceSemantic/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SCNGeometrySourceSemantic/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/SCNGeometrySourceSemantic/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNGeometrySourceSemantic/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="SCNGeometrySourceSemanticVertex", optional=true)
        public static native NSString Vertex();
        @GlobalValue(symbol="SCNGeometrySourceSemanticNormal", optional=true)
        public static native NSString Normal();
        @GlobalValue(symbol="SCNGeometrySourceSemanticColor", optional=true)
        public static native NSString Color();
        @GlobalValue(symbol="SCNGeometrySourceSemanticTexcoord", optional=true)
        public static native NSString Texcoord();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNGeometrySourceSemanticVertexCrease", optional=true)
        public static native NSString VertexCrease();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNGeometrySourceSemanticEdgeCrease", optional=true)
        public static native NSString EdgeCrease();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNGeometrySourceSemanticBoneWeights", optional=true)
        public static native NSString BoneWeights();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNGeometrySourceSemanticBoneIndices", optional=true)
        public static native NSString BoneIndices();
        /*</values>*/
    }
}
