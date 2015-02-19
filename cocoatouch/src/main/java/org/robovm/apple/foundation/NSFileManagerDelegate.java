/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSFileManagerDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "fileManager:shouldCopyItemAtPath:toPath:")
    boolean shouldCopyItemAtPath(NSFileManager fileManager, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldCopyItemAtURL:toURL:")
    boolean shouldCopyItemAtURL(NSFileManager fileManager, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldProceedAfterError:copyingItemAtPath:toPath:")
    boolean shouldProceedCopyingItemAtPath(NSFileManager fileManager, NSError error, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:copyingItemAtURL:toURL:")
    boolean shouldProceedCopyingItemAtURL(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldMoveItemAtPath:toPath:")
    boolean shouldMoveItemAtPath(NSFileManager fileManager, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldMoveItemAtURL:toURL:")
    boolean shouldMoveItemAtURL(NSFileManager fileManager, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldProceedAfterError:movingItemAtPath:toPath:")
    boolean shouldProceedMovingItemAtPath(NSFileManager fileManager, NSError error, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:movingItemAtURL:toURL:")
    boolean shouldProceedMovingItemAtURL(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldLinkItemAtPath:toPath:")
    boolean shouldLinkItemAtPath(NSFileManager fileManager, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldLinkItemAtURL:toURL:")
    boolean shouldLinkItemAtURL(NSFileManager fileManager, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldProceedAfterError:linkingItemAtPath:toPath:")
    boolean shouldProceedLinkingItemAtPath(NSFileManager fileManager, NSError error, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:linkingItemAtURL:toURL:")
    boolean shouldProceedLinkingItemAtURL(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldRemoveItemAtPath:")
    boolean shouldRemoveItemAtPath(NSFileManager fileManager, String path);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldRemoveItemAtURL:")
    boolean shouldRemoveItemAtURL(NSFileManager fileManager, NSURL URL);
    @Method(selector = "fileManager:shouldProceedAfterError:removingItemAtPath:")
    boolean shouldProceedRemovingItemAtPath(NSFileManager fileManager, NSError error, String path);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:removingItemAtURL:")
    boolean shouldProceedRemovingItemAtURL(NSFileManager fileManager, NSError error, NSURL URL);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
