/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
 * {@code CFStreamErrorException} extends from {@link RuntimeException} and wraps {@link CFStreamError}.
 * It is used throughout the Cocoa frameworks on methods that return pointers to {@code CFStreamError}.
 * 
 * Use {@link #getError()} to get the {@code CFStreamError} instance that was used to instantiate this exception.
 */
public class CFStreamErrorException extends RuntimeException {
    private static final long serialVersionUID = -1411765445335553566L;
    private CFStreamError error;
    
    public CFStreamErrorException(CFStreamError error) {
        this.error = error;
    }
    
    @Override
    public String getMessage() {
        return String.format("%s (%d)", error.getDomain(), error.getError());
    }
    
    public CFStreamError getError() {
        return error;
    }
}

