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
package org.robovm.apple.coreservices;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNetDiagnostic/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNetDiagnosticPtr extends Ptr<CFNetDiagnostic, CFNetDiagnosticPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFNetDiagnostic.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNetDiagnostic() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFNetDiagnostic create(CFReadStream readStream, CFWriteStream writeStream) {
        return create(null, readStream, writeStream);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFNetDiagnostic create(NSURL url) {
        return create(null, url);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFNetDiagnosticStatus getNetworkStatusPassively() {
        return getNetworkStatusPassively(null);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getNetworkStatusDescriptionPassively() {
        NSString.NSStringPtr ptr = new NSString.NSStringPtr();
        getNetworkStatusPassively(ptr);
        NSString desc = ptr.get();
        if (desc != null) return desc.toString();
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetDiagnosticCreateWithStreams", optional=true)
    protected static native CFNetDiagnostic create(CFAllocator alloc, CFReadStream readStream, CFWriteStream writeStream);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetDiagnosticCreateWithURL", optional=true)
    protected static native CFNetDiagnostic create(CFAllocator alloc, NSURL url);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetDiagnosticSetName", optional=true)
    public native void setName(String name);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetDiagnosticDiagnoseProblemInteractively", optional=true)
    public native CFNetDiagnosticStatus diagnoseProblemInteractively();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetDiagnosticCopyNetworkStatusPassively", optional=true)
    protected native CFNetDiagnosticStatus getNetworkStatusPassively(NSString.NSStringPtr description);
    /*</methods>*/
}
