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

import org.robovm.rt.bro.Bits;

public final class UIRemoteNotificationType extends Bits<UIRemoteNotificationType> {
    public static final UIRemoteNotificationType None = new UIRemoteNotificationType(0);
    public static final UIRemoteNotificationType Badge = new UIRemoteNotificationType(1 << 0);
    public static final UIRemoteNotificationType Sound = new UIRemoteNotificationType(1 << 1);
    public static final UIRemoteNotificationType Alert = new UIRemoteNotificationType(1 << 2);
    public static final UIRemoteNotificationType NewsstandContentAvailability = new UIRemoteNotificationType(1 << 3);

    private static final UIRemoteNotificationType[] values = _values(UIRemoteNotificationType.class);
    
    private UIRemoteNotificationType(long value) { super(value); }
    private UIRemoteNotificationType(long value, long mask) { super(value, mask); }
    protected UIRemoteNotificationType wrap(long value, long mask) {
        return new UIRemoteNotificationType(value, mask);
    }
    protected UIRemoteNotificationType[] _values() {
        return values;
    }
}
