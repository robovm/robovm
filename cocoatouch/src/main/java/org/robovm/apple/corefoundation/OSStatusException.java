/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.corefoundation;

/**
 * {@code OSStatusException} extends from {@link Exception} and wraps
 * {@link OSStatus}. It is used throughout the Cocoa frameworks on methods that
 * return {@code OSStatus}.
 * 
 * Use {@link #getStatus()} to get the {@code OSStatus} instance that was used
 * to instantiate this exception.
 */
public class OSStatusException extends Exception {
    private static final long serialVersionUID = -6426060324258198313L;
    private OSStatus status;

    public OSStatusException(OSStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return status.toString();
    }

    public OSStatus getStatus() {
        return status;
    }

    /**
     * Throws an {@link #OSStatusException} if the supplied status isn't
     * {@code null} and doesn't equal {@link OSStatus#NO_ERR}.
     * 
     * @param status
     * @return true if the status is {@link OSStatus#NO_ERR}.
     * @throws OSStatusException
     */
    public static boolean throwIfNecessary(OSStatus status) throws OSStatusException {
        if (status != null && !status.equals(OSStatus.NO_ERR)) {
            throw new OSStatusException(status);
        }
        return true;
    }
}
