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
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPTimedMetadata/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MPTimedMetadataPtr extends Ptr<MPTimedMetadata, MPTimedMetadataPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPTimedMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPTimedMetadata() {}
    protected MPTimedMetadata(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "key")
    public native String getKey();
    @Property(selector = "keyspace")
    public native String getKeyspace();
    @Property(selector = "value")
    public native NSObject getValue();
    @Property(selector = "timestamp")
    public native double getTimestamp();
    @Property(selector = "allMetadata")
    public native NSDictionary<NSString, NSObject> getAllMetadata();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getName() {
        NSDictionary<NSString, NSObject> data = getAllMetadata();
        if (data.containsKey(Name())) {
            NSString val = (NSString) data.get(Name());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getInfo() {
        NSDictionary<NSString, NSObject> data = getAllMetadata();
        if (data.containsKey(Info())) {
            NSString val = (NSString) data.get(Info());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getMIMEType() {
        NSDictionary<NSString, NSObject> data = getAllMetadata();
        if (data.containsKey(MIMEType())) {
            NSString val = (NSString) data.get(MIMEType());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getDataType() {
        NSDictionary<NSString, NSObject> data = getAllMetadata();
        if (data.containsKey(DataType())) {
            NSString val = (NSString) data.get(DataType());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getLanguageCode() {
        NSDictionary<NSString, NSObject> data = getAllMetadata();
        if (data.containsKey(LanguageCode())) {
            NSString val = (NSString) data.get(LanguageCode());
            return val.toString();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyName", optional=true)
    protected static native NSString Name();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyInfo", optional=true)
    protected static native NSString Info();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyMIMEType", optional=true)
    protected static native NSString MIMEType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyDataType", optional=true)
    protected static native NSString DataType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyLanguageCode", optional=true)
    protected static native NSString LanguageCode();
    
    
    /*</methods>*/
}
