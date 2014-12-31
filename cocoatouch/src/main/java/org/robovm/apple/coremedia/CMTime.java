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
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTime/*</name>*/ 
    extends /*<extends>*/Struct<CMTime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsValuedListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CMTime> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSValue> o = (NSArray<NSValue>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTime> list = new ArrayList<>();
            for (NSValue v : o) {
                list.add(v.timeValue());
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTime> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSValue> array = new NSMutableArray<>();
            for (CMTime i : l) {
                array.add(NSValue.valueOf(i));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/public static class CMTimePtr extends Ptr<CMTime, CMTimePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTime.class); }/*</bind>*/
    /*<constants>*/
    public static final long MaxTimescale = 0x7fffffffL;
    /*</constants>*/
    /*<constructors>*/
    public CMTime() {}
    public CMTime(long value, int timescale, CMTimeFlags flags, long epoch) {
        this.setValue(value);
        this.setTimescale(timescale);
        this.setFlags(flags);
        this.setEpoch(epoch);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native long getValue();
    @StructMember(0) public native CMTime setValue(long value);
    
    @Deprecated
    @StructMember(0) public native long value();
    @Deprecated
    @StructMember(0) public native CMTime value(long value);
    
    @StructMember(1) public native int getTimescale();
    @StructMember(1) public native CMTime setTimescale(int timescale);
    
    @Deprecated
    @StructMember(1) public native int timescale();
    @Deprecated
    @StructMember(1) public native CMTime timescale(int timescale);
    
    @StructMember(2) public native CMTimeFlags getFlags();
    @StructMember(2) public native CMTime setFlags(CMTimeFlags flags);
    
    @Deprecated
    @StructMember(2) public native CMTimeFlags flags();
    @Deprecated
    @StructMember(2) public native CMTime flags(CMTimeFlags flags);
    
    @StructMember(3) public native long getEpoch();
    @StructMember(3) public native CMTime setEpoch(long epoch);
    
    @Deprecated
    @StructMember(3) public native long epoch();
    @Deprecated
    @StructMember(3) public native CMTime epoch(long epoch);
    
    /*</members>*/
    public String asString() {
        return copyDescription(null, this);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeMake", optional=true)
    public static native @ByVal CMTime create(long value, int timescale);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeMakeWithEpoch", optional=true)
    public static native @ByVal CMTime create(long value, int timescale, long epoch);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeMakeWithSeconds", optional=true)
    public static native @ByVal CMTime create(double seconds, int preferredTimeScale);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getSeconds() { return getSeconds(this); }
    @Bridge(symbol="CMTimeGetSeconds", optional=true)
    private static native double getSeconds(@ByVal CMTime time);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime convertScale(int newTimescale, CMTimeRoundingMethod method) { return convertScale(this, newTimescale, method); }
    @Bridge(symbol="CMTimeConvertScale", optional=true)
    private static native @ByVal CMTime convertScale(@ByVal CMTime time, int newTimescale, CMTimeRoundingMethod method);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime add(CMTime addend2) { return add(this, addend2); }
    @Bridge(symbol="CMTimeAdd", optional=true)
    private static native @ByVal CMTime add(@ByVal CMTime addend1, @ByVal CMTime addend2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime subtract(CMTime subtrahend) { return subtract(this, subtrahend); }
    @Bridge(symbol="CMTimeSubtract", optional=true)
    private static native @ByVal CMTime subtract(@ByVal CMTime minuend, @ByVal CMTime subtrahend);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime multiply(int multiplier) { return multiply(this, multiplier); }
    @Bridge(symbol="CMTimeMultiply", optional=true)
    private static native @ByVal CMTime multiply(@ByVal CMTime time, int multiplier);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime multiplyByFloat64(double multiplier) { return multiplyByFloat64(this, multiplier); }
    @Bridge(symbol="CMTimeMultiplyByFloat64", optional=true)
    private static native @ByVal CMTime multiplyByFloat64(@ByVal CMTime time, double multiplier);
    /**
     * @since Available in iOS 7.1 and later.
     */
    public CMTime multiplyByRatio(int multiplier, int divisor) { return multiplyByRatio(this, multiplier, divisor); }
    @Bridge(symbol="CMTimeMultiplyByRatio", optional=true)
    private static native @ByVal CMTime multiplyByRatio(@ByVal CMTime time, int multiplier, int divisor);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int compare(CMTime time2) { return compare(this, time2); }
    @Bridge(symbol="CMTimeCompare", optional=true)
    private static native int compare(@ByVal CMTime time1, @ByVal CMTime time2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime minimum(CMTime time2) { return minimum(this, time2); }
    @Bridge(symbol="CMTimeMinimum", optional=true)
    private static native @ByVal CMTime minimum(@ByVal CMTime time1, @ByVal CMTime time2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime maximum(CMTime time2) { return maximum(this, time2); }
    @Bridge(symbol="CMTimeMaximum", optional=true)
    private static native @ByVal CMTime maximum(@ByVal CMTime time1, @ByVal CMTime time2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime absoluteValue() { return absoluteValue(this); }
    @Bridge(symbol="CMTimeAbsoluteValue", optional=true)
    private static native @ByVal CMTime absoluteValue(@ByVal CMTime time);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDictionary<?, ?> copyAsDictionary(CFAllocator allocator) { return copyAsDictionary(this, allocator); }
    @Bridge(symbol="CMTimeCopyAsDictionary", optional=true)
    private static native NSDictionary<?, ?> copyAsDictionary(@ByVal CMTime time, CFAllocator allocator);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeMakeFromDictionary", optional=true)
    public static native @ByVal CMTime create(NSDictionary<?, ?> dict);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeCopyDescription", optional=true)
    protected static native String copyDescription(CFAllocator allocator, @ByVal CMTime time);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void print() { print(this); }
    @Bridge(symbol="CMTimeShow", optional=true)
    private static native void print(@ByVal CMTime time);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime mapTimeFromRangeToRange(CMTimeRange fromRange, CMTimeRange toRange) { return mapTimeFromRangeToRange(this, fromRange, toRange); }
    @Bridge(symbol="CMTimeMapTimeFromRangeToRange", optional=true)
    private static native @ByVal CMTime mapTimeFromRangeToRange(@ByVal CMTime t, @ByVal CMTimeRange fromRange, @ByVal CMTimeRange toRange);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime clampToRange(CMTimeRange range) { return clampToRange(this, range); }
    @Bridge(symbol="CMTimeClampToRange", optional=true)
    private static native @ByVal CMTime clampToRange(@ByVal CMTime time, @ByVal CMTimeRange range);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime mapDurationFromRangeToRange(CMTimeRange fromRange, CMTimeRange toRange) { return mapDurationFromRangeToRange(this, fromRange, toRange); }
    @Bridge(symbol="CMTimeMapDurationFromRangeToRange", optional=true)
    private static native @ByVal CMTime mapDurationFromRangeToRange(@ByVal CMTime dur, @ByVal CMTimeRange fromRange, @ByVal CMTimeRange toRange);
    /*</methods>*/
}
