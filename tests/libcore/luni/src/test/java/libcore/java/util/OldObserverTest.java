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

import java.util.Observable;
import java.util.Observer;
import junit.framework.TestCase;

public class OldObserverTest extends TestCase {

    class Mock_Observer implements Observer {
        int updateCount = 0;

        public void update(Observable observed, Object arg) {
            ++updateCount;
        }

        public int getUpdateCount() {
            return updateCount;
        }
    }

    class TestObservable extends Observable {
        public void doChange() {
            setChanged();
        }
    }

    public void testUpdate() {
        TestObservable observable = new TestObservable();
        Mock_Observer observer = null;
        observable.addObserver(observer = new Mock_Observer());
        observable.notifyObservers();
        assertEquals(0, observer.getUpdateCount());
        observable.doChange();
        observable.notifyObservers();
        assertEquals(1, observer.getUpdateCount());
    }
}
