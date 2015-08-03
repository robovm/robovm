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
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
@Marshaler(/*<name>*/SCNSceneSourceContributor/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSourceContributor/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNSceneSourceContributor toObject(Class<SCNSceneSourceContributor> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SCNSceneSourceContributor(o);
        }
        @MarshalsPointer
        public static long toNative(SCNSceneSourceContributor o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SCNSceneSourceContributor> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNSceneSourceContributor> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SCNSceneSourceContributor(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNSceneSourceContributor> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (SCNSceneSourceContributor i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SCNSceneSourceContributor(NSDictionary data) {
        super(data);
    }
    public SCNSceneSourceContributor() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public SCNSceneSourceContributor set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public String getAuthoringTool() {
        if (has(Keys.AuthoringTool())) {
            NSString val = (NSString) get(Keys.AuthoringTool());
            return val.toString();
        }
        return null;
    }
    public SCNSceneSourceContributor setAuthoringTool(String authoringTool) {
        set(Keys.AuthoringTool(), new NSString(authoringTool));
        return this;
    }
    public String getAuthor() {
        if (has(Keys.Author())) {
            NSString val = (NSString) get(Keys.Author());
            return val.toString();
        }
        return null;
    }
    public SCNSceneSourceContributor setAuthor(String author) {
        set(Keys.Author(), new NSString(author));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("SceneKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="SCNSceneSourceAssetAuthoringToolKey", optional=true)
        public static native NSString AuthoringTool();
        @GlobalValue(symbol="SCNSceneSourceAssetAuthorKey", optional=true)
        public static native NSString Author();
    }
    /*</keys>*/
}
