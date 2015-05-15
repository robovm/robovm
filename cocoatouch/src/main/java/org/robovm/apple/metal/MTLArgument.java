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
package org.robovm.apple.metal;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Metal") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLArgument/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLArgumentPtr extends Ptr<MTLArgument, MTLArgumentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLArgument.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLArgument() {}
    protected MTLArgument(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "type")
    public native MTLArgumentType getType();
    @Property(selector = "access")
    public native MTLArgumentAccess getAccess();
    @Property(selector = "index")
    public native @MachineSizedUInt long getIndex();
    @Property(selector = "isActive")
    public native boolean isActive();
    @Property(selector = "bufferAlignment")
    public native @MachineSizedUInt long getBufferAlignment();
    @Property(selector = "bufferDataSize")
    public native @MachineSizedUInt long getBufferDataSize();
    @Property(selector = "bufferDataType")
    public native MTLDataType getBufferDataType();
    @Property(selector = "bufferStructType")
    public native MTLStructType getBufferStructType();
    @Property(selector = "threadgroupMemoryAlignment")
    public native @MachineSizedUInt long getThreadgroupMemoryAlignment();
    @Property(selector = "threadgroupMemoryDataSize")
    public native @MachineSizedUInt long getThreadgroupMemoryDataSize();
    @Property(selector = "textureType")
    public native MTLTextureType getTextureType();
    @Property(selector = "textureDataType")
    public native MTLDataType getTextureDataType();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
