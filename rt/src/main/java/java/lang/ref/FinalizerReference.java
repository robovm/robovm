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
    public static final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

    private static FinalizerReference head = null;

    private T zombie;

    private FinalizerReference prev;

    private FinalizerReference next;

    public FinalizerReference(T r, ReferenceQueue<? super T> q) {
        super(r, q);
    }

    @Override
    public T get() {
        return zombie;
    }

    @Override
    public void clear() {
        zombie = null;
    }

    static void add(Object referent) {
        FinalizerReference<?> reference = new FinalizerReference<Object>(referent, queue);
        synchronized (FinalizerReference.class) {
            reference.prev = null;
            reference.next = head;
            if (head != null) {
                head.prev = reference;
            }
            head = reference;
        }
    }

    public static void remove(FinalizerReference reference) {
        synchronized (FinalizerReference.class) {
            FinalizerReference next = reference.next;
            FinalizerReference prev = reference.prev;
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
     * Returns once all currently-enqueued references have been finalized.
     */
    public static void finalizeAllEnqueued() throws InterruptedException {
        Sentinel sentinel = new Sentinel();
        FinalizerReference<Object> reference = new FinalizerReference<Object>(null, queue);
        reference.zombie = sentinel;
        reference.enqueueInternal();
        sentinel.awaitFinalization();
    }

    /**
     * A marker object that we can immediately enqueue. When this object's
     * finalize() method is called, we know all previously-enqueued finalizable
     * references have been finalized.
     *
     * <p>Each instance of this class will be finalized twice as it is enqueued
     * directly and by the garbage collector.
     */
    private static class Sentinel {
        boolean finalized = false;
        @Override protected synchronized void finalize() throws Throwable {
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
