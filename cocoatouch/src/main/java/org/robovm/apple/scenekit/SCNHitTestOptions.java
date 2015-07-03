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
@Marshaler(/*<name>*/SCNHitTestOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNHitTestOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNHitTestOptions toObject(Class<SCNHitTestOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SCNHitTestOptions(o);
        }
        @MarshalsPointer
        public static long toNative(SCNHitTestOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SCNHitTestOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNHitTestOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SCNHitTestOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNHitTestOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (SCNHitTestOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SCNHitTestOptions(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public SCNHitTestOptions() {}
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
    public SCNHitTestOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public boolean returnsFirstFoundOnly() {
        if (has(Keys.FirstFoundOnly())) {
            NSNumber val = (NSNumber) get(Keys.FirstFoundOnly());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setReturnsFirstFoundOnly(boolean returnsFirstFoundOnly) {
        set(Keys.FirstFoundOnly(), NSNumber.valueOf(returnsFirstFoundOnly));
        return this;
    }
    public boolean sortsResults() {
        if (has(Keys.SortResults())) {
            NSNumber val = (NSNumber) get(Keys.SortResults());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setSortsResults(boolean sortsResults) {
        set(Keys.SortResults(), NSNumber.valueOf(sortsResults));
        return this;
    }
    public boolean clipsToZRange() {
        if (has(Keys.ClipToZRange())) {
            NSNumber val = (NSNumber) get(Keys.ClipToZRange());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setClipsToZRange(boolean clipsToZRange) {
        set(Keys.ClipToZRange(), NSNumber.valueOf(clipsToZRange));
        return this;
    }
    public boolean isBackFaceCulling() {
        if (has(Keys.BackFaceCulling())) {
            NSNumber val = (NSNumber) get(Keys.BackFaceCulling());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setBackFaceCulling(boolean backFaceCulling) {
        set(Keys.BackFaceCulling(), NSNumber.valueOf(backFaceCulling));
        return this;
    }
    public boolean searchesBoundingBoxOnly() {
        if (has(Keys.BoundingBoxOnly())) {
            NSNumber val = (NSNumber) get(Keys.BoundingBoxOnly());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setSearchesBoundingBoxOnly(boolean searchesBoundingBoxOnly) {
        set(Keys.BoundingBoxOnly(), NSNumber.valueOf(searchesBoundingBoxOnly));
        return this;
    }
    public boolean ignoresChildNodes() {
        if (has(Keys.IgnoreChildNodes())) {
            NSNumber val = (NSNumber) get(Keys.IgnoreChildNodes());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setIgnoresChildNodes(boolean ignoresChildNodes) {
        set(Keys.IgnoreChildNodes(), NSNumber.valueOf(ignoresChildNodes));
        return this;
    }
    public SCNNode getRootNode() {
        if (has(Keys.RootNode())) {
            SCNNode val = (SCNNode) get(Keys.RootNode());
            return val;
        }
        return null;
    }
    public SCNHitTestOptions setRootNode(SCNNode rootNode) {
        set(Keys.RootNode(), rootNode);
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean ignoresHiddenNodes() {
        if (has(Keys.IgnoreHiddenNodes())) {
            NSNumber val = (NSNumber) get(Keys.IgnoreHiddenNodes());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SCNHitTestOptions setIgnoresHiddenNodes(boolean ignoresHiddenNodes) {
        set(Keys.IgnoreHiddenNodes(), NSNumber.valueOf(ignoresHiddenNodes));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("SceneKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="SCNHitTestFirstFoundOnlyKey", optional=true)
        public static native NSString FirstFoundOnly();
        @GlobalValue(symbol="SCNHitTestSortResultsKey", optional=true)
        public static native NSString SortResults();
        @GlobalValue(symbol="SCNHitTestClipToZRangeKey", optional=true)
        public static native NSString ClipToZRange();
        @GlobalValue(symbol="SCNHitTestBackFaceCullingKey", optional=true)
        public static native NSString BackFaceCulling();
        @GlobalValue(symbol="SCNHitTestBoundingBoxOnlyKey", optional=true)
        public static native NSString BoundingBoxOnly();
        @GlobalValue(symbol="SCNHitTestIgnoreChildNodesKey", optional=true)
        public static native NSString IgnoreChildNodes();
        @GlobalValue(symbol="SCNHitTestRootNodeKey", optional=true)
        public static native NSString RootNode();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNHitTestIgnoreHiddenNodesKey", optional=true)
        public static native NSString IgnoreHiddenNodes();
    }
    /*</keys>*/
}
