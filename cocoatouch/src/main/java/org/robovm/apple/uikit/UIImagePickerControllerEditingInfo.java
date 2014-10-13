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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UIImagePickerControllerEditingInfo.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIImagePickerControllerEditingInfo/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UIImagePickerControllerEditingInfo toObject(Class<UIImagePickerControllerEditingInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIImagePickerControllerEditingInfo(o);
        }
        @MarshalsPointer
        public static long toNative(UIImagePickerControllerEditingInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected UIImagePickerControllerEditingInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public UIImagePickerControllerEditingInfo() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(UIImagePickerControllerEditingInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
//    public UTType getMediaType() { TODO
//        if (data.containsKey(MediaTypeKey())) {
//            NSString val = (NSString)data.get(MediaTypeKey());
//            return UTType.valueOf(val);
//        }
//        return null;
//    }
//    public UIImagePickerControllerEditingInfo setMediaType(UTType mediaType) { TODO
//        data.put(MediaTypeKey(), mediaType.value());
//        return this;
//    }
    public UIImage getOriginalImage() {
        if (data.containsKey(OriginalImageKey())) {
            UIImage val = (UIImage)data.get(OriginalImageKey());
            return val;
        }
        return null;
    }
    public UIImagePickerControllerEditingInfo setOriginalImage(UIImage image) {
        data.put(OriginalImageKey(), image);
        return this;
    }
    public UIImage getEditedImage() {
        if (data.containsKey(EditedImageKey())) {
            UIImage val = (UIImage)data.get(EditedImageKey());
            return val;
        }
        return null;
    }
    public UIImagePickerControllerEditingInfo setEditedImage(UIImage image) {
        data.put(EditedImageKey(), image);
        return this;
    }
    public @ByVal CGRect getCropRect() {
        if (data.containsKey(CropRectKey())) {
            NSValue val = (NSValue)data.get(CropRectKey());
            return val.rectValue();
        }
        return null;
    }
    public UIImagePickerControllerEditingInfo setCropRect(@ByVal CGRect cropRect) {
        data.put(CropRectKey(), NSValue.valueOf(cropRect));
        return this;
    }
    public NSURL getMediaURL() {
        if (data.containsKey(MediaURLKey())) {
            NSURL val = (NSURL)data.get(MediaURLKey());
            return val;
        }
        return null;
    }
    public UIImagePickerControllerEditingInfo setMediaURL(NSURL url) {
        data.put(MediaURLKey(), url);
        return this;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public NSURL getReferenceURL() {
        if (data.containsKey(ReferenceURLKey())) {
            NSURL val = (NSURL)data.get(ReferenceURLKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public UIImagePickerControllerEditingInfo setReferenceURL(NSURL url) {
        data.put(ReferenceURLKey(), url);
        return this;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    @SuppressWarnings("unchecked")
    public Map<String, NSObject> getMediaMetadata() {
        if (data.containsKey(MediaMetadataKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>)data.get(MediaMetadataKey());
            return val.asStringMap();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.1 and later.
     */
    public UIImagePickerControllerEditingInfo setMediaMetadata(Map<String, NSObject> metadata) {
        data.put(MediaMetadataKey(), NSDictionary.fromStringMap(metadata));
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="UIImagePickerControllerMediaType", optional=true)
    protected static native NSString MediaTypeKey();
    @GlobalValue(symbol="UIImagePickerControllerOriginalImage", optional=true)
    protected static native NSString OriginalImageKey();
    @GlobalValue(symbol="UIImagePickerControllerEditedImage", optional=true)
    protected static native NSString EditedImageKey();
    @GlobalValue(symbol="UIImagePickerControllerCropRect", optional=true)
    protected static native NSString CropRectKey();
    @GlobalValue(symbol="UIImagePickerControllerMediaURL", optional=true)
    protected static native NSString MediaURLKey();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="UIImagePickerControllerReferenceURL", optional=true)
    protected static native NSString ReferenceURLKey();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="UIImagePickerControllerMediaMetadata", optional=true)
    protected static native NSString MediaMetadataKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
