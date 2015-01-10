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
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIActivityItemProvider/*</name>*/ 
    extends /*<extends>*/NSOperation/*</extends>*/ 
    /*<implements>*/implements UIActivityItemSource/*</implements>*/ {

    /*<ptr>*/public static class UIActivityItemProviderPtr extends Ptr<UIActivityItemProvider, UIActivityItemProviderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIActivityItemProvider.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIActivityItemProvider() {}
    protected UIActivityItemProvider(SkipInit skipInit) { super(skipInit); }
    public UIActivityItemProvider(NSObject placeholderItem) { super((SkipInit) null); initObject(init(placeholderItem)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "placeholderItem")
    public native NSObject getPlaceholderItem();
    @Property(selector = "activityType")
    public native String getActivityType();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPlaceholderItem:")
    protected native @Pointer long init(NSObject placeholderItem);
    @Method(selector = "item")
    public native NSObject getItem();
    @Method(selector = "activityViewControllerPlaceholderItem:")
    public native NSObject getPlaceholderItem(UIActivityViewController activityViewController);
    @Method(selector = "activityViewController:itemForActivityType:")
    public native NSObject getItem(UIActivityViewController activityViewController, String activityType);
    @Method(selector = "activityViewController:subjectForActivityType:")
    public native String getSubject(UIActivityViewController activityViewController, String activityType);
    @Method(selector = "activityViewController:dataTypeIdentifierForActivityType:")
    public native String getDataTypeIdentifier(UIActivityViewController activityViewController, String activityType);
    @Method(selector = "activityViewController:thumbnailImageForActivityType:suggestedSize:")
    public native UIImage getThumbnailImage(UIActivityViewController activityViewController, String activityType, @ByVal CGSize size);
    /*</methods>*/
}
