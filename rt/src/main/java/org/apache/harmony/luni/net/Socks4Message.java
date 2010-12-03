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

package org.apache.harmony.luni.net;

import java.io.UnsupportedEncodingException;

import org.apache.harmony.luni.internal.nls.Messages;

class Socks4Message {
    static final int COMMAND_CONNECT = 1;

    static final int COMMAND_BIND = 2;

    static final int RETURN_SUCCESS = 90;

    static final int RETURN_FAILURE = 91;

    static final int RETURN_CANNOT_CONNECT_TO_IDENTD = 92;

    static final int RETURN_DIFFERENT_USER_IDS = 93;

    static final int REPLY_LENGTH = 8;

    static final int INDEX_VERSION = 0;

    private static final int SOCKS_VERSION = 4;

    private static final int INDEX_COMMAND = 1;

    private static final int INDEX_PORT = 2;

    private static final int INDEX_IP = 4;

    private static final int INDEX_USER_ID = 8;

    private static final int BUFFER_LENGTH = 256;

    private static final int MAX_USER_ID_LENGTH = BUFFER_LENGTH - INDEX_USER_ID;

    protected byte[] buffer;

    public Socks4Message() {
        super();
        buffer = new byte[BUFFER_LENGTH];
        setVersionNumber(SOCKS_VERSION);
    }

    /**
     * Get the request's command or result.
     */
    public int getCommandOrResult() {
        return buffer[INDEX_COMMAND];
    }

    /**
     * Set the request's command or result.
     */
    public void setCommandOrResult(int command) {
        buffer[INDEX_COMMAND] = (byte) command;
    }

    /**
     * Answer the request's port number.
     */
    public int getPort() {
        return getInt16(INDEX_PORT);
    }

    /**
     * Set the request's port number.
     */
    public void setPort(int port) {
        setInt16(INDEX_PORT, port);
    }

    /*
     * Answer the IP address of the request as an integer.
     */
    public int getIP() {
        return getInt32(INDEX_IP);
    }

    /**
     * Set the IP address. This expects an array of four bytes in host order.
     */
    public void setIP(byte[] ip) {
        buffer[INDEX_IP] = ip[0];
        buffer[INDEX_IP + 1] = ip[1];
        buffer[INDEX_IP + 2] = ip[2];
        buffer[INDEX_IP + 3] = ip[3];
    }

    /**
     * Answer the user id for authentication.
     */
    public String getUserId() {
        return getString(INDEX_USER_ID, MAX_USER_ID_LENGTH);
    }

    /**
     * Set the user id for authentication.
     */
    public void setUserId(String id) {
        setString(INDEX_USER_ID, MAX_USER_ID_LENGTH, id);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(50);
        buf.append("Version: ");
        buf.append(Integer.toHexString(getVersionNumber()));
        buf.append(" Command: ");
        buf.append(Integer.toHexString(getCommandOrResult()));
        buf.append(" Port: ");
        buf.append(getPort());
        buf.append(" IP: ");
        buf.append(Integer.toHexString(getIP()));
        buf.append(" User ID: ");
        buf.append(getUserId());
        return buf.toString();
    }

    /**
     * Answer the total number of bytes used for the request. This method
     * searches for the end of the user id, then searches for the end of the
     * password and returns the final index as the requests length.
     */
    public int getLength() {
        int index = 0;

        // Look for the end of the user id.
        for (index = INDEX_USER_ID; buffer[index] != 0; index++) {
            /*
             * Finds the end of the user id by searching for the null
             * termination of the user id string.
             */
        }

        // Increment the index to include the NULL character in the length;
        index++;
        return index;
    }

    /**
     * Answer an error string corresponding to the given error value.
     */
    public String getErrorString(int error) {
        switch (error) {
            case RETURN_FAILURE:
                return Messages.getString("luni.14"); //$NON-NLS-1$
            case RETURN_CANNOT_CONNECT_TO_IDENTD:
                return Messages.getString("luni.15"); //$NON-NLS-1$
            case RETURN_DIFFERENT_USER_IDS:
                return Messages.getString("luni.16"); //$NON-NLS-1$
            default:
                return Messages.getString("luni.17"); //$NON-NLS-1$
        }
    }

    /**
     * Answer the message's byte buffer.
     */
    public byte[] getBytes() {
        return buffer;
    }

    /**
     * Get a 16 bit integer from the buffer at the offset given.
     */
    private int getInt16(int offset) {
        return (((buffer[offset] & 0xFF) << 8) + (buffer[offset + 1] & 0xFF));
    }

    /**
     * Get a 32 bit integer from the buffer at the offset given.
     */
    private int getInt32(int offset) {
        return ((buffer[offset + 3] & 0xFF)
                + ((buffer[offset + 2] & 0xFF) << 8)
                + ((buffer[offset + 1] & 0xFF) << 16) + ((buffer[offset + 0] & 0xFF) << 24));
    }

    /**
     * Get a String from the buffer at the offset given. The method reads until
     * it encounters a null value or reaches the maxLength given.
     */
    private String getString(int offset, int maxLength) {
        int index = offset;
        int lastIndex = index + maxLength;
        String result;

        while (index < lastIndex && (buffer[index] != 0)) {
            index++;
        }
        try {
            result = new String(buffer, offset, index - offset, "ISO8859_1"); //$NON-NLS-1$
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.toString());
        }
        return result;
    }

    /**
     * Answer the SOCKS version number. Should always be 4.
     */
    private int getVersionNumber() {
        return buffer[INDEX_VERSION];
    }

    /**
     * Put a 16 bit integer into the buffer at the offset given.
     */
    private void setInt16(int offset, int value) {
        buffer[offset] = (byte) (value >>> 8 & 0xFF);
        buffer[offset + 1] = (byte) (value & 0xFF);
    }

    /**
     * Put a string into the buffer at the offset given.
     */
    private void setString(int offset, int maxLength, String theString) {
        byte[] stringBytes;
        try {
            stringBytes = theString.getBytes("ISO8859_1"); //$NON-NLS-1$
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.toString());
        }
        int length = Math.min(stringBytes.length, maxLength);
        System.arraycopy(stringBytes, 0, buffer, offset, length);
        buffer[offset + length] = 0;
    }

    /**
     * Set the SOCKS version number. This should always be 4.
     */
    private void setVersionNumber(int number) {
        buffer[INDEX_VERSION] = (byte) number;
    }
}
