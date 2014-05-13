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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCompoundPredicate/*</name>*/ 
    extends /*<extends>*/NSPredicate/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSCompoundPredicatePtr extends Ptr<NSCompoundPredicate, NSCompoundPredicatePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCompoundPredicate.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSCompoundPredicate() {}
    protected NSCompoundPredicate(SkipInit skipInit) { super(skipInit); }
    public NSCompoundPredicate(NSCompoundPredicateType type, NSArray<NSPredicate> subpredicates) { super((SkipInit) null); initObject(initWithType$subpredicates$(type, subpredicates)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithType:subpredicates:")
    protected native @Pointer long initWithType$subpredicates$(NSCompoundPredicateType type, NSArray<NSPredicate> subpredicates);
    @Method(selector = "compoundPredicateType")
    public native NSCompoundPredicateType getCompoundPredicateType();
    @Method(selector = "subpredicates")
    public native NSArray<NSPredicate> getSubpredicates();
    @Method(selector = "andPredicateWithSubpredicates:")
    public static native NSPredicate createAndPredicate(NSArray<NSPredicate> subpredicates);
    @Method(selector = "orPredicateWithSubpredicates:")
    public static native NSPredicate createOrPredicate(NSArray<NSPredicate> subpredicates);
    @Method(selector = "notPredicateWithSubpredicate:")
    public static native NSPredicate createNotPredicate(NSPredicate predicate);
    /*</methods>*/
}
