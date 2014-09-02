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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaEntity/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MPMediaEntityPtr extends Ptr<MPMediaEntity, MPMediaEntityPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPMediaEntity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPMediaEntity() {}
    protected MPMediaEntity(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getPersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaEntityProperty.EntityPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    
    public NSObject getValue(MPMediaEntityProperty property) {
        return getValue(property.value());
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateValues(List<MPMediaEntityProperty> properties, @Block VoidBlock3<NSString, NSObject, BytePtr> block) {
        NSSet<NSString> set = new NSMutableSet<>();
        for (MPMediaEntityProperty property : properties) {
            set.add(property.value());
        }
        enumerateValues(set, block);
    }
    public static boolean canFilterByProperty(MPMediaEntityProperty property) {
        return canFilterByProperty(property.value());
    }
    /*<methods>*/
    @Method(selector = "valueForProperty:")
    protected native NSObject getValue(NSString property);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "enumerateValuesForProperties:usingBlock:")
    protected native void enumerateValues(NSSet<NSString> properties, @Block VoidBlock3<NSString, NSObject, BytePtr> block);
    @Method(selector = "canFilterByProperty:")
    protected static native boolean canFilterByProperty(NSString property);
    /*</methods>*/
}
