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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSXMLParser/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSXMLParserPtr extends Ptr<NSXMLParser, NSXMLParserPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSXMLParser.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSXMLParser() {}
    protected NSXMLParser(SkipInit skipInit) { super(skipInit); }
    public NSXMLParser(NSURL url) { super((SkipInit) null); initObject(initWithContentsOfURL$(url)); }
    public NSXMLParser(NSData data) { super((SkipInit) null); initObject(initWithData$(data)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSXMLParser(NSInputStream stream) { super((SkipInit) null); initObject(initWithStream$(stream)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithContentsOfURL:")
    protected native @Pointer long initWithContentsOfURL$(NSURL url);
    @Method(selector = "initWithData:")
    protected native @Pointer long initWithData$(NSData data);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithStream:")
    protected native @Pointer long initWithStream$(NSInputStream stream);
    @Method(selector = "delegate")
    public native NSXMLParserDelegate delegate();
    @Method(selector = "setDelegate:")
    public native void setDelegate(NSXMLParserDelegate delegate);
    @Method(selector = "setShouldProcessNamespaces:")
    public native void setShouldProcessNamespaces(boolean shouldProcessNamespaces);
    @Method(selector = "setShouldReportNamespacePrefixes:")
    public native void setShouldReportNamespacePrefixes(boolean shouldReportNamespacePrefixes);
    @Method(selector = "setShouldResolveExternalEntities:")
    public native void setShouldResolveExternalEntities(boolean shouldResolveExternalEntities);
    @Method(selector = "shouldProcessNamespaces")
    public native boolean shouldProcessNamespaces();
    @Method(selector = "shouldReportNamespacePrefixes")
    public native boolean shouldReportNamespacePrefixes();
    @Method(selector = "shouldResolveExternalEntities")
    public native boolean shouldResolveExternalEntities();
    @Method(selector = "parse")
    public native boolean parse();
    @Method(selector = "abortParsing")
    public native void abortParsing();
    @Method(selector = "parserError")
    public native NSError parserError();
    @Method(selector = "publicID")
    public native String publicID();
    @Method(selector = "systemID")
    public native String systemID();
    @Method(selector = "lineNumber")
    public native @MachineSizedSInt long lineNumber();
    @Method(selector = "columnNumber")
    public native @MachineSizedSInt long columnNumber();
    /*</methods>*/
}
