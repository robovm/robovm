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

import org.robovm.rt.bro.ValuedEnum;

/**
 *
 */
public enum CGBlendMode implements ValuedEnum {

    Normal(0),
    Multiply(1),
    Screen(2),
    Overlay(3),
    Darken(4),
    Lighten(5),
    ColorDodge(6),
    ColorBurn(7),
    SoftLight(8),
    HardLight(9),
    Difference(10),
    Exclusion(11),
    Hue(12),
    Saturation(13),
    Color(14),
    Luminosity(15),
    Clear(16),
    Copy(17),
    SourceIn(18),
    SourceOut(19),
    SourceAtop(20),
    DestinationOver(21),
    DestinationIn(22),
    DestinationOut(23),
    DestinationAtop(24),
    XOR(25),
    PlusDarker(26),
    PlusLighter(27);
    
    private final int n;

    private CGBlendMode(int n) { this.n = n; }
    public int value() { return n; }
}
