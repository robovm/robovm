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
package org.robovm.apple.mobilecoreservices;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UTTagClass.Marshaler.class)
/*<annotations>*/@Library("MobileCoreServices")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UTTagClass/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static UTTagClass toObject(Class<UTTagClass> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return UTTagClass.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(UTTagClass o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UTTagClass.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final UTTagClass FilenameExtension = new UTTagClass("FilenameExtensionValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final UTTagClass MIMEType = new UTTagClass("MIMETypeValue");
    
    private static UTTagClass[] values = new UTTagClass[] {FilenameExtension, MIMEType};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private UTTagClass(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static UTTagClass valueOf(CFString value) {
        for (UTTagClass v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UTTagClass/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTagClassFilenameExtension", optional=true)
    protected static native CFString FilenameExtensionValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kUTTagClassMIMEType", optional=true)
    protected static native CFString MIMETypeValue();
    /*</methods>*/
}
