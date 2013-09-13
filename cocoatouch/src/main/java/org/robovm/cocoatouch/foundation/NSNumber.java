/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.foundation;

/*<imports>*/
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html">NSNumber Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSNumber /*</name>*/ 
    extends /*<extends>*/ NSValue /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSNumber /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSNumber /*</name>*/.class);

    /*<constructors>*/
    protected NSNumber(SkipInit skipInit) { super(skipInit); }
    public NSNumber() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector numberWithBool$ = Selector.register("numberWithBool:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, boolean value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithBool:">+ (NSNumber *)numberWithBool:(BOOL)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(boolean value) {
        return objc_valueOf(objCClass, numberWithBool$, value);
    }
    
    private static final Selector numberWithChar$ = Selector.register("numberWithChar:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, byte value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithChar:">+ (NSNumber *)numberWithChar:(char)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(byte value) {
        return objc_valueOf(objCClass, numberWithChar$, value);
    }
    
    private static final Selector numberWithDouble$ = Selector.register("numberWithDouble:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, double value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithDouble:">+ (NSNumber *)numberWithDouble:(double)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(double value) {
        return objc_valueOf(objCClass, numberWithDouble$, value);
    }
    
    private static final Selector numberWithFloat$ = Selector.register("numberWithFloat:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, float value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithFloat:">+ (NSNumber *)numberWithFloat:(float)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(float value) {
        return objc_valueOf(objCClass, numberWithFloat$, value);
    }
    
    private static final Selector numberWithInt$ = Selector.register("numberWithInt:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, int value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithInt:">+ (NSNumber *)numberWithInt:(int)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(int value) {
        return objc_valueOf(objCClass, numberWithInt$, value);
    }
    
    private static final Selector numberWithLongLong$ = Selector.register("numberWithLongLong:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, long value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithLongLong:">+ (NSNumber *)numberWithLongLong:(long long)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(long value) {
        return objc_valueOf(objCClass, numberWithLongLong$, value);
    }
    
    private static final Selector numberWithShort$ = Selector.register("numberWithShort:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, short value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithShort:">+ (NSNumber *)numberWithShort:(short)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(short value) {
        return objc_valueOf(objCClass, numberWithShort$, value);
    }
    
    private static final Selector numberWithUnsignedShort$ = Selector.register("numberWithUnsignedShort:");
    @Bridge private native static NSNumber objc_valueOf(ObjCClass __self__, Selector __cmd__, char value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/clm/NSNumber/numberWithUnsignedShort:">+ (NSNumber *)numberWithUnsignedShort:(unsigned short)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSNumber valueOf(char value) {
        return objc_valueOf(objCClass, numberWithUnsignedShort$, value);
    }
    
    private static final Selector boolValue = Selector.register("boolValue");
    @Bridge private native static boolean objc_booleanValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static boolean objc_booleanValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/boolValue">- (BOOL)boolValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean booleanValue() {
        if (customClass) { return objc_booleanValueSuper(getSuper(), boolValue); } else { return objc_booleanValue(this, boolValue); }
    }
    
    private static final Selector charValue = Selector.register("charValue");
    @Bridge private native static byte objc_byteValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static byte objc_byteValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/charValue">- (char)charValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public byte byteValue() {
        if (customClass) { return objc_byteValueSuper(getSuper(), charValue); } else { return objc_byteValue(this, charValue); }
    }
    
    private static final Selector unsignedShortValue = Selector.register("unsignedShortValue");
    @Bridge private native static char objc_charValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static char objc_charValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/unsignedShortValue">- (unsigned short)unsignedShortValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public char charValue() {
        if (customClass) { return objc_charValueSuper(getSuper(), unsignedShortValue); } else { return objc_charValue(this, unsignedShortValue); }
    }
    
    private static final Selector doubleValue = Selector.register("doubleValue");
    @Bridge private native static double objc_doubleValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static double objc_doubleValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/doubleValue">- (double)doubleValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public double doubleValue() {
        if (customClass) { return objc_doubleValueSuper(getSuper(), doubleValue); } else { return objc_doubleValue(this, doubleValue); }
    }
    
    private static final Selector floatValue = Selector.register("floatValue");
    @Bridge private native static float objc_floatValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static float objc_floatValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/floatValue">- (float)floatValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public float floatValue() {
        if (customClass) { return objc_floatValueSuper(getSuper(), floatValue); } else { return objc_floatValue(this, floatValue); }
    }
    
    private static final Selector intValue = Selector.register("intValue");
    @Bridge private native static int objc_intValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static int objc_intValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/intValue">- (int)intValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public int intValue() {
        if (customClass) { return objc_intValueSuper(getSuper(), intValue); } else { return objc_intValue(this, intValue); }
    }
    
    private static final Selector longLongValue = Selector.register("longLongValue");
    @Bridge private native static long objc_longValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static long objc_longValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/longLongValue">- (long long)longLongValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public long longValue() {
        if (customClass) { return objc_longValueSuper(getSuper(), longLongValue); } else { return objc_longValue(this, longLongValue); }
    }
    
    private static final Selector shortValue = Selector.register("shortValue");
    @Bridge private native static short objc_shortValue(NSNumber __self__, Selector __cmd__);
    @Bridge private native static short objc_shortValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSNumber_Class/Reference/Reference.html#//apple_ref/occ/instm/NSNumber/shortValue">- (short)shortValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public short shortValue() {
        if (customClass) { return objc_shortValueSuper(getSuper(), shortValue); } else { return objc_shortValue(this, shortValue); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("boolValue") public static boolean booleanValue(NSNumber __self__, Selector __cmd__) { return __self__.booleanValue(); }
        @Callback @BindSelector("charValue") public static byte byteValue(NSNumber __self__, Selector __cmd__) { return __self__.byteValue(); }
        @Callback @BindSelector("unsignedShortValue") public static char charValue(NSNumber __self__, Selector __cmd__) { return __self__.charValue(); }
        @Callback @BindSelector("doubleValue") public static double doubleValue(NSNumber __self__, Selector __cmd__) { return __self__.doubleValue(); }
        @Callback @BindSelector("floatValue") public static float floatValue(NSNumber __self__, Selector __cmd__) { return __self__.floatValue(); }
        @Callback @BindSelector("intValue") public static int intValue(NSNumber __self__, Selector __cmd__) { return __self__.intValue(); }
        @Callback @BindSelector("longLongValue") public static long longValue(NSNumber __self__, Selector __cmd__) { return __self__.longValue(); }
        @Callback @BindSelector("shortValue") public static short shortValue(NSNumber __self__, Selector __cmd__) { return __self__.shortValue(); }
    }
    /*</callbacks>*/

}
