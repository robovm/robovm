/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.foundation;

/*<imports>*/
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSURLRequest_Class/Reference/Reference.html">NSURLRequest Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSURLRequest /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSURLRequest /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSURLRequest /*</name>*/.class);

    /*<constructors>*/
    protected NSURLRequest(SkipInit skipInit) { super(skipInit); }
    public NSURLRequest() {}
    
    //+ (id)requestWithURL:(NSURL *)URL;
  	private static final Selector requestWithURL$ = Selector.register("requestWithURL:");
  	@Bridge private native static NSURLRequest objc_requestWithURL(ObjCClass __self__, Selector __cmd__, NSURL URL);
  	public static NSURLRequest requestWithURL(NSURL url) {
  		return objc_requestWithURL(objCClass, requestWithURL$, url);
  	}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
  	//- (NSURL *)URL
  	private static final Selector URL$ = Selector.register("URL");
  	@Bridge private native static NSURL objc_URL(NSURLRequest __self__, Selector __cmd__);
  	public NSURL getURL() {
  		return objc_URL(this, URL$);
  	}
    /*</methods>*/
    /*<callbacks>*/
    /*</callbacks>*/

}
