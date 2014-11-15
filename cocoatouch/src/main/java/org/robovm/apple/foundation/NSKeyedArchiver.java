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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSKeyedArchiver/*</name>*/ 
    extends /*<extends>*/NSCoder/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSKeyedArchiverPtr extends Ptr<NSKeyedArchiver, NSKeyedArchiverPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSKeyedArchiver.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSKeyedArchiver() {}
    protected NSKeyedArchiver(SkipInit skipInit) { super(skipInit); }
    public NSKeyedArchiver(NSMutableData data) { super((SkipInit) null); initObject(initForWritingWithMutableData$(data)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native NSKeyedArchiverDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSKeyedArchiverDelegate v);
    @Property(selector = "outputFormat")
    public native NSPropertyListFormat getOutputFormat();
    @Property(selector = "setOutputFormat:")
    public native void setOutputFormat(NSPropertyListFormat v);
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public boolean archive(NSObject rootObject, File file) {
        if (rootObject == null) {
            throw new NullPointerException("rootObject");
        }
        if (file == null) {
            throw new NullPointerException("file");
        }
        return archiveRootObject$toFile$(rootObject, file.getAbsolutePath());
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSKeyedArchiveRootObjectKey", optional=true)
    public static native String ArchiveRootObjectKey();
    
    @Method(selector = "initForWritingWithMutableData:")
    protected native @Pointer long initForWritingWithMutableData$(NSMutableData data);
    @Method(selector = "finishEncoding")
    public native void finishEncoding();
    @Method(selector = "setClassName:forClass:")
    public native void setClassNameForClass(String codedName, Class<?> cls);
    @Method(selector = "classNameForClass:")
    public native String getClassNameForClass(Class<?> cls);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setRequiresSecureCoding:")
    public native void setRequiresSecureCoding(boolean b);
    @Method(selector = "archivedDataWithRootObject:")
    public static native NSData archive(NSObject rootObject);
    @Method(selector = "archiveRootObject:toFile:")
    private static native boolean archiveRootObject$toFile$(NSObject rootObject, String path);
    @Method(selector = "setClassName:forClass:")
    public static native void setDefaultClassNameForClass(String codedName, Class<?> cls);
    @Method(selector = "classNameForClass:")
    public static native String getDefaultClassNameForClass(Class<?> cls);
    /*</methods>*/
}
