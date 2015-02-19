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
package org.robovm.apple.messageui;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(MFMessageComposeViewControllerAttachment.Marshaler.class)
/*<annotations>*/@Library("MessageUI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MFMessageComposeViewControllerAttachment/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static MFMessageComposeViewControllerAttachment toObject(Class<MFMessageComposeViewControllerAttachment> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new MFMessageComposeViewControllerAttachment(o);
        }
        @MarshalsPointer
        public static long toNative(MFMessageComposeViewControllerAttachment o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<MFMessageComposeViewControllerAttachment> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<MFMessageComposeViewControllerAttachment> list = new ArrayList<>();
            for (NSDictionary<NSString, NSObject> dict : o) {
                list.add(new MFMessageComposeViewControllerAttachment(dict));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MFMessageComposeViewControllerAttachment> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (MFMessageComposeViewControllerAttachment i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected MFMessageComposeViewControllerAttachment(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(MFMessageComposeViewControllerAttachment.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public String getURL() {
        if (data.containsKey(URLKey())) {
            NSString val = (NSString)data.get(URLKey());
            return val.toString();
        }
        return null;
    }
    public String getAlternateFilename() {
        if (data.containsKey(AlternateFilenameKey())) {
            NSString val = (NSString)data.get(AlternateFilenameKey());
            return val.toString();
        }
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="MFMessageComposeViewControllerAttachmentURL", optional=true)
    protected static native NSString URLKey();
    @GlobalValue(symbol="MFMessageComposeViewControllerAttachmentAlternateFilename", optional=true)
    protected static native NSString AlternateFilenameKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
