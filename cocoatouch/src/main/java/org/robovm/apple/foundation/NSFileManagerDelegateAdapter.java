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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
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
    public boolean fileManager$shouldCopyItemAtPath$toPath$(NSFileManager fileManager, String srcPath, String dstPath) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldCopyItemAtURL:toURL:")
    public boolean fileManager$shouldCopyItemAtURL$toURL$(NSFileManager fileManager, NSURL srcURL, NSURL dstURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("fileManager:shouldProceedAfterError:copyingItemAtPath:toPath:")
    public boolean fileManager$shouldProceedAfterError$copyingItemAtPath$toPath$(NSFileManager fileManager, NSError error, String srcPath, String dstPath) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:copyingItemAtURL:toURL:")
    public boolean fileManager$shouldProceedAfterError$copyingItemAtURL$toURL$(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("fileManager:shouldMoveItemAtPath:toPath:")
    public boolean fileManager$shouldMoveItemAtPath$toPath$(NSFileManager fileManager, String srcPath, String dstPath) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldMoveItemAtURL:toURL:")
    public boolean fileManager$shouldMoveItemAtURL$toURL$(NSFileManager fileManager, NSURL srcURL, NSURL dstURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("fileManager:shouldProceedAfterError:movingItemAtPath:toPath:")
    public boolean fileManager$shouldProceedAfterError$movingItemAtPath$toPath$(NSFileManager fileManager, NSError error, String srcPath, String dstPath) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:movingItemAtURL:toURL:")
    public boolean fileManager$shouldProceedAfterError$movingItemAtURL$toURL$(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("fileManager:shouldLinkItemAtPath:toPath:")
    public boolean fileManager$shouldLinkItemAtPath$toPath$(NSFileManager fileManager, String srcPath, String dstPath) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldLinkItemAtURL:toURL:")
    public boolean fileManager$shouldLinkItemAtURL$toURL$(NSFileManager fileManager, NSURL srcURL, NSURL dstURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("fileManager:shouldProceedAfterError:linkingItemAtPath:toPath:")
    public boolean fileManager$shouldProceedAfterError$linkingItemAtPath$toPath$(NSFileManager fileManager, NSError error, String srcPath, String dstPath) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:linkingItemAtURL:toURL:")
    public boolean fileManager$shouldProceedAfterError$linkingItemAtURL$toURL$(NSFileManager fileManager, NSError error, NSURL srcURL, NSURL dstURL) { throw new UnsupportedOperationException(); }
    @NotImplemented("fileManager:shouldRemoveItemAtPath:")
    public boolean fileManager$shouldRemoveItemAtPath$(NSFileManager fileManager, String path) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldRemoveItemAtURL:")
    public boolean fileManager$shouldRemoveItemAtURL$(NSFileManager fileManager, NSURL URL) { throw new UnsupportedOperationException(); }
    @NotImplemented("fileManager:shouldProceedAfterError:removingItemAtPath:")
    public boolean fileManager$shouldProceedAfterError$removingItemAtPath$(NSFileManager fileManager, NSError error, String path) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("fileManager:shouldProceedAfterError:removingItemAtURL:")
    public boolean fileManager$shouldProceedAfterError$removingItemAtURL$(NSFileManager fileManager, NSError error, NSURL URL) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
