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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSource/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNSceneSourcePtr extends Ptr<SCNSceneSource, SCNSceneSourcePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNSceneSource.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNSceneSource() {}
    protected SCNSceneSource(SkipInit skipInit) { super(skipInit); }
    public SCNSceneSource(NSURL url, SCNSceneSourceOptions options) { super((SkipInit) null); initObject(init(url, options)); }
    public SCNSceneSource(NSData data, SCNSceneSourceOptions options) { super((SkipInit) null); initObject(init(data, options)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "url")
    public native NSURL getUrl();
    @Property(selector = "data")
    public native NSData getData();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param options
     * @return
     * @throws NSErrorException
     */
    public SCNScene newScene(SCNSceneSourceOptions options) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        SCNScene result = newScene(options, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    
    /* Convenience methods */
    @SuppressWarnings("unchecked")
    public SCNSceneSourceContributor getContributors() {
        NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) getProperty(SCNSceneSourceProperty.Contributors);
        if (val != null) {
            return new SCNSceneSourceContributor(val);
        }
        return null;
    }
    public NSDate getCreatedDate() {
        return (NSDate) getProperty(SCNSceneSourceProperty.CreatedDate);
    }
    public NSDate getModifiedDate() {
        return (NSDate) getProperty(SCNSceneSourceProperty.ModifiedDate);
    }
    public String getUpAxis() {
        NSString val = (NSString) getProperty(SCNSceneSourceProperty.UpAxis);
        return val.toString();
    }
    @SuppressWarnings("unchecked")
    public SCNSceneSourceUnit getUnit() {
        NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) getProperty(SCNSceneSourceProperty.Unit);
        if (val != null) {
            return new SCNSceneSourceUnit(val);
        }
        return null;
    }
    /*<methods>*/
    @Method(selector = "initWithURL:options:")
    protected native @Pointer long init(NSURL url, SCNSceneSourceOptions options);
    @Method(selector = "initWithData:options:")
    protected native @Pointer long init(NSData data, SCNSceneSourceOptions options);
    @Method(selector = "sceneWithOptions:statusHandler:")
    public native SCNScene newScene(SCNSceneSourceOptions options, @Block VoidBlock4<Float, SCNSceneSourceStatus, NSError, BooleanPtr> statusHandler);
    @Method(selector = "sceneWithOptions:error:")
    protected native SCNScene newScene(SCNSceneSourceOptions options, NSError.NSErrorPtr error);
    @Method(selector = "propertyForKey:")
    public native NSObject getProperty(SCNSceneSourceProperty key);
    @Method(selector = "entryWithIdentifier:withClass:")
    public native NSObject getEntryWithIdentifier(String uid, Class<? extends NSObject> entryClass);
    @Method(selector = "identifiersOfEntriesWithClass:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getIdentifiersOfEntriesWithClass(Class<? extends NSObject> entryClass);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "entriesPassingTest:")
    public native NSArray<?> getEntriesPassingTest(@Block Block3<NSObject, String, BooleanPtr, Boolean> predicate);
    @Method(selector = "sceneSourceWithURL:options:")
    public static native SCNSceneSource create(NSURL url, SCNSceneSourceOptions options);
    @Method(selector = "sceneSourceWithData:options:")
    public static native SCNSceneSource create(NSData data, SCNSceneSourceOptions options);
    /*</methods>*/
}
