/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.prefs;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EventObject;

/**
 * This is the event class to indicate that one child of the preference node has
 * been added or deleted.
 * <p>
 * Please note that although the class is marked as {@code Serializable} by
 * inheritance from {@code EventObject}, this type is not intended to be serialized
 * so the serialization methods do nothing but throw a {@code NotSerializableException}.
 *
 * @see java.util.prefs.Preferences
 * @see java.util.prefs.NodeChangeListener
 *
 * @since 1.4
 */
public class NodeChangeEvent extends EventObject implements Serializable {

    private static final long serialVersionUID = 8068949086596572957L;

    private final Preferences parent;
    private final Preferences child;

    /**
     * Constructs a new {@code NodeChangeEvent} instance.
     *
     * @param p
     *            the {@code Preferences} instance that fired this event; this object is
     *            considered as the event source.
     * @param c
     *            the child {@code Preferences} instance that was added or deleted.
     */
    public NodeChangeEvent (Preferences p, Preferences c) {
        super(p);
        parent = p;
        child = c;
    }

    /**
     * Gets the {@code Preferences} instance that fired this event.
     *
     * @return the {@code Preferences} instance that fired this event.
     */
    public Preferences getParent() {
        return parent;
    }

    /**
     * Gets the child {@code Preferences} node that was added or removed.
     *
     * @return the added or removed child {@code Preferences} node.
     */
    public Preferences getChild() {
        return child;
    }

    /**
     * This method always throws a <code>NotSerializableException</code>,
     * because this object cannot be serialized,
     */
    private void writeObject (ObjectOutputStream out) throws IOException {
        throw new NotSerializableException();
    }

    /**
     * This method always throws a <code>NotSerializableException</code>,
     * because this object cannot be serialized,
     */
    private void readObject (ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new NotSerializableException();
    }
}
