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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
@Marshaler(NSError.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSError/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSError toObject(Class<? extends NSError> cls, long handle, long flags) {
            if (handle == 0) {
                return null;
            }
            String domain = domain(handle);
            Class<? extends NSError> nsErrorClass = allNSErrorClasses.get(domain);
            if (nsErrorClass != null) {
                cls = nsErrorClass;
            }
            NSError o = (NSError) ObjCObject.toObjCObject(cls, handle, true);
            return o;
        }
        @MarshalsPointer
        public static long toNative(NSError o, long flags) {
            if (o == null) {
                return 0L;
            }
            return o.getHandle();
        }
    }
    
    private static final Map<String, Class<? extends NSError>> allNSErrorClasses = new HashMap<>();
    private static final int ABSTRACT = 0x00000400;
    
    static {
        @SuppressWarnings("unchecked")
        Class<? extends NSError>[] classes = (Class<? extends NSError>[]) 
                VM.listClasses(NSError.class, ClassLoader.getSystemClassLoader());
        Class<?>[] emptyArgs = new Class<?>[0];
        final Class<?> nsErrorClass = NSError.class;
        for (Class<? extends NSError> cls : classes) {
            if (cls != nsErrorClass && (cls.getModifiers() & ABSTRACT) == 0) {
                try {
                    Bro.bind(cls); // Global values need to be bound.
                    java.lang.reflect.Method m = cls.getMethod("getClassDomain", emptyArgs);
                    String domain = (String) m.invoke(null);
                    allNSErrorClasses.put(domain, cls);
                } catch (Throwable e) {
                    System.err.println("WARN: Failed to call getClassDomain() for " 
                            + "the NSError subclass " + cls.getName());
                }
            }
        }
    }
    
    
    /*<ptr>*/public static class NSErrorPtr extends Ptr<NSError, NSErrorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public NSError(String domain, @MachineSizedSInt long code, NSErrorUserInfo dict) {
        this(new NSString(domain), code, dict);
    }
    /*<constructors>*/
    protected NSError(SkipInit skipInit) { super(skipInit); }
    public NSError(NSString domain, @MachineSizedSInt long code, NSErrorUserInfo dict) { super((SkipInit) null); initObject(initWithDomain$code$userInfo$(domain, code, dict)); }
    /*</constructors>*/
    
    private static final Selector domain = Selector.register("domain");
    protected static String domain(long handle) {
        long h = ObjCRuntime.ptr_objc_msgSend(handle, domain.getHandle());
        NSString s = ObjCObject.toObjCObject(NSString.class, h);
        return s.toString();
    }
    
    /*<properties>*/
    @Property(selector = "domain")
    public native String getDomain();
    @Property(selector = "code")
    public native @MachineSizedSInt long getCode();
    @Property(selector = "userInfo")
    public native NSErrorUserInfo getUserInfo();
    @Property(selector = "localizedDescription")
    public native String getLocalizedDescription();
    @Property(selector = "localizedFailureReason")
    public native String getLocalizedFailureReason();
    @Property(selector = "localizedRecoverySuggestion")
    public native String getLocalizedRecoverySuggestion();
    @Property(selector = "localizedRecoveryOptions")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getLocalizedRecoveryOptions();
    @Property(selector = "recoveryAttempter")
    public native NSErrorRecoveryAttempting getRecoveryAttempter();
    @Property(selector = "helpAnchor")
    public native String getHelpAnchor();
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSURL getURL() {
        NSErrorUserInfo info = getUserInfo();
        if (info == null) return null;
        return info.getURL();
    }
    /*<methods>*/
    @Method(selector = "initWithDomain:code:userInfo:")
    protected native @Pointer long initWithDomain$code$userInfo$(NSString domain, @MachineSizedSInt long code, NSErrorUserInfo dict);
    /*</methods>*/
}
