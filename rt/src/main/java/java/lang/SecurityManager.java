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

package java.lang;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FilePermission;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.net.InetAddress;
import java.net.SocketPermission;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permission;
import java.security.Security;
import java.security.SecurityPermission;
import java.util.PropertyPermission;

import org.apache.harmony.luni.util.PriviAction;

/**
 * Provides security verification facilities for applications. {@code
 * SecurityManager} contains a set of {@code checkXXX} methods which determine
 * if it is safe to perform a specific operation such as establishing network
 * connections, modifying files, and many more. In general, these methods simply
 * return if they allow the application to perform the operation; if an
 * operation is not allowed, then they throw a {@link SecurityException}. The
 * only exception is {@link #checkTopLevelWindow(Object)}, which returns a
 * boolean to indicate permission.
 */
public class SecurityManager {

    private static final PropertyPermission READ_WRITE_ALL_PROPERTIES_PERMISSION = new PropertyPermission(
            "*", "read,write"); //$NON-NLS-1$ //$NON-NLS-2$

    private static final String PKG_ACC_KEY = "package.access"; //$NON-NLS-1$

    private static final String PKG_DEF_KEY = "package.definition"; //$NON-NLS-1$

    /**
     * Flag to indicate whether a security check is in progress.
     * 
     * @deprecated Use {@link #checkPermission}
     */
    @Deprecated
    protected boolean inCheck;

    /**
     * Constructs a new {@code SecurityManager} instance.
     * <p>
     * The {@code RuntimePermission("createSecurityManager")} is checked if a
     * security manager is installed.
     */
    public SecurityManager() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security
                    .checkPermission(RuntimePermission.permissionToCreateSecurityManager);
        }
        Class<?> type = Security.class; // initialize Security properties
        if (type == null) {
            throw new AssertionError();
        }
    }

    /**
     * Checks whether the calling thread is allowed to accept socket
     * connections.
     * 
     * @param host
     *            the address of the host that attempts to connect.
     * @param port
     *            the port number to check.
     * @throws NullPointerException
     *             if {@code host} is {@code null}.
     * @throws SecurityException
     *             if the calling thread is not allowed to accept socket
     *             connections from {@code host} through {@code port}.
     */
    public void checkAccept(String host, int port) {
        if (host == null) {
            throw new NullPointerException();
        }
        checkPermission(new SocketPermission(host + ':' + port, "accept")); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to modify the specified
     * thread.
     * 
     * @param thread
     *            the thread to access.
     * @throws SecurityException
     *             if the calling thread is not allowed to access {@code thread}.
     */
    public void checkAccess(Thread thread) {
        // Only worry about system threads. Dead threads have a null group.
        ThreadGroup group = thread.getThreadGroup();
        if ((group != null) && (group.parent == null)) {
            checkPermission(RuntimePermission.permissionToModifyThread);
        }
    }

    /**
     * Checks whether the calling thread is allowed to modify the specified
     * thread group.
     * 
     * @param group
     *            the thread group to access.
     * @throws NullPointerException
     *             if {@code group} is {@code null}.
     * @throws SecurityException
     *             if the calling thread is not allowed to access {@code group}.
     */
    public void checkAccess(ThreadGroup group) {
        // Only worry about system threads.
        if (group == null) {
            throw new NullPointerException();
        }
        if (group.parent == null) {
            checkPermission(RuntimePermission.permissionToModifyThreadGroup);
        }
    }

    /**
     * Checks whether the calling thread is allowed to establish socket
     * connections. A -1 port indicates the caller is trying to resolve the
     * hostname.
     * 
     * @param host
     *            the address of the host to connect to.
     * @param port
     *            the port number to check, or -1 for resolve.
     * @throws NullPointerException
     *             if {@code host} is {@code null}.
     * @throws SecurityException
     *             if the calling thread is not allowed to connect to {@code
     *             host} through {@code port}.
     */
    public void checkConnect(String host, int port) {
        if (host == null) {
            throw new NullPointerException();
        }
        if (port > 0) {
            checkPermission(new SocketPermission(host + ':' + port, "connect")); //$NON-NLS-1$
        } else {
            checkPermission(new SocketPermission(host, "resolve")); //$NON-NLS-1$
        }
    }

    /**
     * Checks whether the specified security context is allowed to establish
     * socket connections. A -1 port indicates the caller is trying to resolve
     * the hostname.
     * 
     * @param host
     *            the address of the host to connect to.
     * @param port
     *            the port number to check, or -1 for resolve.
     * @param context
     *            the security context to use for the check.
     * @throws NullPointerException
     *             if {@code host} is {@code null}.
     * @throws SecurityException
     *             if {@code context} is not allowed to connect to {@code host}
     *             through {@code port}.
     */
    public void checkConnect(String host, int port, Object context) {
        if (port > 0) {
            checkPermission(new SocketPermission(host + ':' + port, "connect"), //$NON-NLS-1$
                    context);
        } else {
            checkPermission(new SocketPermission(host, "resolve"), context); //$NON-NLS-1$
        }
    }

    /**
     * Checks whether the calling thread is allowed to create a class loader.
     *
     * @throws SecurityException
     *             if the calling thread is not allowed to create a class
     *             loader.
     */
    public void checkCreateClassLoader() {
        checkPermission(RuntimePermission.permissionToCreateClassLoader);
    }

    /**
     * Checks whether the calling thread is allowed to delete the file with the
     * specified name, which should be passed in canonical form.
     * 
     * @param file
     *            the name of the file to delete.
     * @throws SecurityException
     *             if the calling thread is not allowed to delete {@code file}.
     */
    public void checkDelete(String file) {
        checkPermission(new FilePermission(file, "delete")); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to execute the specified
     * platform specific command.
     * 
     * @param cmd
     *            the command line to execute.
     * @throws SecurityException
     *             if the calling thread is not allowed to execute {@code cmd}.
     */
    public void checkExec(String cmd) {
        checkPermission(new FilePermission(new File(cmd).isAbsolute() ? cmd
                : "<<ALL FILES>>", "execute")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Checks whether the calling thread is allowed to terminate the virtual
     * machine.
     * 
     * @param status
     *            the status that the virtual machine returns when it is
     *            terminated.
     * @throws SecurityException
     *             if the calling thread is not allowed to terminate the virtual
     *             machine with {@code status}.
     */
    public void checkExit(int status) {
        checkPermission(RuntimePermission.permissionToExitVM);
    }

    /**
     * Checks whether the calling thread is allowed to load the specified native
     * library.
     * 
     * @param libName
     *            the name of the library to load.
     * @throws SecurityException
     *             if the calling thread is not allowed to load {@code libName}.
     */
    public void checkLink(String libName) {
        if (libName == null) {
            throw new NullPointerException();
        }
        checkPermission(new RuntimePermission("loadLibrary." + libName)); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to listen on the specified
     * port.
     * 
     * @param port
     *            the port number to check.
     * @throws SecurityException
     *             if the calling thread is not allowed listen on {@code port}.
     */
    public void checkListen(int port) {
        if (port == 0) {
            checkPermission(new SocketPermission("localhost:1024-", "listen")); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            checkPermission(new SocketPermission("localhost:" + port, "listen")); //$NON-NLS-1$//$NON-NLS-2$
        }
    }

    /**
     * Checks whether the calling thread is allowed to access members. The
     * default is to allow access to public members (that is, {@code
     * java.lang.reflect.Member.PUBLIC}) and to classes loaded by the same
     * loader as the original caller (that is, the method that called the
     * reflect API). Due to the nature of the check, overriding implementations
     * cannot call {@code super.checkMemberAccess()} since the stack would no
     * longer be of the expected shape.
     * 
     * @param cls
     *            the class of which members are accessed.
     * @param type
     *            the access type, either {@code
     *            java.lang.reflect.Member.PUBLIC} or {@code
     *            java.lang.reflect.Member.DECLARED}.
     * @throws SecurityException
     *             if the calling thread is not allowed to access members of
     *             {@code cls}.
     */
    public void checkMemberAccess(Class<?> cls, int type) {
        if (cls == null) {
            throw new NullPointerException();
        }
        if (type == Member.PUBLIC) {
            return;
        }
        //
        // Need to compare the classloaders.
        // Stack shape is
        // <user code> <- want this class
        // Class.getDeclared*();
        // Class.checkMemberAccess();
        // SecurityManager.checkMemberAccess(); <- current frame
        //
        // Use getClassLoaderImpl() since getClassLoader()
        // returns null for the bootstrap class loader.
        if (ClassLoader.getStackClassLoader(3) == cls.getClassLoaderImpl()) {
            return;
        }

        // Forward off to the permission mechanism.
        checkPermission(new RuntimePermission("accessDeclaredMembers")); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to use the specified IP
     * multicast group address.
     *
     * @param maddr
     *            the internet group address to use.
     * @throws SecurityException
     *             if the calling thread is not allowed to use {@code maddr}.
     */
    public void checkMulticast(InetAddress maddr) {
        checkPermission(new SocketPermission(maddr.getHostAddress(),
                "accept,connect")); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to use the specified IP
     * multicast group address.
     * 
     * @param maddr
     *            the internet group address to use.
     * @param ttl
     *            the value in use for multicast send. This parameter is
     *            ignored.
     * @throws SecurityException
     *             if the calling thread is not allowed to use {@code maddr}.
     * @deprecated use {@link #checkMulticast(java.net.InetAddress)}
     */
    @Deprecated
    public void checkMulticast(InetAddress maddr, byte ttl) {
        checkPermission(new SocketPermission(maddr.getHostAddress(),
                "accept,connect")); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to access the specified
     * package.
     * 
     * @param packageName
     *            the name of the package to access.
     * @throws SecurityException
     *             if the calling thread is not allowed to access {@code
     *             packageName}.
     */
    public void checkPackageAccess(String packageName) {
        if (packageName == null) {
            throw new NullPointerException();
        }
        if (checkPackageProperty(PKG_ACC_KEY, packageName)) {
            checkPermission(new RuntimePermission("accessClassInPackage." //$NON-NLS-1$
                    + packageName));
        }
    }

    /**
     * Checks whether the calling thread is allowed to define new classes in the
     * specified package.
     * 
     * @param packageName
     *            the name of the package to add a class to.
     * @throws SecurityException
     *             if the calling thread is not allowed to add classes to
     *             {@code packageName}.
     */
    public void checkPackageDefinition(String packageName) {
        if (packageName == null) {
            throw new NullPointerException();
        }
        if (checkPackageProperty(PKG_DEF_KEY, packageName)) {
            checkPermission(new RuntimePermission("defineClassInPackage." //$NON-NLS-1$
                    + packageName));
        }
    }

    /**
     * Returns true if the package name is restricted by the specified security
     * property.
     */
    private static boolean checkPackageProperty(final String property,
            final String pkg) {
        String list = AccessController.doPrivileged(PriviAction
                .getSecurityProperty(property));
        if (list != null) {
            int plen = pkg.length();
            String[] tokens = list.split(", *"); //$NON-NLS-1$
            for (String token : tokens) {
                int tlen = token.length();
                if (plen > tlen
                        && pkg.startsWith(token)
                        && (token.charAt(tlen - 1) == '.' || pkg.charAt(tlen) == '.')) {
                    return true;
                } else if (plen == tlen && token.startsWith(pkg)) {
                    return true;
                } else if (plen + 1 == tlen && token.startsWith(pkg)
                        && token.charAt(tlen - 1) == '.') {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks whether the calling thread is allowed to access the system
     * properties.
     *
     * @throws SecurityException
     *             if the calling thread is not allowed to access system
     *             properties.
     */
    public void checkPropertiesAccess() {
        checkPermission(READ_WRITE_ALL_PROPERTIES_PERMISSION);
    }

    /**
     * Checks whether the calling thread is allowed to access a particular
     * system property.
     * 
     * @param key
     *            the name of the property to access.
     * @throws SecurityException
     *             if the calling thread is not allowed to access the {@code
     *             key} system property.
     */
    public void checkPropertyAccess(String key) {
        checkPermission(new PropertyPermission(key, "read")); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to read from the file with
     * the specified file descriptor.
     * 
     * @param fd
     *            the file descriptor of the file to read from.
     * @throws SecurityException
     *             if the calling thread is not allowed to read from {@code fd}.
     */
    public void checkRead(FileDescriptor fd) {
        if (fd == null) {
            throw new NullPointerException();
        }
        checkPermission(RuntimePermission.permissionToReadFileDescriptor);
    }

    /**
     * Checks whether the calling thread is allowed to read from the file with
     * the specified name, which should be passed in canonical form.
     * 
     * @param file
     *            the name of the file or directory to read from.
     * @throws SecurityException
     *             if the calling thread is not allowed to read from {@code
     *             file}.
     */
    public void checkRead(String file) {
        checkPermission(new FilePermission(file, "read")); //$NON-NLS-1$
    }

    /**
     * Checks whether the given security context is allowed to read from the
     * file named by the argument, which should be passed in canonical form.
     * 
     * @param file
     *            the name of the file or directory to check.
     * @param context
     *            the security context to use for the check.
     * @throws SecurityException
     *             if {@code context} is not allowed to read from {@code file}.
     */
    public void checkRead(String file, Object context) {
        checkPermission(new FilePermission(file, "read"), context); //$NON-NLS-1$
    }

    /**
     * Checks whether the calling thread is allowed to perform the security
     * operation named by the target.
     * 
     * @param target
     *            the name of the operation to perform.
     * @throws SecurityException
     *             if the calling thread is not allowed to perform
     *             {@code target}.
     */
    public void checkSecurityAccess(String target) {
        checkPermission(new SecurityPermission(target));
    }

    /**
     * Checks whether the calling thread is allowed to set the net object
     * factories.
     *
     * @throws SecurityException
     *             if the calling thread is not allowed to set the net object
     *             factories.
     */
    public void checkSetFactory() {
        checkPermission(RuntimePermission.permissionToSetFactory);
    }

    /**
     * Checks whether the calling thread is trusted to show the specified top
     * level window.
     * 
     * @param window
     *            the window to show.
     * @return {@code true} if the calling thread is allowed to show {@code
     *         window}; {@code false} otherwise.
     * @throws NullPointerException
     *             if {@code window} is {@code null}.
     */
    public boolean checkTopLevelWindow(Object window) {
        if (window == null) {
            throw new NullPointerException();
        }
        try {
            Class<?> awtPermission = Class.forName("java.awt.AWTPermission"); //$NON-NLS-1$
            Constructor<?> constructor = awtPermission
                    .getConstructor(String.class);
            Object perm = constructor
                    .newInstance("showWindowWithoutWarningBanner"); //$NON-NLS-1$
            checkPermission((Permission) perm);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (SecurityException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the calling thread is allowed to access the system
     * clipboard.
     *
     * @throws SecurityException
     *             if the calling thread is not allowed to access the system
     *             clipboard.
     */
    public void checkSystemClipboardAccess() {
        try {
            Class<?> awtPermission = Class.forName("java.awt.AWTPermission"); //$NON-NLS-1$
            Constructor<?> constructor = awtPermission
                    .getConstructor(String.class);
            Object perm = constructor.newInstance("accessClipboard"); //$NON-NLS-1$
            checkPermission((Permission) perm);
            return;
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
        throw new SecurityException();
    }

    /**
     * Checks whether the calling thread is allowed to access the AWT event
     * queue.
     *
     * @throws SecurityException
     *             if the calling thread is not allowed to access the AWT event
     *             queue.
     */
    public void checkAwtEventQueueAccess() {
        try {
            Class<?> awtPermission = Class.forName("java.awt.AWTPermission"); //$NON-NLS-1$
            Constructor<?> constructor = awtPermission
                    .getConstructor(String.class);
            Object perm = constructor.newInstance("accessEventQueue"); //$NON-NLS-1$
            checkPermission((Permission) perm);
            return;
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
        throw new SecurityException();
    }

    /**
     * Checks whether the calling thread is allowed to start a new print job.
     *
     * @throws SecurityException
     *             if the calling thread is not allowed to start a new print
     *             job.
     */
    public void checkPrintJobAccess() {
        checkPermission(RuntimePermission.permissionToQueuePrintJob);
    }

    /**
     * Checks whether the calling thread is allowed to write to the file with
     * the specified file descriptor.
     * 
     * @param fd
     *            the file descriptor of the file to write to.
     * @throws SecurityException
     *             if the calling thread is not allowed to write to {@code fd}.
     */
    public void checkWrite(FileDescriptor fd) {
        if (fd == null) {
            throw new NullPointerException();
        }
        checkPermission(RuntimePermission.permissionToWriteFileDescriptor);
    }

    /**
     * Checks whether the calling thread is allowed to write to the file with
     * the specified name, which should be passed in canonical form.
     * 
     * @param file
     *            the name of the file or directory to write to.
     * @throws SecurityException
     *             if the calling thread is not allowed to write to
     *             {@code file}.
     */
    public void checkWrite(String file) {
        checkPermission(new FilePermission(file, "write")); //$NON-NLS-1$
    }

    /**
     * Indicates if this security manager is currently checking something.
     * 
     * @return {@code true} if this security manager is executing a security
     *         check method; {@code false} otherwise.
     * @deprecated Use {@link #checkPermission}.
     */
    @Deprecated
    public boolean getInCheck() {
        return inCheck;
    }

    /**
     * Returns an array containing one entry for each method in the current
     * execution stack. Each entry is the {@code java.lang.Class} which
     * represents the class in which the method is defined.
     * 
     * @return all classes in the execution stack.
     */
    @SuppressWarnings("unchecked")
    protected Class[] getClassContext() {
        return Class.getStackClasses(-1/*, false*/);
    }

    /**
     * Returns the class loader of the first class in the execution stack whose
     * class loader is not a system class loader.
     * 
     * @return the most recent non-system class loader.
     * @deprecated Use {@link #checkPermission}.
     */
    @Deprecated
    protected ClassLoader currentClassLoader() {
        /*
         * We are running in an unsafe environment, so just answer null
         * (==> everything is a system class).
         */
        return null;
    }

    /**
     * Returns the index in the call stack of the first class whose class loader
     * is not a system class loader.
     * 
     * @return the frame index of the first method whose class was loaded by a
     *         non-system class loader.
     * @deprecated Use {@link #checkPermission}.
     */
    @Deprecated
    protected int classLoaderDepth() {
        /*
         * We are running in an unsafe environment, so just answer -1 (==>
         * everything is a system class).
         */
        return -1;
    }

    /**
     * Returns the first class in the call stack that was loaded by a class
     * loader which is not a system class loader.
     * 
     * @return the most recent class loaded by a non-system class loader.
     * @deprecated Use {@link #checkPermission}.
     */
    @Deprecated
    protected Class<?> currentLoadedClass() {
        /*
         * We are running in an unsafe environment, so just answer null
         * (==> everything is a system class).
         */
        return null;
    }

    /**
     * Returns the index in the call stack of the first method which is
     * contained in the class with the specified name. Returns -1 if no methods
     * from this class are in the stack.
     * 
     * @param name
     *            the name of the class to look for.
     * @return the frame index of the first method found is contained in the
     *         class identified by {@code name}.
     * @deprecated Use {@link #checkPermission}.
     */
    @Deprecated
    protected int classDepth(String name) {
        Class<?>[] classes = Class.getStackClasses(-1/*, false*/);
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Indicates whether there is a method in the call stack from the class with
     * the specified name.
     * 
     * @param name
     *            the name of the class to look for.
     * @return {@code true} if a method from the class identified by {@code
     *         name} is executing; {@code false} otherwise.
     * @deprecated Use {@link #checkPermission}.
     */
    @Deprecated
    protected boolean inClass(String name) {
        return classDepth(name) != -1;
    }

    /**
     * Indicates whether there is a method in the call stack from a class which
     * was defined by a non-system class loader.
     * 
     * @return {@code true} if a method from a class that was defined by a
     *         non-system class loader is executing; {@code false} otherwise.
     * @deprecated Use {@link #checkPermission}
     */
    @Deprecated
    protected boolean inClassLoader() {
        return currentClassLoader() != null;
    }

    /**
     * Returns the thread group which should be used to instantiate new threads.
     * By default, this is the same as the thread group of the thread running
     * this method.
     * 
     * @return ThreadGroup the thread group to create new threads in.
     */
    public ThreadGroup getThreadGroup() {
        return Thread.currentThread().getThreadGroup();
    }

    /**
     * Returns an object which encapsulates the security state of the current
     * point in the execution. In our case, this is an {@link
     * java.security.AccessControlContext}.
     *
     * @return an object that encapsulates information about the current
     *         execution environment.
     */
    public Object getSecurityContext() {
        return AccessController.getContext();
    }

    /**
     * Checks whether the calling thread is allowed to access the resource being
     * guarded by the specified permission object.
     *
     * @param permission
     *            the permission to check.
     * @throws SecurityException
     *             if the requested {@code permission} is denied according to
     *             the current security policy.
     */
    public void checkPermission(Permission permission) {
        try {
            inCheck = true;
            AccessController.checkPermission(permission);
        } finally {
            inCheck = false;
        }
    }

    /**
     * Checks whether the specified security context is allowed to access the
     * resource being guarded by the specified permission object.
     * 
     * @param permission
     *            the permission to check.
     * @param context
     *            the security context for which to check permission.
     * @throws SecurityException
     *             if {@code context} is not an instance of {@code
     *             AccessControlContext} or if the requested {@code permission}
     *             is denied for {@code context} according to the current
     *             security policy.
     */
    public void checkPermission(Permission permission, Object context) {
        try {
            inCheck = true;
            // Must be an AccessControlContext. If we don't check
            // this, then applications could pass in an arbitrary
            // object which circumvents the security check.
            if (context instanceof AccessControlContext) {
                ((AccessControlContext) context).checkPermission(permission);
            } else {
                throw new SecurityException();
            }
        } finally {
            inCheck = false;
        }
    }
}
