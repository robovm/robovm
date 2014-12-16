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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(SCNGeometrySourceSemantic.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNGeometrySourceSemantic/*</name>*/ 
    extends /*<extends>*/SCNProgramSemantic/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNGeometrySourceSemantic.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNGeometrySourceSemantic Vertex = new SCNGeometrySourceSemantic("VertexValue");
    public static final SCNGeometrySourceSemantic Normal = new SCNGeometrySourceSemantic("NormalValue");
    public static final SCNGeometrySourceSemantic Color = new SCNGeometrySourceSemantic("ColorValue");
    public static final SCNGeometrySourceSemantic Texcoord = new SCNGeometrySourceSemantic("TexcoordValue");
    public static final SCNGeometrySourceSemantic VertexCrease = new SCNGeometrySourceSemantic("VertexCreaseValue");
    public static final SCNGeometrySourceSemantic EdgeCrease = new SCNGeometrySourceSemantic("EdgeCreaseValue");
    public static final SCNGeometrySourceSemantic BoneWeights = new SCNGeometrySourceSemantic("BoneWeightsValue");
    public static final SCNGeometrySourceSemantic BoneIndices = new SCNGeometrySourceSemantic("BoneIndicesValue");
    
    private static SCNGeometrySourceSemantic[] values = new SCNGeometrySourceSemantic[] {Vertex, Normal, Color, Texcoord, 
        VertexCrease, EdgeCrease, BoneWeights, BoneIndices};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNGeometrySourceSemantic(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNGeometrySourceSemantic valueOf(NSString value) {
        for (SCNGeometrySourceSemantic v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/SCNGeometrySourceSemantic/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNGeometrySourceSemanticVertex", optional=true)
    protected static native NSString VertexValue();
    @GlobalValue(symbol="SCNGeometrySourceSemanticNormal", optional=true)
    protected static native NSString NormalValue();
    @GlobalValue(symbol="SCNGeometrySourceSemanticColor", optional=true)
    protected static native NSString ColorValue();
    @GlobalValue(symbol="SCNGeometrySourceSemanticTexcoord", optional=true)
    protected static native NSString TexcoordValue();
    @GlobalValue(symbol="SCNGeometrySourceSemanticVertexCrease", optional=true)
    protected static native NSString VertexCreaseValue();
    @GlobalValue(symbol="SCNGeometrySourceSemanticEdgeCrease", optional=true)
    protected static native NSString EdgeCreaseValue();
    @GlobalValue(symbol="SCNGeometrySourceSemanticBoneWeights", optional=true)
    protected static native NSString BoneWeightsValue();
    @GlobalValue(symbol="SCNGeometrySourceSemanticBoneIndices", optional=true)
    protected static native NSString BoneIndicesValue();
    /*</methods>*/
}
