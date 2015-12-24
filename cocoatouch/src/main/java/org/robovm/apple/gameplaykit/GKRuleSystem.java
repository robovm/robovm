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
package org.robovm.apple.gameplaykit;

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

/*</javadoc>*/
/*<annotations>*/@Library("GameplayKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKRuleSystem/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKRuleSystemPtr extends Ptr<GKRuleSystem, GKRuleSystemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKRuleSystem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKRuleSystem() {}
    protected GKRuleSystem(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "state")
    public native NSMutableDictionary getState();
    @Property(selector = "rules")
    public native NSArray<GKRule> getRules();
    @Property(selector = "agenda")
    public native NSArray<GKRule> getAgenda();
    @Property(selector = "executed")
    public native NSArray<GKRule> getExecuted();
    @Property(selector = "facts")
    public native NSArray<?> getFacts();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "evaluate")
    public native void evaluate();
    @Method(selector = "addRule:")
    public native void addRule(GKRule rule);
    @Method(selector = "addRulesFromArray:")
    public native void addRules(NSArray<GKRule> rules);
    @Method(selector = "removeAllRules")
    public native void removeAllRules();
    @Method(selector = "gradeForFact:")
    public native float getGradeForFact(NSObject fact);
    @Method(selector = "minimumGradeForFacts:")
    public native float getMinimumGradeForFacts(NSArray<?> facts);
    @Method(selector = "maximumGradeForFacts:")
    public native float getMaximumGradeForFacts(NSArray<?> facts);
    @Method(selector = "assertFact:")
    public native void assertFact(NSObject fact);
    @Method(selector = "assertFact:grade:")
    public native void assertFact(NSObject fact, float grade);
    @Method(selector = "retractFact:")
    public native void retractFact(NSObject fact);
    @Method(selector = "retractFact:grade:")
    public native void retractFact(NSObject fact, float grade);
    @Method(selector = "reset")
    public native void reset();
    /*</methods>*/
}
