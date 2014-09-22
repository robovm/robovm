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
package org.robovm.apple.coretext;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CTFontCollectionOptions.Marshaler.class)
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontCollectionOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CTFontCollectionOptions toObject(Class<CTFontCollectionOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTFontCollectionOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CTFontCollectionOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CTFontCollectionOptions(CFDictionary data) {
        this.data = data;
    }
    public CTFontCollectionOptions() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CTFontCollectionOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected CFDictionary getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public boolean isRemovingDuplicates() {
        if (data.containsKey(RemoveDuplicates())) {
            CFBoolean val = data.get(RemoveDuplicates(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontCollectionOptions setRemoveDuplicates(boolean removeDuplicates) {
        data.put(RemoveDuplicates(), CFBoolean.valueOf(removeDuplicates));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontCollectionRemoveDuplicatesOption", optional=true)
    protected static native CFString RemoveDuplicates();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
