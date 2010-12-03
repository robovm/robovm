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


package org.apache.harmony.security.provider.crypto;


import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.security.ProviderException;
import java.security.AccessController;
import java.security.PrivilegedActionException;

import org.apache.harmony.security.internal.nls.Messages;


/**
 *  The static class providing access on Linux platform
 *  to system means for generating true random bits. <BR>
 *
 *  The source for true random bits is one of Linux's devices "/dev/urandom" or
 *  "/dev/random" depends on which one is available; if both the first is used. <BR>
 *
 *  If no device available the service is not available,
 *  that is, provider shouldn't register the algorithm. <BR>
 */


public class RandomBitsSupplier implements SHA1_Data {


    /**
     *  BufferedInputStream to read from device
     */
    private static BufferedInputStream bis = null;

    /**
     * File to connect to device
     */
    private static File randomFile = null;

    /**
     * value of field is "true" only if a device is available
     */
    private static boolean serviceAvailable = false;


    static {
        AccessController.doPrivileged(
            new java.security.PrivilegedAction() {
                public Object run() {

                    for ( int i = 0 ; i < DEVICE_NAMES.length ; i++ ) {
                        File file = new File(DEVICE_NAMES[i]);

                        try {
                            if ( file.canRead() ) {
                                bis = new BufferedInputStream(
                                          new FileInputStream(file));
                                randomFile = file;
                                serviceAvailable = true;
                                return null;
                            }
                        } catch (FileNotFoundException e) {
                        }
                    }

                    // If we have come out of the above loop, then we have been unable to
                    // access /dev/*random, so try to fall back to using the system random() API
                    try {
                        System.loadLibrary(LIBRARY_NAME); 
                        serviceAvailable = true;
                    } catch (UnsatisfiedLinkError e) {
                        serviceAvailable = false;
                    }
                    return null;
                }
            }
        );
    }


    /**
     * The method is called by provider to determine if a device is available.
     */
    static boolean isServiceAvailable() {
        return serviceAvailable;
    }


    /**
     * On platforms with "random" devices available,
     * the method reads random bytes from the device.  <BR>
     *
     * In case of any runtime failure ProviderException gets thrown.
     */
    private static synchronized byte[] getUnixDeviceRandom(int numBytes) {

        byte[] bytes = new byte[numBytes];

        int total = 0;
        int bytesRead;
        int offset = 0;
        try {
            for ( ; ; ) {

                bytesRead = bis.read(bytes, offset, numBytes-total);


                // the below case should not occur because /dev/random or /dev/urandom is a special file
                // hence, if it is happened there is some internal problem
                if ( bytesRead == -1 ) {
                    throw new ProviderException(
                        Messages.getString("security.193") ); //$NON-NLS-1$
                }

                total  += bytesRead;
                offset += bytesRead;

                if ( total >= numBytes ) {
                    break;
                }          
            }
        } catch (IOException e) {

            // actually there should be no IOException because device is a special file;
            // hence, there is either some internal problem or, for instance,
            // device was removed in runtime, or something else
            throw new ProviderException(
                Messages.getString("security.194"), e ); //$NON-NLS-1$
        }
        return bytes; 
    }


    /**
     * On platforms with no "random" devices available, this native 
     * method uses system API calls to generate random numbers<BR> 
     *
     * In case of any runtime failure ProviderException gets thrown.
     */
    private static native synchronized boolean getUnixSystemRandom(byte[] randomBits, int numBytes);


    /**
     * The method returns byte array of requested length provided service is available.
     * ProviderException gets thrown otherwise.
     *
     * @param
     *       numBytes - length of bytes requested
     * @return
     *       byte array
     * @throws
     *       InvalidArgumentException - if numBytes <= 0
     */
    public static byte[] getRandomBits(int numBytes) {

        if ( numBytes <= 0 ) {
            throw new IllegalArgumentException(Messages.getString("security.195", numBytes)); //$NON-NLS-1$
        }

        // We have been unable to get a random device or fall back to the
        // native security module code - throw an exception.
        if ( !serviceAvailable ) {
            throw new ProviderException(
                Messages.getString("security.196")); //$NON-NLS-1$
        }

        byte[] randomBits;
        if (bis != null) {
            // Random devices exist
            randomBits = getUnixDeviceRandom(numBytes);
        } else {
            // No random devices exist, use the system random() call
            randomBits = new byte[numBytes];
            if (!getUnixSystemRandom(randomBits, numBytes)) {
                // Even the system call has failed, throw an exception
                throw new ProviderException(
                    Messages.getString("security.196") ); //$NON-NLS-1$
            }
        }

        return randomBits;
    }
}
