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
package org.robovm.apple.modelio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("ModelIO") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/MDLVertexAttributeName/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLVertexAttributeName/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/MDLVertexAttributeName/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static MDLVertexAttributeName toObject(Class<MDLVertexAttributeName> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return MDLVertexAttributeName.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(MDLVertexAttributeName o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<MDLVertexAttributeName> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MDLVertexAttributeName> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(MDLVertexAttributeName.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MDLVertexAttributeName> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (MDLVertexAttributeName o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final MDLVertexAttributeName Anisotropy = new MDLVertexAttributeName("Anisotropy");
    public static final MDLVertexAttributeName Binormal = new MDLVertexAttributeName("Binormal");
    public static final MDLVertexAttributeName Bitangent = new MDLVertexAttributeName("Bitangent");
    public static final MDLVertexAttributeName Color = new MDLVertexAttributeName("Color");
    public static final MDLVertexAttributeName EdgeCrease = new MDLVertexAttributeName("EdgeCrease");
    public static final MDLVertexAttributeName JointIndices = new MDLVertexAttributeName("JointIndices");
    public static final MDLVertexAttributeName JointWeights = new MDLVertexAttributeName("JointWeights");
    public static final MDLVertexAttributeName Normal = new MDLVertexAttributeName("Normal");
    public static final MDLVertexAttributeName OcclusionValue = new MDLVertexAttributeName("OcclusionValue");
    public static final MDLVertexAttributeName Position = new MDLVertexAttributeName("Position");
    public static final MDLVertexAttributeName ShadingBasisU = new MDLVertexAttributeName("ShadingBasisU");
    public static final MDLVertexAttributeName ShadingBasisV = new MDLVertexAttributeName("ShadingBasisV");
    public static final MDLVertexAttributeName SubdivisionStencil = new MDLVertexAttributeName("SubdivisionStencil");
    public static final MDLVertexAttributeName Tangent = new MDLVertexAttributeName("Tangent");
    public static final MDLVertexAttributeName TextureCoordinate = new MDLVertexAttributeName("TextureCoordinate");
    /*</constants>*/
    
    private static /*<name>*/MDLVertexAttributeName/*</name>*/[] values = new /*<name>*/MDLVertexAttributeName/*</name>*/[] {/*<value_list>*/Anisotropy, Binormal, Bitangent, Color, EdgeCrease, JointIndices, JointWeights, Normal, OcclusionValue, Position, ShadingBasisU, ShadingBasisV, SubdivisionStencil, Tangent, TextureCoordinate/*</value_list>*/};
    
    /*<name>*/MDLVertexAttributeName/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/MDLVertexAttributeName/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/MDLVertexAttributeName/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/MDLVertexAttributeName/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ModelIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="MDLVertexAttributeAnisotropy", optional=true)
        public static native NSString Anisotropy();
        @GlobalValue(symbol="MDLVertexAttributeBinormal", optional=true)
        public static native NSString Binormal();
        @GlobalValue(symbol="MDLVertexAttributeBitangent", optional=true)
        public static native NSString Bitangent();
        @GlobalValue(symbol="MDLVertexAttributeColor", optional=true)
        public static native NSString Color();
        @GlobalValue(symbol="MDLVertexAttributeEdgeCrease", optional=true)
        public static native NSString EdgeCrease();
        @GlobalValue(symbol="MDLVertexAttributeJointIndices", optional=true)
        public static native NSString JointIndices();
        @GlobalValue(symbol="MDLVertexAttributeJointWeights", optional=true)
        public static native NSString JointWeights();
        @GlobalValue(symbol="MDLVertexAttributeNormal", optional=true)
        public static native NSString Normal();
        @GlobalValue(symbol="MDLVertexAttributeOcclusionValue", optional=true)
        public static native NSString OcclusionValue();
        @GlobalValue(symbol="MDLVertexAttributePosition", optional=true)
        public static native NSString Position();
        @GlobalValue(symbol="MDLVertexAttributeShadingBasisU", optional=true)
        public static native NSString ShadingBasisU();
        @GlobalValue(symbol="MDLVertexAttributeShadingBasisV", optional=true)
        public static native NSString ShadingBasisV();
        @GlobalValue(symbol="MDLVertexAttributeSubdivisionStencil", optional=true)
        public static native NSString SubdivisionStencil();
        @GlobalValue(symbol="MDLVertexAttributeTangent", optional=true)
        public static native NSString Tangent();
        @GlobalValue(symbol="MDLVertexAttributeTextureCoordinate", optional=true)
        public static native NSString TextureCoordinate();
        /*</values>*/
    }
}
