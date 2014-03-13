/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    
    /**
     * Marshaler used for create and copy methods which have already retained
     * the object they return.
     */
    public static class NoRetainMarshaler {
        @MarshalsPointer
        public static CFType toObject(Class<? extends CFType> cls, long handle, long flags) {
            return Marshaler.toObject(cls, handle, flags, false);
        }
    }

    private static final Map<Long, Class<? extends CFType>> allCFTypeClasses = new HashMap<>();

    static {
        @SuppressWarnings("unchecked")
        Class<? extends CFType>[] classes = (Class<? extends CFType>[]) 
                VM.listClasses(CFType.class, ClassLoader.getSystemClassLoader());
        Class<?>[] emptyArgs = new Class<?>[0];
        for (Class<? extends CFType> cls : classes) {
            if (!java.lang.reflect.Modifier.isAbstract(cls.getModifiers())) {
                try {
                    Bro.bind(cls);
                    java.lang.reflect.Method m = cls.getMethod("getClassTypeID", emptyArgs);
                    Long typeId = (Long) m.invoke(null);
                    allCFTypeClasses.put(typeId, cls);
                } catch (Throwable e) {
                    System.err.println("WARN: Failed to call getClassTypeID() for " 
                            + "the CFType subclass " + cls.getName());
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
    
    public final void dispose() throws Throwable {
        dispose(false);
    }
    
    protected void dispose(boolean finalizing) throws Throwable {
        release();
        if (finalizing) {
            super.finalize();
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
        try (CFString s = copyDescription()) {
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
    @Bridge(symbol="CFGetTypeID")
    public native @MachineSizedUInt long getTypeID();
    @Bridge(symbol="CFCopyTypeIDDescription")
    public static native CFString copyTypeIDDescription(@MachineSizedUInt long type_id);
    @Bridge(symbol="CFRetain")
    public native CFType retain();
    @Bridge(symbol="CFRelease")
    public native void release();
    @Bridge(symbol="CFAutorelease")
    public native CFType autorelease();
    @Bridge(symbol="CFGetRetainCount")
    public native @MachineSizedSInt long getRetainCount();
    @Bridge(symbol="CFEqual")
    public native boolean equal(CFType cf2);
    @Bridge(symbol="CFHash")
    public native @MachineSizedUInt long hash();
    @Bridge(symbol="CFCopyDescription")
    public native CFString copyDescription();
    @Bridge(symbol="CFGetAllocator")
    public native CFAllocator getAllocator();
    @Bridge(symbol="CFMakeCollectable")
    public native CFType makeCollectable();
    @Bridge(symbol="CFShow")
    public native void show();
    /*</methods>*/
}
