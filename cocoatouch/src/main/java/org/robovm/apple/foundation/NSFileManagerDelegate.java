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
import org.robovm.apple.uikit.*;
import org.robovm.apple.security.*;
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
    boolean fileManager$shouldCopyItemAtPath$toPath$(NSFileManager fileManager, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldCopyItemAtURL:toURL:")
    boolean fileManager$shouldCopyItemAtURL$toURL$(NSFileManager fileManager, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldProceedAfterError:copyingItemAtPath:toPath:")
    boolean fileManager$shouldProceedAfterError$copyingItemAtPath$toPath$(NSFileManager fileManager, NSError error, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:copyingItemAtURL:toURL:")
    boolean fileManager$shouldProceedAfterError$copyingItemAtURL$toURL$(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldMoveItemAtPath:toPath:")
    boolean fileManager$shouldMoveItemAtPath$toPath$(NSFileManager fileManager, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldMoveItemAtURL:toURL:")
    boolean fileManager$shouldMoveItemAtURL$toURL$(NSFileManager fileManager, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldProceedAfterError:movingItemAtPath:toPath:")
    boolean fileManager$shouldProceedAfterError$movingItemAtPath$toPath$(NSFileManager fileManager, NSError error, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:movingItemAtURL:toURL:")
    boolean fileManager$shouldProceedAfterError$movingItemAtURL$toURL$(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldLinkItemAtPath:toPath:")
    boolean fileManager$shouldLinkItemAtPath$toPath$(NSFileManager fileManager, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldLinkItemAtURL:toURL:")
    boolean fileManager$shouldLinkItemAtURL$toURL$(NSFileManager fileManager, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldProceedAfterError:linkingItemAtPath:toPath:")
    boolean fileManager$shouldProceedAfterError$linkingItemAtPath$toPath$(NSFileManager fileManager, NSError error, String srcPath, String dstPath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:linkingItemAtURL:toURL:")
    boolean fileManager$shouldProceedAfterError$linkingItemAtURL$toURL$(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL);
    @Method(selector = "fileManager:shouldRemoveItemAtPath:")
    boolean fileManager$shouldRemoveItemAtPath$(NSFileManager fileManager, String path);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldRemoveItemAtURL:")
    boolean fileManager$shouldRemoveItemAtURL$(NSFileManager fileManager, NSURL URL);
    @Method(selector = "fileManager:shouldProceedAfterError:removingItemAtPath:")
    boolean fileManager$shouldProceedAfterError$removingItemAtPath$(NSFileManager fileManager, NSError error, String path);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileManager:shouldProceedAfterError:removingItemAtURL:")
    boolean fileManager$shouldProceedAfterError$removingItemAtURL$(NSFileManager fileManager, NSError error, NSURL URL);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
