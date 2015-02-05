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
 * {@code CFErrorException} extends from {@link RuntimeException} and wraps {@link CFError}.
 * It is used throughout the Cocoa frameworks on methods that return pointers to {@code CFError}.
 * 
 * Use {@link #getError()} to get the {@code CFError} instance that was used to instantiate this exception.
 */
public class CFErrorException extends RuntimeException {
    private static final long serialVersionUID = -58889462578263462L;
    private CFError error;
    
    public CFErrorException(CFError error) {
        this.error = error;
    }
    
    @Override
    public String getMessage() {
        return String.format("%s (%d)", error.getDomain(), error.getCode());
    }
    
    public CFError getError() {
        return error;
    }
}

