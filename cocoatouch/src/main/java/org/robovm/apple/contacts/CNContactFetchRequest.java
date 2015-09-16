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
package org.robovm.apple.contacts;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContactFetchRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNContactFetchRequestPtr extends Ptr<CNContactFetchRequest, CNContactFetchRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNContactFetchRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNContactFetchRequest() {}
    protected CNContactFetchRequest(SkipInit skipInit) { super(skipInit); }
    public CNContactFetchRequest(@org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> keysToFetch) { super((SkipInit) null); initObject(init(keysToFetch)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "predicate")
    public native NSPredicate getPredicate();
    @Property(selector = "setPredicate:")
    public native void setPredicate(NSPredicate v);
    @Property(selector = "keysToFetch")
    public native @org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> getKeysToFetch();
    @Property(selector = "setKeysToFetch:")
    public native void setKeysToFetch(@org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> v);
    @Property(selector = "mutableObjects")
    public native boolean isMutableObjects();
    @Property(selector = "setMutableObjects:")
    public native void setMutableObjects(boolean v);
    @Property(selector = "unifyResults")
    public native boolean isUnifyResults();
    @Property(selector = "setUnifyResults:")
    public native void setUnifyResults(boolean v);
    @Property(selector = "sortOrder")
    public native CNContactSortOrder getSortOrder();
    @Property(selector = "setSortOrder:")
    public native void setSortOrder(CNContactSortOrder v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithKeysToFetch:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> keysToFetch);
    /*</methods>*/
}
