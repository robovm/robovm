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

    /*<constructors>*/
    public UITableViewCell() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/initWithStyle:reuseIdentifier:">- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("initWithStyle:reuseIdentifier:") public UITableViewCell(@Type("UITableViewCellStyle") UITableViewCellStyle style, @Type("NSString *") String reuseIdentifier) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryType">@property(nonatomic) UITableViewCellAccessoryType accessoryType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("accessoryType") public native @Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType getAccessoryType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryType">@property(nonatomic) UITableViewCellAccessoryType accessoryType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAccessoryType:") public native void setAccessoryType(@Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryView">@property(nonatomic, retain) UIView *accessoryView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("accessoryView") public native @Type("UIView *") UIView getAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryView">@property(nonatomic, retain) UIView *accessoryView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAccessoryView:") public native void setAccessoryView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backgroundView") public native @Type("UIView *") UIView getBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackgroundView:") public native void setBackgroundView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/contentView">@property(nonatomic, readonly, retain) UIView *contentView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentView") public native @Type("UIView *") UIView getContentView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/detailTextLabel">@property(nonatomic, readonly, retain) UILabel *detailTextLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("detailTextLabel") public native @Type("UILabel *") UILabel getDetailTextLabel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditing") public native @Type("BOOL") boolean isEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:") public native void setEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryType">@property(nonatomic) UITableViewCellAccessoryType editingAccessoryType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("editingAccessoryType") public native @Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType getEditingAccessoryType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryType">@property(nonatomic) UITableViewCellAccessoryType editingAccessoryType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setEditingAccessoryType:") public native void setEditingAccessoryType(@Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryView">@property(nonatomic, retain) UIView *editingAccessoryView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("editingAccessoryView") public native @Type("UIView *") UIView getEditingAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryView">@property(nonatomic, retain) UIView *editingAccessoryView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setEditingAccessoryView:") public native void setEditingAccessoryView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingStyle">@property(nonatomic, readonly) UITableViewCellEditingStyle editingStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("editingStyle") public native @Type("UITableViewCellEditingStyle") UITableViewCellEditingStyle getEditingStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/imageView">@property(nonatomic, readonly, retain) UIImageView *imageView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("imageView") public native @Type("UIImageView *") UIImageView getImageView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationLevel">@property(nonatomic) NSInteger indentationLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indentationLevel") public native @Type("NSInteger") int getIndentationLevel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationLevel">@property(nonatomic) NSInteger indentationLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setIndentationLevel:") public native void setIndentationLevel(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationWidth">@property(nonatomic) CGFloat indentationWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indentationWidth") public native @Type("CGFloat") float getIndentationWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationWidth">@property(nonatomic) CGFloat indentationWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setIndentationWidth:") public native void setIndentationWidth(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/multipleSelectionBackgroundView">@property(nonatomic,retain) UIView *multipleSelectionBackgroundView</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("multipleSelectionBackgroundView") public native @Type("UIView *") UIView getMultipleSelectionBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/multipleSelectionBackgroundView">@property(nonatomic,retain) UIView *multipleSelectionBackgroundView</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMultipleSelectionBackgroundView:") public native void setMultipleSelectionBackgroundView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/reuseIdentifier">@property(nonatomic, readonly, copy) NSString *reuseIdentifier</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reuseIdentifier") public native @Type("NSString *") String getReuseIdentifier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isSelected") public native @Type("BOOL") boolean isSelected();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelected:") public native void setSelected(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectedBackgroundView">@property(nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedBackgroundView") public native @Type("UIView *") UIView getSelectedBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectedBackgroundView">@property(nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedBackgroundView:") public native void setSelectedBackgroundView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectionStyle">@property(nonatomic) UITableViewCellSelectionStyle selectionStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectionStyle") public native @Type("UITableViewCellSelectionStyle") UITableViewCellSelectionStyle getSelectionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectionStyle">@property(nonatomic) UITableViewCellSelectionStyle selectionStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectionStyle:") public native void setSelectionStyle(@Type("UITableViewCellSelectionStyle") UITableViewCellSelectionStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/shouldIndentWhileEditing">@property(nonatomic) BOOL shouldIndentWhileEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("shouldIndentWhileEditing") public native @Type("BOOL") boolean isShouldIndentWhileEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/shouldIndentWhileEditing">@property(nonatomic) BOOL shouldIndentWhileEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShouldIndentWhileEditing:") public native void setShouldIndentWhileEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showingDeleteConfirmation">@property(nonatomic, readonly) BOOL showingDeleteConfirmation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showingDeleteConfirmation") public native @Type("BOOL") boolean isShowingDeleteConfirmation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showsReorderControl">@property(nonatomic) BOOL showsReorderControl</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsReorderControl") public native @Type("BOOL") boolean isShowsReorderControl();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showsReorderControl">@property(nonatomic) BOOL showsReorderControl</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsReorderControl:") public native void setShowsReorderControl(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/textLabel">@property(nonatomic, readonly, retain) UILabel *textLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("textLabel") public native @Type("UILabel *") UILabel getTextLabel();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/didTransitionToState:">- (void)didTransitionToState:(UITableViewCellStateMask)state</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("didTransitionToState:") public native @Type("void") void didTransitionToState(@Type("UITableViewCellStateMask") EnumSet<UITableViewCellStateMask> state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/prepareForReuse">- (void)prepareForReuse</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("prepareForReuse") public native @Type("void") void prepareForReuse();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:animated:") public native @Type("void") void setEditing(@Type("BOOL") boolean editing, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/setHighlighted:animated:">- (void)setHighlighted:(BOOL)highlighted animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setHighlighted:animated:") public native @Type("void") void setHighlighted(@Type("BOOL") boolean highlighted, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/setSelected:animated:">- (void)setSelected:(BOOL)selected animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelected:animated:") public native @Type("void") void setSelected(@Type("BOOL") boolean selected, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/willTransitionToState:">- (void)willTransitionToState:(UITableViewCellStateMask)state</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("willTransitionToState:") public native @Type("void") void willTransitionToState(@Type("UITableViewCellStateMask") EnumSet<UITableViewCellStateMask> state);
    /*</methods>*/

}
