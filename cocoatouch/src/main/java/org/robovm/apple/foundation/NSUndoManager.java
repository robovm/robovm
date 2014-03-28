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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSUndoManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSUndoManagerPtr extends Ptr<NSUndoManager, NSUndoManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSUndoManager.class); }/*</bind>*/
    /*<constants>*/
    public static final int UndoCloseGroupingRunLoopOrdering = 350000;
    /*</constants>*/
    /*<constructors>*/
    public NSUndoManager() {}
    protected NSUndoManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSUndoManagerGroupIsDiscardableKey")
    public static native NSString KeyGroupIsDiscardable();
    @GlobalValue(symbol="NSUndoManagerCheckpointNotification")
    public static native String NotificationCheckpoint();
    @GlobalValue(symbol="NSUndoManagerWillUndoChangeNotification")
    public static native String NotificationWillUndoChange();
    @GlobalValue(symbol="NSUndoManagerWillRedoChangeNotification")
    public static native String NotificationWillRedoChange();
    @GlobalValue(symbol="NSUndoManagerDidUndoChangeNotification")
    public static native String NotificationDidUndoChange();
    @GlobalValue(symbol="NSUndoManagerDidRedoChangeNotification")
    public static native String NotificationDidRedoChange();
    @GlobalValue(symbol="NSUndoManagerDidOpenUndoGroupNotification")
    public static native String NotificationDidOpenUndoGroup();
    @GlobalValue(symbol="NSUndoManagerWillCloseUndoGroupNotification")
    public static native String NotificationWillCloseUndoGroup();
    @GlobalValue(symbol="NSUndoManagerDidCloseUndoGroupNotification")
    public static native String NotificationDidCloseUndoGroup();
    
    @Method(selector = "beginUndoGrouping")
    public native void beginUndoGrouping();
    @Method(selector = "endUndoGrouping")
    public native void endUndoGrouping();
    @Method(selector = "groupingLevel")
    public native @MachineSizedSInt long groupingLevel();
    @Method(selector = "disableUndoRegistration")
    public native void disableUndoRegistration();
    @Method(selector = "enableUndoRegistration")
    public native void enableUndoRegistration();
    @Method(selector = "isUndoRegistrationEnabled")
    public native boolean isUndoRegistrationEnabled();
    @Method(selector = "groupsByEvent")
    public native boolean groupsByEvent();
    @Method(selector = "setGroupsByEvent:")
    public native void setGroupsByEvent(boolean groupsByEvent);
    @Method(selector = "setLevelsOfUndo:")
    public native void setLevelsOfUndo(@MachineSizedUInt long levels);
    @Method(selector = "levelsOfUndo")
    public native @MachineSizedUInt long levelsOfUndo();
    @Method(selector = "setRunLoopModes:")
    public native void setRunLoopModes(NSArray<?> runLoopModes);
    @Method(selector = "runLoopModes")
    public native NSArray<?> runLoopModes();
    @Method(selector = "undo")
    public native void undo();
    @Method(selector = "redo")
    public native void redo();
    @Method(selector = "undoNestedGroup")
    public native void undoNestedGroup();
    @Method(selector = "canUndo")
    public native boolean canUndo();
    @Method(selector = "canRedo")
    public native boolean canRedo();
    @Method(selector = "isUndoing")
    public native boolean isUndoing();
    @Method(selector = "isRedoing")
    public native boolean isRedoing();
    @Method(selector = "removeAllActions")
    public native void removeAllActions();
    @Method(selector = "removeAllActionsWithTarget:")
    public native void removeAllActionsWithTarget$(NSObject target);
    @Method(selector = "registerUndoWithTarget:selector:object:")
    public native void registerUndoWithTarget$selector$object$(NSObject target, Selector selector, NSObject anObject);
    @Method(selector = "prepareWithInvocationTarget:")
    public native NSObject prepareWithInvocationTarget$(NSObject target);
    @Method(selector = "setActionIsDiscardable:")
    public native void setActionIsDiscardable(boolean discardable);
    @Method(selector = "undoActionIsDiscardable")
    public native boolean undoActionIsDiscardable();
    @Method(selector = "redoActionIsDiscardable")
    public native boolean redoActionIsDiscardable();
    @Method(selector = "undoActionName")
    public native String undoActionName();
    @Method(selector = "redoActionName")
    public native String redoActionName();
    @Method(selector = "setActionName:")
    public native void setActionName(String actionName);
    @Method(selector = "undoMenuItemTitle")
    public native String undoMenuItemTitle();
    @Method(selector = "redoMenuItemTitle")
    public native String redoMenuItemTitle();
    @Method(selector = "undoMenuTitleForUndoActionName:")
    public native String undoMenuTitleForUndoActionName$(String actionName);
    @Method(selector = "redoMenuTitleForUndoActionName:")
    public native String redoMenuTitleForUndoActionName$(String actionName);
    /*</methods>*/
}
