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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
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
    public NSKeyedUnarchiver(NSData data) { super((SkipInit) null); initObject(initForReadingWithData$(data)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public static NSObject unarchive(File file) {
        if (file == null) {
            throw new NullPointerException("file");
        }
        return unarchiveObjectWithFile$(file.getAbsolutePath());
    }
    
    /*<methods>*/
    @Method(selector = "initForReadingWithData:")
    protected native @Pointer long initForReadingWithData$(NSData data);
    @Method(selector = "setDelegate:")
    public native void setDelegate(NSKeyedUnarchiverDelegate delegate);
    @Method(selector = "delegate")
    public native NSKeyedUnarchiverDelegate getDelegate();
    @Method(selector = "finishDecoding")
    public native void finishDecoding();
    @Method(selector = "setClass:forClassName:")
    public native void setClassForClassName(ObjCClass cls, String codedName);
    @Method(selector = "classForClassName:")
    public native ObjCClass getClassForClassName(String codedName);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setRequiresSecureCoding:")
    public native void setRequiresSecureCoding(boolean b);
    @Method(selector = "unarchiveObjectWithData:")
    public static native NSObject unarchive(NSData data);
    @Method(selector = "unarchiveObjectWithFile:")
    private static native NSObject unarchiveObjectWithFile$(String path);
    @Method(selector = "setClass:forClassName:")
    public static native void setDefaultClassForClassName(ObjCClass cls, String codedName);
    @Method(selector = "classForClassName:")
    public static native ObjCClass getDefaultClassForClassName(String codedName);
    /*</methods>*/
}
