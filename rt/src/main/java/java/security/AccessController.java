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

package java.security;

/**
 * This class must be implemented by the vm vendor, or the reference
 * implementation can be used if the documented native is implemented.
 * 
 * Checks access to system resources. Supports marking of code as priveleged.
 * Makes context snapshots to allow checking from other contexts.
 */
public final class AccessController {
//    static {
//        // Initialize vm-internal caches
//        initializeInternal();
//    }
//
//    private static native void initializeInternal();

    /**
     * Prevents this class from being instantiated.
     */
    private AccessController() {
    }

    /**
     * This native must be implemented to use the reference implementation of
     * this class. It is used by checkPermission() and getContext(), which call
     * this native with depth = 1.
     * 
     * Returns an array of ProtectionDomain from the classes on the stack, from
     * the specified depth up to the first privileged frame, or the end of the
     * stack if there is not a privileged frame. The array may be larger than
     * required, but must be null terminated. As bootstrap classes have all
     * permissions, bootstrap class frames SHOULD be skipped. Bootstrap class
     * frames MUST be skipped if the ProtectionDomain of bootstrap classes is
     * null. Duplicate ProtectionDomains SHOULD be removed.
     * 
     * The first element of the result is the AccessControlContext, which may be
     * null, either from the privileged frame, or from the current Thread if
     * there is not a privileged frame.
     * 
     * A privileged frame is any frame running one of the following methods:
     * 
     * <code><ul>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedAction;)Ljava/lang/Object;</li>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;</li>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;</li>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedExceptionAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;</li>
     * </ul></code>
     * 
     * @param depth
     *            The stack depth at which to start. Depth 0 is the current
     *            frame (the caller of this native).
     * 
     * @return an Object[] where the first element is AccessControlContext, and
     *         the other elements are ProtectionsDomain.
     */

//    private static native Object[] getProtectionDomains(int depth);

    /**
     * Checks whether the running program is allowed to access the resource
     * being guarded by the given Permission argument.
     * 
     * 
     * @param perm
     *            the permission to check
     * @exception AccessControlException
     *                if access is not allowed.
     */
    public static void checkPermission(Permission perm)
            throws AccessControlException {
        if (perm == null)
            throw new NullPointerException();
//        Object[] domains = getProtectionDomains(1);
//        AccessControlContext acc = (AccessControlContext) domains[0];
//        ProtectionDomain[] pDomains = null;
//        if (acc != null && acc.domainCombiner != null) {
//            pDomains = acc.domainCombiner.combine(toArrayOfProtectionDomains(
//                    domains, null), acc.domainsArray);
//        } else {
//            pDomains = toArrayOfProtectionDomains(domains, acc);
//        }
//        for (int i = 0, length = pDomains.length; i < length; i++) {
//            if (!pDomains[i].implies(perm)) {
//                throw new AccessControlException("Access Denied " + perm, perm); //$NON-NLS-1$
//            }
//        }
    }

    /**
     * Used to keep the context live during doPrivileged().
     * 
     * @see #doPrivileged(PrivilegedAction, AccessControlContext)
     */
    private static void keepalive(AccessControlContext context) {
    }

    /**
     * Answers the access controller context of the current thread, including
     * the inherited ones. It basically retrieves all the protection domains
     * from the calling stack and creates an <code>AccessControlContext</code>
     * with them.
     * 
     * @return the access control context of the current thread
     * @see AccessControlContext
     */
    public static AccessControlContext getContext() {
        return new AccessControlContext(new ProtectionDomain[0], false);
//        Object[] domains = getProtectionDomains(1);
//        AccessControlContext acc = (AccessControlContext) domains[0];
//        ProtectionDomain[] pDomains = null;
//        if (acc != null && acc.domainCombiner != null) {
//            pDomains = acc.domainCombiner.combine(toArrayOfProtectionDomains(
//                    domains, null), acc.domainsArray);
//            AccessControlContext result = new AccessControlContext(pDomains,
//                    false);
//            result.domainCombiner = acc.domainCombiner;
//            return result;
//        }
//        return new AccessControlContext(
//                toArrayOfProtectionDomains(domains, acc), false);
    }

//    private static ProtectionDomain[] toArrayOfProtectionDomains(
//            Object[] domains, AccessControlContext acc) {
//        int len = 0, size = domains.length - 1;
//        int extra = acc == null ? 0 : acc.domainsArray.length;
//        ProtectionDomain[] answer = new ProtectionDomain[size + extra];
//        for (int i = 1; i <= size; i++) {
//            boolean found = false;
//            if ((answer[len] = (ProtectionDomain) domains[i]) == null)
//                break;
//            if (acc != null) {
//                for (int j = 0; j < acc.domainsArray.length; j++) {
//                    if (answer[len] == acc.domainsArray[j]) {
//                        found = true;
//                        break;
//                    }
//                }
//            }
//            if (!found)
//                len++;
//        }
//        if (len == 0 && acc != null)
//            return acc.domainsArray;
//        else if (len < size) {
//            ProtectionDomain[] copy = new ProtectionDomain[len + extra];
//            System.arraycopy(answer, 0, copy, 0, len);
//            answer = copy;
//        }
//        if (acc != null)
//            System.arraycopy(acc.domainsArray, 0, answer, len,
//                    acc.domainsArray.length);
//        return answer;
//    }

    /**
     * Performs the privileged action specified by <code>action</code>.
     * 
     * When permission checks are made, if the permission has been granted by
     * all frames below and including the one representing the call to this
     * method, then the permission is granted. In otherwords, the check stops
     * here.
     * 
     * Any unchecked exception generated by this method will propagate up the
     * chain.
     * 
     * @param action
     *            the action being performed
     * @param <T>
     *            the return type for the privileged action
     * @return the result of evaluating the action
     * 
     * @see #doPrivileged(PrivilegedAction)
     */
    public static <T> T doPrivileged(PrivilegedAction<T> action) {
        return action.run();
    }

    /**
     * Performs the privileged action specified by <code>action</code>.
     * 
     * When permission checks are made, if the permission has been granted by
     * all frames below and including the one representing the call to this
     * method, then the permission is granted iff it is granted by the
     * AccessControlContext <code>context</code>. In otherwords, no more
     * checking of the current stack is performed. Instead, the passed in
     * context is checked.
     * 
     * Any unchecked exception generated by this method will propagate up the
     * chain.
     * 
     * @param action
     *            the action being performed
     * @param <T>
     *            the return type for the privileged action
     * @param context
     *            the context being checked for the privileged action
     * @return the result of evaluating the action
     * 
     * @see #doPrivileged(PrivilegedAction)
     */
    public static <T> T doPrivileged(PrivilegedAction<T> action,
            AccessControlContext context) {
        T result = action.run();
        keepalive(context);
        return result;
    }

    /**
     * Performs the privileged action specified by <code>action</code>.
     * 
     * When permission checks are made, if the permission has been granted by
     * all frames below and including the one representing the call to this
     * method, then the permission is granted. In otherwords, the check stops
     * here.
     * 
     * Any unchecked exception generated by this method will propagate up the
     * chain. However, checked exceptions will be caught an re-thrown as
     * PrivilegedActionExceptions.
     * 
     * @param action
     *            the action being performed
     * @param <T>
     *            the return type for the privileged action
     * @return the result of evaluating the action
     * @throws PrivilegedActionException
     *             if a checked exception was thrown
     * @see #doPrivileged(PrivilegedAction)
     */
    public static <T> T doPrivileged(PrivilegedExceptionAction<T> action)
            throws PrivilegedActionException {
        try {
            return action.run();
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PrivilegedActionException(ex);
        }
    }

    /**
     * Performs the privileged action specified by <code>action</code>.
     * 
     * When permission checks are made, if the permission has been granted by
     * all frames below and including the one representing the call to this
     * method, then the permission is granted iff it is granted by the
     * AccessControlContext <code>context</code>. In otherwords, no more
     * checking of the current stack is performed. Instead, the passed in
     * context is checked.
     * 
     * Any unchecked exception generated by this method will propagate up the
     * chain. However, checked exceptions will be caught an re-thrown as
     * PrivilegedActionExceptions
     * 
     * @param action
     *            the action being performed
     * @param <T>
     *            the return type for the privileged action
     * @param context
     *            the context being checked for the privileged action
     * @return the result of evaluating the action
     * @throws PrivilegedActionException
     *             if a checked exception was thrown
     * 
     * @see #doPrivileged(PrivilegedAction)
     */
    public static <T> T doPrivileged(PrivilegedExceptionAction<T> action,
            AccessControlContext context) throws PrivilegedActionException {
        try {
            T result = action.run();
            keepalive(context);
            return result;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PrivilegedActionException(ex);
        }
    }

}
