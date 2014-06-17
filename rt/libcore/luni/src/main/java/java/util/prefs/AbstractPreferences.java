/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.util.prefs;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import libcore.io.Base64;
import libcore.util.EmptyArray;

/**
 * This abstract class is a partial implementation of the abstract class
 * Preferences, which can be used to simplify {@code Preferences} provider's
 * implementation. This class defines nine abstract SPI methods, which must be
 * implemented by a preference provider.
 *
 * @since 1.4
 * @see Preferences
 */
public abstract class AbstractPreferences extends Preferences {
    /*
     * -----------------------------------------------------------
     * Class fields
     * -----------------------------------------------------------
     */
    /** the unhandled events collection */
    private static final List<EventObject> events = new LinkedList<EventObject>();
    /** the event dispatcher thread */
    private static final EventDispatcher dispatcher = new EventDispatcher("Preference Event Dispatcher");

    /*
     * -----------------------------------------------------------
     * Class initializer
     * -----------------------------------------------------------
     */
    static {
        dispatcher.setDaemon(true);
        dispatcher.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Preferences uroot = Preferences.userRoot();
                Preferences sroot = Preferences.systemRoot();
                try {
                    uroot.flush();
                } catch (BackingStoreException e) {
                    // ignore
                }
                try {
                    sroot.flush();
                } catch (BackingStoreException e) {
                    // ignore
                }
            }
        });
    }

    /*
     * -----------------------------------------------------------
     * Instance fields (package-private)
     * -----------------------------------------------------------
     */
    /** true if this node is in user preference hierarchy */
    boolean userNode;

    /*
     * -----------------------------------------------------------
     * Instance fields (private)
     * -----------------------------------------------------------
     */
    /**
     * The object used to lock this node.
     */
    protected final Object lock;

    /**
     * This field is true if this node is created while it doesn't exist in the
     * backing store. This field's default value is false, and it is checked
     * when the node creation is completed, and if it is true, the node change
     * event will be fired for this node's parent.
     */
    protected boolean newNode;

    /** cached child nodes */
    private Map<String, AbstractPreferences> cachedNode;

    //the collections of listeners
    private List<EventListener> nodeChangeListeners;
    private List<EventListener> preferenceChangeListeners;

    //this node's name
    private String nodeName;

    //handler to this node's parent
    private AbstractPreferences parentPref;

    //true if this node has been removed
    private boolean isRemoved;

    //handler to this node's root node
    private AbstractPreferences root;

    /*
     * -----------------------------------------------------------
     * Constructors
     * -----------------------------------------------------------
     */
    /**
     * Constructs a new {@code AbstractPreferences} instance using the given
     * parent node and node name.
     *
     * @param parent
     *            the parent node of the new node or {@code null} to indicate
     *            that the new node is a root node.
     * @param name
     *            the name of the new node or an empty string to indicate that
     *            this node is called "root".
     * @throws IllegalArgumentException
     *             if the name contains a slash character or is empty if {@code
     *             parent} is not {@code null}.
     */
    protected AbstractPreferences(AbstractPreferences parent, String name) {
        if ((parent == null ^ name.length() == 0) || name.indexOf("/") >= 0) {
            throw new IllegalArgumentException();
        }
        root = (parent == null) ? this : parent.root;
        nodeChangeListeners = new LinkedList<EventListener>();
        preferenceChangeListeners = new LinkedList<EventListener>();
        isRemoved = false;
        cachedNode = new HashMap<String, AbstractPreferences>();
        nodeName = name;
        parentPref = parent;
        lock = new Object();
        userNode = root.userNode;
    }

    /*
     * -----------------------------------------------------------
     * Methods
     * -----------------------------------------------------------
     */
    /**
     * Returns an array of all cached child nodes.
     *
     * @return the array of cached child nodes.
     */
    protected final AbstractPreferences[] cachedChildren() {
        return cachedNode.values().toArray(new AbstractPreferences[cachedNode.size()]);
    }

    /**
     * Returns the child node with the specified name or {@code null} if it
     * doesn't exist. Implementers can assume that the name supplied to this
     * method will be a valid node name string (conforming to the node naming
     * format) and will not correspond to a node that has been cached or
     * removed.
     *
     * @param name
     *            the name of the desired child node.
     * @return the child node with the given name or {@code null} if it doesn't
     *         exist.
     * @throws BackingStoreException
     *             if the backing store is unavailable or causes an operation
     *             failure.
     */
    protected AbstractPreferences getChild(String name)
            throws BackingStoreException {
        synchronized (lock) {
            checkState();
            AbstractPreferences result = null;
            String[] childrenNames = childrenNames();
            for (String childrenName : childrenNames) {
                if (childrenName.equals(name)) {
                    result = childSpi(name);
                    break;
                }
            }
            return result;
        }

    }

    /**
     * Returns whether this node has been removed by invoking the method {@code
     * removeNode()}.
     *
     * @return {@code true}, if this node has been removed, {@code false}
     *         otherwise.
     */
    protected boolean isRemoved() {
        synchronized (lock) {
            return isRemoved;
        }
    }

    /**
     * Flushes changes of this node to the backing store. This method should
     * only flush this node and should not include the descendant nodes. Any
     * implementation that wants to provide functionality to flush all nodes
     * at once should override the method {@link #flush() flush()}.
     *
     * @throws BackingStoreException
     *             if the backing store is unavailable or causes an operation
     *             failure.
     */
    protected abstract void flushSpi() throws BackingStoreException;

    /**
     * Returns the names of all of the child nodes of this node or an empty
     * array if this node has no children. The names of cached children are not
     * required to be returned.
     *
     * @return the names of this node's children.
     * @throws BackingStoreException
     *             if the backing store is unavailable or causes an operation
     *             failure.
     */
    protected abstract String[] childrenNamesSpi() throws BackingStoreException;

    /**
     * Returns the child preference node with the given name, creating it
     * if it does not exist. The caller of this method should ensure that the
     * given name is valid and that this node has not been removed or cached.
     * If the named node has just been removed, the implementation
     * of this method must create a new one instead of reactivating the removed
     * one.
     * <p>
     * The new creation is not required to be persisted immediately until the
     * flush method will be invoked.
     * </p>
     *
     * @param name
     *            the name of the child preference to be returned.
     * @return the child preference node.
     */
    protected abstract AbstractPreferences childSpi(String name);


    /**
     * Puts the given key-value pair into this node. Caller of this method
     * should ensure that both of the given values are valid and that this
     * node has not been removed.
     *
     * @param name
     *            the given preference key.
     * @param value
     *            the given preference value.
     */
    protected abstract void putSpi(String name, String value);

    /**
     * Gets the preference value mapped to the given key. The caller of this
     * method should ensure that the given key is valid and that this node has
     * not been removed. This method should not throw any exceptions but if it
     * does, the caller will ignore the exception, regarding it as a {@code
     * null} return value.
     *
     * @param key
     *            the given key to be searched for.
     * @return the preference value mapped to the given key.
     */
    protected abstract String getSpi(String key);


    /**
     * Returns an array of all preference keys of this node or an empty array if
     * no preferences have been found. The caller of this method should ensure
     * that this node has not been removed.
     *
     * @return the array of all preference keys.
     * @throws BackingStoreException
     *             if the backing store is unavailable or causes an operation
     *             failure.
     */
    protected abstract String[] keysSpi() throws BackingStoreException;

    /**
     * Removes this node from the preference hierarchy tree. The caller of this
     * method should ensure that this node has no child nodes, which means the
     * method {@link Preferences#removeNode() Preferences.removeNode()} should
     * invoke this method multiple-times in bottom-up pattern. The removal is
     * not required to be persisted until after it is flushed.
     *
     * @throws BackingStoreException
     *             if the backing store is unavailable or causes an operation
     *             failure.
     */
    protected abstract void removeNodeSpi() throws BackingStoreException;

    /**
     * Removes the preference with the specified key. The caller of this method
     * should ensure that the given key is valid and that this node has not been
     * removed.
     *
     * @param key
     *            the key of the preference that is to be removed.
     */
    protected abstract void removeSpi(String key);

    /**
     * Synchronizes this node with the backing store. This method should only
     * synchronize this node and should not include the descendant nodes. An
     * implementation that wants to provide functionality to synchronize all
     * nodes at once should override the method {@link #sync() sync()}.
     *
     * @throws BackingStoreException
     *             if the backing store is unavailable or causes an operation
     *             failure.
     */
    protected abstract void syncSpi() throws BackingStoreException;

    /*
     * -----------------------------------------------------------
     * Methods inherited from Preferences
     * -----------------------------------------------------------
     */
    @Override
    public String absolutePath() {
        if (parentPref == null) {
            return "/";
        } else if (parentPref == root) {
            return "/" + nodeName;
        }
        return parentPref.absolutePath() + "/" + nodeName;
    }

    @Override
    public String[] childrenNames() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            TreeSet<String> result = new TreeSet<String>(cachedNode.keySet());
            String[] names = childrenNamesSpi();
            for (int i = 0; i < names.length; i++) {
                result.add(names[i]);
            }
            return result.toArray(new String[result.size()]);
        }
    }

    @Override
    public void clear() throws BackingStoreException {
        synchronized (lock) {
            for (String key : keys()) {
                remove(key);
            }
        }
    }

    @Override
    public void exportNode(OutputStream ostream) throws IOException, BackingStoreException {
        if (ostream == null) {
            throw new NullPointerException("ostream == null");
        }
        checkState();
        XMLParser.exportPrefs(this, ostream, false);
    }

    @Override
    public void exportSubtree(OutputStream ostream) throws IOException, BackingStoreException {
        if (ostream == null) {
            throw new NullPointerException("ostream == null");
        }
        checkState();
        XMLParser.exportPrefs(this, ostream, true);
    }

    @Override
    public void flush() throws BackingStoreException {
        synchronized (lock) {
            flushSpi();
        }
        AbstractPreferences[] cc = cachedChildren();
        int i;
        for (i = 0; i < cc.length; i++) {
            cc[i].flush();
        }
    }

    @Override
    public String get(String key, String deflt) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        String result = null;
        synchronized (lock) {
            checkState();
            try {
                result = getSpi(key);
            } catch (Exception e) {
                // ignored
            }
        }
        return (result == null ? deflt : result);
    }

    @Override
    public boolean getBoolean(String key, boolean deflt) {
        String result = get(key, null);
        if (result == null) {
            return deflt;
        }
        if ("true".equalsIgnoreCase(result)) {
            return true;
        } else if ("false".equalsIgnoreCase(result)) {
            return false;
        } else {
            return deflt;
        }
    }

    @Override
    public byte[] getByteArray(String key, byte[] deflt) {
        String svalue = get(key, null);
        if (svalue == null) {
            return deflt;
        }
        if (svalue.length() == 0) {
            return EmptyArray.BYTE;
        }
        try {
            byte[] bavalue = svalue.getBytes(StandardCharsets.US_ASCII);
            if (bavalue.length % 4 != 0) {
                return deflt;
            }
            return Base64.decode(bavalue);
        } catch (Exception e) {
            return deflt;
        }
    }

    @Override
    public double getDouble(String key, double deflt) {
        String result = get(key, null);
        if (result == null) {
            return deflt;
        }
        try {
            return Double.parseDouble(result);
        } catch (NumberFormatException e) {
            return deflt;
        }
    }

    @Override
    public float getFloat(String key, float deflt) {
        String result = get(key, null);
        if (result == null) {
            return deflt;
        }
        try {
            return Float.parseFloat(result);
        } catch (NumberFormatException e) {
            return deflt;
        }
    }

    @Override
    public int getInt(String key, int deflt) {
        String result = get(key, null);
        if (result == null) {
            return deflt;
        }
        try {
            return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            return deflt;
        }
    }

    @Override
    public long getLong(String key, long deflt) {
        String result = get(key, null);
        if (result == null) {
            return deflt;
        }
        try {
            return Long.parseLong(result);
        } catch (NumberFormatException e) {
            return deflt;
        }
    }

    @Override
    public boolean isUserNode() {
        return root == Preferences.userRoot();
    }

    @Override
    public String[] keys() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            return keysSpi();
        }
    }

    @Override
    public String name() {
        return nodeName;
    }

    @Override
    public Preferences node(String name) {
        AbstractPreferences startNode = null;
        synchronized (lock) {
            checkState();
            validateName(name);
            if (name.isEmpty()) {
                return this;
            } else if ("/".equals(name)) {
                return root;
            }
            if (name.startsWith("/")) {
                startNode = root;
                name = name.substring(1);
            } else {
                startNode = this;
            }
        }
        try {
            return startNode.nodeImpl(name, true);
        } catch (BackingStoreException e) {
            // should not happen
            return null;
        }
    }

    private void validateName(String name) {
        if (name.endsWith("/") && name.length() > 1) {
            throw new IllegalArgumentException("Name cannot end with '/'");
        }
        if (name.indexOf("//") >= 0) {
            throw new IllegalArgumentException("Name cannot contain consecutive '/' characters");
        }
    }

    private AbstractPreferences nodeImpl(String path, boolean createNew)
            throws BackingStoreException {
        String[] names = path.split("/");
        AbstractPreferences currentNode = this;
        AbstractPreferences temp;
        for (String name : names) {
            synchronized (currentNode.lock) {
                temp = currentNode.cachedNode.get(name);
                if (temp == null) {
                    temp = getNodeFromBackend(createNew, currentNode, name);
                }
            }
            currentNode = temp;
            if (currentNode == null) {
                break;
            }
        }
        return currentNode;
    }

    private AbstractPreferences getNodeFromBackend(boolean createNew,
            AbstractPreferences currentNode, String name) throws BackingStoreException {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name '" + name + "' too long");
        }
        AbstractPreferences temp;
        if (createNew) {
            temp = currentNode.childSpi(name);
            currentNode.cachedNode.put(name, temp);
            if (temp.newNode && currentNode.nodeChangeListeners.size() > 0) {
                currentNode.notifyChildAdded(temp);
            }
        } else {
            temp = currentNode.getChild(name);
        }
        return temp;
    }

    @Override
    public boolean nodeExists(String name) throws BackingStoreException {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        AbstractPreferences startNode = null;
        synchronized (lock) {
            if (isRemoved()) {
                if (name.isEmpty()) {
                    return false;
                }
                throw new IllegalStateException("This node has been removed");
            }
            validateName(name);
            if (name.isEmpty() || "/".equals(name)) {
                return true;
            }
            if (name.startsWith("/")) {
                startNode = root;
                name = name.substring(1);
            } else {
                startNode = this;
            }
        }
        try {
            Preferences result = startNode.nodeImpl(name, false);
            return (result != null);
        } catch(IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Preferences parent() {
        checkState();
        return parentPref;
    }

    private void checkState() {
        if (isRemoved()) {
            throw new IllegalStateException("This node has been removed");
        }
    }

    @Override
    public void put(String key, String value) {
        if (key == null) {
            throw new NullPointerException("key == null");
        } else if (value == null) {
            throw new NullPointerException("value == null");
        }
        if (key.length() > MAX_KEY_LENGTH || value.length() > MAX_VALUE_LENGTH) {
            throw new IllegalArgumentException();
        }
        synchronized (lock) {
            checkState();
            putSpi(key, value);
        }
        notifyPreferenceChange(key, value);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        put(key, String.valueOf(value));
    }

    @Override
    public void putByteArray(String key, byte[] value) {
        put(key, Base64.encode(value));
    }

    @Override
    public void putDouble(String key, double value) {
        put(key, Double.toString(value));
    }

    @Override
    public void putFloat(String key, float value) {
        put(key, Float.toString(value));
    }

    @Override
    public void putInt(String key, int value) {
        put(key, Integer.toString(value));
    }

    @Override
    public void putLong(String key, long value) {
        put(key, Long.toString(value));
    }

    @Override
    public void remove(String key) {
        synchronized (lock) {
            checkState();
            removeSpi(key);
        }
        notifyPreferenceChange(key, null);
    }

    @Override
    public void removeNode() throws BackingStoreException {
        if (root == this) {
            throw new UnsupportedOperationException("Cannot remove root node");
        }
        synchronized (parentPref.lock) {
            removeNodeImpl();
        }
    }

    private void removeNodeImpl() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            String[] childrenNames = childrenNamesSpi();
            for (String childrenName : childrenNames) {
                if (cachedNode.get(childrenName) == null) {
                    AbstractPreferences child = childSpi(childrenName);
                    cachedNode.put(childrenName, child);
                }
            }

            final Collection<AbstractPreferences> values = cachedNode.values();
            final AbstractPreferences[] children = values.toArray(new AbstractPreferences[values.size()]);
            for (AbstractPreferences child : children) {
                child.removeNodeImpl();
            }
            removeNodeSpi();
            isRemoved = true;
            parentPref.cachedNode.remove(nodeName);
        }
        if (parentPref.nodeChangeListeners.size() > 0) {
            parentPref.notifyChildRemoved(this);
        }
    }

    @Override
    public void addNodeChangeListener(NodeChangeListener ncl) {
        if (ncl == null) {
            throw new NullPointerException("ncl == null");
        }
        checkState();
        synchronized (nodeChangeListeners) {
            nodeChangeListeners.add(ncl);
        }
    }

    @Override
    public void addPreferenceChangeListener(PreferenceChangeListener pcl) {
        if (pcl == null) {
            throw new NullPointerException("pcl == null");
        }
        checkState();
        synchronized (preferenceChangeListeners) {
            preferenceChangeListeners.add(pcl);
        }
    }

    @Override
    public void removeNodeChangeListener(NodeChangeListener ncl) {
        checkState();
        synchronized (nodeChangeListeners) {
            int pos;
            if ((pos = nodeChangeListeners.indexOf(ncl)) == -1) {
                throw new IllegalArgumentException();
            }
            nodeChangeListeners.remove(pos);
        }
    }

    @Override
    public void removePreferenceChangeListener(PreferenceChangeListener pcl) {
        checkState();
        synchronized (preferenceChangeListeners) {
            int pos;
            if ((pos = preferenceChangeListeners.indexOf(pcl)) == -1) {
                throw new IllegalArgumentException();
            }
            preferenceChangeListeners.remove(pos);
        }
    }

    @Override
    public void sync() throws BackingStoreException {
        synchronized (lock) {
            checkState();
            syncSpi();
        }
        for (AbstractPreferences child : cachedChildren()) {
            child.sync();
        }
    }

    @Override
    public String toString() {
        return (isUserNode() ? "User" : "System") + " Preference Node: " + absolutePath();
    }

    private void notifyChildAdded(Preferences child) {
        NodeChangeEvent nce = new NodeAddEvent(this, child);
        synchronized (events) {
            events.add(nce);
            events.notifyAll();
        }
    }

    private void notifyChildRemoved(Preferences child) {
        NodeChangeEvent nce = new NodeRemoveEvent(this, child);
        synchronized (events) {
            events.add(nce);
            events.notifyAll();
        }
    }

    private void notifyPreferenceChange(String key, String newValue) {
        PreferenceChangeEvent pce = new PreferenceChangeEvent(this, key, newValue);
        synchronized (events) {
            events.add(pce);
            events.notifyAll();
        }
    }

    private static class EventDispatcher extends Thread {
        EventDispatcher(String name){
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                EventObject event;
                try {
                    event = getEventObject();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                AbstractPreferences pref = (AbstractPreferences) event.getSource();
                if (event instanceof NodeAddEvent) {
                    dispatchNodeAdd((NodeChangeEvent) event,
                            pref.nodeChangeListeners);
                } else if (event instanceof NodeRemoveEvent) {
                    dispatchNodeRemove((NodeChangeEvent) event,
                            pref.nodeChangeListeners);
                } else if (event instanceof PreferenceChangeEvent) {
                    dispatchPrefChange((PreferenceChangeEvent) event,
                            pref.preferenceChangeListeners);
                }
            }
        }

        private EventObject getEventObject() throws InterruptedException {
            synchronized (events) {
                if (events.isEmpty()) {
                    events.wait();
                }
                EventObject event = events.get(0);
                events.remove(0);
                return event;
            }
        }

        private void dispatchPrefChange(PreferenceChangeEvent event,
                List<EventListener> preferenceChangeListeners) {
            synchronized (preferenceChangeListeners) {
                for (EventListener preferenceChangeListener : preferenceChangeListeners) {
                    ((PreferenceChangeListener) preferenceChangeListener).preferenceChange(event);
                }
            }
        }

        private void dispatchNodeRemove(NodeChangeEvent event,
                List<EventListener> nodeChangeListeners) {
            synchronized (nodeChangeListeners) {
                for (EventListener nodeChangeListener : nodeChangeListeners) {
                    ((NodeChangeListener) nodeChangeListener).childRemoved(event);
                }
            }
        }

        private void dispatchNodeAdd(NodeChangeEvent event,
                List<EventListener> nodeChangeListeners) {
            synchronized (nodeChangeListeners) {
                for (EventListener nodeChangeListener : nodeChangeListeners) {
                    NodeChangeListener ncl = (NodeChangeListener) nodeChangeListener;
                    ncl.childAdded(event);
                }
            }
        }
    }

    private static class NodeAddEvent extends NodeChangeEvent {
        //The base class is NOT serializable, so this class isn't either.
        private static final long serialVersionUID = 1L;

        public NodeAddEvent(Preferences p, Preferences c) {
            super(p, c);
        }
    }

    private static class NodeRemoveEvent extends NodeChangeEvent {
        //The base class is NOT serializable, so this class isn't either.
        private static final long serialVersionUID = 1L;

        public NodeRemoveEvent(Preferences p, Preferences c) {
            super(p, c);
        }
    }
}
