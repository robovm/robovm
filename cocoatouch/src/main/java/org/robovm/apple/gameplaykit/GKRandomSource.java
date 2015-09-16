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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKRandomSource/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements GKRandom/*</implements>*/ {

    /*<ptr>*/public static class GKRandomSourcePtr extends Ptr<GKRandomSource, GKRandomSourcePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKRandomSource.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKRandomSource() {}
    protected GKRandomSource(SkipInit skipInit) { super(skipInit); }
    public GKRandomSource(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "arrayByShufflingObjectsInArray:")
    public native NSArray<?> shuffleArray(NSArray<?> array);
    @Method(selector = "sharedRandom")
    public static native GKRandomSource getSharedRandom();
    @Method(selector = "nextInt")
    public native @MachineSizedSInt long nextInt();
    @Method(selector = "nextIntWithUpperBound:")
    public native @MachineSizedUInt long nextInt(@MachineSizedUInt long upperBound);
    @Method(selector = "nextUniform")
    public native float nextUniform();
    @Method(selector = "nextBool")
    public native boolean nextBool();
    /*</methods>*/
}
