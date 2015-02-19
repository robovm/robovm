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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*//*</visibility>*/ class /*<name>*/CoreGraphics/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreGraphics.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    private static float[] doublesToFloats(double[] doubles) {
        float[] floats = new float[doubles.length];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = (float) doubles[i];
        }
        return floats;
    }

    private static double[] floatsToDoubles(float[] floats) {
        double[] doubles = new double[floats.length];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = floats[i];
        }
        return doubles;
    }

    static Object toMachineSizedFloatArray(Object array) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (VoidPtr.sizeOf() == 4 && array instanceof double[]) {
            return CoreGraphics.doublesToFloats((double[]) array);
        } else if (VoidPtr.sizeOf() == 8 && array instanceof float[]) {
                return CoreGraphics.floatsToDoubles((float[]) array);
        } else {
            return array;
        }
    }
    
    /*<methods>*/
    /*</methods>*/
}
