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
package org.robovm.apple.messageui;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MessageUI")/*</annotations>*/
@Marshaler(/*<name>*/MFMessageComposeViewControllerAttachment/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MFMessageComposeViewControllerAttachment/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static MFMessageComposeViewControllerAttachment toObject(Class<MFMessageComposeViewControllerAttachment> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
        @MarshalsPointer
        public static List<MFMessageComposeViewControllerAttachment> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MFMessageComposeViewControllerAttachment> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new MFMessageComposeViewControllerAttachment(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MFMessageComposeViewControllerAttachment> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (MFMessageComposeViewControllerAttachment i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    MFMessageComposeViewControllerAttachment(NSDictionary data) {
        super(data);
    }
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
    

    public String getURL() {
        if (has(Keys.URL())) {
            NSString val = (NSString) get(Keys.URL());
            return val.toString();
        }
        return null;
    }
    public String getAlternateFilename() {
        if (has(Keys.AlternateFilename())) {
            NSString val = (NSString) get(Keys.AlternateFilename());
            return val.toString();
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("MessageUI")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="MFMessageComposeViewControllerAttachmentURL", optional=true)
        public static native NSString URL();
        @GlobalValue(symbol="MFMessageComposeViewControllerAttachmentAlternateFilename", optional=true)
        public static native NSString AlternateFilename();
    }
    /*</keys>*/
}
