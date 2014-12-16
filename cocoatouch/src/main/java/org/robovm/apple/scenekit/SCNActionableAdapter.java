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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNActionableAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNActionable/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("runAction:")
    public void runAction(SCNAction action) { throw new UnsupportedOperationException(); }
    @NotImplemented("runAction:completionHandler:")
    public void runAction(SCNAction action, @Block Runnable block) { throw new UnsupportedOperationException(); }
    @NotImplemented("runAction:forKey:")
    public void runAction(SCNAction action, String key) { throw new UnsupportedOperationException(); }
    @NotImplemented("runAction:forKey:completionHandler:")
    public void runAction(SCNAction action, String key, @Block Runnable block) { throw new UnsupportedOperationException(); }
    @NotImplemented("hasActions")
    public boolean hasActions() { throw new UnsupportedOperationException(); }
    @NotImplemented("actionForKey:")
    public SCNAction getAction(String key) { throw new UnsupportedOperationException(); }
    @NotImplemented("removeActionForKey:")
    public void removeAction(String key) { throw new UnsupportedOperationException(); }
    @NotImplemented("removeAllActions")
    public void removeAllActions() { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
