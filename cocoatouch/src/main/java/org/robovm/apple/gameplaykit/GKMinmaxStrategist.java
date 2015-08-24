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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKMinmaxStrategist/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKMinmaxStrategistPtr extends Ptr<GKMinmaxStrategist, GKMinmaxStrategistPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKMinmaxStrategist.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKMinmaxStrategist() {}
    protected GKMinmaxStrategist(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "gameModel")
    public native GKGameModel getGameModel();
    @Property(selector = "setGameModel:")
    public native void setGameModel(GKGameModel v);
    @Property(selector = "maxLookAheadDepth")
    public native @MachineSizedSInt long getMaxLookAheadDepth();
    @Property(selector = "setMaxLookAheadDepth:")
    public native void setMaxLookAheadDepth(@MachineSizedSInt long v);
    @Property(selector = "randomSource")
    public native GKRandom getRandomSource();
    @Property(selector = "setRandomSource:")
    public native void setRandomSource(GKRandom v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "bestMoveForPlayer:")
    public native GKGameModelUpdate getBestMoveForPlayer(GKGameModelPlayer player);
    @Method(selector = "randomMoveForPlayer:fromNumberOfBestMoves:")
    public native GKGameModelUpdate getRandomMoveForPlayer(GKGameModelPlayer player, @MachineSizedSInt long numMovesToConsider);
    /*</methods>*/
}
