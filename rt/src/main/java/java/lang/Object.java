/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package java.lang;

/**
 * The root class of the Java class hierarchy. All non-primitive types
 * (including arrays) inherit either directly or indirectly from this class.
 * <p>
 * {@code Object} provides some fundamental methods for accessing the
 * {@link Class} of an object, getting its {@link #hashCode()}, or checking
 * whether one object {@link #equals(Object)} another. The {@link #toString()}
 * method can be used to convert an object reference into a printable string and
 * is often overridden in subclasses.
 * <p>
 * The {@link #wait()} and {@link #notify()} methods provide a foundation for
 * synchronization, acquiring and releasing an internal monitor associated with
 * each {@code Object}.
 */
public class Object {

    /*
     * This class must be implemented by the vm vendor. Object is the root of
     * the java class hierarchy. All non-base types respond to the messages
     * defined in this class.
     */

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Object() {
    }

    /**
     * Creates and returns a copy of this {@code Object}. The default
     * implementation returns a so-called "shallow" copy: It creates a new
     * instance of the same class and then copies the field values (including
     * object references) from this instance to the new instance. A "deep" copy,
     * in contrast, would also recursively clone nested objects. A subclass that
     * needs to implement this kind of cloning should call {@code super.clone()}
     * to create the new instance and then create deep copies of the nested,
     * mutable objects.
     *
     * @return a copy of this object.
     * @throws CloneNotSupportedException
     *             if this object's class does not implement the {@code
     *             Cloneable} interface.
     */
    protected Object clone() throws CloneNotSupportedException {
        if (!(this instanceof Cloneable)) {
            throw new CloneNotSupportedException("Class does not implement Cloneable interface");
        }
        return nativeClone();
    }

    private native Object nativeClone();
    
    /**
     * Compares this instance with the specified object and indicates if they
     * are equal. In order to be equal, {@code o} must represent the same object
     * as this instance using a class-specific comparison. The general contract
     * is that this comparison should be both transitive and reflexive.
     * <p>
     * The implementation in {@code Object} returns {@code true} only if {@code
     * o} is the exact same object as the receiver (using the == operator for
     * comparison). Subclasses often implement {@code equals(Object)} so that
     * it takes into account the two object's types and states.
     * <p>
     * The general contract for the {@code equals(Object)} and {@link
     * #hashCode()} methods is that if {@code equals} returns {@code true} for
     * any two objects, then {@code hashCode()} must return the same value for
     * these objects. This means that subclasses of {@code Object} usually
     * override either both methods or none of them.
     *
     * @param o
     *            the object to compare this instance with.
     * @return {@code true} if the specified object is equal to this {@code
     *         Object}; {@code false} otherwise.
     * @see #hashCode
     */
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * Is called before the object's memory is being reclaimed by the VM. This
     * can only happen once the VM has detected, during a run of the garbage
     * collector, that the object is no longer reachable by any thread of the
     * running application.
     * <p>
     * The method can be used to free system resources or perform other cleanup
     * before the object is garbage collected. The default implementation of the
     * method is empty, which is also expected by the VM, but subclasses can
     * override {@code finalize()} as required. Uncaught exceptions which are
     * thrown during the execution of this method cause it to terminate
     * immediately but are otherwise ignored.
     * <p>
     * Note that the VM does guarantee that {@code finalize()} is called at most
     * once for any object, but it doesn't guarantee when (if at all) {@code
     * finalize()} will be called. For example, object B's {@code finalize()}
     * can delay the execution of object A's {@code finalize()} method and
     * therefore it can delay the reclamation of A's memory. To be safe, use a
     * {@link java.lang.ref.ReferenceQueue}, because it provides more control
     * over the way the VM deals with references during garbage collection.
     *
     * @throws Throwable
     *             any exception which is raised during finalization; these are
     *             ignored by the virtual machine.
     */
    protected void finalize() throws Throwable {
    }

    /**
     * Returns the unique instance of {@link Class} which represents this
     * object's class. Note that {@code getClass()} is a special case in that it
     * actually returns {@code Class<? extends Foo>} where {@code Foo} is the
     * erasure of the type of expression {@code getClass()} was called upon.
     * <p>
     * As an example, the following code actually compiles, although one might
     * think it shouldn't:
     * <p>
     * <pre>
     * List<Integer> l = new ArrayList<Integer>();
     * Class<? extends List> c = l.getClass();
     * </pre>
     *
     * @return this object's {@code Class} instance.
     */
    public final native Class<? extends Object> getClass();

    /**
     * Returns an integer hash code for this object. By contract, any two
     * objects for which {@code equals(Object)} returns {@code true} must return
     * the same hash code value. This means that subclasses of {@code Object}
     * usually override both methods or neither method.
     *
     * @return this object's hash code.
     * @see #equals
     */
    public native int hashCode();

    /**
     * Causes a thread which is waiting on this object's monitor (by means of
     * calling one of the {@code wait()} methods) to be woken up. If more than
     * one thread is waiting, one of them is chosen at the discretion of the
     * virtual machine. The chosen thread will not run immediately. The thread
     * that called {@code notify()} has to release the object's monitor first.
     * Also, the chosen thread still has to compete against other threads that
     * try to synchronize on the same object.
     * <p>
     * This method can only be invoked by a thread which owns this object's
     * monitor. A thread becomes owner of an object's monitor
     * </p>
     * <ul>
     * <li>by executing a synchronized method of that object;</li>
     * <li>by executing the body of a {@code synchronized} statement that
     * synchronizes on the object;</li>
     * <li>by executing a synchronized static method if the object is of type
     * {@code Class}.</li>
     * </ul>
     *
     * @see #notifyAll
     * @see #wait()
     * @see #wait(long)
     * @see #wait(long,int)
     * @see java.lang.Thread
     */
    public final native void notify();

    /**
     * Causes all threads which are waiting on this object's monitor (by means
     * of calling one of the {@code wait()} methods) to be woken up. The threads
     * will not run immediately. The thread that called {@code notify()} has to
     * release the object's monitor first. Also, the threads still have to
     * compete against other threads that try to synchronize on the same object.
     * <p>
     * This method can only be invoked by a thread which owns this object's
     * monitor. A thread becomes owner of an object's monitor
     * </p>
     * <ul>
     * <li>by executing a synchronized method of that object;</li>
     * <li>by executing the body of a {@code synchronized} statement that
     * synchronizes on the object;</li>
     * <li>by executing a synchronized static method if the object is of type
     * {@code Class}.</li>
     * </ul>
     *
     * @throws IllegalMonitorStateException
     *             if the thread calling this method is not the owner of this
     *             object's monitor.
     * @see #notify
     * @see #wait()
     * @see #wait(long)
     * @see #wait(long,int)
     * @see java.lang.Thread
     */
    public final native void notifyAll();

    /**
     * Returns a string containing a concise, human-readable description of this
     * object. Subclasses are encouraged to override this method and provide an
     * implementation that takes into account the object's type and data. The
     * default implementation simply concatenates the class name, the '@' sign
     * and a hexadecimal representation of the object's {@link #hashCode()},
     * that is, it is equivalent to the following expression:
     *
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre>
     *
     * @return a printable representation of this object.
     */
    public String toString() {
        return getClass().getName() + '@' + Integer.toHexString(hashCode());
    }

    /**
     * Causes the calling thread to wait until another thread calls the {@code
     * notify()} or {@code notifyAll()} method of this object. This method can
     * only be invoked by a thread which owns this object's monitor; see
     * {@link #notify()} on how a thread can become the owner of a monitor.
     * <p>
     * A waiting thread can be sent {@code interrupt()} to cause it to
     * prematurely stop waiting, so {@code wait} should be called in a loop to
     * check that the condition that has been waited for has been met before
     * continuing.
     * </p>
     * <p>
     * While the thread waits, it gives up ownership of this object's monitor.
     * When it is notified (or interrupted), it re-acquires the monitor before
     * it starts running.
     * </p>
     *
     * @throws IllegalMonitorStateException
     *             if the thread calling this method is not the owner of this
     *             object's monitor.
     * @throws InterruptedException
     *             if another thread interrupts this thread while it is waiting.
     * @see #notify
     * @see #notifyAll
     * @see #wait(long)
     * @see #wait(long,int)
     * @see java.lang.Thread
     */
    public final void wait() throws InterruptedException {
        wait(0, 0);
    }

    /**
     * Causes the calling thread to wait until another thread calls the {@code
     * notify()} or {@code notifyAll()} method of this object or until the
     * specified timeout expires. This method can only be invoked by a thread
     * which owns this object's monitor; see {@link #notify()} on how a thread
     * can become the owner of a monitor.
     * <p>
     * A waiting thread can be sent {@code interrupt()} to cause it to
     * prematurely stop waiting, so {@code wait} should be called in a loop to
     * check that the condition that has been waited for has been met before
     * continuing.
     * </p>
     * <p>
     * While the thread waits, it gives up ownership of this object's monitor.
     * When it is notified (or interrupted), it re-acquires the monitor before
     * it starts running.
     * </p>
     *
     * @param millis
     *            the maximum time to wait in milliseconds.
     * @throws IllegalArgumentException
     *             if {@code millis < 0}.
     * @throws IllegalMonitorStateException
     *             if the thread calling this method is not the owner of this
     *             object's monitor.
     * @throws InterruptedException
     *             if another thread interrupts this thread while it is waiting.
     * @see #notify
     * @see #notifyAll
     * @see #wait()
     * @see #wait(long,int)
     * @see java.lang.Thread
     */
    public final void wait(long millis) throws InterruptedException {
        wait(millis, 0);
    }

    /**
     * Causes the calling thread to wait until another thread calls the {@code
     * notify()} or {@code notifyAll()} method of this object or until the
     * specified timeout expires. This method can only be invoked by a thread
     * that owns this object's monitor; see {@link #notify()} on how a thread
     * can become the owner of a monitor.
     * <p>
     * A waiting thread can be sent {@code interrupt()} to cause it to
     * prematurely stop waiting, so {@code wait} should be called in a loop to
     * check that the condition that has been waited for has been met before
     * continuing.
     * </p>
     * <p>
     * While the thread waits, it gives up ownership of this object's monitor.
     * When it is notified (or interrupted), it re-acquires the monitor before
     * it starts running.
     * </p>
     *
     * @param millis
     *            the maximum time to wait in milliseconds.
     * @param nanos
     *            the fraction of a millisecond to wait, specified in
     *            nanoseconds.
     * @throws IllegalArgumentException
     *             if {@code millis < 0}, {@code nanos < 0} or {@code nanos >
     *             999999}.
     * @throws IllegalMonitorStateException
     *             if the thread calling this method is not the owner of this
     *             object's monitor.
     * @throws InterruptedException
     *             if another thread interrupts this thread while it is waiting.
     * @see #notify
     * @see #notifyAll
     * @see #wait()
     * @see #wait(long,int)
     * @see java.lang.Thread
     */
    public final native void wait(long millis, int nanos) throws InterruptedException;
}
