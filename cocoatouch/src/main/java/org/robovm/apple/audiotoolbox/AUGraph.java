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
import org.robovm.apple.coremidi.*;
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
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AUGraph create() throws OSStatusException {
        AUGraph.AUGraphPtr ptr = new AUGraph.AUGraphPtr();
        OSStatus status = create0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int addNode(AudioComponentDescription description) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = addNode0(description, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void removeNode(int node) throws OSStatusException {
        OSStatus status = removeNode0(node);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getNodeCount() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getNodeCount0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getIndNode(int index) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getIndNode0(index, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public AudioComponentDescription getNodeDescription(int node) throws OSStatusException {
        AudioComponentDescription.AudioComponentDescriptionPtr ptr = new AudioComponentDescription.AudioComponentDescriptionPtr();
        OSStatus status = getNodeInfo0(node, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public AudioUnit getNodeAudioUnit(int node) throws OSStatusException {
        AudioUnit.AudioUnitPtr ptr = new AudioUnit.AudioUnitPtr();
        OSStatus status = getNodeInfo0(node, null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void connectNodeInput(int sourceNode, int sourceOutputNumber, int destNode, int destInputNumber) throws OSStatusException {
        OSStatus status = connectNodeInput0(sourceNode, sourceOutputNumber, destNode, destInputNumber);
        OSStatusException.throwIfNecessary(status);
    }

    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void setNodeInputCallback(int destNode, int destInputNumber, AURenderCallback inputCallback) throws OSStatusException {
        long cid = callbackId.getAndIncrement();
        
        AURenderCallbackStruct struct = new AURenderCallbackStruct(new FunctionPtr(cbRender), cid);
        OSStatus status = setNodeInputCallback0(destNode, destInputNumber, struct);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (renderCallbacks) {
                renderCallbacks.put(cid, inputCallback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void disconnectNodeInput(int destNode, int destInputNumber) throws OSStatusException {
        OSStatus status = disconnectNodeInput0(destNode, destInputNumber);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void clearConnections() throws OSStatusException {
        OSStatus status = clearConnections0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfInteractions() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getNumberOfInteractions0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public AUNodeInteraction getInteractionInfo(int interactionIndex) throws OSStatusException {
        AUNodeInteraction.AUNodeInteractionPtr ptr = new AUNodeInteraction.AUNodeInteractionPtr();
        OSStatus status = getInteractionInfo0(interactionIndex, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int countNodeInteractions(int node) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = countNodeInteractions0(node, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public AUNodeInteraction[] getNodeInteractions(int node) throws OSStatusException {
        return getNodeInteractions(node, countNodeInteractions(node));
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public AUNodeInteraction[] getNodeInteractions(int node, int maxInteractions) throws OSStatusException {
        IntPtr count = new IntPtr(maxInteractions);
        AUNodeInteraction.AUNodeInteractionPtr ptr = new AUNodeInteraction.AUNodeInteractionPtr();
        
        OSStatus status = getNodeInteractions0(node, count, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            AUNodeInteraction[] result = new AUNodeInteraction[count.get()];
            for (int i = 0; i < result.length; i++) {
                result[i] = ptr.next(i).get();
            }
            return result;
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean update() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = update0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void open() throws OSStatusException {
        OSStatus status = open0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void initialize() throws OSStatusException {
        OSStatus status = initialize0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void uninitialize() throws OSStatusException {
        OSStatus status = uninitialize0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void start() throws OSStatusException {
        OSStatus status = start0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void stop() throws OSStatusException {
        OSStatus status = stop0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isOpen() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = isOpen0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isInitialized() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = isInitialized0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isRunning() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = isRunning0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public float getCPULoad() throws OSStatusException {
        FloatPtr ptr = new FloatPtr();
        OSStatus status = getCPULoad0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public float getMaxCPULoad() throws OSStatusException {
        FloatPtr ptr = new FloatPtr();
        OSStatus status = getMaxCPULoad0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void addRenderNotify(AURenderCallback callback) throws OSStatusException {
        long cid = callbackId.getAndIncrement();
        
        OSStatus status = addRenderNotify0(new FunctionPtr(cbRender), cid);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (renderCallbacks) {
                renderCallbacks.put(cid, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void removeRenderNotify(AURenderCallback callback) throws OSStatusException {
        synchronized (renderCallbacks) {
            for (Iterator<LongMap.Entry<AURenderCallback>> it = renderCallbacks.entries().iterator(); it.hasNext();) {
                LongMap.Entry<AURenderCallback> entry = it.next();
                if (entry.value == callback) {
                    OSStatus status = removeRenderNotify0(new FunctionPtr(cbRender), entry.key);
                    OSStatusException.throwIfNecessary(status);
                }
            }
        }
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="NewAUGraph", optional=true)
    private static native OSStatus create0(AUGraph.AUGraphPtr outGraph);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphAddNode", optional=true)
    protected native OSStatus addNode0(AudioComponentDescription inDescription, IntPtr outNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphRemoveNode", optional=true)
    protected native OSStatus removeNode0(int inNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNodeCount", optional=true)
    protected native OSStatus getNodeCount0(IntPtr outNumberOfNodes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetIndNode", optional=true)
    protected native OSStatus getIndNode0(int inIndex, IntPtr outNode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphNodeInfo", optional=true)
    protected native OSStatus getNodeInfo0(int inNode, AudioComponentDescription.AudioComponentDescriptionPtr outDescription, AudioUnit.AudioUnitPtr outAudioUnit);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphConnectNodeInput", optional=true)
    protected native OSStatus connectNodeInput0(int inSourceNode, int inSourceOutputNumber, int inDestNode, int inDestInputNumber);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphSetNodeInputCallback", optional=true)
    protected native OSStatus setNodeInputCallback0(int inDestNode, int inDestInputNumber, AURenderCallbackStruct inInputCallback);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphDisconnectNodeInput", optional=true)
    protected native OSStatus disconnectNodeInput0(int inDestNode, int inDestInputNumber);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphClearConnections", optional=true)
    protected native OSStatus clearConnections0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNumberOfInteractions", optional=true)
    protected native OSStatus getNumberOfInteractions0(IntPtr outNumInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetInteractionInfo", optional=true)
    protected native OSStatus getInteractionInfo0(int interactionIndex, AUNodeInteraction.AUNodeInteractionPtr outInteraction);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphCountNodeInteractions", optional=true)
    protected native OSStatus countNodeInteractions0(int inNode, IntPtr outNumInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetNodeInteractions", optional=true)
    protected native OSStatus getNodeInteractions0(int node, IntPtr ioNumInteractions, AUNodeInteraction.AUNodeInteractionPtr outInteractions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphUpdate", optional=true)
    protected native OSStatus update0(BooleanPtr outIsUpdated);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphOpen", optional=true)
    protected native OSStatus open0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphInitialize", optional=true)
    protected native OSStatus initialize0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphUninitialize", optional=true)
    protected native OSStatus uninitialize0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphStart", optional=true)
    protected native OSStatus start0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphStop", optional=true)
    protected native OSStatus stop0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsOpen", optional=true)
    protected native OSStatus isOpen0(BooleanPtr outIsOpen);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsInitialized", optional=true)
    protected native OSStatus isInitialized0(BooleanPtr outIsInitialized);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphIsRunning", optional=true)
    protected native OSStatus isRunning0(BooleanPtr outIsRunning);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetCPULoad", optional=true)
    protected native OSStatus getCPULoad0(FloatPtr outAverageCPULoad);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphGetMaxCPULoad", optional=true)
    protected native OSStatus getMaxCPULoad0(FloatPtr outMaxLoad);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphAddRenderNotify", optional=true)
    protected native OSStatus addRenderNotify0(FunctionPtr callback, @Pointer long refCon);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AUGraphRemoveRenderNotify", optional=true)
    protected native OSStatus removeRenderNotify0(FunctionPtr callback, @Pointer long refCon);
    /*</methods>*/
}
