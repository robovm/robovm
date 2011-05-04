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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;
import java.security.Policy;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyPermission;

/**
 * Provides access to system-related information and resources including
 * standard input and output. Enables clients to dynamically load native
 * libraries. All methods of this class are accessed in a static way and the
 * class itself can not be instantiated.
 *
 * @see Runtime
 */
public final class System {

    // The standard input, output, and error streams.
    // Typically, these are connected to the shell which
    // ran the Java program.
    /**
     * Default input stream.
     */
    public static final InputStream in;

    /**
     * Default output stream.
     */
    public static final PrintStream out;

    /**
     * Default error output stream.
     */
    public static final PrintStream err;

    // Get a ref to the Runtime instance for faster lookup
    private static final Runtime RUNTIME = Runtime.getRuntime();

    /**
     * The System Properties table.
     */
    private static Properties systemProperties;

    // The System default SecurityManager
    private static SecurityManager security;

    // Indicates whether the classes needed for
    // permission checks was initialized or not
    private static boolean security_initialized;

    // Initialize all the slots in System on first use.
    static {
        // Fill in the properties from the VM information.
        ensureProperties();
        // Set up standard in, out, and err.
        err = new String.ConsolePrintStream(new BufferedOutputStream(new FileOutputStream(
                FileDescriptor.err)));
        out = new String.ConsolePrintStream(new BufferedOutputStream(new FileOutputStream(
                FileDescriptor.out)));
        in = new BufferedInputStream(new FileInputStream(FileDescriptor.in));
    }

    /**
     * Sets the standard input stream to the given user defined input stream.
     * 
     * @param newIn
     *            the user defined input stream to set as the standard input
     *            stream.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPermission()} method does not allow the change of the
     *             stream.
     */
    @SuppressWarnings("unused")
    public static void setIn(InputStream newIn) {
        SecurityManager secMgr = System.getSecurityManager();
        setFieldImpl("in", newIn);
    }

    /**
     * Sets the standard output stream to the given user defined output stream.
     * 
     * @param newOut
     *            the user defined output stream to set as the standard output
     *            stream.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPermission()} method does not allow the change of the
     *             stream.
     */
    @SuppressWarnings("unused")
    public static void setOut(java.io.PrintStream newOut) {
        SecurityManager secMgr = System.getSecurityManager();
        setFieldImpl("out", newOut);
    }

    /**
     * Sets the standard error output stream to the given user defined output
     * stream.
     * 
     * @param newErr
     *            the user defined output stream to set as the standard error
     *            output stream.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPermission()} method does not allow the change of the
     *             stream.
     */
    @SuppressWarnings("unused")
    public static void setErr(java.io.PrintStream newErr) {
        SecurityManager secMgr = System.getSecurityManager();
    }

    /**
     * Prevents this class from being instantiated.
     */
    private System() {
    }

    /**
     * Copies the number of {@code length} elements of the Array {@code src}
     * starting at the offset {@code srcPos} into the Array {@code dest} at
     * the position {@code destPos}.
     *
     * @param src
     *            the source array to copy the content.
     * @param srcPos
     *            the starting index of the content in {@code src}.
     * @param dest
     *            the destination array to copy the data into.
     * @param destPos
     *            the starting index for the copied content in {@code dest}.
     * @param length
     *            the number of elements of the {@code array1} content they have
     *            to be copied.
     */
    public static void arraycopy(Object src, int srcPos, Object dest, int destPos,
            int length) {
        // sending getClass() to both arguments will check for null
        Class<?> type1 = src.getClass();
        Class<?> type2 = dest.getClass();
        if (!type1.isArray() || !type2.isArray()) {
            throw new ArrayStoreException();
        }
        Class<?> componentType1 = type1.getComponentType();
        Class<?> componentType2 = type2.getComponentType();
        if (!componentType1.isPrimitive()) {
            if (componentType2.isPrimitive()) {
                throw new ArrayStoreException();
            }
            arraycopy((Object[]) src, srcPos, (Object[]) dest, destPos, length);
        } else {
            if (componentType2 != componentType1) {
                throw new ArrayStoreException();
            }
            if (componentType1 == Integer.TYPE) {
                arraycopy((int[]) src, srcPos, (int[]) dest, destPos, length);
            } else if (componentType1 == Byte.TYPE) {
                arraycopy((byte[]) src, srcPos, (byte[]) dest, destPos, length);
            } else if (componentType1 == Long.TYPE) {
                arraycopy((long[]) src, srcPos, (long[]) dest, destPos, length);
            } else if (componentType1 == Short.TYPE) {
                arraycopy((short[]) src, srcPos, (short[]) dest, destPos, length);
            } else if (componentType1 == Character.TYPE) {
                arraycopy((char[]) src, srcPos, (char[]) dest, destPos, length);
            } else if (componentType1 == Boolean.TYPE) {
                arraycopy((boolean[]) src, srcPos, (boolean[]) dest, destPos, length);
            } else if (componentType1 == Double.TYPE) {
                arraycopy((double[]) src, srcPos, (double[]) dest, destPos, length);
            } else if (componentType1 == Float.TYPE) {
                arraycopy((float[]) src, srcPos, (float[]) dest, destPos, length);
            }
        }
    }

    /**
     * Private version of the arraycopy method (used by the jit for reference
     * arraycopies)
     */
    private static void arraycopy(Object[] A1, int offset1, Object[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(int[] A1, int offset1, int[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(byte[] A1, int offset1, byte[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(short[] A1, int offset1, short[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(long[] A1, int offset1, long[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(char[] A1, int offset1, char[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(boolean[] A1, int offset1, boolean[] A2, int offset2,
            int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(double[] A1, int offset1, double[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the contents of <code>A1</code> starting at offset
     * <code>offset1</code> into <code>A2</code> starting at offset
     * <code>offset2</code> for <code>length</code> elements.
     * 
     * @param A1 the array to copy out of
     * @param offset1 the starting index in array1
     * @param A2 the array to copy into
     * @param offset2 the starting index in array2
     * @param length the number of elements in the array to copy
     */
    private static void arraycopy(float[] A1, int offset1, float[] A2, int offset2, int length) {
        if (offset1 >= 0 && offset2 >= 0 && length >= 0 && length <= A1.length - offset1
                && length <= A2.length - offset2) {
            // Check if this is a forward or backwards arraycopy
            if (A1 != A2 || offset1 > offset2 || offset1 + length <= offset2) {
                for (int i = 0; i < length; ++i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    A2[offset2 + i] = A1[offset1 + i];
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Returns the current system time in milliseconds since January 1, 1970
     * 00:00:00 UTC. This method shouldn't be used for measuring timeouts or
     * other elapsed time measurements, as changing the system time can affect
     * the results.
     *
     * @return the local system time in milliseconds.
     */
    public static native long currentTimeMillis();

    /**
     * Returns the current timestamp of the most precise timer available on the
     * local system. This timestamp can only be used to measure an elapsed
     * period by comparing it against another timestamp. It cannot be used as a
     * very exact system time expression.
     *
     * @return the current timestamp in nanoseconds.
     */
    public static native long nanoTime();

    private static final int InitLocale = 0;

    private static final int PlatformEncoding = 1;

    private static final int FileEncoding = 2;

    private static final int OSEncoding = 3;

    /**
     * If systemProperties is unset, then create a new one based on the values
     * provided by the virtual machine.
     */
    private static void ensureProperties() {
        systemProperties = new Properties();

        String platformEncoding = null;
        String fileEncoding, osEncoding = null;
        String definedFileEncoding = getEncoding(FileEncoding);
        String definedOSEncoding = getEncoding(OSEncoding);
        if (definedFileEncoding != null) {
            fileEncoding = definedFileEncoding;
            // if file.encoding is defined, and os.encoding is not, use the
            // detected
            // platform encoding for os.encoding
            if (definedOSEncoding == null) {
                platformEncoding = getEncoding(PlatformEncoding);
                osEncoding = platformEncoding;
            } else {
                getEncoding(InitLocale);
            }
        } else {
            platformEncoding = getEncoding(PlatformEncoding);
            fileEncoding = platformEncoding;
        }
        // if os.encoding is not defined, file.encoding will be used
        if (osEncoding == null) {
            osEncoding = definedOSEncoding;
        }
        if (osEncoding != null) {
            systemProperties.put("os.encoding", osEncoding);
        }

        systemProperties.put("file.encoding", fileEncoding);

        systemProperties.put("java.version", "1.5 subset");
        systemProperties.put("java.specification.version", "1.5");

        systemProperties.put("java.specification.vendor", "Sun Microsystems Inc.");
        systemProperties.put("java.specification.name", "Java Platform API Specification");

        systemProperties.put("com.ibm.oti.configuration", "clear");
        systemProperties.put("com.ibm.oti.configuration.dir", "jclClear");

        String[] list = getPropertyList();
        for (int i = 0; i < list.length; i += 2) {
            String key = list[i];
            if (key == null) {
                break;
            }
            systemProperties.put(key, list[i + 1]);
        }

        String consoleEncoding = (String) systemProperties.get("console.encoding");
        if (consoleEncoding == null) {
            if (platformEncoding == null) {
                platformEncoding = getEncoding(PlatformEncoding);
            }
            consoleEncoding = platformEncoding;
            systemProperties.put("console.encoding", consoleEncoding);
        }

    }

    /**
     * Causes the virtual machine to stop running and the program to exit. If
     * {@link #runFinalizersOnExit(boolean)} has been previously invoked with a
     * {@code true} argument, then all objects will be properly
     * garbage-collected and finalized first.
     *
     * @param code
     *            the return code.
     * @throws SecurityException
     *             if the running thread has not enough permission to exit the
     *             virtual machine.
     * @see SecurityManager#checkExit
     */
    public static void exit(int code) {
        RUNTIME.exit(code);
    }

    /**
     * Indicates to the virtual machine that it would be a good time to run the
     * garbage collector. Note that this is a hint only. There is no guarantee
     * that the garbage collector will actually be run.
     */
    public static void gc() {
        RUNTIME.gc();
    }

    /**
     * Returns the value of the environment variable with the given name {@code
     * var}.
     *
     * @param name
     *            the name of the environment variable.
     * @return the value of the specified environment variable or {@code null}
     *         if no variable exists with the given name.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPermission()} method does not allow the querying of
     *             single environment variables.
     */
    public static String getenv(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        SecurityManager secMgr = System.getSecurityManager();
        if (secMgr != null) {
            secMgr.checkPermission(new RuntimePermission("getenv." + name));
        }
        throw new Error();
    }

    /**
     * Returns an unmodifiable map of all available environment variables.
     *
     * @return the map representing all environment variables.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPermission()} method does not allow the querying of
     *             all environment variables.
     */
    public static Map<String, String> getenv() {
        SecurityManager secMgr = System.getSecurityManager();
        if (secMgr != null) {
            secMgr.checkPermission(new RuntimePermission("getenv.*"));
        }
        throw new Error();
    }

    /**
     * Returns the inherited channel from the creator of the current virtual
     * machine.
     *
     * @return the inherited {@link Channel} or {@code null} if none exists.
     * @throws IOException
     *             if an I/O error occurred.
     * @see SelectorProvider
     * @see SelectorProvider#inheritedChannel()
     */
    public static Channel inheritedChannel() throws IOException {
        return SelectorProvider.provider().inheritedChannel();
    }

    /**
     * Returns the system properties. Note that this is not a copy, so that
     * changes made to the returned Properties object will be reflected in
     * subsequent calls to getProperty and getProperties.
     *
     * @return the system properties.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPropertiesAccess()} method does not allow the operation.
     */
    public static Properties getProperties() {
        SecurityManager secMgr = System.getSecurityManager();
        if (secMgr != null) {
            secMgr.checkPropertiesAccess();
        }
        return systemProperties;
    }

    /**
     * Returns the system properties without any security checks. This is used
     * for access from within java.lang.
     * 
     * @return the system properties
     */
    static Properties internalGetProperties() {
        return systemProperties;
    }

    /**
     * Returns the value of a particular system property or {@code null} if no
     * such property exists.
     * <p>
     * The properties currently provided by the virtual machine are:
     * 
     * <pre>
     *        java.vendor.url
     *        java.class.path
     *        user.home
     *        java.class.version
     *        os.version
     *        java.vendor
     *        user.dir
     *        user.timezone
     *        path.separator
     *        os.name
     *        os.arch
     *        line.separator
     *        file.separator
     *        user.name
     *        java.version
     *        java.home
     * </pre>
     * 
     * @param prop
     *            the name of the system property to look up.
     * @return the value of the specified system property or {@code null} if the
     *         property doesn't exist.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPropertyAccess()} method does not allow the operation.
     */
    public static String getProperty(String prop) {
        return getProperty(prop, null);
    }

    /**
     * Returns the value of a particular system property. The {@code
     * defaultValue} will be returned if no such property has been found.
     *
     * @param prop
     *            the name of the system property to look up.
     * @param defaultValue
     *            the return value if the system property with the given name
     *            does not exist.
     * @return the value of the specified system property or the {@code
     *         defaultValue} if the property does not exist.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPropertyAccess()} method does not allow the operation.
     */
    public static String getProperty(String prop, String defaultValue) {
        if (prop.length() == 0) {
            throw new IllegalArgumentException();
        }
        SecurityManager secMgr = System.getSecurityManager();
        if (secMgr != null) {
            secMgr.checkPropertyAccess(prop);
        }
        return systemProperties.getProperty(prop, defaultValue);
    }

    /**
     * Sets the value of a particular system property.
     *
     * @param prop
     *            the name of the system property to be changed.
     * @param value
     *            the value to associate with the given property {@code prop}.
     * @return the old value of the property or {@code null} if the property
     *         didn't exist.
     * @throws SecurityException
     *             if a security manager exists and write access to the
     *             specified property is not allowed.
     */
    public static String setProperty(String prop, String value) {
        if (prop.length() == 0) {
            throw new IllegalArgumentException();
        }
        SecurityManager secMgr = System.getSecurityManager();
        if (secMgr != null) {
            secMgr.checkPermission(new PropertyPermission(prop, "write"));
        }
        return (String) systemProperties.setProperty(prop, value);
    }

    /**
     * Removes a specific system property.
     *
     * @param key
     *            the name of the system property to be removed.
     * @return the property value or {@code null} if the property didn't exist.
     * @throws NullPointerException
     *             if the argument {@code key} is {@code null}.
     * @throws IllegalArgumentException
     *             if the argument {@code key} is empty.
     * @throws SecurityException
     *             if a security manager exists and write access to the
     *             specified property is not allowed.
     * @since 1.5
     */
    public static String clearProperty(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException();
        }

        SecurityManager secMgr = System.getSecurityManager();
        if (secMgr != null) {
            secMgr.checkPermission(new PropertyPermission(key, "write"));
        }
        return (String) systemProperties.remove(key);
    }

    /**
     * Answers an array of Strings containing key..value pairs (in consecutive
     * array elements) which represent the starting values for the system
     * properties as provided by the virtual machine.
     * 
     * @return the default values for the system properties.
     */
    private static native String[] getPropertyList();

    /**
     * Return the requested encoding. 0 - initialize locale 1 - detected
     * platform encoding 2 - command line defined file.encoding 3 - command line
     * defined os.encoding
     */
    private static native String getEncoding(int type);

    /**
     * Returns the active security manager.
     *
     * @return the system security manager object.
     */
    public static SecurityManager getSecurityManager() {
        return security;
    }

    /**
     * Returns an integer hash code for the parameter. The hash code returned is
     * the same one that would be returned by the method {@code
     * java.lang.Object.hashCode()}, whether or not the object's class has
     * overridden hashCode(). The hash code for {@code null} is {@code 0}.
     *
     * @param anObject
     *            the object to calculate the hash code.
     * @return the hash code for the given object.
     * @see java.lang.Object#hashCode
     */
    public static native int identityHashCode(Object anObject);

    /**
     * Loads the specified file as a dynamic library.
     * 
     * @param pathName
     *            the path of the file to be loaded.
     * @throws SecurityException
     *             if the library was not allowed to be loaded.
     */
    public static void load(String pathName) {
        Runtime.getRuntime().load0(
                pathName,
                ClassLoader.callerClassLoader(), 
                true);
    }

    /**
     * Loads and links the shared library with the given name {@code libName}.
     * The file will be searched in the default directory for shared libraries
     * of the local system.
     *
     * @param libName
     *            the name of the library to load.
     * @throws UnsatisfiedLinkError
     *             if the library could not be loaded.
     * @throws SecurityException
     *             if the library was not allowed to be loaded.
     */
    public static void loadLibrary(String libName) {
        Runtime.getRuntime().loadLibrary0(
                libName,
                ClassLoader.callerClassLoader(), 
                true);
    }

    /**
     * Provides a hint to the virtual machine that it would be useful to attempt
     * to perform any outstanding object finalizations.
     */
    public static void runFinalization() {
        RUNTIME.runFinalization();
    }

    /**
     * Ensures that, when the virtual machine is about to exit, all objects are
     * finalized. Note that all finalization which occurs when the system is
     * exiting is performed after all running threads have been terminated.
     * 
     * @param flag
     *            the flag determines if finalization on exit is enabled.
     * @deprecated this method is unsafe.
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public static void runFinalizersOnExit(boolean flag) {
        Runtime.runFinalizersOnExit(flag);
    }

    /**
     * Sets all system properties. Note that the object which is passed in
     * not copied, so that subsequent changes made to the object will be
     * reflected in calls to getProperty and getProperties.
     * 
     * @param p
     *            the new system property.
     * @throws SecurityException
     *             if a {@link SecurityManager} is installed and its {@code
     *             checkPropertiesAccess()} method does not allow the operation.
     */
    public static void setProperties(Properties p) {
        SecurityManager secMgr = System.getSecurityManager();
        if (secMgr != null) {
            secMgr.checkPropertiesAccess();
        }
        if (p == null) {
            ensureProperties();
        } else {
            systemProperties = p;
        }
    }

    /**
     * Sets the active security manager. Note that once the security manager has
     * been set, it can not be changed. Attempts to do that will cause a
     * security exception.
     * 
     * @param sm
     *            the new security manager.
     * 
     * @throws SecurityException
     *             if the security manager has already been set and if its
     *             checkPermission method does not allow to redefine the
     *             security manager.
     */
    public static void setSecurityManager(final SecurityManager sm) {
        if (!security_initialized) {
            try {
                // Preload and initialize Policy implementation classes
                // otherwise we could go recursive
                Policy.getPolicy();
            } catch (Exception e) {
            }
            security_initialized = true;
        }

        security = sm;
    }

    /**
     * Returns the platform specific file name format for the shared library
     * named by the argument.
     * 
     * @param userLibName
     *            the name of the library to look up.
     * @return the platform specific filename for the library
     */
    public static native String mapLibraryName(String userLibName);

    /**
     * Sets the value of the named static field in the receiver to the passed in
     * argument.
     * 
     * @param fieldName
     *            the name of the field to set, one of in, out, or err
     * @param stream
     *            the new value of the field
     */
    private static native void setFieldImpl(String fieldName, Object stream);

}
