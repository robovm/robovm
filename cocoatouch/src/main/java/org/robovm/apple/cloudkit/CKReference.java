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
import org.robovm.rt.annotation.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKReference/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements CKRecordValue/*</implements>*/ {

    /*<ptr>*/public static class CKReferencePtr extends Ptr<CKReference, CKReferencePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKReference.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKReference() {}
    protected CKReference(SkipInit skipInit) { super(skipInit); }
    public CKReference(CKRecordID recordID, CKReferenceAction action) { super((SkipInit) null); initObject(init(recordID, action)); }
    public CKReference(CKRecord record, CKReferenceAction action) { super((SkipInit) null); initObject(init(record, action)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "referenceAction")
    public native CKReferenceAction getReferenceAction();
    @Property(selector = "recordID")
    public native CKRecordID getRecordID();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRecordID:action:")
    protected native @Pointer long init(CKRecordID recordID, CKReferenceAction action);
    @Method(selector = "initWithRecord:action:")
    protected native @Pointer long init(CKRecord record, CKReferenceAction action);
    /*</methods>*/
}
