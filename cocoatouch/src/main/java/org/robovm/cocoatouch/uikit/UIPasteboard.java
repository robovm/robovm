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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html">UIPasteboard Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPasteboard /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPasteboard /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPasteboard() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URL">@property(nonatomic, copy) NSURL *URL</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("URL") public native @Type("NSURL *") NSURL getURL();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URL">@property(nonatomic, copy) NSURL *URL</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setURL:") public native void setURL(@Type("NSURL *") NSURL v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URLs">@property(nonatomic, copy) NSArray *URLs</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("URLs") public native @Type("NSArray *") NSArray getURLs();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URLs">@property(nonatomic, copy) NSArray *URLs</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setURLs:") public native void setURLs(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/changeCount">@property(readonly, nonatomic) NSInteger changeCount</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("changeCount") public native @Type("NSInteger") int getChangeCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/color">@property(nonatomic, copy) UIColor *color</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("color") public native @Type("UIColor *") UIColor getColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/color">@property(nonatomic, copy) UIColor *color</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setColor:") public native void setColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/colors">@property(nonatomic, copy) NSArray *colors</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("colors") public native @Type("NSArray *") NSArray getColors();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/colors">@property(nonatomic, copy) NSArray *colors</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setColors:") public native void setColors(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/numberOfItems">@property(readonly, nonatomic) NSInteger numberOfItems</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("numberOfItems") public native @Type("NSInteger") int getCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/image">@property(nonatomic, copy) UIImage *image</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("image") public native @Type("UIImage *") UIImage getImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/image">@property(nonatomic, copy) UIImage *image</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setImage:") public native void setImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/images">@property(nonatomic, copy) NSArray *images</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("images") public native @Type("NSArray *") NSArray getImages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/images">@property(nonatomic, copy) NSArray *images</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setImages:") public native void setImages(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/items">@property(nonatomic,copy) NSArray *items</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/items">@property(nonatomic,copy) NSArray *items</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/name">@property(readonly, nonatomic) NSString *name</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("name") public native @Type("NSString *") String getName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/persistent">@property(getter=isPersistent, nonatomic) BOOL persistent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isPersistent") public native @Type("BOOL") boolean isPersistent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/persistent">@property(getter=isPersistent, nonatomic) BOOL persistent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setPersistent:") public native void setPersistent(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/string">@property(nonatomic, copy) NSString *string</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("string") public native @Type("NSString *") String getString();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/string">@property(nonatomic, copy) NSString *string</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setString:") public native void setString(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/strings">@property(nonatomic, copy) NSArray *strings</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("strings") public native @Type("NSArray *") NSArray getStrings();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/strings">@property(nonatomic, copy) NSArray *strings</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setStrings:") public native void setStrings(@Type("NSArray *") NSArray v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/pasteboardWithName:create:">+ (UIPasteboard *)pasteboardWithName:(NSString *)pasteboardName create:(BOOL)create</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("pasteboardWithName:create:") public native static @Type("UIPasteboard *") UIPasteboard fromName(@Type("NSString *") String pasteboardName, @Type("BOOL") boolean create);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/generalPasteboard">+ (UIPasteboard *)generalPasteboard</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("generalPasteboard") public native static @Type("UIPasteboard *") UIPasteboard getGeneral();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/pasteboardWithUniqueName">+ (UIPasteboard *)pasteboardWithUniqueName</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("pasteboardWithUniqueName") public native static @Type("UIPasteboard *") UIPasteboard getUnique();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/removePasteboardWithName:">+ (void)removePasteboardWithName:(NSString *)pasteboardName</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("removePasteboardWithName:") public native static @Type("void") void remove(@Type("NSString *") String pasteboardName);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/addItems:">- (void)addItems:(NSArray *)items</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("addItems:") public native @Type("void") void addItems(@Type("NSArray *") NSArray items);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/containsPasteboardTypes:">- (BOOL)containsPasteboardTypes:(NSArray *)pasteboardTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("containsPasteboardTypes:") public native @Type("BOOL") boolean contains(@Type("NSArray *") NSArray pasteboardTypes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/containsPasteboardTypes:inItemSet:">- (BOOL)containsPasteboardTypes:(NSArray *)pasteboardTypes inItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("containsPasteboardTypes:inItemSet:") public native @Type("BOOL") boolean contains(@Type("NSArray *") NSArray pasteboardTypes, @Type("NSIndexSet *") NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/dataForPasteboardType:">- (NSData *)dataForPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("dataForPasteboardType:") public native @Type("NSData *") NSData getData(@Type("NSString *") String pasteboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/dataForPasteboardType:inItemSet:">- (NSArray *)dataForPasteboardType:(NSString *)pasteboardType inItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("dataForPasteboardType:inItemSet:") public native @Type("NSArray *") NSArray getData(@Type("NSString *") String pasteboardType, @Type("NSIndexSet *") NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/itemSetWithPasteboardTypes:">- (NSIndexSet *)itemSetWithPasteboardTypes:(NSArray *)pasteboardTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("itemSetWithPasteboardTypes:") public native @Type("NSIndexSet *") NSIndexSet getItemsWithTypes(@Type("NSArray *") NSArray pasteboardTypes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/pasteboardTypes">- (NSArray *)pasteboardTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("pasteboardTypes") public native @Type("NSArray *") NSArray getTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/pasteboardTypesForItemSet:">- (NSArray *)pasteboardTypesForItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("pasteboardTypesForItemSet:") public native @Type("NSArray *") NSArray getTypes(@Type("NSIndexSet *") NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/valueForPasteboardType:">- (id)valueForPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("valueForPasteboardType:") public native @Type("id") NSObject getValue(@Type("NSString *") String pasteboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/valuesForPasteboardType:inItemSet:">- (NSArray *)valuesForPasteboardType:(NSString *)pasteboardType inItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("valuesForPasteboardType:inItemSet:") public native @Type("NSArray *") NSArray getValues(@Type("NSString *") String pasteboardType, @Type("NSIndexSet *") NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/setData:forPasteboardType:">- (void)setData:(NSData *)data forPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setData:forPasteboardType:") public native @Type("void") void setData(@Type("NSData *") NSData data, @Type("NSString *") String pasteboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/setValue:forPasteboardType:">- (void)setValue:(id)value forPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setValue:forPasteboardType:") public native @Type("void") void setValue(@Type("id") NSObject value, @Type("NSString *") String pasteboardType);
    /*</methods>*/

}
