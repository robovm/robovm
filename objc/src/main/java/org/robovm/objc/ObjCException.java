/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.objc;

/**
 *
 */
@SuppressWarnings("serial")
public class ObjCException extends RuntimeException {

    public ObjCException(String message) {
        super(message);
    }

    public ObjCException() {
        super();
    }

    public ObjCException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjCException(Throwable cause) {
        super(cause);
    }

}
