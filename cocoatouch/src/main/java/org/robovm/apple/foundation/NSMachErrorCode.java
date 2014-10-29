package org.robovm.apple.foundation;

import org.robovm.rt.bro.ValuedEnum;

public enum NSMachErrorCode implements ValuedEnum {
    SUCCESS(0),
    /**
     * Specified address is not currently valid.
     */
    INVALID_ADDRESS(1),
    /**
     * Specified memory is valid, but does not permit the required forms of
     * access.
     */
    PROTECTION_FAILURE(2),
    /**
     * The address range specified is already in use, or no address range of the
     * size specified could be found.
     */
    KERN_NO_SPACE(3),
    /**
     * The function requested was not applicable to this type of argument, or an
     * argument is invalid
     */
    INVALID_ARGUMENT(4),
    /**
     * The function could not be performed. A catch-all.
     */
    FAILURE(5),
    /**
     * A system resource could not be allocated to fulfill this request. This
     * failure may not be permanent.
     */
    RESOURCE_SHORTAGE(6),
    /**
     * The task in question does not hold receive rights for the port argument.
     */
    NOT_RECEIVER(7),
    /**
     * Bogus access restriction.
     */
    NO_ACCESS(8),
    /**
     * During a page fault, the target address refers to a memory object that
     * has been destroyed. This failure is permanent.
     */
    MEMORY_FAILURE(9),
    /**
     * During a page fault, the memory object indicated that the data could not
     * be returned. This failure may be temporary; future attempts to access
     * this same data may succeed, as defined by the memory object.
     */
    MEMORY_ERROR(10),
    /**
     * The receive right is already a member of the portset.
     */
    ALREADY_IN_SET(11),
    /**
     * The receive right is not a member of a port set.
     */
    NOT_IN_SET(12),
    /**
     * The name already denotes a right in the task.
     */
    NAME_EXISTS(13),
    /**
     * The operation was aborted. Ipc code will catch this and reflect it as a
     * message error.
     */
    ABORTED(14),
    /**
     * The name doesn't denote a right in the task.
     */
    INVALID_NAME(15),
    /**
     * Target task isn't an active task.
     */
    INVALID_TASK(16),
    /**
     * The name denotes a right, but not an appropriate right.
     */
    INVALID_RIGHT(17),
    /**
     * A blatant range error.
     */
    INVALID_VALUE(18),
    /**
     * Operation would overflow limit on user-references.
     */
    UREFS_OVERFLOW(19),
    /**
     * The supplied (port) capability is improper.
     */
    INVALID_CAPABILITY(20),
    /**
     * The task already has send or receive rights for the port under another
     * name.
     */
    RIGHT_EXISTS(21),
    /**
     * Target host isn't actually a host.
     */
    INVALID_HOST(22),
    /**
     * An attempt was made to supply "precious" data for memory that is already
     * present in a memory object.
     */
    MEMORY_PRESENT(23),
    /**
     * A page was requested of a memory manager via memory_object_data_request
     * for an object using a MEMORY_OBJECT_COPY_CALL strategy, with the
     * VM_PROT_WANTS_COPY flag being used to specify that the page desired is
     * for a copy of the object, and the memory manager has detected the page
     * was pushed into a copy of the object while the kernel was walking the
     * shadow chain from the copy to the object. This error code is delivered
     * via memory_object_data_error and is handled by the kernel (it forces the
     * kernel to restart the fault). It will not be seen by users.
     */
    MEMORY_DATA_MOVED(24),
    /**
     * A strategic copy was attempted of an object upon which a quicker copy is
     * now possible. The caller should retry the copy using
     * vm_object_copy_quickly. This error code is seen only by the kernel.
     */
    MEMORY_RESTART_COPY(25),
    /**
     * An argument applied to assert processor set privilege was not a processor
     * set control port.
     */
    INVALID_PROCESSOR_SET(26),
    /**
     * The specified scheduling attributes exceed the thread's limits.
     */
    POLICY_LIMIT(27),
    /**
     * The specified scheduling policy is not currently enabled for the
     * processor set.
     */
    INVALID_POLICY(28),
    /**
     * The external memory manager failed to initialize the memory object.
     */
    INVALID_OBJECT(29),
    /**
     * A thread is attempting to wait for an event for which there is already a
     * waiting thread.
     */
    ALREADY_WAITING(30),
    /**
     * An attempt was made to destroy the default processor set.
     */
    DEFAULT_SET(31),
    /**
     * An attempt was made to fetch an exception port that is protected, or to
     * abort a thread while processing a protected exception.
     */
    EXCEPTION_PROTECTED(32),
    /**
     * A ledger was required but not supplied.
     */
    INVALID_LEDGER(33),
    /**
     * The port was not a memory cache control port.
     */
    INVALID_MEMORY_CONTROL(34),
    /**
     * An argument supplied to assert security privilege was not a host security
     * port.
     */
    INVALID_SECURITY(35),
    /**
     * thread_depress_abort was called on a thread which was not currently
     * depressed.
     */
    NOT_DEPRESSED(36),
    /**
     * Object has been terminated and is no longer available
     */
    TERMINATED(37),
    /**
     * Lock set has been destroyed and is no longer available.
     */
    LOCK_SET_DESTROYED(38),
    /**
     * The thread holding the lock terminated before releasing the lock
     */
    LOCK_UNSTABLE(39),
    /**
     * The lock is already owned by another thread
     */
    LOCK_OWNED(40),
    /**
     * The lock is already owned by the calling thread
     */
    LOCK_OWNED_SELF(41),
    /**
     * Semaphore has been destroyed and is no longer available.
     */
    SEMAPHORE_DESTROYED(42),
    /**
     * Return from RPC indicating the target server was terminated before it
     * successfully replied
     */
    RPC_SERVER_TERMINATED(43),
    /**
     * Terminate an orphaned activation.
     */
    RPC_TERMINATE_ORPHAN(44),
    /**
     * Allow an orphaned activation to continue executing.
     */
    RPC_CONTINUE_ORPHAN(45),
    /**
     * Empty thread activation (No thread linked to it)
     */
    NOT_SUPPORTED(46),
    /**
     * Remote node down or inaccessible.
     */
    NODE_DOWN(47),
    /** A signalled thread was not actually waiting. */
    NOT_WAITING(48),
    /**
     * Some thread-oriented operation (semaphore_wait) timed out
     */
    OPERATION_TIMED_OUT(49),
    /**
     * During a page fault, indicates that the page was rejected as a result of
     * a signature check.
     */
    CODESIGN_ERROR(50),
    /**
     * Maximum return value allowable
     */
    RETURN_MAX(0x100);

    private final long n;

    private /*<name>*/NSMachErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSMachErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSMachErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        return null;
    }
}
