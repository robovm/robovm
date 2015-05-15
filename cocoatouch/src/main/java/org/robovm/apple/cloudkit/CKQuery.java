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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKQuery/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKQueryPtr extends Ptr<CKQuery, CKQueryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKQuery.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKQuery() {}
    protected CKQuery(SkipInit skipInit) { super(skipInit); }
    public CKQuery(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    public CKQuery(String recordType, NSPredicate predicate) { super((SkipInit) null); initObject(init(recordType, predicate)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "recordType")
    public native String getRecordType();
    @Property(selector = "predicate")
    public native NSPredicate getPredicate();
    @Property(selector = "sortDescriptors")
    public native NSArray<NSSortDescriptor> getSortDescriptors();
    @Property(selector = "setSortDescriptors:")
    public native void setSortDescriptors(NSArray<NSSortDescriptor> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "initWithRecordType:predicate:")
    protected native @Pointer long init(String recordType, NSPredicate predicate);
    /*</methods>*/
}
