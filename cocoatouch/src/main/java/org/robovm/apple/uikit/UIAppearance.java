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
package org.robovm.apple.uikit;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.ObjCObject;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

/*<javadoc>*/
/*</javadoc>*/
@Library("UIKit")
public class UIAppearance {

    static {
        ObjCRuntime.bind(UIAppearance.class);
    }
    
    private static final Selector appearance = Selector.register("appearance");
    @Bridge
    private static native @Pointer long objc_appearance(ObjCClass cls, Selector sel);
    
    private static final Selector appearanceWhenContainedIn = Selector.register("appearanceWhenContainedIn:");
    @Bridge
    private static native @Pointer long objc_appearanceWhenContainedIn(ObjCClass cls, Selector sel, 
            ObjCClass c01, ObjCClass c02, ObjCClass c03, ObjCClass c04, ObjCClass c05, ObjCClass c06,
            ObjCClass c07, ObjCClass c08, ObjCClass c09, ObjCClass c10, @Pointer long nil);
    
    @SuppressWarnings("unchecked")
    private static ObjCClass toObjCClass(Class<?>[] array, int index) {
        return array.length > index ? ObjCClass.getByType((Class<? extends ObjCObject>) array[index]) : null;
    }
    
    public static <T extends NSObject & UIAppearanceContainer> T getAppearance(Class<T> type, 
            Class<?> ... containedIn) {
        
        ObjCClass objCClass = ObjCClass.getByType(type);
        long proxyHandle = 0;
        if (containedIn == null || containedIn.length == 0) {
            proxyHandle = objc_appearance(objCClass, appearance);
        } else {
            if (containedIn.length > 10) {
                throw new IllegalArgumentException("A maximum of 10 container classes is supported");
            }
            ObjCClass c01 = toObjCClass(containedIn, 0);
            ObjCClass c02 = toObjCClass(containedIn, 1);
            ObjCClass c03 = toObjCClass(containedIn, 2);
            ObjCClass c04 = toObjCClass(containedIn, 3);
            ObjCClass c05 = toObjCClass(containedIn, 4);
            ObjCClass c06 = toObjCClass(containedIn, 5);
            ObjCClass c07 = toObjCClass(containedIn, 6);
            ObjCClass c08 = toObjCClass(containedIn, 7);
            ObjCClass c09 = toObjCClass(containedIn, 8);
            ObjCClass c10 = toObjCClass(containedIn, 9);
            proxyHandle = objc_appearanceWhenContainedIn(objCClass, appearanceWhenContainedIn, 
                    c01, c02, c03, c04, c05, c06, c07, c08, c09, c10, 0);
        }
        return ObjCObject.toObjCObject(type, proxyHandle, true);
    }
}
