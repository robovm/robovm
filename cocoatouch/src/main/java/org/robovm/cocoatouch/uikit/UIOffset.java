/*
 * Copyright (C) 2012 Trillian Mobile AB
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
@Deprecated public final class UIOffset extends Struct<UIOffset> {
    public static final UIOffset Zero = new UIOffset();

    public UIOffset() {
    }
    
    public UIOffset(float horizontal, float vertical) {
        horizontal(horizontal);
        vertical(vertical);
    }
    
    @StructMember(0)
    public native float horizontal();
    @StructMember(0)
    public native UIOffset horizontal(float f);
    @StructMember(1)
    public native float vertical();
    @StructMember(1)
    public native UIOffset vertical(float f);
}
