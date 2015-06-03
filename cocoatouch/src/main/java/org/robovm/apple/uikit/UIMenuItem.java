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
import org.robovm.rt.annotation.*;
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
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIMenuItem/*</name>*/
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface OnActionListener {
        void onAction(UIMenuController menuController, UIMenuItem menuItem);
    }
    
    /*<ptr>*/public static class UIMenuItemPtr extends Ptr<UIMenuItem, UIMenuItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIMenuItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    static final LongMap<UIMenuItem> items = new LongMap<>();
    
    private OnActionListener actionListener;
    
    public UIMenuItem(String title) {
        setTitle(title);
    }
    
    public UIMenuItem(String title, OnActionListener action) {
        super((SkipInit) null);
        if (action == null) {
            throw new NullPointerException("action");
        }
        this.actionListener = action;
        Selector sel = getUniqueSelector();
        initObject(init(title, sel));
        
        synchronized (items) {
            items.put(sel.getHandle(), this);
        }
    }
    
    public void setActionListener(OnActionListener action) {
        this.actionListener = action;

        Selector sel = null;
        if (action != null) {
            sel = getUniqueSelector();
        } 
        setAction(sel);
        
        synchronized (items) {
            if (action != null) {
                items.put(sel.getHandle(), this);
            } else {
                items.remove(sel.getHandle());
            }
        }
    }
    public OnActionListener getActionListener() {
        return actionListener;
    }
    
    private Selector getUniqueSelector() {
        String name = "__menuitem_" + getHandle();
        return Selector.register(name);
    }
    /*<constructors>*/
    public UIMenuItem() {}
    protected UIMenuItem(SkipInit skipInit) { super(skipInit); }
    public UIMenuItem(String title, Selector action) { super((SkipInit) null); initObject(init(title, action)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "action")
    public native Selector getAction();
    @Property(selector = "setAction:")
    public native void setAction(Selector v);
    /*</properties>*/
    /*<members>*//*</members>*/
    
    /*<methods>*/
    @Method(selector = "initWithTitle:action:")
    protected native @Pointer long init(String title, Selector action);
    /*</methods>*/
}
