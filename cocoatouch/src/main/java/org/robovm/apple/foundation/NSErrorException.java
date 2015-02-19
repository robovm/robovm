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
package org.robovm.apple.foundation;

/**
 * {@code NSErrorException} extends from {@link RuntimeException} and wraps {@link NSError}.
 * It is used throughout the Cocoa frameworks on methods that return pointers to {@code NSError}.
 * 
 * Use {@link #getError()} to get the {@code NSError} instance that was used to instantiate this exception.
 */
public class NSErrorException extends RuntimeException {
    private static final long serialVersionUID = -1423038930507165128L;
    private NSError error;
    
    public NSErrorException(NSError error) {
        this.error = error;
    }
    
    @Override
    public String getMessage() {
        return error.getLocalizedDescription();
    }
    
    public NSError getError() {
        return error;
    }
}
