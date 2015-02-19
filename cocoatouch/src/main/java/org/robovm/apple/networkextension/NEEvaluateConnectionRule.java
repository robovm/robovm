/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.networkextension;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("NetworkExtension") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NEEvaluateConnectionRule/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NEEvaluateConnectionRulePtr extends Ptr<NEEvaluateConnectionRule, NEEvaluateConnectionRulePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NEEvaluateConnectionRule.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NEEvaluateConnectionRule() {}
    protected NEEvaluateConnectionRule(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NEEvaluateConnectionRule(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> domains, NEEvaluateConnectionRuleAction action) { super((SkipInit) null); initObject(init(domains, action)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "action")
    public native NEEvaluateConnectionRuleAction getAction();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "matchDomains")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getMatchDomains();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "useDNSServers")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getDNSServers();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setUseDNSServers:")
    public native void setDNSServers(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "probeURL")
    public native NSURL getProbeURL();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setProbeURL:")
    public native void setProbeURL(NSURL v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "initWithMatchDomains:andAction:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> domains, NEEvaluateConnectionRuleAction action);
    /*</methods>*/
}
