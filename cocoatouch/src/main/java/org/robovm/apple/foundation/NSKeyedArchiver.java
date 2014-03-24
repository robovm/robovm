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
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSKeyedArchiveRootObjectKey")
    public static native NSString KeyRootObjectKey();
    
    @Method(selector = "initForWritingWithMutableData:")
    protected native @Pointer long initForWritingWithMutableData$(NSMutableData data);
    @Method(selector = "setDelegate:")
    public native void setDelegate$(NSKeyedArchiverDelegate delegate);
    @Method(selector = "delegate")
    public native NSKeyedArchiverDelegate delegate();
    @Method(selector = "setOutputFormat:")
    public native void setOutputFormat$(NSPropertyListFormat format);
    @Method(selector = "outputFormat")
    public native NSPropertyListFormat outputFormat();
    @Method(selector = "finishEncoding")
    public native void finishEncoding();
    @Method(selector = "setClassName:forClass:")
    public native void setClassName$forClass$(String codedName, ObjCClass cls);
    @Method(selector = "classNameForClass:")
    public native String classNameForClass$(ObjCClass cls);
    @Method(selector = "encodeObject:forKey:")
    public native void encodeObject$forKey$(NSObject objv, String key);
    @Method(selector = "encodeConditionalObject:forKey:")
    public native void encodeConditionalObject$forKey$(NSObject objv, String key);
    @Method(selector = "encodeBool:forKey:")
    public native void encodeBool$forKey$(boolean boolv, String key);
    @Method(selector = "encodeInt:forKey:")
    public native void encodeInt$forKey$(int intv, String key);
    @Method(selector = "encodeInt32:forKey:")
    public native void encodeInt32$forKey$(int intv, String key);
    @Method(selector = "encodeInt64:forKey:")
    public native void encodeInt64$forKey$(long intv, String key);
    @Method(selector = "encodeFloat:forKey:")
    public native void encodeFloat$forKey$(float realv, String key);
    @Method(selector = "encodeDouble:forKey:")
    public native void encodeDouble$forKey$(double realv, String key);
    @Method(selector = "encodeBytes:length:forKey:")
    public native void encodeBytes$length$forKey$(BytePtr bytesp, @MachineSizedUInt long lenv, String key);
    @Method(selector = "setRequiresSecureCoding:")
    public native void setRequiresSecureCoding$(boolean b);
    @Method(selector = "archivedDataWithRootObject:")
    public static native NSData archivedDataWithRootObject$(NSObject rootObject);
    @Method(selector = "archiveRootObject:toFile:")
    public static native boolean archiveRootObject$toFile$(NSObject rootObject, String path);
    @Method(selector = "setClassName:forClass:")
    public static native void setDefaultClassName(String codedName, ObjCClass cls);
    @Method(selector = "classNameForClass:")
    public static native String getDefaultClassName(ObjCClass cls);
    /*</methods>*/
}
