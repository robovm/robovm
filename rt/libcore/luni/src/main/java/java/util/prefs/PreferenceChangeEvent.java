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
 * This is the event class to indicate that a preference has been added, deleted
 * or updated.
 * <p>
 * Please note that although the class is marked as {@code Serializable} by
 * inheritance from {@code EventObject}, this type is not intended to be serialized
 * so the serialization methods do nothing but throw a {@code NotSerializableException}.
 *
 * @see java.util.prefs.Preferences
 * @see java.util.prefs.PreferenceChangeListener
 *
 * @since 1.4
 */
public class PreferenceChangeEvent extends EventObject implements Serializable {

    private static final long serialVersionUID = 793724513368024975L;

    private final Preferences node;

    private final String key;

    private final String value;

    /**
     * Construct a new {@code PreferenceChangeEvent} instance.
     *
     * @param p
     *            the {@code Preferences} instance that fired this event; this object is
     *            considered as the event's source.
     * @param k
     *            the changed preference key.
     * @param v
     *            the new value of the changed preference, this value can be
     *            {@code null}, which means the preference has been removed.
     */
    public PreferenceChangeEvent(Preferences p, String k, String v) {
        super(p);
        node = p;
        key = k;
        value = v;
    }

    /**
     * Gets the key of the changed preference.
     *
     * @return the changed preference's key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the new value of the changed preference or {@code null} if the
     * preference has been removed.
     *
     * @return the new value of the changed preference or {@code null} if the
     *         preference has been removed.
     */
    public String getNewValue() {
        return value;
    }

    /**
     * Gets the {@code Preferences} instance that fired this event.
     *
     * @return the {@code Preferences} instance that fired this event.
     */
    public Preferences getNode() {
        return node;
    }

    /**
     * This method always throws a <code>NotSerializableException</code>,
     * because this object cannot be serialized,
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException();
    }

    /**
     * This method always throws a <code>NotSerializableException</code>,
     * because this object cannot be serialized,
     */
    private void readObject(ObjectInputStream in) throws IOException{
        throw new NotSerializableException();
    }
}
