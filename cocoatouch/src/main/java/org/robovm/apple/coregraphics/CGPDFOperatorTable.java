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
package org.robovm.apple.coregraphics;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFOperatorTable/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface OperatorCallback {
        void invoke(CGPDFScanner scanner);
    }
    
    private static java.util.concurrent.atomic.AtomicLong infoId = new java.util.concurrent.atomic.AtomicLong();
    private static final java.lang.reflect.Method cbOperator;
    private static final LongMap<OperatorCallback> callbacks = new LongMap<>();
    
    static {
        try {
            cbOperator = CGPDFOperatorTable.class.getDeclaredMethod("cbOperator", CGPDFScanner.class, long.class); 
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<ptr>*/public static class CGPDFOperatorTablePtr extends Ptr<CGPDFOperatorTable, CGPDFOperatorTablePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPDFOperatorTable.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGPDFOperatorTable() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    long localInfoId = -1;
    
    @Callback
    private static void cbOperator(CGPDFScanner scanner, long info) {
        OperatorCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(info);
        }
        callback.invoke(scanner);
    }
    
    public void setCallback(String name, OperatorCallback callback) {
        if (localInfoId == -1) {
            localInfoId = CGPDFOperatorTable.infoId.getAndIncrement();
        }
        setCallback(name, new FunctionPtr(cbOperator));
        synchronized (callbacks) {
            callbacks.put(localInfoId, callback);
        }
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFOperatorTableCreate", optional=true)
    public static native CGPDFOperatorTable create();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFOperatorTableSetCallback", optional=true)
    private native void setCallback(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsAsciiZMarshaler.class) String name, FunctionPtr callback);
    /*</methods>*/
}
