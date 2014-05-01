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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextStorage/*</name>*/ 
    extends /*<extends>*/NSMutableAttributedString/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSTextStoragePtr extends Ptr<NSTextStorage, NSTextStoragePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSTextStorage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSTextStorage() {}
    protected NSTextStorage(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "layoutManagers")
    public native NSArray<NSLayoutManager> getLayoutManagers();
    @Property(selector = "editedMask")
    public native NSTextStorageEditActions getEditedMask();
    @Property(selector = "setEditedMask:")
    public native void setEditedMask(NSTextStorageEditActions v);
    @Property(selector = "editedRange")
    public native @ByVal NSRange getEditedRange();
    @Property(selector = "setEditedRange:")
    public native void setEditedRange(@ByVal NSRange v);
    @Property(selector = "changeInLength")
    public native @MachineSizedSInt long getChangeInLength();
    @Property(selector = "setChangeInLength:")
    public native void setChangeInLength(@MachineSizedSInt long v);
    @Property(selector = "delegate")
    public native NSTextStorageDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSTextStorageDelegate v);
    @Property(selector = "fixesAttributesLazily")
    public native boolean isFixesAttributesLazily();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSTextStorageWillProcessEditingNotification", optional=true)
    public static native String NotificationTextStorageWillProcessEditing();
    @GlobalValue(symbol="NSTextStorageDidProcessEditingNotification", optional=true)
    public static native String NotificationTextStorageDidProcessEditing();
    
    @Method(selector = "addLayoutManager:")
    public native void addLayoutManager(NSLayoutManager aLayoutManager);
    @Method(selector = "removeLayoutManager:")
    public native void removeLayoutManager(NSLayoutManager aLayoutManager);
    @Method(selector = "edited:range:changeInLength:")
    public native void edited(NSTextStorageEditActions editedMask, @ByVal NSRange editedRange, @MachineSizedSInt long delta);
    @Method(selector = "processEditing")
    public native void processEditing();
    @Method(selector = "invalidateAttributesInRange:")
    public native void invalidateAttributes(@ByVal NSRange range);
    @Method(selector = "ensureAttributesAreFixedInRange:")
    public native void ensureAttributesAreFixed(@ByVal NSRange range);
    /*</methods>*/
}
