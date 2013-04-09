/*
 * Copyright (C) 2008 The Android Open Source Project
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

package libcore.java.util;

import junit.framework.TestCase;

import java.util.EventListener;
import java.util.EventListenerProxy;

public class OldEventListenerProxyTest extends TestCase {

    class Mock_EventListener implements EventListener {
    }

    class Mock_EventListenerProxy extends EventListenerProxy {
        public Mock_EventListenerProxy(EventListener listener) {
            super(listener);
        }
    }

    public void testEventListenerProxy() {
        assertNotNull(new Mock_EventListenerProxy(null));
        assertNotNull(new Mock_EventListenerProxy(new Mock_EventListener()));
    }

    public void testGetListener() {
        EventListener el = new Mock_EventListener();
        EventListenerProxy elp = new Mock_EventListenerProxy(el);

        assertSame(el, elp.getListener());
    }
}
