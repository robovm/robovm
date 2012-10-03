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
    @Bind("URL") public native @Type("NSURL *") NSURL getURL();
    @Bind("setURL:") public native void setURL(@Type("NSURL *") NSURL v);
    @Bind("URLs") public native @Type("NSArray *") NSArray getURLs();
    @Bind("setURLs:") public native void setURLs(@Type("NSArray *") NSArray v);
    @Bind("changeCount") public native @Type("NSInteger") int getChangeCount();
    @Bind("color") public native @Type("UIColor *") UIColor getColor();
    @Bind("setColor:") public native void setColor(@Type("UIColor *") UIColor v);
    @Bind("colors") public native @Type("NSArray *") NSArray getColors();
    @Bind("setColors:") public native void setColors(@Type("NSArray *") NSArray v);
    @Bind("numberOfItems") public native @Type("NSInteger") int getCount();
    @Bind("image") public native @Type("UIImage *") UIImage getImage();
    @Bind("setImage:") public native void setImage(@Type("UIImage *") UIImage v);
    @Bind("images") public native @Type("NSArray *") NSArray getImages();
    @Bind("setImages:") public native void setImages(@Type("NSArray *") NSArray v);
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    @Bind("name") public native @Type("NSString *") String getName();
    @Bind("isPersistent") public native @Type("BOOL") boolean isPersistent();
    @Bind("setPersistent:") public native void setPersistent(@Type("BOOL") boolean v);
    @Bind("string") public native @Type("NSString *") String getString();
    @Bind("setString:") public native void setString(@Type("NSString *") String v);
    @Bind("strings") public native @Type("NSArray *") NSArray getStrings();
    @Bind("setStrings:") public native void setStrings(@Type("NSArray *") NSArray v);
    /*</properties>*/
    /*<methods>*/
    @Bind("pasteboardWithName:create:") public native static @Type("UIPasteboard *") UIPasteboard fromName(@Type("NSString *") String pasteboardName, @Type("BOOL") boolean create);
    @Bind("generalPasteboard") public native static @Type("UIPasteboard *") UIPasteboard getGeneral();
    @Bind("pasteboardWithUniqueName") public native static @Type("UIPasteboard *") UIPasteboard getUnique();
    @Bind("removePasteboardWithName:") public native static @Type("void") void remove(@Type("NSString *") String pasteboardName);
    @Bind("addItems:") public native @Type("void") void addItems(@Type("NSArray *") NSArray items);
    @Bind("containsPasteboardTypes:") public native @Type("BOOL") boolean contains(@Type("NSArray *") NSArray pasteboardTypes);
    @Bind("containsPasteboardTypes:inItemSet:") public native @Type("BOOL") boolean contains(@Type("NSArray *") NSArray pasteboardTypes, @Type("NSIndexSet *") NSIndexSet itemSet);
    @Bind("dataForPasteboardType:") public native @Type("NSData *") NSData getData(@Type("NSString *") String pasteboardType);
    @Bind("dataForPasteboardType:inItemSet:") public native @Type("NSArray *") NSArray getData(@Type("NSString *") String pasteboardType, @Type("NSIndexSet *") NSIndexSet itemSet);
    @Bind("itemSetWithPasteboardTypes:") public native @Type("NSIndexSet *") NSIndexSet getItemsWithTypes(@Type("NSArray *") NSArray pasteboardTypes);
    @Bind("pasteboardTypes") public native @Type("NSArray *") NSArray getTypes();
    @Bind("pasteboardTypesForItemSet:") public native @Type("NSArray *") NSArray getTypes(@Type("NSIndexSet *") NSIndexSet itemSet);
    @Bind("valueForPasteboardType:") public native @Type("id") NSObject getValue(@Type("NSString *") String pasteboardType);
    @Bind("valuesForPasteboardType:inItemSet:") public native @Type("NSArray *") NSArray getValues(@Type("NSString *") String pasteboardType, @Type("NSIndexSet *") NSIndexSet itemSet);
    @Bind("setData:forPasteboardType:") public native @Type("void") void setData(@Type("NSData *") NSData data, @Type("NSString *") String pasteboardType);
    @Bind("setValue:forPasteboardType:") public native @Type("void") void setValue(@Type("id") NSObject value, @Type("NSString *") String pasteboardType);
    /*</methods>*/

}
