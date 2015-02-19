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
package org.robovm.apple.coreaudio;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioBuffer/*</name>*/ 
    extends /*<extends>*/Struct<AudioBuffer>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioBufferPtr extends Ptr<AudioBuffer, AudioBufferPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioBuffer() {}
    public AudioBuffer(int mNumberChannels, int mDataByteSize, VoidPtr mData) {
        this.setMNumberChannels(mNumberChannels);
        this.setMDataByteSize(mDataByteSize);
        this.setMData(mData);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getMNumberChannels();
    @StructMember(0) public native AudioBuffer setMNumberChannels(int mNumberChannels);
    @StructMember(1) public native int getMDataByteSize();
    @StructMember(1) public native AudioBuffer setMDataByteSize(int mDataByteSize);
    @StructMember(2) public native VoidPtr getMData();
    @StructMember(2) public native AudioBuffer setMData(VoidPtr mData);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
