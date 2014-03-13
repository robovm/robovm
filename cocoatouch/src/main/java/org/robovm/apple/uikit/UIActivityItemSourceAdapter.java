/*
 * Copyright (C) 2014 Trillian AB
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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIActivityItemSourceAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIActivityItemSource/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("activityViewControllerPlaceholderItem:")
    public NSObject getPlaceholderItem(UIActivityViewController activityViewController) { throw new UnsupportedOperationException(); }
    @NotImplemented("activityViewController:itemForActivityType:")
    public NSObject getItem(UIActivityViewController activityViewController, String activityType) { throw new UnsupportedOperationException(); }
    @NotImplemented("activityViewController:subjectForActivityType:")
    public String activityViewController$subjectForActivityType$(UIActivityViewController activityViewController, String activityType) { throw new UnsupportedOperationException(); }
    @NotImplemented("activityViewController:dataTypeIdentifierForActivityType:")
    public String activityViewController$dataTypeIdentifierForActivityType$(UIActivityViewController activityViewController, String activityType) { throw new UnsupportedOperationException(); }
    @NotImplemented("activityViewController:thumbnailImageForActivityType:suggestedSize:")
    public UIImage activityViewController$thumbnailImageForActivityType$suggestedSize$(UIActivityViewController activityViewController, String activityType, @ByVal CGSize size) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
