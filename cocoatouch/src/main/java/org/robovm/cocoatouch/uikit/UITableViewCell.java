/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html">UITableViewCell Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITableViewCell /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableViewCell /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITableViewCell /*</name>*/.class);

    /*<constructors>*/
    protected UITableViewCell(SkipInit skipInit) { super(skipInit); }
    public UITableViewCell() {}
    
    private static final Selector initWithStyle$reuseIdentifier$ = Selector.register("initWithStyle:reuseIdentifier:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithStyle(UITableViewCell __self__, Selector __cmd__, UITableViewCellStyle style, String reuseIdentifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/initWithStyle:reuseIdentifier:">- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewCell(UITableViewCellStyle style, String reuseIdentifier) {
        super((SkipInit) null);
        objc_initWithStyle(this, initWithStyle$reuseIdentifier$, style, reuseIdentifier);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryType">@property(nonatomic) UITableViewCellAccessoryType accessoryType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("accessoryType") public native UITableViewCellAccessoryType getAccessoryType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryType">@property(nonatomic) UITableViewCellAccessoryType accessoryType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAccessoryType:") public native void setAccessoryType(UITableViewCellAccessoryType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryView">@property(nonatomic, retain) UIView *accessoryView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("accessoryView") public native UIView getAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryView">@property(nonatomic, retain) UIView *accessoryView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAccessoryView:") public native void setAccessoryView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backgroundView") public native UIView getBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackgroundView:") public native void setBackgroundView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/contentView">@property(nonatomic, readonly, retain) UIView *contentView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentView") public native UIView getContentView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/detailTextLabel">@property(nonatomic, readonly, retain) UILabel *detailTextLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("detailTextLabel") public native UILabel getDetailTextLabel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditing") public native boolean isEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:") public native void setEditing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryType">@property(nonatomic) UITableViewCellAccessoryType editingAccessoryType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("editingAccessoryType") public native UITableViewCellAccessoryType getEditingAccessoryType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryType">@property(nonatomic) UITableViewCellAccessoryType editingAccessoryType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setEditingAccessoryType:") public native void setEditingAccessoryType(UITableViewCellAccessoryType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryView">@property(nonatomic, retain) UIView *editingAccessoryView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("editingAccessoryView") public native UIView getEditingAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryView">@property(nonatomic, retain) UIView *editingAccessoryView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setEditingAccessoryView:") public native void setEditingAccessoryView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingStyle">@property(nonatomic, readonly) UITableViewCellEditingStyle editingStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("editingStyle") public native UITableViewCellEditingStyle getEditingStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isHighlighted") public native boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/imageView">@property(nonatomic, readonly, retain) UIImageView *imageView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("imageView") public native UIImageView getImageView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationLevel">@property(nonatomic) NSInteger indentationLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indentationLevel") public native int getIndentationLevel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationLevel">@property(nonatomic) NSInteger indentationLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setIndentationLevel:") public native void setIndentationLevel(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationWidth">@property(nonatomic) CGFloat indentationWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indentationWidth") public native float getIndentationWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationWidth">@property(nonatomic) CGFloat indentationWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setIndentationWidth:") public native void setIndentationWidth(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/multipleSelectionBackgroundView">@property(nonatomic,retain) UIView *multipleSelectionBackgroundView</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("multipleSelectionBackgroundView") public native UIView getMultipleSelectionBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/multipleSelectionBackgroundView">@property(nonatomic,retain) UIView *multipleSelectionBackgroundView</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMultipleSelectionBackgroundView:") public native void setMultipleSelectionBackgroundView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/reuseIdentifier">@property(nonatomic, readonly, copy) NSString *reuseIdentifier</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reuseIdentifier") public native String getReuseIdentifier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isSelected") public native boolean isSelected();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelected:") public native void setSelected(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectedBackgroundView">@property(nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedBackgroundView") public native UIView getSelectedBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectedBackgroundView">@property(nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedBackgroundView:") public native void setSelectedBackgroundView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectionStyle">@property(nonatomic) UITableViewCellSelectionStyle selectionStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectionStyle") public native UITableViewCellSelectionStyle getSelectionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectionStyle">@property(nonatomic) UITableViewCellSelectionStyle selectionStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectionStyle:") public native void setSelectionStyle(UITableViewCellSelectionStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/shouldIndentWhileEditing">@property(nonatomic) BOOL shouldIndentWhileEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("shouldIndentWhileEditing") public native boolean isShouldIndentWhileEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/shouldIndentWhileEditing">@property(nonatomic) BOOL shouldIndentWhileEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShouldIndentWhileEditing:") public native void setShouldIndentWhileEditing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showingDeleteConfirmation">@property(nonatomic, readonly) BOOL showingDeleteConfirmation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showingDeleteConfirmation") public native boolean isShowingDeleteConfirmation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showsReorderControl">@property(nonatomic) BOOL showsReorderControl</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsReorderControl") public native boolean isShowsReorderControl();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showsReorderControl">@property(nonatomic) BOOL showsReorderControl</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsReorderControl:") public native void setShowsReorderControl(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/textLabel">@property(nonatomic, readonly, retain) UILabel *textLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("textLabel") public native UILabel getTextLabel();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector didTransitionToState$ = Selector.register("didTransitionToState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didTransitionToState(UITableViewCell __self__, Selector __cmd__, EnumSet<UITableViewCellStateMask> state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didTransitionToStateSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, EnumSet<UITableViewCellStateMask> state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/didTransitionToState:">- (void)didTransitionToState:(UITableViewCellStateMask)state</a>
     * @since Available in iOS 3.0 and later.
     */
    public void didTransitionToState(EnumSet<UITableViewCellStateMask> state) {
        if (customClass) { objc_didTransitionToStateSuper(getSuper(), this, didTransitionToState$, state); } else { objc_didTransitionToState(this, didTransitionToState$, state); }
    }
    
    private static final Selector prepareForReuse = Selector.register("prepareForReuse");
    @Bridge(symbol = "objc_msgSend") private native static void objc_prepareForReuse(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_prepareForReuseSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/prepareForReuse">- (void)prepareForReuse</a>
     * @since Available in iOS 2.0 and later.
     */
    public void prepareForReuse() {
        if (customClass) { objc_prepareForReuseSuper(getSuper(), this, prepareForReuse); } else { objc_prepareForReuse(this, prepareForReuse); }
    }
    
    private static final Selector setEditing$animated$ = Selector.register("setEditing:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditing(UITableViewCell __self__, Selector __cmd__, boolean editing, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditingSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean editing, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing, boolean animated) {
        if (customClass) { objc_setEditingSuper(getSuper(), this, setEditing$animated$, editing, animated); } else { objc_setEditing(this, setEditing$animated$, editing, animated); }
    }
    
    private static final Selector setHighlighted$animated$ = Selector.register("setHighlighted:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setHighlighted(UITableViewCell __self__, Selector __cmd__, boolean highlighted, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setHighlightedSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean highlighted, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/setHighlighted:animated:">- (void)setHighlighted:(BOOL)highlighted animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setHighlighted(boolean highlighted, boolean animated) {
        if (customClass) { objc_setHighlightedSuper(getSuper(), this, setHighlighted$animated$, highlighted, animated); } else { objc_setHighlighted(this, setHighlighted$animated$, highlighted, animated); }
    }
    
    private static final Selector setSelected$animated$ = Selector.register("setSelected:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSelected(UITableViewCell __self__, Selector __cmd__, boolean selected, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSelectedSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean selected, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/setSelected:animated:">- (void)setSelected:(BOOL)selected animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelected(boolean selected, boolean animated) {
        if (customClass) { objc_setSelectedSuper(getSuper(), this, setSelected$animated$, selected, animated); } else { objc_setSelected(this, setSelected$animated$, selected, animated); }
    }
    
    private static final Selector willTransitionToState$ = Selector.register("willTransitionToState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_willTransitionToState(UITableViewCell __self__, Selector __cmd__, EnumSet<UITableViewCellStateMask> state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willTransitionToStateSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, EnumSet<UITableViewCellStateMask> state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/willTransitionToState:">- (void)willTransitionToState:(UITableViewCellStateMask)state</a>
     * @since Available in iOS 3.0 and later.
     */
    public void willTransitionToState(EnumSet<UITableViewCellStateMask> state) {
        if (customClass) { objc_willTransitionToStateSuper(getSuper(), this, willTransitionToState$, state); } else { objc_willTransitionToState(this, willTransitionToState$, state); }
    }
    /*</methods>*/

}
