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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html">UINib Class Reference</a>
 *   @since Available in iOS 4.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UINib /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINib /*</name>*/.class);
    }

    /*<constructors>*/
    public UINib() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html#//apple_ref/occ/clm/UINib/nibWithData:bundle:">+ (UINib *)nibWithData:(NSData *)data bundle:(NSBundle *)bundleOrNil</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("nibWithData:bundle:") public native static @Type("UINib *") UINib fromData(@Type("NSData *") NSData data, @Type("NSBundle *") NSBundle bundleOrNil);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html#//apple_ref/occ/clm/UINib/nibWithNibName:bundle:">+ (UINib *)nibWithNibName:(NSString *)name bundle:(NSBundle *)bundleOrNil</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("nibWithNibName:bundle:") public native static @Type("UINib *") UINib fromName(@Type("NSString *") String name, @Type("NSBundle *") NSBundle bundleOrNil);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html#//apple_ref/occ/instm/UINib/instantiateWithOwner:options:">- (NSArray *)instantiateWithOwner:(id)ownerOrNil options:(NSDictionary *)optionsOrNil</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("instantiateWithOwner:options:") public native @Type("NSArray *") NSArray instantiate(@Type("id") NSObject ownerOrNil, @Type("NSDictionary *") NSDictionary optionsOrNil);
    /*</methods>*/

}
