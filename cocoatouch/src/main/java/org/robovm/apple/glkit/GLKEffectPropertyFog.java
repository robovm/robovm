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
package org.robovm.apple.glkit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("GLKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKEffectPropertyFog/*</name>*/ 
    extends /*<extends>*/GLKEffectProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKEffectPropertyFogPtr extends Ptr<GLKEffectPropertyFog, GLKEffectPropertyFogPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GLKEffectPropertyFog.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKEffectPropertyFog() {}
    protected GLKEffectPropertyFog(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "enabled")
    public native byte getEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(byte v);
    @Property(selector = "mode")
    public native int getMode();
    @Property(selector = "setMode:")
    public native void setMode(int v);
    @Property(selector = "color")
    public native @ByVal GLKVector4 getColor();
    @Property(selector = "setColor:")
    public native void setColor(@ByVal GLKVector4 v);
    @Property(selector = "density")
    public native float getDensity();
    @Property(selector = "setDensity:")
    public native void setDensity(float v);
    @Property(selector = "start")
    public native float getStart();
    @Property(selector = "setStart:")
    public native void setStart(float v);
    @Property(selector = "end")
    public native float getEnd();
    @Property(selector = "setEnd:")
    public native void setEnd(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
