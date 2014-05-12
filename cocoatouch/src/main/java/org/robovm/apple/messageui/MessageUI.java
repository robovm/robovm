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
package org.robovm.apple.messageui;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MessageUI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MessageUI/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MessageUI.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="MFMailComposeErrorDomain", optional=true)
    public static native NSString MailComposeErrorDomain();
    @GlobalValue(symbol="MFMessageComposeViewControllerAttachmentURL", optional=true)
    public static native NSString MessageComposeViewControllerAttachmentURL();
    @GlobalValue(symbol="MFMessageComposeViewControllerAttachmentAlternateFilename", optional=true)
    public static native NSString MessageComposeViewControllerAttachmentAlternateFilename();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MFMessageComposeViewControllerTextMessageAvailabilityDidChangeNotification", optional=true)
    public static native NSString MessageComposeViewControllerTextMessageAvailabilityDidChangeNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MFMessageComposeViewControllerTextMessageAvailabilityKey", optional=true)
    public static native NSString MessageComposeViewControllerTextMessageAvailabilityKey();
    /*</methods>*/
}
