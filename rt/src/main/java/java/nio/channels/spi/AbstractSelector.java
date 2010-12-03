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

package java.nio.channels.spi;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@code AbstractSelector} is the base implementation class for selectors.
 * It realizes the interruption of selection by {@code begin} and
 * {@code end}. It also holds the cancellation and the deletion of the key
 * set.
 */
public abstract class AbstractSelector extends Selector {
    private final AtomicBoolean isOpen = new AtomicBoolean(true);

    private SelectorProvider provider = null;

    /*
     * Set of cancelled keys.
     */
    private Set<SelectionKey> cancelledKeysSet = new HashSet<SelectionKey>();

    /**
     * Constructs a new {@code AbstractSelector}.
     * 
     * @param selectorProvider
     *            the selector provider that creates this selector.
     */
    protected AbstractSelector(SelectorProvider selectorProvider) {
        provider = selectorProvider;
    }

    /**
     * Closes this selector. This method does nothing if this selector is
     * already closed. The actual closing must be implemented by subclasses in
     * {@code implCloseSelector()}.
     * 
     * @throws IOException
     *             if an I/O error occurs.
     */
    @Override
    public final void close() throws IOException {
        if (isOpen.getAndSet(false)) {
            implCloseSelector();
        }
    }

    /**
     * Implements the closing of this channel.
     * 
     * @throws IOException
     *             if an I/O error occurs.
     */
    protected abstract void implCloseSelector() throws IOException;

    /**
     * Indicates whether this selector is open.
     *
     * @return {@code true} if this selector is not closed, {@code false}
     *         otherwise.
     */
    @Override
    public final boolean isOpen() {
        return isOpen.get();
    }

    /**
     * Gets this selector's provider.
     * 
     * @return the provider of this selector.
     */
    @Override
    public final SelectorProvider provider() {
        return provider;
    }

    /**
     * Returns this channel's set of canceled selection keys.
     * 
     * @return the set of canceled selection keys.
     */
    protected final Set<SelectionKey> cancelledKeys() {
        return cancelledKeysSet;
    }

    /**
     * Registers a channel with this selector.
     * 
     * @param channel
     *            the channel to be registered.
     * @param operations
     *            the {@link SelectionKey interest set} of {@code channel}.
     * @param attachment
     *            the attachment for the selection key.
     * @return the key related to the channel and this selector.
     */
    protected abstract SelectionKey register(AbstractSelectableChannel channel,
            int operations, Object attachment);

    /**
     * Deletes the key from the channel's key set.
     * 
     * @param key
     *            the key.
     */
    protected final void deregister(AbstractSelectionKey key) {
        ((AbstractSelectableChannel) key.channel()).deregister(key);
        key.isValid = false;
    }

    /**
     * Indicates the beginning of a code section that includes an I/O operation
     * that is potentially blocking. After this operation, the application
     * should invoke the corresponding {@code end(boolean)} method.
     */
    protected final void begin() {
        // FIXME: be accommodate before VM actually provides
        // setInterruptAction method
        if (AbstractInterruptibleChannel.setInterruptAction != null) {
            try {
                AbstractInterruptibleChannel.setInterruptAction.invoke(Thread
                        .currentThread(), new Object[] { new Runnable() {
                    public void run() {
                        AbstractSelector.this.wakeup();
                    }
                } });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Indicates the end of a code section that has been started with
     * {@code begin()} and that includes a potentially blocking I/O operation.
     */
    protected final void end() {
        // FIXME: be accommodate before VM actually provides
        // setInterruptAction method
        if (AbstractInterruptibleChannel.setInterruptAction != null) {
            try {
                AbstractInterruptibleChannel.setInterruptAction.invoke(Thread
                        .currentThread(), new Object[] { null });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
     * package private method for AbstractSelectionKey.cancel()
     */
    void cancel(SelectionKey key) {
        synchronized (cancelledKeysSet) {
            cancelledKeysSet.add(key);
        }
    }
}
