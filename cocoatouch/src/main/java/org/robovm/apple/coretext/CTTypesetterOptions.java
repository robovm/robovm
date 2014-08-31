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
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTTypesetterOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data = CFMutableDictionary.create();
    
    public CTTypesetterOptions() {
    }
    /*<bind>*/static { Bro.bind(CTTypesetterOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary () {
        return data;
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public void setBidiProcessingDisabled(boolean disable) {
        data.put(DisableBidiProcessing(), CFBoolean.valueOf(disable));
    }
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public boolean isBidiProcessingDisabled() {
        if (data.containsKey(DisableBidiProcessing())) {
            CFBoolean val = data.get(DisableBidiProcessing(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public int getEmbeddingLevel() {
        if (data.containsKey(ForcedEmbeddingLevel())) {
            CFNumber val = data.get(ForcedEmbeddingLevel(), CFNumber.class);
            return val.intValue();
        }
        return -1;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setEmbeddingLevel(int level) {
        data.put(ForcedEmbeddingLevel(), CFNumber.valueOf(level));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCTTypesetterOptionDisableBidiProcessing", optional=true)
    protected static native CFString DisableBidiProcessing();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTTypesetterOptionForcedEmbeddingLevel", optional=true)
    protected static native CFString ForcedEmbeddingLevel();
    /*</methods>*/
}
