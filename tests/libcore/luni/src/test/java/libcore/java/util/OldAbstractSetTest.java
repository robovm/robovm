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

import java.util.AbstractSequentialList;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class OldAbstractSetTest extends TestCase {

    class Mock_AbstractSet extends AbstractSet{

        @Override
        public Iterator iterator() {
            return new Iterator() {
                public boolean hasNext() {
                    return false;
                }

                public Object next() {
                    return null;
                }

                public void remove() {
                }
            };
        }

        @Override
        public int size() {
            return 0;
        }
    }

    public void testHashCode() {
        AbstractSet as = new Mock_AbstractSet();
        assertNotNull(as.hashCode());
    }

    public void testEquals() {
        AbstractSet as1 = new Mock_AbstractSet();
        AbstractSet as2 = new Mock_AbstractSet();

        assertTrue(as1.equals(as2));
    }

    public void testRemoveAll() {
        AbstractSet as = new AbstractSet(){
            @Override
            public Iterator iterator() {
                return new Iterator() {
                    public boolean hasNext() {
                        return true;
                    }

                    public Object next() {
                        return null;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                };
            }

            @Override
            public int size() {
                return 10;
            }
        };

        try {
            as.removeAll(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
        Collection c = new Vector();
        c.add(null);
        try {
            as.removeAll(c);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException e) {
            //expected
        }

        as = new Mock_AbstractSet();

        as.removeAll(c);
    }
}
