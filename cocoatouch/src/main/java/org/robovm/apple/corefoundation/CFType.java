/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.corefoundation;

import static org.robovm.rt.bro.MarshalerFlags.*;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
@Marshalers({
    @Marshaler(CFType.Marshaler.class),
    @Marshaler(CFString.AsStringMarshaler.class)
})
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFType/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ implements AutoCloseable {

    /*<ptr>*/public static class CFTypePtr extends Ptr<CFType, CFTypePtr> {}/*</ptr>*/
    
    public static class Marshaler {
        @MarshalsPointer
        public static CFType toObject(Class<? extends CFType> cls, long handle, long flags) {
            return toObject(cls, handle, flags, true);
        }
        static CFType toObject(Class<? extends CFType> cls, long handle, long flags, boolean retain) {
            if (handle == 0) {
                return null;
            }
            long typeId = getTypeID(handle);
            Class<? extends CFType> cfTypeClass = allCFTypeClasses.get(typeId);
            if (cfTypeClass != null) {
                cls = cfTypeClass;
            }
            CFType o = (CFType) NativeObject.Marshaler.toObject(cls, handle, flags);
            if (retain) {
                retain(handle);
            }
            return o;
        }
        @MarshalsPointer
        public static long toNative(CFType o, long flags) {
            if (o == null) {
                return 0L;
            }
            if ((flags & (CALL_TYPE_CALLBACK | CALL_TYPE_GLOBAL_VALUE | CALL_TYPE_STRUCT_MEMBER)) > 0) {
                // Make sure the object outlives the GC
                o.retain();
            }
            return o.getHandle();
        }
    }
    
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<?> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.toList(CFType.class);
        }
        @MarshalsPointer
        public static long toNative(List<? extends CFType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray o = null;
            if (l instanceof CFArray) {
                o = (CFArray) l;
            } else {
                o = CFArray.create((List<? extends CFType>) l);
            }
            return CFType.Marshaler.toNative(o, flags);
        }
    }
    
    /**
     * Marshaler used for create and copy methods which have already retained
     * the object they return.
     */
    public static class NoRetainMarshaler {
        @MarshalsPointer
        public static CFType toObject(Class<? extends CFType> cls, long handle, long flags) {
            return Marshaler.toObject(cls, handle, flags, false);
        }
        @MarshalsPointer
        public static long toNative(CFType o, long flags) {
            return Marshaler.toNative(o, flags);
        }
    }

    private static final LongMap<Class<? extends CFType>> allCFTypeClasses = new LongMap<>();
    private static final int ABSTRACT = 0x00000400;
    
    static {
        @SuppressWarnings("unchecked")
        Class<? extends CFType>[] classes = (Class<? extends CFType>[]) 
                VM.listClasses(CFType.class, ClassLoader.getSystemClassLoader());
        Class<?>[] emptyArgs = new Class<?>[0];
        final Class<?> cfTypeClass = CFType.class;
        for (Class<? extends CFType> cls : classes) {
            if (cls != cfTypeClass && (cls.getModifiers() & ABSTRACT) == 0) {
                try {
                    java.lang.reflect.Method m = cls.getMethod("getClassTypeID", emptyArgs);
                    Long typeId = (Long) m.invoke(null);
                    allCFTypeClasses.put(typeId, cls);
                } catch (Throwable e) {
                	// Ignore, because several of Apple's CFType subclasses don't contain a getClassTypeID() method.
                }
            }
        }
    }
    
    /*<bind>*/static { Bro.bind(CFType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFType() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    @Override
    protected final void finalize() throws Throwable {
        dispose(true);
    }
    
    public final void dispose() {
        dispose(false);
    }
    
    protected void doDispose() {
        // Only release once
        long handle = getHandle();
        if (handle != 0) {
            release(handle);
        }
    }
    
    protected void dispose(boolean finalizing) {
        long handle = getHandle();
        if (handle != 0) {
            doDispose();
            setHandle(0);
        }
        if (finalizing) {
            try {
                super.finalize();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public final void close() {
        try {
            dispose(false);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }    

    @Override
    public String toString() {
        try (CFString s = getDescription()) {
            return s.toString();
        }
    }

    @Bridge(symbol="CFGetTypeID")
    private static native @MachineSizedUInt long getTypeID(@Pointer long handle);
    @Bridge(symbol="CFRetain")
    protected static native @Pointer long retain(@Pointer long handle);
    @Bridge(symbol="CFRelease")
    protected static native void release(@Pointer long handle);

    /*<methods>*/
    @Bridge(symbol="CFGetTypeID", optional=true)
    public native @MachineSizedUInt long getTypeID();
    @Bridge(symbol="CFCopyTypeIDDescription", optional=true)
    public static native String getTypeIDDescription(@MachineSizedUInt long type_id);
    @Bridge(symbol="CFRetain", optional=true)
    public native CFType retain();
    @Bridge(symbol="CFRelease", optional=true)
    public native void release();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFAutorelease", optional=true)
    public native CFType autorelease();
    @Bridge(symbol="CFGetRetainCount", optional=true)
    public native @MachineSizedSInt long getRetainCount();
    @Bridge(symbol="CFEqual", optional=true)
    public native boolean equalsTo(CFType cf2);
    @Bridge(symbol="CFHash", optional=true)
    public native @MachineSizedUInt long hash();
    @Bridge(symbol="CFCopyDescription", optional=true)
    protected native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString getDescription();
    @Bridge(symbol="CFGetAllocator", optional=true)
    public native CFAllocator getAllocator();
    @Bridge(symbol="CFMakeCollectable", optional=true)
    public native CFType makeCollectable();
    @Bridge(symbol="CFShow", optional=true)
    public native void show();
    /*</methods>*/
}
