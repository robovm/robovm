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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AUGraph/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AUGraphPtr extends Ptr<AUGraph, AUGraphPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(AUGraph.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AUGraph() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="NewAUGraph", optional=true)
    public static native OSStatus create(AUGraph.AUGraphPtr outGraph);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphAddNode", optional=true)
    public native OSStatus addNode(AudioComponentDescription inDescription, IntPtr outNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphRemoveNode", optional=true)
    public native OSStatus removeNode(int inNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNodeCount", optional=true)
    public native OSStatus getNodeCount(IntPtr outNumberOfNodes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetIndNode", optional=true)
    public native OSStatus getIndNode(int inIndex, IntPtr outNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphNodeInfo", optional=true)
    public native OSStatus nodeInfo(int inNode, AudioComponentDescription outDescription, AudioComponentInstance.AudioComponentInstancePtr outAudioUnit);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphConnectNodeInput", optional=true)
    public native OSStatus connectNodeInput(int inSourceNode, int inSourceOutputNumber, int inDestNode, int inDestInputNumber);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphSetNodeInputCallback", optional=true)
    public native OSStatus setNodeInputCallback(int inDestNode, int inDestInputNumber, AURenderCallbackStruct inInputCallback);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphDisconnectNodeInput", optional=true)
    public native OSStatus disconnectNodeInput(int inDestNode, int inDestInputNumber);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphClearConnections", optional=true)
    public native OSStatus clearConnections();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNumberOfInteractions", optional=true)
    public native OSStatus getNumberOfInteractions(IntPtr outNumInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetInteractionInfo", optional=true)
    public native OSStatus getInteractionInfo(int inInteractionIndex, AUNodeInteraction outInteraction);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphCountNodeInteractions", optional=true)
    public native OSStatus countNodeInteractions(int inNode, IntPtr outNumInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNodeInteractions", optional=true)
    public native OSStatus getNodeInteractions(int inNode, IntPtr ioNumInteractions, AUNodeInteraction outInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphUpdate", optional=true)
    public native OSStatus update(BooleanPtr outIsUpdated);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphOpen", optional=true)
    public native OSStatus open();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphInitialize", optional=true)
    public native OSStatus initialize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphUninitialize", optional=true)
    public native OSStatus uninitialize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphStart", optional=true)
    public native OSStatus start();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphStop", optional=true)
    public native OSStatus stop();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsOpen", optional=true)
    public native OSStatus isOpen(BooleanPtr outIsOpen);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsInitialized", optional=true)
    public native OSStatus isInitialized(BooleanPtr outIsInitialized);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsRunning", optional=true)
    public native OSStatus isRunning(BooleanPtr outIsRunning);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetCPULoad", optional=true)
    public native OSStatus getCPULoad(FloatPtr outAverageCPULoad);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetMaxCPULoad", optional=true)
    public native OSStatus getMaxCPULoad(FloatPtr outMaxLoad);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphAddRenderNotify", optional=true)
    public native OSStatus addRenderNotify(FunctionPtr inCallback, VoidPtr inRefCon);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphRemoveRenderNotify", optional=true)
    public native OSStatus removeRenderNotify(FunctionPtr inCallback, VoidPtr inRefCon);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetAUGraph", optional=true)
    public static native OSStatus musicSequenceSet(MusicSequence inSequence, AUGraph inGraph);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetAUGraph", optional=true)
    public static native OSStatus musicSequenceGet(MusicSequence inSequence, AUGraph.AUGraphPtr outGraph);
    /*</methods>*/
}
