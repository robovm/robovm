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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIContextOptions.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIContextOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CIContextOptions toObject(Class<CIContextOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIContextOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CIContextOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CIContextOptions(CFDictionary data) {
        this.data = data;
    }
    public CIContextOptions() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CIContextOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary() {
        return data;
    }
    

    public CGColorSpace getOutputColorSpace() {
        if (data.containsKey(OutputColorSpaceKey())) {
            CGColorSpace val = data.get(OutputColorSpaceKey(), CGColorSpace.class);
            return val;
        }
        return null;
    }
    public CIContextOptions setOutputColorSpace(CGColorSpace colorSpace) {
        data.put(OutputColorSpaceKey(), colorSpace != null ? colorSpace : NSNull.getNull());
        return this;
    }
    public CGColorSpace getWorkingColorSpace() {
        if (data.containsKey(WorkingColorSpaceKey())) {
            CGColorSpace val = data.get(WorkingColorSpaceKey(), CGColorSpace.class);
            return val;
        }
        return null;
    }
    public CIContextOptions setWorkingColorSpace(CGColorSpace colorSpace) {
        data.put(WorkingColorSpaceKey(), colorSpace != null ? colorSpace : NSNull.getNull());
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIFormat getWorkingFormat() {
        if (data.containsKey(WorkingFormatKey())) {
            NSNumber val = data.get(WorkingFormatKey(), NSNumber.class);
            return CIFormat.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIContextOptions setWorkingFormat(CIFormat format) {
        data.put(WorkingFormatKey(), NSNumber.valueOf(format.value()));
        return this;
    }
    public boolean isUsingSoftwareRenderer() {
        if (data.containsKey(UseSoftwareRendererKey())) {
            NSNumber val = data.get(UseSoftwareRendererKey(), NSNumber.class);
            return val.booleanValue();
        }
        return false;
    }
    public CIContextOptions setUseSoftwareRenderer(boolean softwareRenderer) {
        data.put(UseSoftwareRendererKey(), NSNumber.valueOf(softwareRenderer));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isRequestingLowPriority() {
        if (data.containsKey(PriorityRequestLowKey())) {
            NSNumber val = data.get(PriorityRequestLowKey(), NSNumber.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIContextOptions setRequestLowPriority(boolean requestLowPriority) {
        data.put(PriorityRequestLowKey(), NSNumber.valueOf(requestLowPriority));
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="kCIContextOutputColorSpace", optional=true)
    protected static native NSString OutputColorSpaceKey();
    @GlobalValue(symbol="kCIContextWorkingColorSpace", optional=true)
    protected static native NSString WorkingColorSpaceKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCIContextWorkingFormat", optional=true)
    protected static native NSString WorkingFormatKey();
    @GlobalValue(symbol="kCIContextUseSoftwareRenderer", optional=true)
    protected static native NSString UseSoftwareRendererKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCIContextPriorityRequestLow", optional=true)
    protected static native NSString PriorityRequestLowKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
