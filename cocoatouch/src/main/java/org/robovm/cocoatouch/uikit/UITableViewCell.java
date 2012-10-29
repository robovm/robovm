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
import org.robovm.objc.annotation.*;
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
@NativeClass public class /*<name>*/ UITableViewCell /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static @Pointer long objc_initWithStyle(UITableViewCell __self__, Selector __cmd__, UITableViewCellStyle style, String reuseIdentifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/initWithStyle:reuseIdentifier:">- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewCell(UITableViewCellStyle style, String reuseIdentifier) {
        super((SkipInit) null);
        setHandle(objc_initWithStyle(this, initWithStyle$reuseIdentifier$, style, reuseIdentifier));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector accessoryType = Selector.register("accessoryType");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewCellAccessoryType objc_getAccessoryType(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewCellAccessoryType objc_getAccessoryTypeSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryType">@property(nonatomic) UITableViewCellAccessoryType accessoryType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewCellAccessoryType getAccessoryType() {
        if (customClass) { return objc_getAccessoryTypeSuper(getSuper(), this, accessoryType); } else { return objc_getAccessoryType(this, accessoryType); }
    }
    
    private static final Selector setAccessoryType$ = Selector.register("setAccessoryType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAccessoryType(UITableViewCell __self__, Selector __cmd__, UITableViewCellAccessoryType accessoryType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAccessoryTypeSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UITableViewCellAccessoryType accessoryType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryType">@property(nonatomic) UITableViewCellAccessoryType accessoryType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAccessoryType(UITableViewCellAccessoryType accessoryType) {
        if (customClass) { objc_setAccessoryTypeSuper(getSuper(), this, setAccessoryType$, accessoryType); } else { objc_setAccessoryType(this, setAccessoryType$, accessoryType); }
    }
    
    private static final Selector accessoryView = Selector.register("accessoryView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getAccessoryView(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getAccessoryViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryView">@property(nonatomic, retain) UIView *accessoryView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getAccessoryView() {
        if (customClass) { return objc_getAccessoryViewSuper(getSuper(), this, accessoryView); } else { return objc_getAccessoryView(this, accessoryView); }
    }
    
    private static final Selector setAccessoryView$ = Selector.register("setAccessoryView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAccessoryView(UITableViewCell __self__, Selector __cmd__, UIView accessoryView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAccessoryViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UIView accessoryView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/accessoryView">@property(nonatomic, retain) UIView *accessoryView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAccessoryView(UIView accessoryView) {
        if (customClass) { objc_setAccessoryViewSuper(getSuper(), this, setAccessoryView$, accessoryView); } else { objc_setAccessoryView(this, setAccessoryView$, accessoryView); }
    }
    
    private static final Selector backgroundView = Selector.register("backgroundView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getBackgroundView(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getBackgroundViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getBackgroundView() {
        if (customClass) { return objc_getBackgroundViewSuper(getSuper(), this, backgroundView); } else { return objc_getBackgroundView(this, backgroundView); }
    }
    
    private static final Selector setBackgroundView$ = Selector.register("setBackgroundView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundView(UITableViewCell __self__, Selector __cmd__, UIView backgroundView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UIView backgroundView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/backgroundView">@property(nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBackgroundView(UIView backgroundView) {
        if (customClass) { objc_setBackgroundViewSuper(getSuper(), this, setBackgroundView$, backgroundView); } else { objc_setBackgroundView(this, setBackgroundView$, backgroundView); }
    }
    
    private static final Selector contentView = Selector.register("contentView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getContentView(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getContentViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/contentView">@property(nonatomic, readonly, retain) UIView *contentView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getContentView() {
        if (customClass) { return objc_getContentViewSuper(getSuper(), this, contentView); } else { return objc_getContentView(this, contentView); }
    }
    
    private static final Selector detailTextLabel = Selector.register("detailTextLabel");
    @Bridge(symbol = "objc_msgSend") private native static UILabel objc_getDetailTextLabel(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UILabel objc_getDetailTextLabelSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/detailTextLabel">@property(nonatomic, readonly, retain) UILabel *detailTextLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    public UILabel getDetailTextLabel() {
        if (customClass) { return objc_getDetailTextLabelSuper(getSuper(), this, detailTextLabel); } else { return objc_getDetailTextLabel(this, detailTextLabel); }
    }
    
    private static final Selector isEditing = Selector.register("isEditing");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isEditing(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isEditingSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEditing() {
        if (customClass) { return objc_isEditingSuper(getSuper(), this, isEditing); } else { return objc_isEditing(this, isEditing); }
    }
    
    private static final Selector setEditing$ = Selector.register("setEditing:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditing(UITableViewCell __self__, Selector __cmd__, boolean editing);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditingSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean editing);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing) {
        if (customClass) { objc_setEditingSuper(getSuper(), this, setEditing$, editing); } else { objc_setEditing(this, setEditing$, editing); }
    }
    
    private static final Selector editingAccessoryType = Selector.register("editingAccessoryType");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewCellAccessoryType objc_getEditingAccessoryType(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewCellAccessoryType objc_getEditingAccessoryTypeSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryType">@property(nonatomic) UITableViewCellAccessoryType editingAccessoryType</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewCellAccessoryType getEditingAccessoryType() {
        if (customClass) { return objc_getEditingAccessoryTypeSuper(getSuper(), this, editingAccessoryType); } else { return objc_getEditingAccessoryType(this, editingAccessoryType); }
    }
    
    private static final Selector setEditingAccessoryType$ = Selector.register("setEditingAccessoryType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditingAccessoryType(UITableViewCell __self__, Selector __cmd__, UITableViewCellAccessoryType editingAccessoryType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditingAccessoryTypeSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UITableViewCellAccessoryType editingAccessoryType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryType">@property(nonatomic) UITableViewCellAccessoryType editingAccessoryType</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setEditingAccessoryType(UITableViewCellAccessoryType editingAccessoryType) {
        if (customClass) { objc_setEditingAccessoryTypeSuper(getSuper(), this, setEditingAccessoryType$, editingAccessoryType); } else { objc_setEditingAccessoryType(this, setEditingAccessoryType$, editingAccessoryType); }
    }
    
    private static final Selector editingAccessoryView = Selector.register("editingAccessoryView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getEditingAccessoryView(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getEditingAccessoryViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryView">@property(nonatomic, retain) UIView *editingAccessoryView</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIView getEditingAccessoryView() {
        if (customClass) { return objc_getEditingAccessoryViewSuper(getSuper(), this, editingAccessoryView); } else { return objc_getEditingAccessoryView(this, editingAccessoryView); }
    }
    
    private static final Selector setEditingAccessoryView$ = Selector.register("setEditingAccessoryView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditingAccessoryView(UITableViewCell __self__, Selector __cmd__, UIView editingAccessoryView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditingAccessoryViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UIView editingAccessoryView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingAccessoryView">@property(nonatomic, retain) UIView *editingAccessoryView</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setEditingAccessoryView(UIView editingAccessoryView) {
        if (customClass) { objc_setEditingAccessoryViewSuper(getSuper(), this, setEditingAccessoryView$, editingAccessoryView); } else { objc_setEditingAccessoryView(this, setEditingAccessoryView$, editingAccessoryView); }
    }
    
    private static final Selector editingStyle = Selector.register("editingStyle");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewCellEditingStyle objc_getEditingStyle(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewCellEditingStyle objc_getEditingStyleSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/editingStyle">@property(nonatomic, readonly) UITableViewCellEditingStyle editingStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewCellEditingStyle getEditingStyle() {
        if (customClass) { return objc_getEditingStyleSuper(getSuper(), this, editingStyle); } else { return objc_getEditingStyle(this, editingStyle); }
    }
    
    private static final Selector isHighlighted = Selector.register("isHighlighted");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isHighlighted(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isHighlightedSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isHighlighted() {
        if (customClass) { return objc_isHighlightedSuper(getSuper(), this, isHighlighted); } else { return objc_isHighlighted(this, isHighlighted); }
    }
    
    private static final Selector setHighlighted$ = Selector.register("setHighlighted:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setHighlighted(UITableViewCell __self__, Selector __cmd__, boolean highlighted);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setHighlightedSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean highlighted);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/highlighted">@property(nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setHighlighted(boolean highlighted) {
        if (customClass) { objc_setHighlightedSuper(getSuper(), this, setHighlighted$, highlighted); } else { objc_setHighlighted(this, setHighlighted$, highlighted); }
    }
    
    private static final Selector imageView = Selector.register("imageView");
    @Bridge(symbol = "objc_msgSend") private native static UIImageView objc_getImageView(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImageView objc_getImageViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/imageView">@property(nonatomic, readonly, retain) UIImageView *imageView</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIImageView getImageView() {
        if (customClass) { return objc_getImageViewSuper(getSuper(), this, imageView); } else { return objc_getImageView(this, imageView); }
    }
    
    private static final Selector indentationLevel = Selector.register("indentationLevel");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getIndentationLevel(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getIndentationLevelSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationLevel">@property(nonatomic) NSInteger indentationLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getIndentationLevel() {
        if (customClass) { return objc_getIndentationLevelSuper(getSuper(), this, indentationLevel); } else { return objc_getIndentationLevel(this, indentationLevel); }
    }
    
    private static final Selector setIndentationLevel$ = Selector.register("setIndentationLevel:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setIndentationLevel(UITableViewCell __self__, Selector __cmd__, int indentationLevel);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setIndentationLevelSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, int indentationLevel);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationLevel">@property(nonatomic) NSInteger indentationLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setIndentationLevel(int indentationLevel) {
        if (customClass) { objc_setIndentationLevelSuper(getSuper(), this, setIndentationLevel$, indentationLevel); } else { objc_setIndentationLevel(this, setIndentationLevel$, indentationLevel); }
    }
    
    private static final Selector indentationWidth = Selector.register("indentationWidth");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getIndentationWidth(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getIndentationWidthSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationWidth">@property(nonatomic) CGFloat indentationWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getIndentationWidth() {
        if (customClass) { return objc_getIndentationWidthSuper(getSuper(), this, indentationWidth); } else { return objc_getIndentationWidth(this, indentationWidth); }
    }
    
    private static final Selector setIndentationWidth$ = Selector.register("setIndentationWidth:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setIndentationWidth(UITableViewCell __self__, Selector __cmd__, float indentationWidth);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setIndentationWidthSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, float indentationWidth);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/indentationWidth">@property(nonatomic) CGFloat indentationWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setIndentationWidth(float indentationWidth) {
        if (customClass) { objc_setIndentationWidthSuper(getSuper(), this, setIndentationWidth$, indentationWidth); } else { objc_setIndentationWidth(this, setIndentationWidth$, indentationWidth); }
    }
    
    private static final Selector multipleSelectionBackgroundView = Selector.register("multipleSelectionBackgroundView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getMultipleSelectionBackgroundView(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getMultipleSelectionBackgroundViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/multipleSelectionBackgroundView">@property(nonatomic,retain) UIView *multipleSelectionBackgroundView</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIView getMultipleSelectionBackgroundView() {
        if (customClass) { return objc_getMultipleSelectionBackgroundViewSuper(getSuper(), this, multipleSelectionBackgroundView); } else { return objc_getMultipleSelectionBackgroundView(this, multipleSelectionBackgroundView); }
    }
    
    private static final Selector setMultipleSelectionBackgroundView$ = Selector.register("setMultipleSelectionBackgroundView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMultipleSelectionBackgroundView(UITableViewCell __self__, Selector __cmd__, UIView multipleSelectionBackgroundView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMultipleSelectionBackgroundViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UIView multipleSelectionBackgroundView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/multipleSelectionBackgroundView">@property(nonatomic,retain) UIView *multipleSelectionBackgroundView</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMultipleSelectionBackgroundView(UIView multipleSelectionBackgroundView) {
        if (customClass) { objc_setMultipleSelectionBackgroundViewSuper(getSuper(), this, setMultipleSelectionBackgroundView$, multipleSelectionBackgroundView); } else { objc_setMultipleSelectionBackgroundView(this, setMultipleSelectionBackgroundView$, multipleSelectionBackgroundView); }
    }
    
    private static final Selector reuseIdentifier = Selector.register("reuseIdentifier");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getReuseIdentifier(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getReuseIdentifierSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/reuseIdentifier">@property(nonatomic, readonly, copy) NSString *reuseIdentifier</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getReuseIdentifier() {
        if (customClass) { return objc_getReuseIdentifierSuper(getSuper(), this, reuseIdentifier); } else { return objc_getReuseIdentifier(this, reuseIdentifier); }
    }
    
    private static final Selector isSelected = Selector.register("isSelected");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isSelected(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isSelectedSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isSelected() {
        if (customClass) { return objc_isSelectedSuper(getSuper(), this, isSelected); } else { return objc_isSelected(this, isSelected); }
    }
    
    private static final Selector setSelected$ = Selector.register("setSelected:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSelected(UITableViewCell __self__, Selector __cmd__, boolean selected);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSelectedSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean selected);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selected">@property(nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelected(boolean selected) {
        if (customClass) { objc_setSelectedSuper(getSuper(), this, setSelected$, selected); } else { objc_setSelected(this, setSelected$, selected); }
    }
    
    private static final Selector selectedBackgroundView = Selector.register("selectedBackgroundView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getSelectedBackgroundView(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getSelectedBackgroundViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectedBackgroundView">@property(nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getSelectedBackgroundView() {
        if (customClass) { return objc_getSelectedBackgroundViewSuper(getSuper(), this, selectedBackgroundView); } else { return objc_getSelectedBackgroundView(this, selectedBackgroundView); }
    }
    
    private static final Selector setSelectedBackgroundView$ = Selector.register("setSelectedBackgroundView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSelectedBackgroundView(UITableViewCell __self__, Selector __cmd__, UIView selectedBackgroundView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSelectedBackgroundViewSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UIView selectedBackgroundView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectedBackgroundView">@property(nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelectedBackgroundView(UIView selectedBackgroundView) {
        if (customClass) { objc_setSelectedBackgroundViewSuper(getSuper(), this, setSelectedBackgroundView$, selectedBackgroundView); } else { objc_setSelectedBackgroundView(this, setSelectedBackgroundView$, selectedBackgroundView); }
    }
    
    private static final Selector selectionStyle = Selector.register("selectionStyle");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewCellSelectionStyle objc_getSelectionStyle(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewCellSelectionStyle objc_getSelectionStyleSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectionStyle">@property(nonatomic) UITableViewCellSelectionStyle selectionStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewCellSelectionStyle getSelectionStyle() {
        if (customClass) { return objc_getSelectionStyleSuper(getSuper(), this, selectionStyle); } else { return objc_getSelectionStyle(this, selectionStyle); }
    }
    
    private static final Selector setSelectionStyle$ = Selector.register("setSelectionStyle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSelectionStyle(UITableViewCell __self__, Selector __cmd__, UITableViewCellSelectionStyle selectionStyle);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSelectionStyleSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UITableViewCellSelectionStyle selectionStyle);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/selectionStyle">@property(nonatomic) UITableViewCellSelectionStyle selectionStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelectionStyle(UITableViewCellSelectionStyle selectionStyle) {
        if (customClass) { objc_setSelectionStyleSuper(getSuper(), this, setSelectionStyle$, selectionStyle); } else { objc_setSelectionStyle(this, setSelectionStyle$, selectionStyle); }
    }
    
    private static final Selector shouldIndentWhileEditing = Selector.register("shouldIndentWhileEditing");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isShouldIndentWhileEditing(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isShouldIndentWhileEditingSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/shouldIndentWhileEditing">@property(nonatomic) BOOL shouldIndentWhileEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShouldIndentWhileEditing() {
        if (customClass) { return objc_isShouldIndentWhileEditingSuper(getSuper(), this, shouldIndentWhileEditing); } else { return objc_isShouldIndentWhileEditing(this, shouldIndentWhileEditing); }
    }
    
    private static final Selector setShouldIndentWhileEditing$ = Selector.register("setShouldIndentWhileEditing:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setShouldIndentWhileEditing(UITableViewCell __self__, Selector __cmd__, boolean shouldIndentWhileEditing);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setShouldIndentWhileEditingSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean shouldIndentWhileEditing);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/shouldIndentWhileEditing">@property(nonatomic) BOOL shouldIndentWhileEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShouldIndentWhileEditing(boolean shouldIndentWhileEditing) {
        if (customClass) { objc_setShouldIndentWhileEditingSuper(getSuper(), this, setShouldIndentWhileEditing$, shouldIndentWhileEditing); } else { objc_setShouldIndentWhileEditing(this, setShouldIndentWhileEditing$, shouldIndentWhileEditing); }
    }
    
    private static final Selector showingDeleteConfirmation = Selector.register("showingDeleteConfirmation");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isShowingDeleteConfirmation(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isShowingDeleteConfirmationSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showingDeleteConfirmation">@property(nonatomic, readonly) BOOL showingDeleteConfirmation</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowingDeleteConfirmation() {
        if (customClass) { return objc_isShowingDeleteConfirmationSuper(getSuper(), this, showingDeleteConfirmation); } else { return objc_isShowingDeleteConfirmation(this, showingDeleteConfirmation); }
    }
    
    private static final Selector showsReorderControl = Selector.register("showsReorderControl");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isShowsReorderControl(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isShowsReorderControlSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showsReorderControl">@property(nonatomic) BOOL showsReorderControl</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsReorderControl() {
        if (customClass) { return objc_isShowsReorderControlSuper(getSuper(), this, showsReorderControl); } else { return objc_isShowsReorderControl(this, showsReorderControl); }
    }
    
    private static final Selector setShowsReorderControl$ = Selector.register("setShowsReorderControl:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setShowsReorderControl(UITableViewCell __self__, Selector __cmd__, boolean showsReorderControl);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setShowsReorderControlSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, boolean showsReorderControl);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/showsReorderControl">@property(nonatomic) BOOL showsReorderControl</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsReorderControl(boolean showsReorderControl) {
        if (customClass) { objc_setShowsReorderControlSuper(getSuper(), this, setShowsReorderControl$, showsReorderControl); } else { objc_setShowsReorderControl(this, setShowsReorderControl$, showsReorderControl); }
    }
    
    private static final Selector textLabel = Selector.register("textLabel");
    @Bridge(symbol = "objc_msgSend") private native static UILabel objc_getTextLabel(UITableViewCell __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UILabel objc_getTextLabelSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewCell/textLabel">@property(nonatomic, readonly, retain) UILabel *textLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    public UILabel getTextLabel() {
        if (customClass) { return objc_getTextLabelSuper(getSuper(), this, textLabel); } else { return objc_getTextLabel(this, textLabel); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector didTransitionToState$ = Selector.register("didTransitionToState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didTransitionToState(UITableViewCell __self__, Selector __cmd__, UITableViewCellStateMask state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didTransitionToStateSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UITableViewCellStateMask state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/didTransitionToState:">- (void)didTransitionToState:(UITableViewCellStateMask)state</a>
     * @since Available in iOS 3.0 and later.
     */
    public void didTransitionToState(UITableViewCellStateMask state) {
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
    @Bridge(symbol = "objc_msgSend") private native static void objc_willTransitionToState(UITableViewCell __self__, Selector __cmd__, UITableViewCellStateMask state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willTransitionToStateSuper(ObjCSuper __super__, UITableViewCell __self__, Selector __cmd__, UITableViewCellStateMask state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewCell_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewCell/willTransitionToState:">- (void)willTransitionToState:(UITableViewCellStateMask)state</a>
     * @since Available in iOS 3.0 and later.
     */
    public void willTransitionToState(UITableViewCellStateMask state) {
        if (customClass) { objc_willTransitionToStateSuper(getSuper(), this, willTransitionToState$, state); } else { objc_willTransitionToState(this, willTransitionToState$, state); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("accessoryType") public static UITableViewCellAccessoryType getAccessoryType(UITableViewCell __self__, Selector __cmd__) { return __self__.getAccessoryType(); }
        @Callback @BindSelector("setAccessoryType:") public static void setAccessoryType(UITableViewCell __self__, Selector __cmd__, UITableViewCellAccessoryType accessoryType) { __self__.setAccessoryType(accessoryType); }
        @Callback @BindSelector("accessoryView") public static UIView getAccessoryView(UITableViewCell __self__, Selector __cmd__) { return __self__.getAccessoryView(); }
        @Callback @BindSelector("setAccessoryView:") public static void setAccessoryView(UITableViewCell __self__, Selector __cmd__, UIView accessoryView) { __self__.setAccessoryView(accessoryView); }
        @Callback @BindSelector("backgroundView") public static UIView getBackgroundView(UITableViewCell __self__, Selector __cmd__) { return __self__.getBackgroundView(); }
        @Callback @BindSelector("setBackgroundView:") public static void setBackgroundView(UITableViewCell __self__, Selector __cmd__, UIView backgroundView) { __self__.setBackgroundView(backgroundView); }
        @Callback @BindSelector("contentView") public static UIView getContentView(UITableViewCell __self__, Selector __cmd__) { return __self__.getContentView(); }
        @Callback @BindSelector("detailTextLabel") public static UILabel getDetailTextLabel(UITableViewCell __self__, Selector __cmd__) { return __self__.getDetailTextLabel(); }
        @Callback @BindSelector("isEditing") public static boolean isEditing(UITableViewCell __self__, Selector __cmd__) { return __self__.isEditing(); }
        @Callback @BindSelector("setEditing:") public static void setEditing(UITableViewCell __self__, Selector __cmd__, boolean editing) { __self__.setEditing(editing); }
        @Callback @BindSelector("editingAccessoryType") public static UITableViewCellAccessoryType getEditingAccessoryType(UITableViewCell __self__, Selector __cmd__) { return __self__.getEditingAccessoryType(); }
        @Callback @BindSelector("setEditingAccessoryType:") public static void setEditingAccessoryType(UITableViewCell __self__, Selector __cmd__, UITableViewCellAccessoryType editingAccessoryType) { __self__.setEditingAccessoryType(editingAccessoryType); }
        @Callback @BindSelector("editingAccessoryView") public static UIView getEditingAccessoryView(UITableViewCell __self__, Selector __cmd__) { return __self__.getEditingAccessoryView(); }
        @Callback @BindSelector("setEditingAccessoryView:") public static void setEditingAccessoryView(UITableViewCell __self__, Selector __cmd__, UIView editingAccessoryView) { __self__.setEditingAccessoryView(editingAccessoryView); }
        @Callback @BindSelector("editingStyle") public static UITableViewCellEditingStyle getEditingStyle(UITableViewCell __self__, Selector __cmd__) { return __self__.getEditingStyle(); }
        @Callback @BindSelector("isHighlighted") public static boolean isHighlighted(UITableViewCell __self__, Selector __cmd__) { return __self__.isHighlighted(); }
        @Callback @BindSelector("setHighlighted:") public static void setHighlighted(UITableViewCell __self__, Selector __cmd__, boolean highlighted) { __self__.setHighlighted(highlighted); }
        @Callback @BindSelector("imageView") public static UIImageView getImageView(UITableViewCell __self__, Selector __cmd__) { return __self__.getImageView(); }
        @Callback @BindSelector("indentationLevel") public static int getIndentationLevel(UITableViewCell __self__, Selector __cmd__) { return __self__.getIndentationLevel(); }
        @Callback @BindSelector("setIndentationLevel:") public static void setIndentationLevel(UITableViewCell __self__, Selector __cmd__, int indentationLevel) { __self__.setIndentationLevel(indentationLevel); }
        @Callback @BindSelector("indentationWidth") public static float getIndentationWidth(UITableViewCell __self__, Selector __cmd__) { return __self__.getIndentationWidth(); }
        @Callback @BindSelector("setIndentationWidth:") public static void setIndentationWidth(UITableViewCell __self__, Selector __cmd__, float indentationWidth) { __self__.setIndentationWidth(indentationWidth); }
        @Callback @BindSelector("multipleSelectionBackgroundView") public static UIView getMultipleSelectionBackgroundView(UITableViewCell __self__, Selector __cmd__) { return __self__.getMultipleSelectionBackgroundView(); }
        @Callback @BindSelector("setMultipleSelectionBackgroundView:") public static void setMultipleSelectionBackgroundView(UITableViewCell __self__, Selector __cmd__, UIView multipleSelectionBackgroundView) { __self__.setMultipleSelectionBackgroundView(multipleSelectionBackgroundView); }
        @Callback @BindSelector("reuseIdentifier") public static String getReuseIdentifier(UITableViewCell __self__, Selector __cmd__) { return __self__.getReuseIdentifier(); }
        @Callback @BindSelector("isSelected") public static boolean isSelected(UITableViewCell __self__, Selector __cmd__) { return __self__.isSelected(); }
        @Callback @BindSelector("setSelected:") public static void setSelected(UITableViewCell __self__, Selector __cmd__, boolean selected) { __self__.setSelected(selected); }
        @Callback @BindSelector("selectedBackgroundView") public static UIView getSelectedBackgroundView(UITableViewCell __self__, Selector __cmd__) { return __self__.getSelectedBackgroundView(); }
        @Callback @BindSelector("setSelectedBackgroundView:") public static void setSelectedBackgroundView(UITableViewCell __self__, Selector __cmd__, UIView selectedBackgroundView) { __self__.setSelectedBackgroundView(selectedBackgroundView); }
        @Callback @BindSelector("selectionStyle") public static UITableViewCellSelectionStyle getSelectionStyle(UITableViewCell __self__, Selector __cmd__) { return __self__.getSelectionStyle(); }
        @Callback @BindSelector("setSelectionStyle:") public static void setSelectionStyle(UITableViewCell __self__, Selector __cmd__, UITableViewCellSelectionStyle selectionStyle) { __self__.setSelectionStyle(selectionStyle); }
        @Callback @BindSelector("shouldIndentWhileEditing") public static boolean isShouldIndentWhileEditing(UITableViewCell __self__, Selector __cmd__) { return __self__.isShouldIndentWhileEditing(); }
        @Callback @BindSelector("setShouldIndentWhileEditing:") public static void setShouldIndentWhileEditing(UITableViewCell __self__, Selector __cmd__, boolean shouldIndentWhileEditing) { __self__.setShouldIndentWhileEditing(shouldIndentWhileEditing); }
        @Callback @BindSelector("showingDeleteConfirmation") public static boolean isShowingDeleteConfirmation(UITableViewCell __self__, Selector __cmd__) { return __self__.isShowingDeleteConfirmation(); }
        @Callback @BindSelector("showsReorderControl") public static boolean isShowsReorderControl(UITableViewCell __self__, Selector __cmd__) { return __self__.isShowsReorderControl(); }
        @Callback @BindSelector("setShowsReorderControl:") public static void setShowsReorderControl(UITableViewCell __self__, Selector __cmd__, boolean showsReorderControl) { __self__.setShowsReorderControl(showsReorderControl); }
        @Callback @BindSelector("textLabel") public static UILabel getTextLabel(UITableViewCell __self__, Selector __cmd__) { return __self__.getTextLabel(); }
        @Callback @BindSelector("didTransitionToState:") public static void didTransitionToState(UITableViewCell __self__, Selector __cmd__, UITableViewCellStateMask state) { __self__.didTransitionToState(state); }
        @Callback @BindSelector("prepareForReuse") public static void prepareForReuse(UITableViewCell __self__, Selector __cmd__) { __self__.prepareForReuse(); }
        @Callback @BindSelector("setEditing:animated:") public static void setEditing(UITableViewCell __self__, Selector __cmd__, boolean editing, boolean animated) { __self__.setEditing(editing, animated); }
        @Callback @BindSelector("setHighlighted:animated:") public static void setHighlighted(UITableViewCell __self__, Selector __cmd__, boolean highlighted, boolean animated) { __self__.setHighlighted(highlighted, animated); }
        @Callback @BindSelector("setSelected:animated:") public static void setSelected(UITableViewCell __self__, Selector __cmd__, boolean selected, boolean animated) { __self__.setSelected(selected, animated); }
        @Callback @BindSelector("willTransitionToState:") public static void willTransitionToState(UITableViewCell __self__, Selector __cmd__, UITableViewCellStateMask state) { __self__.willTransitionToState(state); }
    }
    /*</callbacks>*/

}
