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
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class) @Library("AudioToolbox")/*</annotations>*/
public enum /*<name>*/AudioFormatProperty/*</name>*/ implements ValuedEnum {
    /*<values>*/
    FormatInfo(1718449257L),
    FormatName(1718509933L),
    EncodeFormatIDs(1633906534L),
    DecodeFormatIDs(1633904998L),
    FormatList(1718383476L),
    ASBDFromESDS(1702064996L),
    ChannelLayoutFromESDS(1702060908L),
    OutputFormatList(1868983411L),
    FirstPlayableFormatFromList(1718642284L),
    FormatIsVBR(1719034482L),
    FormatIsExternallyFramed(1717925990L),
    FormatIsEncrypted(1668446576L),
    Encoders(1635149166L),
    Decoders(1635148901L),
    AvailableEncodeBitRates(1634034290L),
    AvailableEncodeSampleRates(1634038642L),
    AvailableEncodeChannelLayoutTags(1634034540L),
    AvailableEncodeNumberChannels(1635151459L),
    ASBDFromMPEGPacket(1633971568L),
    BitmapForLayoutTag(1651340391L),
    MatrixMixMap(1835884912L),
    ChannelMap(1667788144L),
    NumberOfChannelsForLayout(1852008557L),
    AreChannelLayoutsEquivalent(1667786097L),
    ChannelLayoutHash(1667786849L),
    ValidateChannelLayout(1986093932L),
    ChannelLayoutForTag(1668116588L),
    TagForChannelLayout(1668116596L),
    ChannelLayoutName(1819242093L),
    ChannelLayoutSimpleName(1819504237L),
    ChannelLayoutForBitmap(1668116578L),
    ChannelName(1668178285L),
    ChannelShortName(1668509293L),
    TagsForNumberOfChannels(1952540515L),
    PanningMatrix(1885433453L),
    BalanceFade(1650551910L),
    ID3TagSize(1768174451L),
    ID3TagToDictionary(1768174436L);
    /*</values>*/

    /*<bind>*/static { Bro.bind(AudioFormatProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getPropertySize(Struct<?> specifier) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(this, specifier == null ? 0 : Struct.sizeOf(specifier), specifier == null ? null : specifier.as(VoidPtr.class), ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getProperty(Struct<?> specifier, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getProperty0(this, specifier == null ? 0 : Struct.sizeOf(specifier), specifier == null ? null : specifier.as(VoidPtr.class), dataSize, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFormatGetPropertyInfo", optional=true)
    protected static native OSStatus getPropertyInfo0(AudioFormatProperty inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr outPropertyDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFormatGetProperty", optional=true)
    protected static native OSStatus getProperty0(AudioFormatProperty inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /*</methods>*/

    private final long n;

    private /*<name>*/AudioFormatProperty/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioFormatProperty/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioFormatProperty/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioFormatProperty/*</name>*/.class.getName());
    }
}
