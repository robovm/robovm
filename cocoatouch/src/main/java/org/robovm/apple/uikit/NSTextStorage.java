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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTextStorage/*</name>*/ 
    extends /*<extends>*/NSMutableAttributedString/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeWillProcessEditing(NSTextStorage object, final VoidBlock1<NSTextStorage> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillProcessEditingNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSTextStorage) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeDidProcessEditing(NSTextStorage object, final VoidBlock1<NSTextStorage> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidProcessEditingNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSTextStorage) a.getObject());
                }
            });
        }
    }
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
    public native boolean fixesAttributesLazily();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextStorageWillProcessEditingNotification", optional=true)
    public static native NSString WillProcessEditingNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextStorageDidProcessEditingNotification", optional=true)
    public static native NSString DidProcessEditingNotification();
    
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
