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

package java.lang.ref;

/**
 * The {@code ReferenceQueue} is the container on which reference objects are
 * enqueued when the garbage collector detects the reachability type specified
 * for the referent.
 *
 * @since 1.2
 */
public class ReferenceQueue<T> {
    
    private static final int DEFAULT_QUEUE_SIZE = 128;
    
    private Reference<? extends T>[] references;

    private int head;
    
    private int tail;

    private boolean empty;
    
    /**
     * Constructs a new instance of this class.
     */
    public ReferenceQueue() {
        super();
        references = newArray(DEFAULT_QUEUE_SIZE);
        head = 0;
        tail = 0;
        empty = true;
    }
    
    @SuppressWarnings("unchecked")
    private Reference<? extends T>[] newArray(int size) {
        return new Reference[size];
    }

    /**
     * Returns the next available reference from the queue, removing it in the
     * process. Does not wait for a reference to become available.
     *
     * @return the next available reference, or {@code null} if no reference is
     *         immediately available
     */
    public Reference<? extends T> poll() {
        Reference<? extends T> ref;

        synchronized (this) {
            if (empty) {
                return null;
            }
            ref = references[head++];
            ref.dequeue();
            if (head == references.length) {
                head = 0;
            }
            if (head == tail) {
                empty = true;
            }
        }
        return ref;
    }

    /**
     * Returns the next available reference from the queue, removing it in the
     * process. Waits indefinitely for a reference to become available.
     *
     * @return the next available reference
     *
     * @throws InterruptedException
     *             if the blocking call was interrupted for some reason
     */
    public Reference<? extends T> remove() throws InterruptedException {
        return remove(0L);
    }

    /**
     * Returns the next available reference from the queue, removing it in the
     * process. Waits for a reference to become available or the given timeout
     * period to elapse, whichever happens first.
     *
     * @param timeout
     *            maximum time (in ms) to spend waiting for a reference object
     *            to become available. A value of zero results in the method
     *            waiting indefinitely.
     * @return the next available reference, or {@code null} if no reference
     *         becomes available within the timeout period
     * @throws IllegalArgumentException
     *             if the wait period is negative.
     * @throws InterruptedException
     *             if the blocking call was interrupted for some reason
     */
    public Reference<? extends T> remove(long timeout) throws IllegalArgumentException,
            InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }

        Reference<? extends T> ref;
        synchronized (this) {
            if (empty) {
                wait(timeout);
                if (empty) {
                    return null;
                }
            }
            ref = references[head++];
            ref.dequeue();
            if (head == references.length) {
                head = 0;
            }
            if (head == tail) {
                empty = true;
            } else {
                notifyAll();
            }
        }
        return ref;
    }

    /**
     * Enqueue the reference object on the receiver.
     *
     * @param reference
     *            reference object to be enqueued.
     * @return boolean true if reference is enqueued. false if reference failed
     *         to enqueue.
     */
    boolean enqueue(Reference<? extends T> reference) {
        synchronized (this) {
            if (!empty && head == tail) {
                /* Queue is full - grow */
                int newQueueSize = (int) (references.length * 1.10);
                Reference<? extends T> newQueue[] = newArray(newQueueSize);
                System.arraycopy(references, head, newQueue, 0, references.length - head);
                if (tail > 0) {
                    System.arraycopy(references, 0, newQueue, references.length - head, tail);
                }
                head = 0;
                tail = references.length;
                references = newQueue;
            }
            references[tail++] = reference;
            if (tail == references.length) {
                tail = 0;
            }
            empty = false;
            notifyAll();
        }
        return true;
    }
}
