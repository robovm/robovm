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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLResponse/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLResponsePtr extends Ptr<NSURLResponse, NSURLResponsePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLResponse.class); }/*</bind>*/
    /*<constants>*/
    public static final int UnknownLength = -1;
    /*</constants>*/
    /*<constructors>*/
    public NSURLResponse() {}
    protected NSURLResponse(SkipInit skipInit) { super(skipInit); }
    public NSURLResponse(NSURL URL, String MIMEType, @MachineSizedSInt long length, String name) { super((SkipInit) null); initObject(initWithURL$MIMEType$expectedContentLength$textEncodingName$(URL, MIMEType, length, name)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:MIMEType:expectedContentLength:textEncodingName:")
    protected native @Pointer long initWithURL$MIMEType$expectedContentLength$textEncodingName$(NSURL URL, String MIMEType, @MachineSizedSInt long length, String name);
    @Method(selector = "URL")
    public native NSURL URL();
    @Method(selector = "MIMEType")
    public native String MIMEType();
    @Method(selector = "expectedContentLength")
    public native long expectedContentLength();
    @Method(selector = "textEncodingName")
    public native String textEncodingName();
    @Method(selector = "suggestedFilename")
    public native String suggestedFilename();
    /*</methods>*/
}
