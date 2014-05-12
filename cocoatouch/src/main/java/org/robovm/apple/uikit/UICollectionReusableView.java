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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionReusableView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UICollectionReusableViewPtr extends Ptr<UICollectionReusableView, UICollectionReusableViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionReusableView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionReusableView() {}
    protected UICollectionReusableView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UICollectionReusableView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "reuseIdentifier")
    public native String getReuseIdentifier();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "prepareForReuse")
    public native void prepareForReuse();
    @Method(selector = "applyLayoutAttributes:")
    public native void applyLayoutAttributes(UICollectionViewLayoutAttributes layoutAttributes);
    @Method(selector = "willTransitionFromLayout:toLayout:")
    public native void willTransition(UICollectionViewLayout oldLayout, UICollectionViewLayout newLayout);
    @Method(selector = "didTransitionFromLayout:toLayout:")
    public native void didTransition(UICollectionViewLayout oldLayout, UICollectionViewLayout newLayout);
    /*</methods>*/
}
