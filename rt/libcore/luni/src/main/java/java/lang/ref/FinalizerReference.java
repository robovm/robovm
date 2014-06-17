/*
 * Copyright (C) 2011 The Android Open Source Project
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

package java.lang.ref;

/**
 * @hide
 */
public final class FinalizerReference<T> extends Reference<T> {
    // This queue contains those objects eligible for finalization.
    public static final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

    // Guards the list (not the queue).
    private static final Object LIST_LOCK = new Object();

    // This list contains a FinalizerReference for every finalizable object in the heap.
    // Objects in this list may or may not be eligible for finalization yet.
    private static FinalizerReference<?> head = null;

    // The links used to construct the list.
    private FinalizerReference<?> prev;
    private FinalizerReference<?> next;

    // When the GC wants something finalized, it moves it from the 'referent' field to
    // the 'zombie' field instead.
    private T zombie;

    public FinalizerReference(T r, ReferenceQueue<? super T> q) {
        super(r, q);
    }

    @Override public T get() {
        return zombie;
    }

    @Override public void clear() {
        zombie = null;
    }

    public static void add(Object referent) {
        FinalizerReference<?> reference = new FinalizerReference<Object>(referent, queue);
        synchronized (LIST_LOCK) {
            reference.prev = null;
            reference.next = head;
            if (head != null) {
                head.prev = reference;
            }
            head = reference;
        }
    }

    public static void remove(FinalizerReference<?> reference) {
        synchronized (LIST_LOCK) {
            FinalizerReference<?> next = reference.next;
            FinalizerReference<?> prev = reference.prev;
            reference.next = null;
            reference.prev = null;
            if (prev != null) {
                prev.next = next;
            } else {
                head = next;
            }
            if (next != null) {
                next.prev = prev;
            }
        }
    }

    /**
     * Waits for all currently-enqueued references to be finalized.
     */
    public static void finalizeAllEnqueued() throws InterruptedException {
        Sentinel sentinel = new Sentinel();
        enqueueSentinelReference(sentinel);
        sentinel.awaitFinalization();
    }

    private static void enqueueSentinelReference(Sentinel sentinel) {
        synchronized (LIST_LOCK) {
            // When a finalizable object is allocated, a FinalizerReference is added to the list.
            // We search the list for that FinalizerReference (it should be at or near the head),
            // and then put it on the queue so that it can be finalized.
            for (FinalizerReference<?> r = head; r != null; r = r.next) {
                if (r.referent == sentinel) {
                    FinalizerReference<Sentinel> sentinelReference = (FinalizerReference<Sentinel>) r;
                    sentinelReference.referent = null;
                    sentinelReference.zombie = sentinel;
                    sentinelReference.enqueueInternal();
                    return;
                }
            }
        }
        // We just created a finalizable object and still hold a reference to it.
        // It must be on the list.
        throw new AssertionError("newly-created live Sentinel not on list!");
    }

    /**
     * A marker object that we can immediately enqueue. When this object's
     * finalize() method is called, we know all previously-enqueued finalizable
     * references have been finalized.
     */
    private static class Sentinel {
        boolean finalized = false;

        @Override protected synchronized void finalize() throws Throwable {
            if (finalized) {
                throw new AssertionError();
            }
            finalized = true;
            notifyAll();
        }

        synchronized void awaitFinalization() throws InterruptedException {
            while (!finalized) {
                wait();
            }
        }
    }
}
