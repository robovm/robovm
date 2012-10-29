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
package org.robovm.cocoatouch.coregraphics;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.StructMember;

public final class CGRect extends Struct<CGRect> {

    public CGRect() {}
    public CGRect(float x, float y, float width, float height) {
        origin().x(x).y(y);
        size().width(width).height(height);
    }
    public CGRect(CGPoint origin, CGSize size) {
        origin(origin);
        size(size);
    }
    
    @StructMember(0)
    public native @ByVal CGPoint origin();
    @StructMember(0)
    public native CGRect origin(@ByVal CGPoint origin);
    @StructMember(1)
    public native @ByVal CGSize size();
    @StructMember(1)
    public native CGRect size(@ByVal CGSize size);
    
}
