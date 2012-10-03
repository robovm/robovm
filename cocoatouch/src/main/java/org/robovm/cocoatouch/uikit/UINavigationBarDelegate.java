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

@Protocol
public interface /*<name>*/ UINavigationBarDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("navigationBar:didPopItem:") @Type("void") void didPopItem(@Type("UINavigationBar *") UINavigationBar navigationBar, @Type("UINavigationItem *") UINavigationItem item);
    @Bind("navigationBar:didPushItem:") @Type("void") void didPushItem(@Type("UINavigationBar *") UINavigationBar navigationBar, @Type("UINavigationItem *") UINavigationItem item);
    @Bind("navigationBar:shouldPopItem:") @Type("BOOL") boolean shouldPopItem(@Type("UINavigationBar *") UINavigationBar navigationBar, @Type("UINavigationItem *") UINavigationItem item);
    @Bind("navigationBar:shouldPushItem:") @Type("BOOL") boolean shouldPushItem(@Type("UINavigationBar *") UINavigationBar navigationBar, @Type("UINavigationItem *") UINavigationItem item);
    /*</methods>*/

}
