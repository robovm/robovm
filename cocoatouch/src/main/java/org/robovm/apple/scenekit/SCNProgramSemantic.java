/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(SCNProgramSemantic.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
public abstract class SCNProgramSemantic {
    
    public static class Marshaler {
        @MarshalsPointer
        public static SCNProgramSemantic toObject(Class<SCNProgramSemantic> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNProgramSemantic.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNProgramSemantic o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    static { Bro.bind(SCNProgramSemantic.class); }

    public abstract NSString value();
    
    public static SCNProgramSemantic valueOf(NSString value) {
        SCNProgramSemantic v = SCNGeometrySourceSemantic.valueOf(value);
        if (v == null) v = SCNRenderingTransform.valueOf(value);
                
        return v;
    }
}
