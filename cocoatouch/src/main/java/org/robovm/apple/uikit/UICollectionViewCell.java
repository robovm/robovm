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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewCell/*</name>*/ 
    extends /*<extends>*/UICollectionReusableView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UICollectionViewCellPtr extends Ptr<UICollectionViewCell, UICollectionViewCellPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionViewCell.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionViewCell() {}
    protected UICollectionViewCell(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UICollectionViewCell(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "contentView")
    public native UIView getContentView();
    @Property(selector = "isSelected")
    public native boolean isSelected();
    @Property(selector = "setSelected:")
    public native void setSelected(boolean v);
    @Property(selector = "isHighlighted")
    public native boolean isHighlighted();
    @Property(selector = "setHighlighted:")
    public native void setHighlighted(boolean v);
    @Property(selector = "backgroundView")
    public native UIView getBackgroundView();
    @Property(selector = "setBackgroundView:")
    public native void setBackgroundView(UIView v);
    @Property(selector = "selectedBackgroundView")
    public native UIView getSelectedBackgroundView();
    @Property(selector = "setSelectedBackgroundView:")
    public native void setSelectedBackgroundView(UIView v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
