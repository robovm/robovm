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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITableViewCell /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableViewCell /*</name>*/.class);
    }

    /*<constructors>*/
    public UITableViewCell() {}
    @Bind("initWithStyle:reuseIdentifier:") public UITableViewCell(@Type("UITableViewCellStyle") UITableViewCellStyle style, @Type("NSString *") String reuseIdentifier) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("accessoryType") public native @Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType getAccessoryType();
    @Bind("setAccessoryType:") public native void setAccessoryType(@Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType v);
    @Bind("accessoryView") public native @Type("UIView *") UIView getAccessoryView();
    @Bind("setAccessoryView:") public native void setAccessoryView(@Type("UIView *") UIView v);
    @Bind("backgroundView") public native @Type("UIView *") UIView getBackgroundView();
    @Bind("setBackgroundView:") public native void setBackgroundView(@Type("UIView *") UIView v);
    @Bind("contentView") public native @Type("UIView *") UIView getContentView();
    @Bind("detailTextLabel") public native @Type("UILabel *") UILabel getDetailTextLabel();
    @Bind("isEditing") public native @Type("BOOL") boolean isEditing();
    @Bind("setEditing:") public native void setEditing(@Type("BOOL") boolean v);
    @Bind("editingAccessoryType") public native @Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType getEditingAccessoryType();
    @Bind("setEditingAccessoryType:") public native void setEditingAccessoryType(@Type("UITableViewCellAccessoryType") UITableViewCellAccessoryType v);
    @Bind("editingAccessoryView") public native @Type("UIView *") UIView getEditingAccessoryView();
    @Bind("setEditingAccessoryView:") public native void setEditingAccessoryView(@Type("UIView *") UIView v);
    @Bind("editingStyle") public native @Type("UITableViewCellEditingStyle") UITableViewCellEditingStyle getEditingStyle();
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    @Bind("imageView") public native @Type("UIImageView *") UIImageView getImageView();
    @Bind("indentationLevel") public native @Type("NSInteger") int getIndentationLevel();
    @Bind("setIndentationLevel:") public native void setIndentationLevel(@Type("NSInteger") int v);
    @Bind("indentationWidth") public native @Type("CGFloat") float getIndentationWidth();
    @Bind("setIndentationWidth:") public native void setIndentationWidth(@Type("CGFloat") float v);
    @Bind("multipleSelectionBackgroundView") public native @Type("UIView *") UIView getMultipleSelectionBackgroundView();
    @Bind("setMultipleSelectionBackgroundView:") public native void setMultipleSelectionBackgroundView(@Type("UIView *") UIView v);
    @Bind("reuseIdentifier") public native @Type("NSString *") String getReuseIdentifier();
    @Bind("isSelected") public native @Type("BOOL") boolean isSelected();
    @Bind("setSelected:") public native void setSelected(@Type("BOOL") boolean v);
    @Bind("selectedBackgroundView") public native @Type("UIView *") UIView getSelectedBackgroundView();
    @Bind("setSelectedBackgroundView:") public native void setSelectedBackgroundView(@Type("UIView *") UIView v);
    @Bind("selectionStyle") public native @Type("UITableViewCellSelectionStyle") UITableViewCellSelectionStyle getSelectionStyle();
    @Bind("setSelectionStyle:") public native void setSelectionStyle(@Type("UITableViewCellSelectionStyle") UITableViewCellSelectionStyle v);
    @Bind("shouldIndentWhileEditing") public native @Type("BOOL") boolean isShouldIndentWhileEditing();
    @Bind("setShouldIndentWhileEditing:") public native void setShouldIndentWhileEditing(@Type("BOOL") boolean v);
    @Bind("showingDeleteConfirmation") public native @Type("BOOL") boolean isShowingDeleteConfirmation();
    @Bind("showsReorderControl") public native @Type("BOOL") boolean isShowsReorderControl();
    @Bind("setShowsReorderControl:") public native void setShowsReorderControl(@Type("BOOL") boolean v);
    @Bind("textLabel") public native @Type("UILabel *") UILabel getTextLabel();
    /*</properties>*/
    /*<methods>*/
    @Bind("didTransitionToState:") public native @Type("void") void didTransitionToState(@Type("UITableViewCellStateMask") EnumSet<UITableViewCellStateMask> state);
    @Bind("prepareForReuse") public native @Type("void") void prepareForReuse();
    @Bind("setEditing:animated:") public native @Type("void") void setEditing(@Type("BOOL") boolean editing, @Type("BOOL") boolean animated);
    @Bind("setHighlighted:animated:") public native @Type("void") void setHighlighted(@Type("BOOL") boolean highlighted, @Type("BOOL") boolean animated);
    @Bind("setSelected:animated:") public native @Type("void") void setSelected(@Type("BOOL") boolean selected, @Type("BOOL") boolean animated);
    @Bind("willTransitionToState:") public native @Type("void") void willTransitionToState(@Type("UITableViewCellStateMask") EnumSet<UITableViewCellStateMask> state);
    /*</methods>*/

}
