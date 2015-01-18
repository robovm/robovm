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
/*
 * Copyright (C) 2008 The Android Open Source Project
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
/*
 * Copyright (C) 2012 Trillian Mobile AB
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


package java.lang;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import libcore.icu.ICU;
import libcore.io.ErrnoException;
import libcore.io.Libcore;
import libcore.io.StructPasswd;
import libcore.io.StructUtsname;

import org.robovm.rt.VM;

import dalvik.system.VMRuntime;
import dalvik.system.VMStack;

/**
 * Provides access to system-related information and resources including
 * standard input and output. Enables clients to dynamically load native
 * libraries. All methods of this class are accessed in a static way and the
 * class itself can not be instantiated.
 *
 * @see Runtime
 */
public final class System {

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

    private static final String lineSeparator;
    private static Properties systemProperties;

    static {
        err = new PrintStream(new FileOutputStream(FileDescriptor.err));
        out = new PrintStream(new FileOutputStream(FileDescriptor.out));
        in = new BufferedInputStream(new FileInputStream(FileDescriptor.in));
        lineSeparator = System.getProperty("line.separator");
    }

    /**
     * Sets the standard input stream to the given user defined input stream.
     *
     * @param newIn
     *            the user defined input stream to set as the standard input
     *            stream.
     */
    public static void setIn(InputStream newIn) {
        setFieldImpl("in", "Ljava/io/InputStream;", newIn);
    }

    /**
     * Sets the standard output stream to the given user defined output stream.
     *
     * @param newOut
     *            the user defined output stream to set as the standard output
     *            stream.
     */
    public static void setOut(PrintStream newOut) {
        setFieldImpl("out", "Ljava/io/PrintStream;", newOut);
    }

    /**
     * Sets the standard error output stream to the given user defined output
     * stream.
     *
     * @param newErr
     *            the user defined output stream to set as the standard error
     *            output stream.
     */
    public static void setErr(PrintStream newErr) {
        setFieldImpl("err", "Ljava/io/PrintStream;", newErr);
    }

    /**
     * Prevents this class from being instantiated.
     */
    private System() {
    }

    /**
     * Copies {@code length} elements from the array {@code src},
     * starting at offset {@code srcPos}, into the array {@code dst},
     * starting at offset {@code dstPos}.
     *
     * <p>The source and destination arrays can be the same array,
     * in which case copying is performed as if the source elements
     * are first copied into a temporary array and then into the
     * destination array.
     *
     * @param src
     *            the source array to copy the content.
     * @param srcPos
     *            the starting index of the content in {@code src}.
     * @param dst
     *            the destination array to copy the data into.
     * @param dstPos
     *            the starting index for the copied content in {@code dst}.
     * @param length
     *            the number of elements to be copied.
     */
    public static void arraycopy(Object src, int srcPos, Object dst, int dstPos,
            int length) {
        // RoboVM note: This is native in Android. We're using code from Apache Harmony instead.
        if (src == null) {
            throw new NullPointerException("src == null");
        }
        if (dst == null) {
            throw new NullPointerException("dst == null");
        }
        Class<?> type1 = src.getClass();
        Class<?> type2 = dst.getClass();
        if (!type1.isArray()) {
            throw new ArrayStoreException("source of type " + type1.getName() + " is not an array");
        }
        if (!type2.isArray()) {
            throw new ArrayStoreException("destination of type " + type2.getName() + " is not an array");
        }
        Class<?> componentType1 = type1.getComponentType();
        Class<?> componentType2 = type2.getComponentType();
        if (!componentType1.isPrimitive()) {
            if (componentType2.isPrimitive()) {
                throw new ArrayStoreException(type1.getCanonicalName() + " and " + type2.getCanonicalName() 
                        + " are incompatible array types");
            }
            arraycopy((Object[]) src, srcPos, (Object[]) dst, dstPos, length);
        } else {
            if (componentType2 != componentType1) {
                throw new ArrayStoreException(type1.getCanonicalName() + " and " + type2.getCanonicalName() 
                        + " are incompatible array types");
            }
            if (componentType1 == int.class) {
                arraycopy((int[]) src, srcPos, (int[]) dst, dstPos, length);
            } else if (componentType1 == byte.class) {
                arraycopy((byte[]) src, srcPos, (byte[]) dst, dstPos, length);
            } else if (componentType1 == long.class) {
                arraycopy((long[]) src, srcPos, (long[]) dst, dstPos, length);
            } else if (componentType1 == short.class) {
                arraycopy((short[]) src, srcPos, (short[]) dst, dstPos, length);
            } else if (componentType1 == char.class) {
                arraycopy((char[]) src, srcPos, (char[]) dst, dstPos, length);
            } else if (componentType1 == boolean.class) {
                arraycopy((boolean[]) src, srcPos, (boolean[]) dst, dstPos, length);
            } else if (componentType1 == double.class) {
                arraycopy((double[]) src, srcPos, (double[]) dst, dstPos, length);
            } else if (componentType1 == float.class) {
                arraycopy((float[]) src, srcPos, (float[]) dst, dstPos, length);
            }
        }
    }

    private static void arraycopyCheckBounds(int srcLength, int srcPos, int dstLength, int dstPos, int length) {
        if (srcPos < 0 || dstPos < 0 || length < 0 || 
                srcPos > srcLength - length ||
                dstPos > dstLength - length) {
            throw new ArrayIndexOutOfBoundsException("src.length=" + srcLength + " srcPos=" + srcPos 
                    + " dst.length=" + dstLength + " dstPos=" + dstPos + " length=" + length);
        }
    }
    
    private static void arraycopyFast(Object src, int srcPos, Object dst, int dstPos, int length, int logElemSize) {
        if (length > 0) {
            long srcAddr = VM.getArrayValuesAddress(src) + (srcPos << logElemSize);
            long dstAddr = VM.getArrayValuesAddress(dst) + (dstPos << logElemSize);
            if (logElemSize == 0) {
                VM.memmove8(dstAddr, srcAddr, length);
            } else if (logElemSize == 1) {
                VM.memmove16(dstAddr, srcAddr, length);
            } else if (logElemSize == 2) {
                VM.memmove32(dstAddr, srcAddr, length);
            } else if (logElemSize == 3) {
                VM.memmove64(dstAddr, srcAddr, length);
            } else {
                throw new AssertionError();
            }
        }
    }
    
    private static void arraycopy(Object[] src, int srcPos, Object[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        if (length > 0) {
            // TODO: Use arraycopyFast() if src.class and dst.class have same dimensionality and (src instanceof dst)
            int i = 0;
            try {
                // Check if this is a forward or backwards arraycopy
                if (src != dst || srcPos > dstPos || srcPos + length <= dstPos) {
                    for (i = 0; i < length; ++i) {
                        dst[dstPos + i] = src[srcPos + i];
                    }
                } else {
                    for (i = length - 1; i >= 0; --i) {
                        dst[dstPos + i] = src[srcPos + i];
                    }
                }
            } catch (ArrayStoreException e) {
                // Throw a new one with a more descriptive message.
                Class<?> srcElemClass = src[i + srcPos].getClass();
                String srcElemTypeName = srcElemClass.isArray() 
                                        ? srcElemClass.getCanonicalName() : srcElemClass.getName();
                throw new ArrayStoreException(String.format(
                        "source[%d] of type %s cannot be stored in destination array of type %s",
                        i + srcPos, srcElemTypeName, dst.getClass().getCanonicalName()));
            }
        }
    }

    private static void arraycopy(int[] src, int srcPos, int[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 2);
    }

    private static void arraycopy(byte[] src, int srcPos, byte[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 0);
    }

    private static void arraycopy(short[] src, int srcPos, short[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 1);
    }

    private static void arraycopy(long[] src, int srcPos, long[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 3);
    }

    private static void arraycopy(char[] src, int srcPos, char[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 1);
    }

    private static void arraycopy(boolean[] src, int srcPos, boolean[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 0);
    }

    private static void arraycopy(double[] src, int srcPos, double[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 3);
    }

    private static void arraycopy(float[] src, int srcPos, float[] dst, int dstPos, int length) {
        arraycopyCheckBounds(src.length, srcPos, dst.length, dstPos, length);
        arraycopyFast(src, srcPos, dst, dstPos, length, 2);
    }
    
    /**
     * Returns the current time in milliseconds since January 1, 1970 00:00:00.0 UTC.
     *
     * <p>This method always returns UTC times, regardless of the system's time zone.
     * This is often called "Unix time" or "epoch time".
     * Use a {@link java.text.DateFormat} instance to format this time for display to a human.
     *
     * <p>This method shouldn't be used for measuring timeouts or
     * other elapsed time measurements, as changing the system time can affect
     * the results. Use {@link #nanoTime} for that.
     */
    public static native long currentTimeMillis();

    /**
     * Returns the current timestamp of the most precise timer available on the
     * local system, in nanoseconds. Equivalent to Linux's {@code CLOCK_MONOTONIC}.
     *
     * <p>This timestamp should only be used to measure a duration by comparing it
     * against another timestamp on the same device.
     * Values returned by this method do not have a defined correspondence to
     * wall clock times; the zero value is typically whenever the device last booted.
     * Use {@link #currentTimeMillis} if you want to know what time it is.
     */
    public static native long nanoTime();

    /**
     * Causes the VM to stop running and the program to exit with the given exit status.
     * If {@link #runFinalizersOnExit(boolean)} has been previously invoked with a
     * {@code true} argument, then all objects will be properly
     * garbage-collected and finalized first.
     */
    public static void exit(int code) {
        Runtime.getRuntime().exit(code);
    }

    /**
     * Indicates to the VM that it would be a good time to run the
     * garbage collector. Note that this is a hint only. There is no guarantee
     * that the garbage collector will actually be run.
     */
    public static void gc() {
        Runtime.getRuntime().gc();
    }

    /**
     * Returns the value of the environment variable with the given name, or null if no such
     * variable exists.
     */
    public static String getenv(String name) {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        return Libcore.os.getenv(name);
    }

    /**
     * Returns an unmodifiable map of all environment variables to their values.
     */
    public static Map<String, String> getenv() {
        Map<String, String> map = new HashMap<String, String>();
        for (String entry : Libcore.os.environ()) {
            int index = entry.indexOf('=');
            if (index != -1) {
                map.put(entry.substring(0, index), entry.substring(index + 1));
            }
        }
        return new SystemEnvironment(map);
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
     */
    public static Properties getProperties() {
        if (systemProperties == null) {
            initSystemProperties();
        }
        return systemProperties;
    }

    private static void initSystemProperties() {
        VMRuntime runtime = VMRuntime.getRuntime();
        Properties p = new Properties();

        String projectUrl = "http://www.robovm.org/";
        String projectName = "RoboVM";

        p.put("java.boot.class.path", runtime.bootClassPath());
        p.put("java.class.path", runtime.classPath());

        // None of these four are meaningful on Android, but these keys are guaranteed
        // to be present for System.getProperty. For java.class.version, we use the maximum
        // class file version that dx currently supports.
        p.put("java.class.version", "50.0");
        p.put("java.compiler", "");
        p.put("java.ext.dirs", "");
        p.put("java.version", "0");

        // RoboVM note: Android uses getenv("JAVA_HOME") here with "/system" as fallback.
        p.put("java.home", VM.basePath());

        // RoboVM note: Use value of $TMPDIR if set. Otherwise use /tmp as Android does.
        String tmpdir = getenv("TMPDIR");
        p.put("java.io.tmpdir", tmpdir != null ? tmpdir : "/tmp");

        String ldLibraryPath = getenv("LD_LIBRARY_PATH");
        if (ldLibraryPath != null) {
            p.put("java.library.path", ldLibraryPath);
        }

        p.put("java.specification.name", "RoboVM Core Library");
        p.put("java.specification.vendor", projectName);
        p.put("java.specification.version", "0.9");

        p.put("java.vendor", projectName);
        p.put("java.vendor.url", projectUrl);
        p.put("java.vm.name", "RoboVM");
        p.put("java.vm.specification.name", "RoboVM Virtual Machine Specification");
        p.put("java.vm.specification.vendor", projectName);
        p.put("java.vm.specification.version", "0.9");
        p.put("java.vm.vendor", projectName);
        p.put("java.vm.version", runtime.vmVersion());

        p.put("file.separator", "/");
        p.put("line.separator", "\n");
        p.put("path.separator", ":");

        p.put("java.runtime.name", "RoboVM Runtime");
        p.put("java.runtime.version", "0.9");
        p.put("java.vm.vendor.url", projectUrl);

        p.put("file.encoding", "UTF-8");
        p.put("user.language", "en");
        p.put("user.region", "US");

        try {
            StructPasswd passwd = Libcore.os.getpwuid(Libcore.os.getuid());
            p.put("user.home", passwd.pw_dir);
            p.put("user.name", passwd.pw_name);
        } catch (ErrnoException exception) {
            // RoboVM note: Start change. Fall back to environment variables. getpwuid() fails on the iOS simulator.
            String home = getenv("HOME");
            String user = getenv("USER");
            p.put("user.home", home != null ? home : "");
            p.put("user.name", user != null ? user : "");
            // RoboVM note: End change.
        }

        StructUtsname info = Libcore.os.uname();
        p.put("os.arch", info.machine);
        p.put("os.name", info.sysname);
        p.put("os.version", info.release);

        // Undocumented Android-only properties.
        p.put("android.icu.library.version", ICU.getIcuVersion());
        p.put("android.icu.unicode.version", ICU.getUnicodeVersion());
        p.put("android.icu.cldr.version", ICU.getCldrVersion());

        parsePropertyAssignments(p, specialProperties());

        parsePropertyAssignments(p, robovmSpecialProperties());
        
        // RoboVM note: Added in RoboVM. Make sure we get sane and consistent
        // user.home, user.dir and user.name values on iOS.
        if (p.getProperty("os.name").contains("iOS")) {
            // On iOS we want user.home and user.dir to point to the app's data
            // container root dir. This is the dir $HOME points to. We also set
            // user.name to $USER or hardcode 'mobile' if $USER isn't set (iOS
            // simulator).
            String home = getenv("HOME");
            String user = getenv("USER");
            p.put("user.home", home != null ? home : "");
            p.put("user.dir", home != null ? home : "/");
            p.put("user.name", user != null ? user : "mobile");
        }
        // RoboVM note: End change.

        // Override built-in properties with settings from the command line.
        parsePropertyAssignments(p, runtime.properties());

        systemProperties = p;
    }

    /**
     * Returns an array of "key=value" strings containing information not otherwise
     * easily available, such as #defined library versions.
     */
    private static native String[] specialProperties();

    private static native String[] robovmSpecialProperties();
    
    /**
     * Adds each element of 'assignments' to 'p', treating each element as an
     * assignment in the form "key=value".
     */
    private static void parsePropertyAssignments(Properties p, String[] assignments) {
        for (String assignment : assignments) {
            int split = assignment.indexOf('=');
            String key = assignment.substring(0, split);
            String value = assignment.substring(split + 1);
            p.put(key, value);
        }
    }

    /**
     * Returns the value of a particular system property or {@code null} if no
     * such property exists.
     *
     * <p>The following properties are always provided by the Dalvik VM:
     * <p><table BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
     * <tr BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
     *     <td><b>Name</b></td>        <td><b>Meaning</b></td>                    <td><b>Example</b></td></tr>
     * <tr><td>file.separator</td>     <td>{@link java.io.File#separator}</td>    <td>{@code /}</td></tr>
     *
     * <tr><td>java.class.path</td>    <td>System class path</td>                 <td>{@code .}</td></tr>
     * <tr><td>java.class.version</td> <td>(Not useful on Android)</td>           <td>{@code 50.0}</td></tr>
     * <tr><td>java.compiler</td>      <td>(Not useful on Android)</td>           <td>Empty</td></tr>
     * <tr><td>java.ext.dirs</td>      <td>(Not useful on Android)</td>           <td>Empty</td></tr>
     * <tr><td>java.home</td>          <td>Location of the VM on the file system</td> <td>{@code /system}</td></tr>
     * <tr><td>java.io.tmpdir</td>     <td>See {@link java.io.File#createTempFile}</td> <td>{@code /sdcard}</td></tr>
     * <tr><td>java.library.path</td>  <td>Search path for JNI libraries</td>     <td>{@code /vendor/lib:/system/lib}</td></tr>
     * <tr><td>java.vendor</td>        <td>Human-readable VM vendor</td>          <td>{@code The Android Project}</td></tr>
     * <tr><td>java.vendor.url</td>    <td>URL for VM vendor's web site</td>      <td>{@code http://www.android.com/}</td></tr>
     * <tr><td>java.version</td>       <td>(Not useful on Android)</td>           <td>{@code 0}</td></tr>
     *
     * <tr><td>java.specification.version</td>    <td>VM libraries version</td>        <td>{@code 0.9}</td></tr>
     * <tr><td>java.specification.vendor</td>     <td>VM libraries vendor</td>         <td>{@code The Android Project}</td></tr>
     * <tr><td>java.specification.name</td>       <td>VM libraries name</td>           <td>{@code Dalvik Core Library}</td></tr>
     * <tr><td>java.vm.version</td>               <td>VM implementation version</td>   <td>{@code 1.2.0}</td></tr>
     * <tr><td>java.vm.vendor</td>                <td>VM implementation vendor</td>    <td>{@code The Android Project}</td></tr>
     * <tr><td>java.vm.name</td>                  <td>VM implementation name</td>      <td>{@code Dalvik}</td></tr>
     * <tr><td>java.vm.specification.version</td> <td>VM specification version</td>    <td>{@code 0.9}</td></tr>
     * <tr><td>java.vm.specification.vendor</td>  <td>VM specification vendor</td>     <td>{@code The Android Project}</td></tr>
     * <tr><td>java.vm.specification.name</td>    <td>VM specification name</td>       <td>{@code Dalvik Virtual Machine Specification}</td></tr>
     *
     * <tr><td>line.separator</td>     <td>The system line separator</td>         <td>{@code \n}</td></tr>
     *
     * <tr><td>os.arch</td>            <td>OS architecture</td>                   <td>{@code armv7l}</td></tr>
     * <tr><td>os.name</td>            <td>OS (kernel) name</td>                  <td>{@code Linux}</td></tr>
     * <tr><td>os.version</td>         <td>OS (kernel) version</td>               <td>{@code 2.6.32.9-g103d848}</td></tr>
     *
     * <tr><td>path.separator</td>     <td>See {@link java.io.File#pathSeparator}</td> <td>{@code :}</td></tr>
     *
     * <tr><td>user.dir</td>           <td>Base of non-absolute paths</td>        <td>{@code /}</td></tr>
     * <tr><td>user.home</td>          <td>(Not useful on Android)</td>           <td>Empty</td></tr>
     * <tr><td>user.name</td>          <td>(Not useful on Android)</td>           <td>Empty</td></tr>
     *
     * </table>
     *
     * <p>It is a mistake to try to override any of these. Doing so will have unpredictable results.
     *
     * @param propertyName
     *            the name of the system property to look up.
     * @return the value of the specified system property or {@code null} if the
     *         property doesn't exist.
     */
    public static String getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    /**
     * Returns the value of a particular system property. The {@code
     * defaultValue} will be returned if no such property has been found.
     */
    public static String getProperty(String name, String defaultValue) {
        checkPropertyName(name);
        return getProperties().getProperty(name, defaultValue);
    }

    /**
     * Sets the value of a particular system property.
     *
     * @return the old value of the property or {@code null} if the property
     *         didn't exist.
     */
    public static String setProperty(String name, String value) {
        checkPropertyName(name);
        return (String) getProperties().setProperty(name, value);
    }

    /**
     * Removes a specific system property.
     *
     * @return the property value or {@code null} if the property didn't exist.
     * @throws NullPointerException
     *             if the argument is {@code null}.
     * @throws IllegalArgumentException
     *             if the argument is empty.
     */
    public static String clearProperty(String name) {
        checkPropertyName(name);
        return (String) getProperties().remove(name);
    }

    private static void checkPropertyName(String name) {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
    }

    /**
     * Returns the {@link java.io.Console} associated with this VM, or null.
     * Not all VMs will have an associated console. A console is typically only
     * available for programs run from the command line.
     * @since 1.6
     */
    public static Console console() {
        return Console.getConsole();
    }

    /**
     * Returns null. Android does not use {@code SecurityManager}. This method
     * is only provided for source compatibility.
     *
     * @return null
     */
    public static SecurityManager getSecurityManager() {
        return null;
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
     * Returns the system's line separator. On Android, this is {@code "\n"}. The value
     * comes from the value of the {@code line.separator} system property when the VM
     * starts. Later changes to the property will not affect the value returned by this
     * method.
     * @since 1.7
     */
    public static String lineSeparator() {
        return lineSeparator;
    }

    /**
     * Loads and links the dynamic library that is identified through the
     * specified path. This method is similar to {@link #loadLibrary(String)},
     * but it accepts a full path specification whereas {@code loadLibrary} just
     * accepts the name of the library to load.
     *
     * @param pathName
     *            the path of the file to be loaded.
     */
    public static void load(String pathName) {
        Runtime.getRuntime().load(pathName, VMStack.getCallingClassLoader());
    }

    /**
     * Loads and links the library with the specified name. The mapping of the
     * specified library name to the full path for loading the library is
     * implementation-dependent.
     *
     * @param libName
     *            the name of the library to load.
     * @throws UnsatisfiedLinkError
     *             if the library could not be loaded.
     */
    public static void loadLibrary(String libName) {
        Runtime.getRuntime().loadLibrary(libName, VMStack.getCallingClassLoader());
    }

    /**
     * @hide internal use only
     */
    public static void logE(String message) {
        log('E', message, null);
    }

    /**
     * @hide internal use only
     */
    public static void logE(String message, Throwable th) {
        log('E', message, th);
    }

    /**
     * @hide internal use only
     */
    public static void logI(String message) {
        log('I', message, null);
    }

    /**
     * @hide internal use only
     */
    public static void logI(String message, Throwable th) {
        log('I', message, th);
    }

    /**
     * @hide internal use only
     */
    public static void logW(String message) {
        log('W', message, null);
    }

    /**
     * @hide internal use only
     */
    public static void logW(String message, Throwable th) {
        log('W', message, th);
    }

    private static native void log(char type, String message, Throwable th);

    /**
     * Provides a hint to the VM that it would be useful to attempt
     * to perform any outstanding object finalization.
     */
    public static void runFinalization() {
        Runtime.getRuntime().runFinalization();
    }

    /**
     * Ensures that, when the VM is about to exit, all objects are
     * finalized. Note that all finalization which occurs when the system is
     * exiting is performed after all running threads have been terminated.
     *
     * @param flag
     *            the flag determines if finalization on exit is enabled.
     * @deprecated This method is unsafe.
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public static void runFinalizersOnExit(boolean flag) {
        Runtime.runFinalizersOnExit(flag);
    }

    /**
     * Sets all system properties. This does not take a copy; the passed-in object is used
     * directly. Passing null causes the VM to reinitialize the properties to how they were
     * when the VM was started.
     */
    public static void setProperties(Properties p) {
        systemProperties = p;
    }

    /**
     * Throws {@code SecurityException}.
     *
     * <p>Security managers do <i>not</i> provide a secure environment for
     * executing untrusted code and are unsupported on Android. Untrusted code
     * cannot be safely isolated within a single VM on Android, so this method
     * <i>always</i> throws a {@code SecurityException}.
     *
     * @param sm a security manager
     * @throws SecurityException always
     */
    public static void setSecurityManager(SecurityManager sm) {
        if (sm != null) {
            throw new SecurityException();
        }
    }

    /**
     * Returns the platform specific file name format for the shared library
     * named by the argument.
     *
     * @param userLibName
     *            the name of the library to look up.
     * @return the platform specific filename for the library.
     */
    public static String mapLibraryName(String userLibName) {
        if (userLibName == null) {
            throw new NullPointerException("userLibName == null");
        }
        return mapLibraryName0(userLibName);
    }

    private static native String mapLibraryName0(String userLibName);

    /**
     * Sets the value of the named static field in the receiver to the passed in
     * argument.
     *
     * @param fieldName
     *            the name of the field to set, one of in, out, or err
     * @param stream
     *            the new value of the field
     */
    private static native void setFieldImpl(String fieldName, String signature, Object stream);


    /**
     * The unmodifiable environment variables map. System.getenv() specifies
     * that this map must throw when passed non-String keys.
     */
    static class SystemEnvironment extends AbstractMap<String, String> {
        private final Map<String, String> map;

        public SystemEnvironment(Map<String, String> map) {
            this.map = Collections.unmodifiableMap(map);
        }

        @Override public Set<Entry<String, String>> entrySet() {
            return map.entrySet();
        }

        @Override public String get(Object key) {
            return map.get(toNonNullString(key));
        }

        @Override public boolean containsKey(Object key) {
            return map.containsKey(toNonNullString(key));
        }

        @Override public boolean containsValue(Object value) {
            return map.containsValue(toNonNullString(value));
        }

        private String toNonNullString(Object o) {
            if (o == null) {
                throw new NullPointerException("o == null");
            }
            return (String) o;
        }
    }
}
