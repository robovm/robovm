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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSOrthography/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    public static class LanguageMapMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static Map<String, List<String>> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<NSString, NSArray<NSString>> o = (NSDictionary<NSString, NSArray<NSString>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            Map<String, List<String>> map = new HashMap<>();
            for (Map.Entry<NSString, NSArray<NSString>> e : o.entrySet()) {
                map.put(e.getKey().toString(), e.getValue().asStringList());
            }
            
            return map;
        }
        @MarshalsPointer
        public static long toNative(Map<String, List<String>> l, long flags) {
            if (l == null) {
                return 0L;
            }
            
            NSMutableDictionary<NSString, NSArray<NSString>> dict = new NSMutableDictionary<>();
            for (Map.Entry<String, List<String>> e : l.entrySet()) {
                dict.put(new NSString(e.getKey()), NSArray.fromStrings(e.getValue()));
            }
            
            return NSObject.Marshaler.toNative(dict, flags);
        }
    }
    
    /*<ptr>*/public static class NSOrthographyPtr extends Ptr<NSOrthography, NSOrthographyPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSOrthography.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSOrthography() {}
    protected NSOrthography(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSOrthography(String script, @org.robovm.rt.bro.annotation.Marshaler(NSOrthography.LanguageMapMarshaler.class) Map<String, List<String>> map) { super((SkipInit) null); initObject(init(script, map)); }
    public NSOrthography(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "dominantScript")
    public native String getDominantScript();
    @Property(selector = "languageMap")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSOrthography.LanguageMapMarshaler.class) Map<String, List<String>> getLanguageMap();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "dominantLanguage")
    public native String getDominantLanguage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "allScripts")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAllScripts();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "allLanguages")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAllLanguages();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithDominantScript:languageMap:")
    protected native @Pointer long init(String script, @org.robovm.rt.bro.annotation.Marshaler(NSOrthography.LanguageMapMarshaler.class) Map<String, List<String>> map);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "languagesForScript:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getLanguagesForScript(String script);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "dominantLanguageForScript:")
    public native String getDominantLanguageForScript(String script);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
