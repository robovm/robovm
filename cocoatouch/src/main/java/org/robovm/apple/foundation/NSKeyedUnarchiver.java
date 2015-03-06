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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSKeyedUnarchiver/*</name>*/ 
    extends /*<extends>*/NSCoder/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSKeyedUnarchiverPtr extends Ptr<NSKeyedUnarchiver, NSKeyedUnarchiverPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSKeyedUnarchiver.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSKeyedUnarchiver() {}
    protected NSKeyedUnarchiver(SkipInit skipInit) { super(skipInit); }
    public NSKeyedUnarchiver(NSData data) { super((SkipInit) null); initObject(init(data)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native NSKeyedUnarchiverDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSKeyedUnarchiverDelegate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public static NSObject unarchive(File file) {
        if (file == null) {
            throw new NullPointerException("file");
        }
        return unarchiveObject(file.getAbsolutePath());
    }
    
    /*<methods>*/
    @Method(selector = "initForReadingWithData:")
    protected native @Pointer long init(NSData data);
    @Method(selector = "finishDecoding")
    public native void finishDecoding();
    @Method(selector = "setClass:forClassName:")
    public native void setClassForClassName(Class<? extends NSObject> cls, String codedName);
    @Method(selector = "classForClassName:")
    public native Class<? extends NSObject> getClassForClassName(String codedName);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setRequiresSecureCoding:")
    public native void setRequiresSecureCoding(boolean b);
    @Method(selector = "unarchiveObjectWithData:")
    public static native NSObject unarchive(NSData data);
    @Method(selector = "unarchiveObjectWithFile:")
    private static native NSObject unarchiveObject(String path);
    @Method(selector = "setClass:forClassName:")
    public static native void setDefaultClassForClassName(Class<? extends NSObject> cls, String codedName);
    @Method(selector = "classForClassName:")
    public static native Class<? extends NSObject> getDefaultClassForClassName(String codedName);
    /*</methods>*/
}
