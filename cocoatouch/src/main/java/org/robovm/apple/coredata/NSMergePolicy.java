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
package org.robovm.apple.coredata;

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
import org.robovm.apple.foundation.NSError.NSErrorPtr;

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMergePolicy/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMergePolicyPtr extends Ptr<NSMergePolicy, NSMergePolicyPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMergePolicy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMergePolicy() {}
    protected NSMergePolicy(SkipInit skipInit) { super(skipInit); }
    public NSMergePolicy(NSMergePolicyType ty) { super((SkipInit) null); initObject(init(ty)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "mergeType")
    public native NSMergePolicyType getMergeType();
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSErrorMergePolicy", optional=true)
    public static native NSMergePolicy getErrorMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMergeByPropertyStoreTrumpMergePolicy", optional=true)
    public static native NSMergePolicy getMergeByPropertyStoreTrumpMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMergeByPropertyObjectTrumpMergePolicy", optional=true)
    public static native NSMergePolicy getMergeByPropertyObjectTrumpMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSOverwriteMergePolicy", optional=true)
    public static native NSMergePolicy getOverwriteMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSRollbackMergePolicy", optional=true)
    public static native NSMergePolicy getRollbackMergePolicy();
    
    @Method(selector = "initWithMergeType:")
    protected native @Pointer long init(NSMergePolicyType ty);
    public boolean resolveConflicts(NSArray<NSMergeConflict> list) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = resolveConflicts(list, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "resolveConflicts:error:")
    private native boolean resolveConflicts(NSArray<NSMergeConflict> list, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 9.0 and later.
     */
    public boolean resolveOptimisticLockingVersionConflicts(NSArray<NSMergeConflict> list) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = resolveOptimisticLockingVersionConflicts(list, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "resolveOptimisticLockingVersionConflicts:error:")
    private native boolean resolveOptimisticLockingVersionConflicts(NSArray<NSMergeConflict> list, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 9.0 and later.
     */
    public boolean resolveConstraintConflicts(NSArray<NSConstraintConflict> list) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = resolveConstraintConflicts(list, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "resolveConstraintConflicts:error:")
    private native boolean resolveConstraintConflicts(NSArray<NSConstraintConflict> list, NSError.NSErrorPtr error);
    /*</methods>*/
}
