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
import org.robovm.apple.audiounit.*;
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
    
    private static java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    
    private static LongMap<AURenderCallback> renderCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbRender;
    
    static {
        try {
            cbRender = AudioUnit.class.getDeclaredMethod("cbRender", Long.TYPE, AUMutableRenderActionFlags.class, AudioTimeStamp.class, Integer.TYPE, Integer.TYPE, AudioBufferList.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(AUGraph.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AUGraph() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static OSStatus cbRender(@Pointer long refCon, AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int busNumber, int numberFrames, AudioBufferList data) {
        synchronized (renderCallbacks) {
            return renderCallbacks.get(refCon).onRender(actionFlags, timeStamp, busNumber, numberFrames, data);
        }
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static AUGraph create() {
        AUGraph.AUGraphPtr ptr = new AUGraph.AUGraphPtr();
        create(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int addNode(AudioComponentDescription description) {
        IntPtr ptr = new IntPtr();
        addNode(description, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getNodeCount() {
        IntPtr ptr = new IntPtr();
        getNodeCount(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getIndNode(int index) {
        IntPtr ptr = new IntPtr();
        getIndNode(index, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public AudioComponentDescription getNodeDescription(int node) {
        AudioComponentDescription.AudioComponentDescriptionPtr ptr = new AudioComponentDescription.AudioComponentDescriptionPtr();
        getNodeInfo(node, ptr, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public AudioUnit getNodeAudioUnit(int node) {
        AudioUnit.AudioUnitPtr ptr = new AudioUnit.AudioUnitPtr();
        getNodeInfo(node, null, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus setNodeInputCallback(int destNode, int destInputNumber, AURenderCallback inputCallback) {
        long cid = callbackId.getAndIncrement();
        
        AURenderCallbackStruct struct = new AURenderCallbackStruct(new FunctionPtr(cbRender), cid);
        OSStatus result = setNodeInputCallback(destNode, destInputNumber, struct);
        if (result.equals(0)) {
            synchronized (renderCallbacks) {
                renderCallbacks.put(cid, inputCallback);
            }
        }
        return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfInteractions() {
        IntPtr ptr = new IntPtr();
        getNumberOfInteractions(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public AUNodeInteraction getInteractionInfo(int interactionIndex) {
        AUNodeInteraction.AUNodeInteractionPtr ptr = new AUNodeInteraction.AUNodeInteractionPtr();
        getInteractionInfo(interactionIndex, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int countNodeInteractions(int node) {
        IntPtr ptr = new IntPtr();
        countNodeInteractions(node, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public AUNodeInteraction[] getNodeInteractions(int node) {
        return getNodeInteractions(node, countNodeInteractions(node));
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public AUNodeInteraction[] getNodeInteractions(int node, int maxInteractions) {
        IntPtr count = new IntPtr(maxInteractions);
        AUNodeInteraction.AUNodeInteractionPtr ptr = new AUNodeInteraction.AUNodeInteractionPtr();
        
        OSStatus status = getNodeInteractions(node, count, ptr);
        if (status.equals(OSStatus.NO_ERR)) {
            AUNodeInteraction[] result = new AUNodeInteraction[count.get()];
            for (int i = 0; i < result.length; i++) {
                result[i] = ptr.next(i).get();
            }
            return result;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean update() {
        BooleanPtr ptr = new BooleanPtr();
        update(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isOpen() {
        BooleanPtr ptr = new BooleanPtr();
        isOpen(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isInitialized() {
        BooleanPtr ptr = new BooleanPtr();
        isInitialized(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isRunning() {
        BooleanPtr ptr = new BooleanPtr();
        isRunning(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public float getCPULoad() {
        FloatPtr ptr = new FloatPtr();
        getCPULoad(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public float getMaxCPULoad() {
        FloatPtr ptr = new FloatPtr();
        getMaxCPULoad(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus addRenderNotify(AURenderCallback callback) {
        long cid = callbackId.getAndIncrement();
        
        OSStatus result = addRenderNotify(new FunctionPtr(cbRender), cid);
        if (result.equals(0)) {
            synchronized (renderCallbacks) {
                renderCallbacks.put(cid, callback);
            }
        }
        return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus removeRenderNotify(AURenderCallback callback) {
        synchronized (renderCallbacks) {
            for (Iterator<LongMap.Entry<AURenderCallback>> it = renderCallbacks.entries().iterator(); it.hasNext();) {
                LongMap.Entry<AURenderCallback> entry = it.next();
                if (entry.value == callback) {
                    return removeRenderNotify(new FunctionPtr(cbRender), entry.key);
                }
            }
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="NewAUGraph", optional=true)
    private static native OSStatus create(AUGraph.AUGraphPtr outGraph);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphAddNode", optional=true)
    private native OSStatus addNode(AudioComponentDescription inDescription, IntPtr outNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphRemoveNode", optional=true)
    public native OSStatus removeNode(int inNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNodeCount", optional=true)
    private native OSStatus getNodeCount(IntPtr outNumberOfNodes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetIndNode", optional=true)
    private native OSStatus getIndNode(int inIndex, IntPtr outNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphNodeInfo", optional=true)
    private native OSStatus getNodeInfo(int inNode, AudioComponentDescription.AudioComponentDescriptionPtr outDescription, AudioUnit.AudioUnitPtr outAudioUnit);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphConnectNodeInput", optional=true)
    public native OSStatus connectNodeInput(int inSourceNode, int inSourceOutputNumber, int inDestNode, int inDestInputNumber);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphSetNodeInputCallback", optional=true)
    private native OSStatus setNodeInputCallback(int inDestNode, int inDestInputNumber, AURenderCallbackStruct inInputCallback);
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
    private native OSStatus getNumberOfInteractions(IntPtr outNumInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetInteractionInfo", optional=true)
    private native OSStatus getInteractionInfo(int interactionIndex, AUNodeInteraction.AUNodeInteractionPtr outInteraction);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphCountNodeInteractions", optional=true)
    private native OSStatus countNodeInteractions(int inNode, IntPtr outNumInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNodeInteractions", optional=true)
    private native OSStatus getNodeInteractions(int node, IntPtr ioNumInteractions, AUNodeInteraction.AUNodeInteractionPtr outInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphUpdate", optional=true)
    private native OSStatus update(BooleanPtr outIsUpdated);
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
    private native OSStatus isOpen(BooleanPtr outIsOpen);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsInitialized", optional=true)
    private native OSStatus isInitialized(BooleanPtr outIsInitialized);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsRunning", optional=true)
    private native OSStatus isRunning(BooleanPtr outIsRunning);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetCPULoad", optional=true)
    private native OSStatus getCPULoad(FloatPtr outAverageCPULoad);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetMaxCPULoad", optional=true)
    private native OSStatus getMaxCPULoad(FloatPtr outMaxLoad);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphAddRenderNotify", optional=true)
    private native OSStatus addRenderNotify(FunctionPtr callback, @Pointer long refCon);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphRemoveRenderNotify", optional=true)
    private native OSStatus removeRenderNotify(FunctionPtr callback, @Pointer long refCon);
    /*</methods>*/
}
