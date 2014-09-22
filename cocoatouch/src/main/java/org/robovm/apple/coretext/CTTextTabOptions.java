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
@Marshaler(CTTextTabOptions.Marshaler.class)
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTTextTabOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CTTextTabOptions toObject(Class<CTTextTabOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTTextTabOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CTTextTabOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CTTextTabOptions(CFDictionary data) {
        this.data = data;
    }
    public CTTextTabOptions() {
    	this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CTTextTabOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary () {
        return data;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSCharacterSet getTerminatingCharacter() {
        if (data.containsKey(TerminatorsAttributeName())) {
            NSCharacterSet val = data.get(TerminatorsAttributeName(), NSCharacterSet.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTTextTabOptions setTerminatingCharacter(NSCharacterSet character) {
        data.put(TerminatorsAttributeName(), character);
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTTabColumnTerminatorsAttributeName", optional=true)
    protected static native CFString TerminatorsAttributeName();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
