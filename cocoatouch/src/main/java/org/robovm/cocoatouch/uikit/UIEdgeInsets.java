/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * 
 */
public class UIEdgeInsets extends Struct<UIEdgeInsets> {
    public static final UIEdgeInsets Zero = new UIEdgeInsets();

    public UIEdgeInsets() {
    }
    
    public UIEdgeInsets(float top, float left) {
        top(top);
        left(left);
    }
    
    @StructMember(0)
    public native float top();
    @StructMember(0)
    public native UIEdgeInsets top(float f);
    @StructMember(1)
    public native float left();
    @StructMember(1)
    public native UIEdgeInsets left(float f);
    @StructMember(2)
    public native float bottom();
    @StructMember(2)
    public native UIEdgeInsets bottom(float f);
    @StructMember(3)
    public native float right();
    @StructMember(3)
    public native UIEdgeInsets right(float f);
}
