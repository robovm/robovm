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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITableViewCell/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIGestureRecognizerDelegate/*</implements>*/ {

    /*<ptr>*/public static class UITableViewCellPtr extends Ptr<UITableViewCell, UITableViewCellPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITableViewCell.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITableViewCell() {}
    protected UITableViewCell(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewCell(UITableViewCellStyle style, String reuseIdentifier) { super((SkipInit) null); initObject(initWithStyle$reuseIdentifier$(style, reuseIdentifier)); }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    public UITableViewCell(@ByVal CGRect frame, String reuseIdentifier) { super((SkipInit) null); initObject(initWithFrame$reuseIdentifier$(frame, reuseIdentifier)); }
    /*</constructors>*/
    
    public UITableViewCell(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "imageView")
    public native UIImageView getImageView();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "textLabel")
    public native UILabel getTextLabel();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "detailTextLabel")
    public native UILabel getDetailTextLabel();
    @Property(selector = "contentView")
    public native UIView getContentView();
    @Property(selector = "backgroundView")
    public native UIView getBackgroundView();
    @Property(selector = "setBackgroundView:")
    public native void setBackgroundView(UIView v);
    @Property(selector = "selectedBackgroundView")
    public native UIView getSelectedBackgroundView();
    @Property(selector = "setSelectedBackgroundView:")
    public native void setSelectedBackgroundView(UIView v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "multipleSelectionBackgroundView")
    public native UIView getMultipleSelectionBackgroundView();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setMultipleSelectionBackgroundView:")
    public native void setMultipleSelectionBackgroundView(UIView v);
    @Property(selector = "reuseIdentifier")
    public native String getReuseIdentifier();
    @Property(selector = "selectionStyle")
    public native UITableViewCellSelectionStyle getSelectionStyle();
    @Property(selector = "setSelectionStyle:")
    public native void setSelectionStyle(UITableViewCellSelectionStyle v);
    @Property(selector = "isSelected")
    public native boolean isSelected();
    @Property(selector = "setSelected:")
    public native void setSelected(boolean v);
    @Property(selector = "isHighlighted")
    public native boolean isHighlighted();
    @Property(selector = "setHighlighted:")
    public native void setHighlighted(boolean v);
    @Property(selector = "editingStyle")
    public native UITableViewCellEditingStyle getEditingStyle();
    @Property(selector = "showsReorderControl")
    public native boolean isShowsReorderControl();
    @Property(selector = "setShowsReorderControl:")
    public native void setShowsReorderControl(boolean v);
    @Property(selector = "shouldIndentWhileEditing")
    public native boolean isShouldIndentWhileEditing();
    @Property(selector = "setShouldIndentWhileEditing:")
    public native void setShouldIndentWhileEditing(boolean v);
    @Property(selector = "accessoryType")
    public native UITableViewCellAccessoryType getAccessoryType();
    @Property(selector = "setAccessoryType:")
    public native void setAccessoryType(UITableViewCellAccessoryType v);
    @Property(selector = "accessoryView")
    public native UIView getAccessoryView();
    @Property(selector = "setAccessoryView:")
    public native void setAccessoryView(UIView v);
    @Property(selector = "editingAccessoryType")
    public native UITableViewCellAccessoryType getEditingAccessoryType();
    @Property(selector = "setEditingAccessoryType:")
    public native void setEditingAccessoryType(UITableViewCellAccessoryType v);
    @Property(selector = "editingAccessoryView")
    public native UIView getEditingAccessoryView();
    @Property(selector = "setEditingAccessoryView:")
    public native void setEditingAccessoryView(UIView v);
    @Property(selector = "indentationLevel")
    public native @MachineSizedSInt long getIndentationLevel();
    @Property(selector = "setIndentationLevel:")
    public native void setIndentationLevel(@MachineSizedSInt long v);
    @Property(selector = "indentationWidth")
    public native @MachineSizedFloat double getIndentationWidth();
    @Property(selector = "setIndentationWidth:")
    public native void setIndentationWidth(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "separatorInset")
    public native @ByVal UIEdgeInsets getSeparatorInset();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setSeparatorInset:")
    public native void setSeparatorInset(@ByVal UIEdgeInsets v);
    @Property(selector = "isEditing")
    public native boolean isEditing();
    @Property(selector = "setEditing:")
    public native void setEditing(boolean v);
    @Property(selector = "showingDeleteConfirmation")
    public native boolean isShowingDeleteConfirmation();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "text")
    public native String getText();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setText:")
    public native void setText(String v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "font")
    public native UIFont getFont();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setFont:")
    public native void setFont(UIFont v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "textAlignment")
    public native NSTextAlignment getTextAlignment();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setTextAlignment:")
    public native void setTextAlignment(NSTextAlignment v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "lineBreakMode")
    public native NSLineBreakMode getLineBreakMode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setLineBreakMode:")
    public native void setLineBreakMode(NSLineBreakMode v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "textColor")
    public native UIColor getTextColor();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setTextColor:")
    public native void setTextColor(UIColor v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "selectedTextColor")
    public native UIColor getSelectedTextColor();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setSelectedTextColor:")
    public native void setSelectedTextColor(UIColor v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "image")
    public native UIImage getImage();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setImage:")
    public native void setImage(UIImage v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "selectedImage")
    public native UIImage getSelectedImage();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setSelectedImage:")
    public native void setSelectedImage(UIImage v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "hidesAccessoryWhenEditing")
    public native boolean isHidesAccessoryWhenEditing();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setHidesAccessoryWhenEditing:")
    public native void setHidesAccessoryWhenEditing(boolean v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "target")
    public native NSObject getTarget();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setTarget:", strongRef = true)
    public native void setTarget(NSObject v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "editAction")
    public native Selector getEditAction();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setEditAction:")
    public native void setEditAction(Selector v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "accessoryAction")
    public native Selector getAccessoryAction();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setAccessoryAction:")
    public native void setAccessoryAction(Selector v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "initWithStyle:reuseIdentifier:")
    protected native @Pointer long initWithStyle$reuseIdentifier$(UITableViewCellStyle style, String reuseIdentifier);
    @Method(selector = "prepareForReuse")
    public native void prepareForReuse();
    @Method(selector = "setSelected:animated:")
    public native void setSelected(boolean selected, boolean animated);
    @Method(selector = "setHighlighted:animated:")
    public native void setHighlighted(boolean highlighted, boolean animated);
    @Method(selector = "setEditing:animated:")
    public native void setEditing(boolean editing, boolean animated);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "willTransitionToState:")
    public native void willTransitionToState(UITableViewCellStateMask state);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "didTransitionToState:")
    public native void didTransitionToState(UITableViewCellStateMask state);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Method(selector = "initWithFrame:reuseIdentifier:")
    protected native @Pointer long initWithFrame$reuseIdentifier$(@ByVal CGRect frame, String reuseIdentifier);
    @Method(selector = "gestureRecognizerShouldBegin:")
    public native boolean shouldBegin(UIGestureRecognizer gestureRecognizer);
    @Method(selector = "gestureRecognizer:shouldRecognizeSimultaneouslyWithGestureRecognizer:")
    public native boolean shouldRecognizeSimultaneously(UIGestureRecognizer gestureRecognizer, UIGestureRecognizer otherGestureRecognizer);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "gestureRecognizer:shouldRequireFailureOfGestureRecognizer:")
    public native boolean shouldRequireFailure(UIGestureRecognizer gestureRecognizer, UIGestureRecognizer otherGestureRecognizer);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "gestureRecognizer:shouldBeRequiredToFailByGestureRecognizer:")
    public native boolean shouldBeRequiredToFail(UIGestureRecognizer gestureRecognizer, UIGestureRecognizer otherGestureRecognizer);
    @Method(selector = "gestureRecognizer:shouldReceiveTouch:")
    public native boolean shouldReceiveTouch(UIGestureRecognizer gestureRecognizer, UITouch touch);
    /*</methods>*/
}
