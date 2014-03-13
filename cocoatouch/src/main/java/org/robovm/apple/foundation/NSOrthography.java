/*
 * Copyright (C) 2014 Trillian AB
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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSOrthography/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSOrthographyPtr extends Ptr<NSOrthography, NSOrthographyPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSOrthography.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSOrthography() {}
    protected NSOrthography(SkipInit skipInit) { super(skipInit); }
    public NSOrthography(String script, NSDictionary<?, ?> map) { super((SkipInit) null); initObject(initWithDominantScript$languageMap$(script, map)); }
    public NSOrthography(NSCoder aDecoder) { super((SkipInit) null); initObject(initWithCoder$(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "dominantScript")
    public native String getDominantScript();
    @Property(selector = "languageMap")
    public native NSDictionary<?, ?> getLanguageMap();
    @Property(selector = "dominantLanguage")
    public native String getDominantLanguage();
    @Property(selector = "allScripts")
    public native NSArray<?> getAllScripts();
    @Property(selector = "allLanguages")
    public native NSArray<?> getAllLanguages();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "languagesForScript:")
    public native NSArray<?> languagesForScript$(String script);
    @Method(selector = "dominantLanguageForScript:")
    public native String dominantLanguageForScript$(String script);
    @Method(selector = "initWithDominantScript:languageMap:")
    protected native @Pointer long initWithDominantScript$languageMap$(String script, NSDictionary<?, ?> map);
    @Method(selector = "orthographyWithDominantScript:languageMap:")
    public static native NSObject orthographyWithDominantScript$languageMap$(String script, NSDictionary<?, ?> map);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long initWithCoder$(NSCoder aDecoder);
    /*</methods>*/
}
