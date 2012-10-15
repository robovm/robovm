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

import org.robovm.rt.bro.ValuedEnum;

public enum UIControlState implements ValuedEnum {
    Normal(0),
    Highlighted(1 << 0),
    Disabled(1 << 1),
    Selected(1 << 2),
    Application(0x00FF0000),
    Reserved(0xFF000000),
    ApplicationState1(1 << 16),
    ApplicationState2(1 << 17),
    ApplicationState3(1 << 18),
    ApplicationState4(1 << 19),
    ApplicationState5(1 << 20),
    ApplicationState6(1 << 21),
    ApplicationState7(1 << 22),
    ApplicationState8(1 << 23);

    private final int n;

    private UIControlState(int n) { this.n = n; }
    public int value() { return n; }
}
