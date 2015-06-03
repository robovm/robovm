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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKModifyBadgeOperation/*</name>*/ 
    extends /*<extends>*/CKOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKModifyBadgeOperationPtr extends Ptr<CKModifyBadgeOperation, CKModifyBadgeOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKModifyBadgeOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKModifyBadgeOperation() {}
    protected CKModifyBadgeOperation(SkipInit skipInit) { super(skipInit); }
    public CKModifyBadgeOperation(@MachineSizedUInt long badgeValue) { super((SkipInit) null); initObject(init(badgeValue)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "badgeValue")
    public native @MachineSizedUInt long getBadgeValue();
    @Property(selector = "setBadgeValue:")
    public native void setBadgeValue(@MachineSizedUInt long v);
    @Property(selector = "modifyBadgeCompletionBlock")
    public native @Block VoidBlock1<NSError> getModifyBadgeCompletionBlock();
    @Property(selector = "setModifyBadgeCompletionBlock:")
    public native void setModifyBadgeCompletionBlock(@Block VoidBlock1<NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithBadgeValue:")
    protected native @Pointer long init(@MachineSizedUInt long badgeValue);
    /*</methods>*/
}
