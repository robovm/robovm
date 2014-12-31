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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMBlockBufferCustomBlockSource/*</name>*/ 
    extends /*<extends>*/Struct<CMBlockBufferCustomBlockSource>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMBlockBufferCustomBlockSourcePtr extends Ptr<CMBlockBufferCustomBlockSource, CMBlockBufferCustomBlockSourcePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMBlockBufferCustomBlockSource() {}
    public CMBlockBufferCustomBlockSource(int version, FunctionPtr AllocateBlock, FunctionPtr FreeBlock, VoidPtr refCon) {
        this.setVersion(version);
        this.setAllocateblock(AllocateBlock);
        this.setFreeblock(FreeBlock);
        this.setRefcon(refCon);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getVersion();
    @StructMember(0) public native CMBlockBufferCustomBlockSource setVersion(int version);
    
    @Deprecated
    @StructMember(0) public native int version();
    @Deprecated
    @StructMember(0) public native CMBlockBufferCustomBlockSource version(int version);
    
    @StructMember(1) public native FunctionPtr getAllocateblock();
    @StructMember(1) public native CMBlockBufferCustomBlockSource setAllocateblock(FunctionPtr AllocateBlock);
    
    @Deprecated
    @StructMember(1) public native FunctionPtr AllocateBlock();
    @Deprecated
    @StructMember(1) public native CMBlockBufferCustomBlockSource AllocateBlock(FunctionPtr AllocateBlock);
    
    @StructMember(2) public native FunctionPtr getFreeblock();
    @StructMember(2) public native CMBlockBufferCustomBlockSource setFreeblock(FunctionPtr FreeBlock);
    
    @Deprecated
    @StructMember(2) public native FunctionPtr FreeBlock();
    @Deprecated
    @StructMember(2) public native CMBlockBufferCustomBlockSource FreeBlock(FunctionPtr FreeBlock);
    
    @StructMember(3) public native VoidPtr getRefcon();
    @StructMember(3) public native CMBlockBufferCustomBlockSource setRefcon(VoidPtr refCon);
    
    @Deprecated
    @StructMember(3) public native VoidPtr refCon();
    @Deprecated
    @StructMember(3) public native CMBlockBufferCustomBlockSource refCon(VoidPtr refCon);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
