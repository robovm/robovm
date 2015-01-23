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
@Marshaler(SCNHitTestOptions.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNHitTestOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected SCNHitTestOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public SCNHitTestOptions() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(SCNHitTestOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public boolean isReturningFirstFoundOnly() {
        if (data.containsKey(FirstFoundOnlyKey())) {
            NSNumber val = (NSNumber) data.get(FirstFoundOnlyKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setReturnFirstFoundOnly(boolean firstOnly) {
        data.put(FirstFoundOnlyKey(), NSNumber.valueOf(firstOnly));
        return this;
    }
    public boolean isSortingResults() {
        if (data.containsKey(SortResultsKey())) {
            NSNumber val = (NSNumber) data.get(SortResultsKey());
            return val.booleanValue();
        }
        return true;
    }
    public SCNHitTestOptions setSortResults(boolean sort) {
        data.put(SortResultsKey(), NSNumber.valueOf(sort));
        return this;
    }
    public boolean isClippingToZRange() {
        if (data.containsKey(ClipToZRangeKey())) {
            NSNumber val = (NSNumber) data.get(ClipToZRangeKey());
            return val.booleanValue();
        }
        return true;
    }
    public SCNHitTestOptions setClipToZRange(boolean clip) {
        data.put(ClipToZRangeKey(), NSNumber.valueOf(clip));
        return this;
    }
    public boolean isBackFaceCulling() {
        if (data.containsKey(BackFaceCullingKey())) {
            NSNumber val = (NSNumber) data.get(BackFaceCullingKey());
            return val.booleanValue();
        }
        return true;
    }
    public SCNHitTestOptions setBackFaceCulling(boolean backFaceCulling) {
        data.put(BackFaceCullingKey(), NSNumber.valueOf(backFaceCulling));
        return this;
    }
    public boolean isSearchingByBoundingBoxOnly() {
        if (data.containsKey(BoundingBoxOnlyKey())) {
            NSNumber val = (NSNumber) data.get(BoundingBoxOnlyKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setSearchByBoundingBoxOnly(boolean boundingBoxOnly) {
        data.put(BoundingBoxOnlyKey(), NSNumber.valueOf(boundingBoxOnly));
        return this;
    }
    public boolean isIgnoringChildNodes() {
        if (data.containsKey(IgnoreChildNodesKey())) {
            NSNumber val = (NSNumber) data.get(IgnoreChildNodesKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNHitTestOptions setIgnoreChildNodes(boolean ignoreChildNodes) {
        data.put(IgnoreChildNodesKey(), NSNumber.valueOf(ignoreChildNodes));
        return this;
    }
    public SCNNode getRootNode() {
        if (data.containsKey(RootNodeKey())) {
            SCNNode val = (SCNNode) data.get(RootNodeKey());
            return val;
        }
        return null;
    }
    public SCNHitTestOptions setRootNode(SCNNode rootNode) {
        data.put(RootNodeKey(), rootNode);
        return this;
    }
    public boolean isIgnoringHiddenNodes() {
        if (data.containsKey(IgnoreHiddenNodesKey())) {
            NSNumber val = (NSNumber) data.get(IgnoreHiddenNodesKey());
            return val.booleanValue();
        }
        return true;
    }
    public SCNHitTestOptions setIgnoreHiddenNodes(boolean ignoreHiddenNodes) {
        data.put(IgnoreHiddenNodesKey(), NSNumber.valueOf(ignoreHiddenNodes));
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNHitTestFirstFoundOnlyKey", optional=true)
    protected static native NSString FirstFoundOnlyKey();
    @GlobalValue(symbol="SCNHitTestSortResultsKey", optional=true)
    protected static native NSString SortResultsKey();
    @GlobalValue(symbol="SCNHitTestClipToZRangeKey", optional=true)
    protected static native NSString ClipToZRangeKey();
    @GlobalValue(symbol="SCNHitTestBackFaceCullingKey", optional=true)
    protected static native NSString BackFaceCullingKey();
    @GlobalValue(symbol="SCNHitTestBoundingBoxOnlyKey", optional=true)
    protected static native NSString BoundingBoxOnlyKey();
    @GlobalValue(symbol="SCNHitTestIgnoreChildNodesKey", optional=true)
    protected static native NSString IgnoreChildNodesKey();
    @GlobalValue(symbol="SCNHitTestRootNodeKey", optional=true)
    protected static native NSString RootNodeKey();
    @GlobalValue(symbol="SCNHitTestIgnoreHiddenNodesKey", optional=true)
    protected static native NSString IgnoreHiddenNodesKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
