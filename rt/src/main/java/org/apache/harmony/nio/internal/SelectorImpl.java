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
package org.apache.harmony.nio.internal;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.IllegalSelectorException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import static java.nio.channels.SelectionKey.OP_ACCEPT;
import static java.nio.channels.SelectionKey.OP_CONNECT;
import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelectionKey;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.harmony.luni.platform.FileDescriptorHandler;
import org.apache.harmony.luni.platform.Platform;

/*
 * Default implementation of java.nio.channels.Selector
 */
final class SelectorImpl extends AbstractSelector {

    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private static final int ACCEPT_OR_READ = OP_ACCEPT | OP_READ;

    private static final int MOCK_WRITEBUF_SIZE = 1;

    private static final int MOCK_READBUF_SIZE = 8;

    private static final int NA = 0;

    private static final int READABLE = 1;

    private static final int WRITEABLE = 2;

    private static final int SELECT_BLOCK = -1;

    private static final int SELECT_NOW = 0;

    /**
     * Used to synchronize when a key's interest ops change.
     */
    private static class KeysLock {}
    final Object keysLock = new KeysLock();

    private SelectionKeyImpl[] keys = new SelectionKeyImpl[1];

    private final Set<SelectionKey> mutableKeys = new HashSet<SelectionKey>();

    /**
     * The unmodifiable set of keys as exposed to the user. This object is used
     * for synchronization.
     */
    private Set<SelectionKey> unmodifiableKeys = Collections
            .<SelectionKey>unmodifiableSet(mutableKeys);

    private final Set<SelectionKey> mutableSelectedKeys = new HashSet<SelectionKey>();

    /**
     * The unmodifiable set of selectable keys as seen by the user. This object
     * is used for synchronization.
     */
    private final Set<SelectionKey> selectedKeys
            = new UnaddableSet<SelectionKey>(mutableSelectedKeys);

    private FileDescriptor[] readableFDs;

    private FileDescriptor[] writableFDs;

    private int lastKeyIndex = -1;

    private int readableKeysCount = 0;

    private int writableKeysCount = 0;

    private int[] keysToReadableFDs;

    private int[] keysToWritableFDs;

    private int[] readableFDsToKeys;

    private int[] writableFDsToKeys;

    /**
     * Selection flags that define the ready ops on the ready keys. When not
     * actively selecting, all elements are 0. Corresponds to the ready keys
     * set.
     */
    private int[] flags = EMPTY_INT_ARRAY;

    /**
     * A mock channel is used to signal wakeups. Whenever the selector should
     * stop blocking on a select(), a byte is written to the sink and will be
     * picked up in source by the selecting thread.
     */
    private Pipe.SinkChannel sink;
    private Pipe.SourceChannel source;
    private FileDescriptor sourcefd;

    public SelectorImpl(SelectorProvider selectorProvider) {
        super(selectorProvider);
        try {
            Pipe mockSelector = selectorProvider.openPipe();
            sink = mockSelector.sink();
            source = mockSelector.source();
            sourcefd = ((FileDescriptorHandler) source).getFD();
            source.configureBlocking(false);

            readableFDs = new FileDescriptor[1];
            writableFDs = new FileDescriptor[0];
            keysToReadableFDs = new int[1];
            keysToWritableFDs = new int[0];
            readableFDsToKeys = new int[1];
            writableFDsToKeys = new int[0];

            // register sink channel
            readableFDs[0] = sourcefd;
            keys[0] = (SelectionKeyImpl) source.keyFor(this);

            // index it
            keysToReadableFDs[0] = 0;
            readableFDsToKeys[0] = 0;

            lastKeyIndex = 0;
            readableKeysCount = 1;
        } catch (IOException e) {
            // do nothing
        }
    }

    /**
     * @see java.nio.channels.spi.AbstractSelector#implCloseSelector()
     */
    @Override
    protected void implCloseSelector() throws IOException {
        wakeup();
        synchronized (this) {
            synchronized (unmodifiableKeys) {
                synchronized (selectedKeys) {
                    sink.close();
                    source.close();
                    doCancel();
                    for (SelectionKey sk : mutableKeys) {
                        deregister((AbstractSelectionKey) sk);
                    }
                }
            }
        }
    }

    private void ensureCommonCapacity(int c) {
        // TODO: rewrite array handling as some internal class
        if (c >= keys.length) {
            SelectionKeyImpl[] newKeys = new SelectionKeyImpl[(keys.length + 1) << 1];
            System.arraycopy(keys, 0, newKeys, 0, keys.length);
            keys = newKeys;
        }

        if (c >= keysToReadableFDs.length) {
            int[] newKeysToReadableFDs = new int[(keysToReadableFDs.length + 1) << 1];
            System.arraycopy(keysToReadableFDs, 0, newKeysToReadableFDs, 0,
                    keysToReadableFDs.length);
            keysToReadableFDs = newKeysToReadableFDs;
        }

        if (c >= keysToWritableFDs.length) {
            int[] newKeysToWritableFDs = new int[(keysToWritableFDs.length + 1) << 1];
            System.arraycopy(keysToWritableFDs, 0, newKeysToWritableFDs, 0,
                    keysToWritableFDs.length);
            keysToWritableFDs = newKeysToWritableFDs;
        }

        if (readableKeysCount >= readableFDsToKeys.length) {
            int[] newReadableFDsToKeys = new int[(readableFDsToKeys.length + 1) << 1];
            System.arraycopy(readableFDsToKeys, 0, newReadableFDsToKeys, 0,
                    readableFDsToKeys.length);
            readableFDsToKeys = newReadableFDsToKeys;
        }

        if (writableKeysCount >= writableFDsToKeys.length) {
            int[] newWritableFDsToKeys = new int[(writableFDsToKeys.length + 1) << 1];
            System.arraycopy(writableFDsToKeys, 0, newWritableFDsToKeys, 0,
                    writableFDsToKeys.length);
            writableFDsToKeys = newWritableFDsToKeys;
        }

    }

    // prepare the space specified for the READ ops
    private void ensureReadCapacity(int c) {
        if (readableKeysCount >= readableFDs.length) {
            FileDescriptor[] newReadableFDs = new FileDescriptor[(readableFDs.length + 1) << 1];
            System.arraycopy(readableFDs, 0, newReadableFDs, 0,
                    readableFDs.length);
            readableFDs = newReadableFDs;
        }
    }

    // prepare the space specified for the WRITE ops
    private void ensureWriteCapacity(int c) {
        if (writableKeysCount >= writableFDs.length) {
            FileDescriptor[] newWriteableFDs = new FileDescriptor[(writableFDs.length + 1) << 1];
            System.arraycopy(writableFDs, 0, newWriteableFDs, 0,
                    writableFDs.length);
            writableFDs = newWriteableFDs;
        }
    }

    private void limitCapacity() {
        // TODO: implement array squeezing
    }

    /**
     * Adds the specified key to storage and updates the indexes accordingly
     * 
     * @param sk
     *            key to add
     * @return index in the storage
     */
    private void addKey(SelectionKeyImpl sk) {

        lastKeyIndex++;
        int c = lastKeyIndex;

        // make sure that enough space is available
        ensureCommonCapacity(c);

        // add to keys storage
        keys[c] = sk;

        // cache the fields
        int ops = sk.interestOps();
        FileDescriptor fd = ((FileDescriptorHandler) sk.channel()).getFD();

        // presume that we have no FD associated
        keysToReadableFDs[c] = -1;
        keysToWritableFDs[c] = -1;

        // if readable operations requested
        if (((SelectionKey.OP_ACCEPT | SelectionKey.OP_READ) & ops) != 0) {
            ensureReadCapacity(c);
            // save as readable FD
            readableFDs[readableKeysCount] = fd;

            // create index
            keysToReadableFDs[c] = readableKeysCount;
            readableFDsToKeys[readableKeysCount] = c;

            readableKeysCount++;
        }

        // if writable operations requested
        if (((SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE) & ops) != 0) {
            ensureWriteCapacity(c);
            // save as writable FD
            writableFDs[writableKeysCount] = fd;

            // create index
            keysToWritableFDs[c] = writableKeysCount;
            writableFDsToKeys[writableKeysCount] = c;

            writableKeysCount++;
        }
        sk.setIndex(c);
    }

    /**
     * Deletes the key from the internal storage and updates the indexes
     * accordingly
     * 
     * @param sk
     *            key to delete
     */
    private void delKey(SelectionKeyImpl sk) {
        int index = sk.getIndex();

        // === deleting the key and FDs

        // key is null now
        keys[index] = null;

        // free FDs connected with the key
        // free indexes
        int readableIndex = keysToReadableFDs[index];
        if (readableIndex != -1) {
            readableFDs[readableIndex] = null;
            readableFDsToKeys[readableIndex] = -1;
            keysToReadableFDs[index] = -1;
        }

        int writableIndex = keysToWritableFDs[index];
        if (writableIndex != -1) {
            writableFDs[writableIndex] = null;
            writableFDsToKeys[writableIndex] = -1;
            keysToWritableFDs[index] = -1;
        }

        // === compacting arrays and indexes

        // key compaction
        if (keys[lastKeyIndex] != null) {
            keys[index] = keys[lastKeyIndex];
            keys[lastKeyIndex] = null;

            // update key index
            keys[index].setIndex(index);

            // the key in the new position references the same FDs
            keysToReadableFDs[index] = keysToReadableFDs[lastKeyIndex];
            keysToWritableFDs[index] = keysToWritableFDs[lastKeyIndex];

            // associated FDs reference the same key at new index
            if (keysToReadableFDs[index] != -1) {
                readableFDsToKeys[keysToReadableFDs[index]] = index;
            }

            if (keysToWritableFDs[index] != -1) {
                writableFDsToKeys[keysToWritableFDs[index]] = index;
            }

        }
        lastKeyIndex--;

        // readableFDs compaction
        if (readableIndex != -1) {
            if (readableFDs[readableKeysCount - 1] != null) {
                readableFDs[readableIndex] = readableFDs[readableKeysCount - 1];

                // new FD references the same key
                readableFDsToKeys[readableIndex] = readableFDsToKeys[readableKeysCount - 1];

                // the key references the same FD at new index
                if (readableFDsToKeys[readableIndex] != -1) {
                    keysToReadableFDs[readableFDsToKeys[readableIndex]] = readableIndex;
                }
            }
            readableKeysCount--;
        }

        // writableFDs compaction
        if (writableIndex != -1) {
            if (writableFDs[writableKeysCount - 1] != null) {
                writableFDs[writableIndex] = writableFDs[writableKeysCount - 1];

                // new FD references the same key
                writableFDsToKeys[writableIndex] = writableFDsToKeys[writableKeysCount - 1];

                // the key references the same FD at new index
                if (writableFDsToKeys[writableIndex] != -1) {
                    keysToWritableFDs[writableFDsToKeys[writableIndex]] = writableIndex;
                }
            }
            writableKeysCount--;
        }
    }

    /**
     * Note that the given key has been modified
     * 
     * @param sk
     *            the modified key.
     */
    void modKey(SelectionKey sk) {
        // TODO: update indexes rather than recreate the key
        synchronized (this) {
            synchronized (unmodifiableKeys) {
                synchronized (selectedKeys) {
                    SelectionKeyImpl ski = (SelectionKeyImpl) sk;
                    delKey(ski);
                    addKey(ski);
                }
            }
        }
    }

    /**
     * @see java.nio.channels.spi.AbstractSelector#register(java.nio.channels.spi.AbstractSelectableChannel,
     *      int, java.lang.Object)
     */
    @Override
    protected SelectionKey register(AbstractSelectableChannel channel,
            int operations, Object attachment) {
        if (!provider().equals(channel.provider())) {
            throw new IllegalSelectorException();
        }
        synchronized (this) {
            synchronized (unmodifiableKeys) {
                // create the key
                SelectionKeyImpl selectionKey = new SelectionKeyImpl(
                        channel, operations, attachment, this);
                addKey(selectionKey);
                mutableKeys.add(selectionKey);
                return selectionKey;
            }
        }
    }

    /**
     * @see java.nio.channels.Selector#keys()
     */
    @Override
    public synchronized Set<SelectionKey> keys() {
        closeCheck();
        return unmodifiableKeys;
    }

    /*
     * Checks that the receiver is not closed. If it is throws an exception.
     */
    private void closeCheck() {
        if (!isOpen()) {
            throw new ClosedSelectorException();
        }
    }

    /**
     * @see java.nio.channels.Selector#select()
     */
    @Override
    public int select() throws IOException {
        return selectInternal(SELECT_BLOCK);
    }

    /**
     * @see java.nio.channels.Selector#select(long)
     */
    @Override
    public int select(long timeout) throws IOException {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }
        return selectInternal((0 == timeout) ? SELECT_BLOCK : timeout);
    }

    /**
     * @see java.nio.channels.Selector#selectNow()
     */
    @Override
    public int selectNow() throws IOException {
        return selectInternal(SELECT_NOW);
    }

    private int selectInternal(long timeout) throws IOException {
        closeCheck();
        synchronized (this) {
            synchronized (unmodifiableKeys) {
                synchronized (selectedKeys) {
                    doCancel();
                    boolean isBlock = (SELECT_NOW != timeout);
                    prepareChannels();
                    boolean success;
                    try {
                        if (isBlock) {
                            begin();
                        }
                        success = Platform.getNetworkSystem().select(
                                readableFDs, writableFDs, readableKeysCount, writableKeysCount, timeout, flags);
                    } finally {
                        if (isBlock) {
                            end();
                        }
                    }

                    int selected = success ? processSelectResult() : 0;

                    Arrays.fill(flags, 0);

                    Set<SelectionKey> cancelledKeys = cancelledKeys();
                    synchronized (cancelledKeys) {
                        if (cancelledKeys.size() > 0) {
                            for (SelectionKey currentkey : cancelledKeys) {
                                delKey((SelectionKeyImpl)currentkey);
                                mutableKeys.remove(currentkey);
                                deregister((AbstractSelectionKey) currentkey);
                                if (mutableSelectedKeys.remove(currentkey)) {
                                    selected--;
                                }
                            }
                            cancelledKeys.clear();
                        }
                        limitCapacity();
                    }

                    return selected;
                }
            }
        }
    }

    /*
     * Prepares and adds channels to list for selection
     */
    private void prepareChannels() {
        if (flags.length < readableKeysCount + writableKeysCount) {
            flags = new int[readableKeysCount + writableKeysCount];
        }

    }

    /**
     * Updates the key ready ops and selected key set with data from the flags
     * array.
     */
    private int processSelectResult() throws IOException {
        // if the mock channel is selected, read the content.
        if (READABLE == flags[0]) {
            ByteBuffer readbuf = ByteBuffer.allocate(MOCK_READBUF_SIZE);
            while (source.read(readbuf) > 0) {
                readbuf.flip();
            }
        }
        int selected = 0;

        for (int i = 1; i < flags.length; i++) {
            if (flags[i] == NA) {
                continue;
            }
            SelectionKeyImpl key = i >= readableKeysCount ? keys[writableFDsToKeys[i
                    - readableKeysCount]]
                    : keys[readableFDsToKeys[i]];

            if (null == key) {
                continue;
            }

            int ops = key.interestOpsNoCheck();
            int selectedOp = 0;

            switch (flags[i]) {
                case READABLE:
                    selectedOp = ACCEPT_OR_READ & ops;
                    break;
                case WRITEABLE:
                    if (key.isConnected()) {
                        selectedOp = OP_WRITE & ops;
                    } else {
                        selectedOp = OP_CONNECT & ops;
                    }
                    break;
            }

            if (0 != selectedOp) {
                boolean wasSelected = mutableSelectedKeys.contains(key);
                if (wasSelected && key.readyOps() != selectedOp) {
                    key.setReadyOps(key.readyOps() | selectedOp);
                    selected++;
                } else if (!wasSelected) {
                    key.setReadyOps(selectedOp);
                    mutableSelectedKeys.add(key);
                    selected++;
                }
            }
        }

        return selected;
    }

    /**
     * @see java.nio.channels.Selector#selectedKeys()
     */
    @Override
    public synchronized Set<SelectionKey> selectedKeys() {
        closeCheck();
        return selectedKeys;
    }

    /*
     * Assumes calling thread holds locks on 'this', 'unmodifiableKeys', and 'selectedKeys'.
     */
    private void doCancel() {
        Set<SelectionKey> cancelledKeys = cancelledKeys();
        synchronized (cancelledKeys) {
            if (cancelledKeys.size() > 0) {
                for (SelectionKey currentkey : cancelledKeys) {
                    delKey((SelectionKeyImpl)currentkey);
                    mutableKeys.remove(currentkey);
                    deregister((AbstractSelectionKey) currentkey);
                    mutableSelectedKeys.remove(currentkey);
                }
                cancelledKeys.clear();
            }
            limitCapacity();
        }
    }

    /**
     * @see java.nio.channels.Selector#wakeup()
     */
    @Override
    public Selector wakeup() {
        try {
            sink.write(ByteBuffer.allocate(MOCK_WRITEBUF_SIZE));
        } catch (IOException e) {
            // do nothing
        }
        return this;
    }

    private static class UnaddableSet<E> implements Set<E> {

        private final Set<E> set;

        UnaddableSet(Set<E> set) {
            this.set = set;
        }

        @Override
        public boolean equals(Object object) {
            return set.equals(object);
        }

        @Override
        public int hashCode() {
            return set.hashCode();
        }

        public boolean add(E object) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            set.clear();
        }

        public boolean contains(Object object) {
            return set.contains(object);
        }

        public boolean containsAll(Collection<?> c) {
            return set.containsAll(c);
        }

        public boolean isEmpty() {
            return set.isEmpty();
        }

        public Iterator<E> iterator() {
            return set.iterator();
        }

        public boolean remove(Object object) {
            return set.remove(object);
        }

        public boolean removeAll(Collection<?> c) {
            return set.removeAll(c);
        }

        public boolean retainAll(Collection<?> c) {
            return set.retainAll(c);
        }

        public int size() {
            return set.size();
        }

        public Object[] toArray() {
            return set.toArray();
        }

        public <T> T[] toArray(T[] a) {
            return set.toArray(a);
        }
    }
}
