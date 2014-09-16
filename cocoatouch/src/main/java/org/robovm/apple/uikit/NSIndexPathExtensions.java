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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSIndexPathExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSIndexPathExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSIndexPathExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "section")
    public static native @MachineSizedSInt long getSection(NSIndexPath thiz);
    @Property(selector = "row")
    public static native @MachineSizedSInt long getRow(NSIndexPath thiz);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "item")
    public static native @MachineSizedSInt long getItem(NSIndexPath thiz);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "indexPathForRow:inSection:")
    protected static native NSIndexPath createIndexPathForRowInSection(ObjCClass clazz, @MachineSizedSInt long row, @MachineSizedSInt long section);
    public static NSIndexPath createIndexPathForRowInSection(@MachineSizedSInt long row, @MachineSizedSInt long section) { return createIndexPathForRowInSection(ObjCClass.getByType(NSIndexPath.class), row, section); }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "indexPathForItem:inSection:")
    protected static native NSIndexPath createIndexPathForItemInSection(ObjCClass clazz, @MachineSizedSInt long item, @MachineSizedSInt long section);
    public static NSIndexPath createIndexPathForItemInSection(@MachineSizedSInt long item, @MachineSizedSInt long section) { return createIndexPathForItemInSection(ObjCClass.getByType(NSIndexPath.class), item, section); }
    /*</methods>*/
}
