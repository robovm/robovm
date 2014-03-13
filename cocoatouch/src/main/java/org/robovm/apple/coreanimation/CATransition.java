/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATransition/*</name>*/ 
    extends /*<extends>*/CAAnimation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CATransitionPtr extends Ptr<CATransition, CATransitionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CATransition.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CATransition() {}
    protected CATransition(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "type")
    public native String getType();
    @Property(selector = "setType:")
    public native void setType(String v);
    @Property(selector = "subtype")
    public native String getSubtype();
    @Property(selector = "setSubtype:")
    public native void setSubtype(String v);
    @Property(selector = "startProgress")
    public native float getStartProgress();
    @Property(selector = "setStartProgress:")
    public native void setStartProgress(float v);
    @Property(selector = "endProgress")
    public native float getEndProgress();
    @Property(selector = "setEndProgress:")
    public native void setEndProgress(float v);
    @Property(selector = "filter")
    public native NSObject getFilter();
    @Property(selector = "setFilter:")
    public native void setFilter(NSObject v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCATransitionFade")
    public static native String TypeFade();
    @GlobalValue(symbol="kCATransitionMoveIn")
    public static native String TypeMoveIn();
    @GlobalValue(symbol="kCATransitionPush")
    public static native String TypePush();
    @GlobalValue(symbol="kCATransitionReveal")
    public static native String TypeReveal();
    @GlobalValue(symbol="kCATransitionFromRight")
    public static native String SubtypeFromRight();
    @GlobalValue(symbol="kCATransitionFromLeft")
    public static native String SubtypeFromLeft();
    @GlobalValue(symbol="kCATransitionFromTop")
    public static native String SubtypeFromTop();
    @GlobalValue(symbol="kCATransitionFromBottom")
    public static native String SubtypeFromBottom();
    
    
    /*</methods>*/
}
