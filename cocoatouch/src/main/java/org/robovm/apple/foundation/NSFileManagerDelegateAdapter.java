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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileManagerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFileManagerDelegate/*</implements>*/ {

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
    @NotImplemented("fileManager:shouldCopyItemAtPath:toPath:")
    public boolean shouldCopyItemAtPath(NSFileManager fileManager, String srcPath, String dstPath) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldCopyItemAtURL:toURL:")
    public boolean shouldCopyItemAtURL(NSFileManager fileManager, NSURL srcURL, NSURL dstURL) { return false; }
    @NotImplemented("fileManager:shouldProceedAfterError:copyingItemAtPath:toPath:")
    public boolean shouldProceedCopyingItemAtPath(NSFileManager fileManager, NSError error, String srcPath, String dstPath) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:copyingItemAtURL:toURL:")
    public boolean shouldProceedCopyingItemAtURL(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL) { return false; }
    @NotImplemented("fileManager:shouldMoveItemAtPath:toPath:")
    public boolean shouldMoveItemAtPath(NSFileManager fileManager, String srcPath, String dstPath) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldMoveItemAtURL:toURL:")
    public boolean shouldMoveItemAtURL(NSFileManager fileManager, NSURL srcURL, NSURL dstURL) { return false; }
    @NotImplemented("fileManager:shouldProceedAfterError:movingItemAtPath:toPath:")
    public boolean shouldProceedMovingItemAtPath(NSFileManager fileManager, NSError error, String srcPath, String dstPath) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:movingItemAtURL:toURL:")
    public boolean shouldProceedMovingItemAtURL(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL) { return false; }
    @NotImplemented("fileManager:shouldLinkItemAtPath:toPath:")
    public boolean shouldLinkItemAtPath(NSFileManager fileManager, String srcPath, String dstPath) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldLinkItemAtURL:toURL:")
    public boolean shouldLinkItemAtURL(NSFileManager fileManager, NSURL srcURL, NSURL dstURL) { return false; }
    @NotImplemented("fileManager:shouldProceedAfterError:linkingItemAtPath:toPath:")
    public boolean shouldProceedLinkingItemAtPath(NSFileManager fileManager, NSError error, String srcPath, String dstPath) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:linkingItemAtURL:toURL:")
    public boolean shouldProceedLinkingItemAtURL(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL) { return false; }
    @NotImplemented("fileManager:shouldRemoveItemAtPath:")
    public boolean shouldRemoveItemAtPath(NSFileManager fileManager, String path) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldRemoveItemAtURL:")
    public boolean shouldRemoveItemAtURL(NSFileManager fileManager, NSURL URL) { return false; }
    @NotImplemented("fileManager:shouldProceedAfterError:removingItemAtPath:")
    public boolean shouldProceedRemovingItemAtPath(NSFileManager fileManager, NSError error, String path) { return false; }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:removingItemAtURL:")
    public boolean shouldProceedRemovingItemAtURL(NSFileManager fileManager, NSError error, NSURL URL) { return false; }
    /*</methods>*/
}
