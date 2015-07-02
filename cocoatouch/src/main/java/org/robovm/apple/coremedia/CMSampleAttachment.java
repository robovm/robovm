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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
@Marshaler(/*<name>*/CMSampleAttachment/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSampleAttachment/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMSampleAttachment toObject(Class<CMSampleAttachment> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMSampleAttachment(o);
        }
        @MarshalsPointer
        public static long toNative(CMSampleAttachment o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMSampleAttachment> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMSampleAttachment> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMSampleAttachment(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMSampleAttachment> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMSampleAttachment i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMSampleAttachment(CFDictionary data) {
        super(data);
    }
    public CMSampleAttachment() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CMSampleAttachmentKey key) {
        return data.containsKey(key.value());
    }
    public <T extends NativeObject> T get(CMSampleAttachmentKey key, Class<T> type) {
        if (has(key)) {
            return data.get(key.value(), type);
        }
        return null;
    }
    public CMSampleAttachment set(CMSampleAttachmentKey key, NativeObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isNotSync() {
        if (has(CMSampleAttachmentKey.NotSync)) {
            CFBoolean val = get(CMSampleAttachmentKey.NotSync, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setNotSync(boolean notSync) {
        set(CMSampleAttachmentKey.NotSync, CFBoolean.valueOf(notSync));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isPartialSync() {
        if (has(CMSampleAttachmentKey.PartialSync)) {
            CFBoolean val = get(CMSampleAttachmentKey.PartialSync, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setPartialSync(boolean partialSync) {
        set(CMSampleAttachmentKey.PartialSync, CFBoolean.valueOf(partialSync));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean hasRedundantCoding() {
        if (has(CMSampleAttachmentKey.HasRedundantCoding)) {
            CFBoolean val = get(CMSampleAttachmentKey.HasRedundantCoding, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setHasRedundantCoding(boolean hasRedundantCoding) {
        set(CMSampleAttachmentKey.HasRedundantCoding, CFBoolean.valueOf(hasRedundantCoding));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDependedOnByOthers() {
        if (has(CMSampleAttachmentKey.IsDependedOnByOthers)) {
            CFBoolean val = get(CMSampleAttachmentKey.IsDependedOnByOthers, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setIsDependedOnByOthers(boolean isDependedOnByOthers) {
        set(CMSampleAttachmentKey.IsDependedOnByOthers, CFBoolean.valueOf(isDependedOnByOthers));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean dependsOnOthers() {
        if (has(CMSampleAttachmentKey.DependsOnOthers)) {
            CFBoolean val = get(CMSampleAttachmentKey.DependsOnOthers, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setDependsOnOthers(boolean dependsOnOthers) {
        set(CMSampleAttachmentKey.DependsOnOthers, CFBoolean.valueOf(dependsOnOthers));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean allowsEarlierDisplayTimes() {
        if (has(CMSampleAttachmentKey.EarlierDisplayTimesAllowed)) {
            CFBoolean val = get(CMSampleAttachmentKey.EarlierDisplayTimesAllowed, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setAllowsEarlierDisplayTimes(boolean allowsEarlierDisplayTimes) {
        set(CMSampleAttachmentKey.EarlierDisplayTimesAllowed, CFBoolean.valueOf(allowsEarlierDisplayTimes));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean displaysImmediately() {
        if (has(CMSampleAttachmentKey.DisplayImmediately)) {
            CFBoolean val = get(CMSampleAttachmentKey.DisplayImmediately, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setDisplaysImmediately(boolean displaysImmediately) {
        set(CMSampleAttachmentKey.DisplayImmediately, CFBoolean.valueOf(displaysImmediately));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean doesNotDisplay() {
        if (has(CMSampleAttachmentKey.DoNotDisplay)) {
            CFBoolean val = get(CMSampleAttachmentKey.DoNotDisplay, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleAttachment setDoesNotDisplay(boolean doesNotDisplay) {
        set(CMSampleAttachmentKey.DoNotDisplay, CFBoolean.valueOf(doesNotDisplay));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
