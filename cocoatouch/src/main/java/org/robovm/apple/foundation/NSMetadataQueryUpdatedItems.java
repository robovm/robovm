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
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMetadataQueryUpdatedItems/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSMetadataQueryUpdatedItems(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(NSMetadataQueryUpdatedItems.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<NSMetadataItem> getAddedItems() {
        if (data.containsKey(UpdateAddedItemsKey())) {
            NSArray<NSMetadataItem> val = (NSArray<NSMetadataItem>)data.get(UpdateAddedItemsKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<NSMetadataItem> getChangedItems() {
        if (data.containsKey(UpdateChangedItemsKey())) {
            NSArray<NSMetadataItem> val = (NSArray<NSMetadataItem>)data.get(UpdateChangedItemsKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<NSMetadataItem> getRemovedItems() {
        if (data.containsKey(UpdateRemovedItemsKey())) {
            NSArray<NSMetadataItem> val = (NSArray<NSMetadataItem>)data.get(UpdateRemovedItemsKey());
            return val;
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateAddedItemsKey", optional=true)
    protected static native NSString UpdateAddedItemsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateChangedItemsKey", optional=true)
    protected static native NSString UpdateChangedItemsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateRemovedItemsKey", optional=true)
    protected static native NSString UpdateRemovedItemsKey();
    /*</methods>*/
}
