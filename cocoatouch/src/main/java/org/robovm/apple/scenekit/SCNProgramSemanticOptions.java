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
@Marshaler(SCNProgramSemanticOptions.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNProgramSemanticOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static SCNProgramSemanticOptions toObject(Class<SCNProgramSemanticOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SCNProgramSemanticOptions(o);
        }
        @MarshalsPointer
        public static long toNative(SCNProgramSemanticOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected SCNProgramSemanticOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public SCNProgramSemanticOptions() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(SCNProgramSemanticOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public long getMappingChannel() {
        if (data.containsKey(MappingChannelKey())) {
            NSNumber val = (NSNumber) data.get(MappingChannelKey());
            return val.longValue();
        }
        return 0;
    }
    public SCNProgramSemanticOptions setMappingChannel(long mappingChannel) {
        data.put(MappingChannelKey(), NSNumber.valueOf(mappingChannel));
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNProgramMappingChannelKey", optional=true)
    protected static native NSString MappingChannelKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
