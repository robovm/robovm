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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTimeRange/*</name>*/ 
    extends /*<extends>*/Struct<CMTimeRange>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsValuedListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CMTimeRange> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSValue> o = (NSArray<NSValue>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTimeRange> list = new ArrayList<>();
            for (NSValue v : o) {
                list.add(v.timeRangeValue());
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTimeRange> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSValue> array = new NSMutableArray<>();
            for (CMTimeRange i : l) {
                array.add(NSValue.valueOf(i));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/public static class CMTimeRangePtr extends Ptr<CMTimeRange, CMTimeRangePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTimeRange.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMTimeRange() {}
    public CMTimeRange(CMTime start, CMTime duration) {
        this.start(start);
        this.duration(duration);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CMTime start();
    @StructMember(0) public native CMTimeRange start(@ByVal CMTime start);
    @StructMember(1) public native @ByVal CMTime duration();
    @StructMember(1) public native CMTimeRange duration(@ByVal CMTime duration);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeRangeMake", optional=true)
    public static native @ByVal CMTimeRange create(@ByVal CMTime start, @ByVal CMTime duration);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeRange union(CMTimeRange range2) { return union(this, range2); }
    @Bridge(symbol="CMTimeRangeGetUnion", optional=true)
    private static native @ByVal CMTimeRange union(@ByVal CMTimeRange range1, @ByVal CMTimeRange range2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeRange intersection(CMTimeRange range2) { return intersection(this, range2); }
    @Bridge(symbol="CMTimeRangeGetIntersection", optional=true)
    private static native @ByVal CMTimeRange intersection(@ByVal CMTimeRange range1, @ByVal CMTimeRange range2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean equals(CMTimeRange range2) { return equals(this, range2); }
    @Bridge(symbol="CMTimeRangeEqual", optional=true)
    private static native boolean equals(@ByVal CMTimeRange range1, @ByVal CMTimeRange range2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean containsTime(CMTime time) { return containsTime(this, time); }
    @Bridge(symbol="CMTimeRangeContainsTime", optional=true)
    private static native boolean containsTime(@ByVal CMTimeRange range, @ByVal CMTime time);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean containsTimeRange(CMTimeRange range2) { return containsTimeRange(this, range2); }
    @Bridge(symbol="CMTimeRangeContainsTimeRange", optional=true)
    private static native boolean containsTimeRange(@ByVal CMTimeRange range1, @ByVal CMTimeRange range2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime getEnd() { return getEnd(this); }
    @Bridge(symbol="CMTimeRangeGetEnd", optional=true)
    private static native @ByVal CMTime getEnd(@ByVal CMTimeRange range);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeRangeFromTimeToTime", optional=true)
    public static native @ByVal CMTimeRange fromTimeToTime(@ByVal CMTime start, @ByVal CMTime end);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDictionary<?, ?> copyAsDictionary(CFAllocator allocator) { return copyAsDictionary(this, allocator); }
    @Bridge(symbol="CMTimeRangeCopyAsDictionary", optional=true)
    private static native NSDictionary<?, ?> copyAsDictionary(@ByVal CMTimeRange range, CFAllocator allocator);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeRangeMakeFromDictionary", optional=true)
    public static native @ByVal CMTimeRange create(NSDictionary<?, ?> dict);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeRangeCopyDescription", optional=true)
    public static native String copyDescription(CFAllocator allocator, @ByVal CMTimeRange range);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void show() { show(this); }
    @Bridge(symbol="CMTimeRangeShow", optional=true)
    private static native void show(@ByVal CMTimeRange range);
    /*</methods>*/
}
